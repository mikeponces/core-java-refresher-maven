package aero.champ.exercise.patternmatching.challenge;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MessageProcessor {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static String formatMessage(Message message) {
        return switch (message) {
// Text message with emoji
            case TextMessage(var sender, var timestamp, var read, var content, var hasEmoji) ->
                    formatHeader(sender, timestamp, read) + "􀀀 " + content + (hasEmoji ? " 􀀀" : "");
//// Text message without emoji
//            case TextMessage(var sender, var timestamp, var read, var content, false) ->
//                    formatHeader(sender, timestamp, read) + "􀀀 " + content;
// Image message with caption
            case ImageMessage(
                    var sender, var timestamp, var read, var url, var
                    caption, var width, var height
            )
                    when caption != null && !caption.isEmpty() ->
                    formatHeader(sender, timestamp, read) + "􀀀 [" + width + "x" + height +
                            "] " +
                            caption + " (" + url + ")";
// Image message without caption
            case ImageMessage(
                    var sender, var timestamp, var read, var url, var
                    caption, var width, var height
            ) -> formatHeader(sender, timestamp, read) + "􀀀 [" + width + "x" + height +
                    "] " +
                    "No caption" + " (" + url + ")";
// Location message with a name
            case LocationMessage(
                    var sender, var timestamp, var read, var lat, var
                    lon, var name
            )
                    when name != null && !name.isEmpty() ->
                    formatHeader(sender, timestamp, read) + "􀀀 " + name + " (" + lat + "," + lon + ")";
// Location message without a name
            case LocationMessage(
                    var sender, var timestamp, var read, var lat, var
                    lon, var name
            ) -> formatHeader(sender, timestamp, read) + "􀀀 Unknown location (" + lat +
                    ", " + lon + ")";
// Contact message with email
            case ContactMessage(
                    var sender, var timestamp, var read, var name, var
                    phone, var email
            )
                    when email != null && !email.isEmpty() ->
                    formatHeader(sender, timestamp, read) + "􀀀 " + name + " - " + phone +
                            " - " + email;
// Contact message without email
            case ContactMessage(
                    var sender, var timestamp, var read, var name, var
                    phone, var email
            ) -> formatHeader(sender, timestamp, read) + "􀀀 " + name + " - " + phone;
// Default case (shouldn't be reached with sealed interface)
            default -> "Unknown message type";
        };
    }

    public static String getNotificationText(Message message) {
        return switch (message) {
            case TextMessage(var sender, _, _, var content, _) when content.length()
                    <= 20 -> sender + ": " + content;
            case TextMessage(var sender, _, _, var content, _) -> sender + ": " + content.substring(0, 17) + "...";
            case ImageMessage(var sender, _, _, _, _, _, _) -> sender + " sent an image";
            case LocationMessage(var sender, _, _, _, _, var name) when name != null
                    && !name.isEmpty() -> sender + " shared a location: " + name;
            case LocationMessage(var sender, _, _, _, _, _) -> sender + " shared a location";
            case ContactMessage(var sender, _, _, var name, _, _) -> sender + " shared contact: " + name;
            default -> "New message received";
        };
    }

    private static String formatHeader(String sender, LocalDateTime timestamp, boolean
            read) {
        String readStatus = read ? "􀀀" : "○";
        return "[" + readStatus + "] " + sender + " (" + timestamp.format(FORMATTER) +
                "): ";
    }

    public static void main(String[] args) {
        System.out.println("\n--- Message Processing Examples ---");
        LocalDateTime now = LocalDateTime.now();
        Message[] messages = {
                new TextMessage("Alice", now.minusMinutes(5), true, "Hello, how are you?", true),
                new TextMessage("Bob", now.minusMinutes(4), false, "This is a very long message that should be truncated in the notification preview", false),
                new ImageMessage("Charlie", now.minusMinutes(3), true,
                        "http://example.com/image.jpg", "My vacation photo", 1200, 800),
                new ImageMessage("Diana", now.minusMinutes(2), false,
                        "http://example.com/image2.jpg", "", 800, 600),
                new LocationMessage("Eve", now.minusMinutes(1), true, 40.7128, -74.0060, "New York City"),
                new LocationMessage("Frank", now, false, 34.0522, -118.2437, ""),
                new ContactMessage("Grace", now.plusMinutes(1), true, "John Doe", "+1-555-1234",
                        "john@example.com"),
                new ContactMessage("Heidi", now.plusMinutes(2), false, "Jane Smith", "+1-555-5678", "")
        };
        System.out.println("--- Formatted Messages ---");
        for (Message message : messages) {
            System.out.println(MessageProcessor.formatMessage(message));
        }
        System.out.println("\n--- Notification Previews ---");
        for (Message message : messages) {
            System.out.println(MessageProcessor.getNotificationText(message));
        }
    }
}
