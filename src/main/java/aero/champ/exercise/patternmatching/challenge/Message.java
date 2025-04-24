package aero.champ.exercise.patternmatching.challenge;

import java.time.LocalDateTime;

public sealed interface Message permits TextMessage, ImageMessage, LocationMessage, ContactMessage {
    String getSender();
    LocalDateTime getTimestamp();
    boolean isRead();
}
