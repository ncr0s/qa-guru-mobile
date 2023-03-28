package com.github.ncr0s.data;

public enum DialogButtons {
    ACCEPT, REJECT;

    public String getChoiceButton() {
        switch(this) {
            case ACCEPT:
                return "org.wikipedia.alpha:id/acceptButton";
            case REJECT:
                return "org.wikipedia.alpha:id/rejectButton";
            default:
                return null;
        }
    }
}
