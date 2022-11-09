package com.capstone.qwirkle.messages.client;

import com.capstone.qwirkle.messages.Message;

public class SetHandle extends Message {
    private static final long serialVersionUID = 5L;

    public String handle;

    public SetHandle(String handle) {
        this.handle = handle;
    }

    @Override
    public String toString() {
        return String.format("SetHandle('%s')", handle);}
}
