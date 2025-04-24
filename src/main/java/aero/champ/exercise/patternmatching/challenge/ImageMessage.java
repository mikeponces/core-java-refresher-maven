package aero.champ.exercise.patternmatching.challenge;

import java.time.LocalDateTime;

public record ImageMessage(
        String sender,
        LocalDateTime timestamp,
        boolean read,
        String imageUrl,
        String caption,
        int width,
        int height
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
