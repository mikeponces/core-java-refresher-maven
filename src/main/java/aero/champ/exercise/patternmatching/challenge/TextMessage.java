package aero.champ.exercise.patternmatching.challenge;

import java.time.LocalDateTime;

public record TextMessage(
        String sender,
        LocalDateTime timestamp,
        boolean read,
        String content,
        boolean hasEmoji
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
