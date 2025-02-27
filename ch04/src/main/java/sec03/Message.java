package sec03;

import java.util.ArrayList;

public final class Message {
    private final String sender;
    private final String text;
    private ArrayList<String> recipients;

    public Message(String sender, String text) {
        this.sender = sender;
        this.text = text;
        recipients = new ArrayList<>();
    }

    public void addRecipient(String recipient) {
        recipients.add(recipient);
    }

    public Message clone() {
        try {
            var cloned = (Message) super.clone();
            @SuppressWarnings("unchecked") var clonedRecipients
                    = (ArrayList<String>) recipients.clone();
            cloned.recipients = clonedRecipients;
            return cloned;
        } catch (CloneNotSupportedException ex) {
            return null;
        }
    }
}
