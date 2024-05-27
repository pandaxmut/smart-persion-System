package com.edu.smartpersionsys.pojo;

import lombok.Data;

@Data
public class Message {
    private String content;
    private String sender;

    public Message(String content, String sender) {
        this.content = content;
        this.sender = sender;
    }

    public Message() {
    }
    // getter和setter方法
}
