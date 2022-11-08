package com.capstone.qwirkle.messages.server;

import com.capstone.qwirkle.messages.Message;

public class SendPlayerCount extends Message {
    private static final long serialVersionUID = 106L;
    int count;
    @Override
    public String toString() {
        return String.format("%s",count);
}
}
