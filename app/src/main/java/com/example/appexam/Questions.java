package com.example.appexam;

public class Questions {
    private int textId;
    private boolean isAnswered;

    public Questions(int textId) {
        this.textId = textId;
        isAnswered = false;
    }

    public int getTextId() {
        return textId;
    }

    public void setTextId(int textId) {
        this.textId = textId;
    }

    public boolean isAnswered() {
        return isAnswered;
    }

    public void setAnswered(boolean answered) {
        isAnswered = answered;
    }
}
