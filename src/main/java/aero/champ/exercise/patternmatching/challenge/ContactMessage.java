package aero.champ.exercise.patternmatching.challenge;

import java.time.LocalDateTime;

public record ContactMessage(
        String sender,
        LocalDateTime timestamp,
        boolean read,
        String contactName,
        String phoneNumber,
        String email
) implements Message {
    @Override
    public String getSender() {
        return sender;
    }

    @Override
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public boolean isRead() {
        return read;
    }
}
