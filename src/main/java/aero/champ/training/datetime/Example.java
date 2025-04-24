package aero.champ.training.datetime;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Set;

public class Example
{
    public static void main(String[] args) throws InterruptedException {
        Date date = new Date();// legacy

        LocalDateTime now = LocalDateTime.now();
        now.plusDays(5);

        ZonedDateTime ny = ZonedDateTime.now(ZoneId.of("America/New York"));
        ZonedDateTime tokyo = ZonedDateTime.now(ZoneId.of("Asia/Tokyo"));

        Set<String> availableZoneIds = ZoneId.getAvailableZoneIds();
    }
}
