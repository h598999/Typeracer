package com.Typinggame.Typinggame.messaging;

/**
 * CursorMessage
 */
public class CursorMessage {

    private String id;
    private String position;

    public CursorMessage(String id, String position){
        this.id = id;
        this.position = position;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
