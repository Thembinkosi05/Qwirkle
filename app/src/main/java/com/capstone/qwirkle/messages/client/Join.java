package com.capstone.qwirkle.messages.client;

import com.capstone.qwirkle.messages.Message;

public class Join extends Message {
    private static final long serialVersionUID = 1L;
    String username;

    public Join(String username){
        this.username =username;
    }
    @Override
    public String toString() {
        return "Join()";
    }
}
