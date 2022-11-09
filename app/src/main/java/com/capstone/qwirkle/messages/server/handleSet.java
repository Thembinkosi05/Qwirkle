package com.capstone.qwirkle.messages.server;

import com.capstone.qwirkle.messages.Message;

public class handleSet extends Message {
    private static final long serialVersionUID = 104L;

    public String username;

    public handleSet(String handle) {
        this.username = handle;
    }

    @Override
    public String toString() {
        return String.format("%s", username);}
}
