package com.capstone.qwirkle.messages;

@FunctionalInterface
public interface MessageReceiver {
    void messageReceived(Message message);
}