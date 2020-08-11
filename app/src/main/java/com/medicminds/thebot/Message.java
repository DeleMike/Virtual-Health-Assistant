package com.medicminds.thebot;

import com.stfalcon.chatkit.commons.models.IMessage;
import com.stfalcon.chatkit.commons.models.IUser;

import java.util.Date;

public class Message implements IMessage {
    private String id;
    private String text;
    private Author user;
    private Date createdAt;

    public Message(String id, Author user, String text){
        this(id, user, text, new Date());
    }

    public Message(String id, Author user, String text, Date createdAt) {
        this.id = id;
        this.text = text;
        this.user = user;
        this.createdAt = createdAt;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public Author getUser() {
        return this.user;
    }

    @Override
    public Date getCreatedAt() {
        return createdAt;
    }
}
