package com.capstone.qwirkle;

import android.util.Log;

import com.capstone.qwirkle.messages.Message;
import com.capstone.qwirkle.messages.MessageReceiver;
import com.capstone.qwirkle.messages.client.Join;
import com.capstone.qwirkle.messages.client.PlaceTile;
import com.capstone.qwirkle.messages.client.Quit;
import com.capstone.qwirkle.messages.server.SetUsername;
import com.capstone.qwirkle.models.Player;
import com.capstone.qwirkle.models.Tile;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class PlayerClient {
    private static final String TAG = "PlayerClient";
    private String serverAddress;

    // I/O streams for communication.
    private ObjectInputStream in = null;
    private ObjectOutputStream out = null;

    private BlockingQueue<Message> outgoingMessages;
    private MessageReceiver messageReceiver;

    // Thread that reads messages from the server.
    private Thread readThread;

    // Thread that writes messages to the server.
    private Thread writeThread;



    /**
     * Enqueues a message to be sent to the server.
     * @param message The message being sent.
     */
    private void send(Message message) {
        try {
            outgoingMessages.put(message);
        } catch (InterruptedException e) {
            Log.e("ChatClient", e.getMessage());
        }
    }

    public PlayerClient(MessageReceiver messageReceiver) {
        super();
        outgoingMessages = new LinkedBlockingQueue<>();
        this.messageReceiver = messageReceiver;
    }


    public void setMessageReceiver(MessageReceiver messageReceiver) {
        this.messageReceiver = messageReceiver;
    }

    /**
     * Establish a connection to the server.
     * @param serverAddress The server's address.
     * @param name The name to be used for the client.
     */
    public void connect(String serverAddress, String name) {
        Log.i(TAG, "Connecting to " + serverAddress + "...");

        // Cache info.
        this.serverAddress = serverAddress;
        // Details about the client.

        // Start the read thread (which establishes a connection.
        Log.i("ChatClient", "Starting Read Loop thread...");
        readThread = new ReadThread();
        readThread.start();

        // Send the setHandle message.
        Log.i(TAG, "Queuing SetName(" + name + ")");
        send(new SetUsername(name));
    }

    /**
     * join a lobby on the server.
     */
    public void joinLobby() {
        send(new Join());
    }

    /**
     * Leave the current lobby.
     */
    public void leaveGroup() {
        send(new Quit());
    }

    /**
     * Send the dice played at the end of a turn with its coordinates
     */
    public void sendTilePlaced(Tile tile, int row, int col, Player appHostPlayer) {
        send(new PlaceTile(tile, row, col, appHostPlayer));
    }

    /**
     * This thread is responsible for establishing a connection with the server,
     * starting the write thread and then going into a read loop which reads
     * messages from the server. When new messages arrive, a messageReceived
     * notification is generated.
     */
    private class ReadThread extends Thread {
        @Override
        public void run() {
            Log.i(TAG, "Started Read Loop thread...");

            readThread = this;

            try {
                // Connect to server (if can).
                Socket connection = new Socket(serverAddress, 5050);
                Log.i(TAG, "Connected to " + serverAddress + "...");

                // Obtain I/O streams.
                // Possible GOTCHA: the order of obtaining the I/O streams in the server and
                // client is flipped. Needed due to synchronising the OBJECT streams on both ends.
                in = new ObjectInputStream(connection.getInputStream());
                out = new ObjectOutputStream(connection.getOutputStream());
                out.flush();
                Log.i(TAG, "Obtained I/O stream...");

                // Create and start the write thread.
                Log.i(TAG, "Starting Write Loop thread...");
                writeThread = new WriteThread();
                writeThread.start();

                // Go into read message loop.
                Log.i(TAG, "Starting Read Loop...");
                Message msg;
                do {
                    // Read message from server.
                    msg = (Message) in.readObject();
                    Log.i(TAG, ">> " + msg);

                    // If Message Receiver given, pass it the message.
                    if (messageReceiver != null) messageReceiver.messageReceived(msg);
                } while (msg.getClass() != Quit.class);

                // Done, so close connection.
                connection.close();
                Log.i(TAG, "Closed connection...");


            } catch (Exception e) {
                Log.e(TAG, "Exception: " + e.getMessage());

            } finally {
                readThread = null;
                if (writeThread != null) writeThread.interrupt();
            }
        }
    }

    /**
     * This thread is responsible for sending messages from the message queue
     * to the server. It is interrupted by the Read thread when it shuts down.
     */
    private class WriteThread extends Thread {
        @Override
        public void run() {
            Log.i(TAG, "Started Write Loop thread...");

            try {
                // Check outgoing messages and send.
                while (true) {
                    // Dequeue message to send. Take blocks until something to send.
                    Message msg = outgoingMessages.take();

                    out.writeObject(msg);
                    out.flush();

                    Log.i(TAG, "<< " + msg);
                }
            } catch (Exception e) {
                writeThread = null;
            }
        }
    }
}
