package aero.champ.exercise.patternmatching.challenge;

import java.time.LocalDateTime;

public record LocationMessage(
        String sender,
        LocalDateTime timestamp,
        boolean read,
        double latitude,
        double longitude,
        String locationName
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
