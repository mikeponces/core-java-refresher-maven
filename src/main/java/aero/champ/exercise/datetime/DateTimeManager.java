package aero.champ.exercise.datetime;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

public class DateTimeManager {
    /**
     * Gets the current date in the system default time zone
     */
    public LocalDate getCurrentDate() {
        return LocalDate.now();
    }

    /**
     * Gets the current time in the system default time zone
     */
    public LocalTime getCurrentTime() {
        return LocalTime.now();
    }

    /**
     * Gets the current date and time in the system default time zone
     */
    public LocalDateTime getCurrentDateTime() {
        return LocalDateTime.now();
    }

    /**
     * Creates a specific date
     */
    public LocalDate createDate(int year, int month, int day) {
        return LocalDate.of(year, month, day);
    }

    /**
     * Creates a specific time
     */
    public LocalTime createTime(int hour, int minute, int second) {
        return LocalTime.of(hour, minute, second);
    }

    /**
     * Creates a specific date and time
     */
    public LocalDateTime createDateTime(int year, int month, int day, int hour, int
            minute, int second) {
        return LocalDateTime.of(year, month, day, hour, minute, second);
    }

    /**
     * Adds days to a date and returns the new date
     */
    public LocalDate addDaysToDate(LocalDate date, long daysToAdd) {
        return date.plusDays(daysToAdd);
    }

    /**
     * Subtracts days from a date and returns the new date
     */
    public LocalDate subtractDaysFromDate(LocalDate date, long daysToSubtract) {
        return date.minusDays(daysToSubtract);
    }

    /**
     * Adds months to a date and returns the new date
     */
    public LocalDate addMonthsToDate(LocalDate date, long monthsToAdd) {
        return date.plusMonths(monthsToAdd);
    }

    /**
     * Finds the next occurrence of a specific day of week after a date
     */
    public LocalDate getNextDayOfWeek(LocalDate date, DayOfWeek dayOfWeek) {
        return date.with(TemporalAdjusters.next(dayOfWeek));
    }

    /**
     * Gets the first day of the month for a given date
     */
    public LocalDate getFirstDayOfMonth(LocalDate date) {
        return date.with(TemporalAdjusters.firstDayOfMonth());
    }

    /**
     * Gets the last day of the month for a given date
     */
    public LocalDate getLastDayOfMonth(LocalDate date) {
        return date.with(TemporalAdjusters.lastDayOfMonth());
    }

    /**
     * Gets the current date and time for a specific time zone
     */
    public ZonedDateTime getCurrentDateTimeInZone(String zoneId) {
        return ZonedDateTime.now(ZoneId.of(zoneId));
    }

    /**
     * Converts a LocalDateTime to a specific time zone
     */
    public ZonedDateTime convertToTimeZone(LocalDateTime dateTime, String fromZone, String
            toZone) {
        ZonedDateTime sourceZonedDateTime = dateTime.atZone(ZoneId.of(fromZone));
        return sourceZonedDateTime.withZoneSameInstant(ZoneId.of(toZone));
    }

    /**
     * Gets available time zone IDs for a specific region
     */
    public List<String> getAvailableTimeZoneIds(String region) {
        List<String> availableZoneIds = new ArrayList<>();
        for (String zoneId : ZoneId.getAvailableZoneIds()) {
            if (zoneId.startsWith(region)) {
                availableZoneIds.add(zoneId);
            }
        }
        return availableZoneIds;
    }

    /**
     * Gets the time difference between two time zones in hours
     */
    public int getTimeDifferenceBetweenZones(String zone1, String zone2) {
        LocalDateTime now = LocalDateTime.now();
        ZonedDateTime zone1DateTime = now.atZone(ZoneId.of(zone1));
        ZonedDateTime zone2DateTime = now.atZone(ZoneId.of(zone2));
        return (int) ChronoUnit.HOURS.between(zone1DateTime, zone2DateTime);
    }

    /**
     * Calculates the duration between two LocalTime objects
     */
    public Duration calculateDuration(LocalTime startTime, LocalTime endTime) {
        return Duration.between(startTime, endTime);
    }

    /**
     * Calculates the period between two LocalDate objects
     */
    public Period calculatePeriod(LocalDate startDate, LocalDate endDate) {
        return Period.between(startDate, endDate);
    }

    /**
     * Calculates the number of days between two dates
     */
    public long calculateDaysBetween(LocalDate startDate, LocalDate endDate) {
        return ChronoUnit.DAYS.between(startDate, endDate);
    }

    /**
     * Calculates the number of workdays (Monday to Friday) between two dates
     */
    public long calculateWorkDaysBetween(LocalDate startDate, LocalDate endDate) {
        long days = 0;
        LocalDate currentDate = startDate;
        while (currentDate.isBefore(endDate) || currentDate.isEqual(endDate)) {
            DayOfWeek dayOfWeek = currentDate.getDayOfWeek();
            if (!(dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY)) {
                days++;
            }
            currentDate = currentDate.plusDays(1);
        }
        return days;
    }

    /**
     * Formats a LocalDate using a specific pattern
     */
    public String formatDate(LocalDate date, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return date.format(formatter);
    }

    /**
     * Formats a LocalDateTime using a specific pattern
     */
    public String formatDateTime(LocalDateTime dateTime, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return dateTime.format(formatter);
    }

    /**
     * Parses a date string using a specific pattern
     */
    public LocalDate parseDate(String dateString, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return LocalDate.parse(dateString, formatter);
    }

    /**
     * Parses a date-time string using a specific pattern
     */
    public LocalDateTime parseDateTime(String dateTimeString, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return LocalDateTime.parse(dateTimeString, formatter);
    }

    /**
     * Calculate age based on birth date
     * @param birthDate The birth date
     * @return Age in years
     */
    public int calculateAge(LocalDate birthDate) {
        return Period.between(birthDate, LocalDate.now()).getYears();
    }
    /**
     * Calculate precise age with years, months and days
     * @param birthDate The birth date
     * @return A string representation of the age
     */
    public String calculatePreciseAge(LocalDate birthDate) {
        Period period = Period.between(birthDate, LocalDate.now());
        return String.format("%d years, %d months, and %d days",
                period.getYears(), period.getMonths(), period.getDays());
    }

    /**
     * Calculate a date that is a specified number of business days in the future
     * @param startDate The starting date
     * @param businessDays Number of business days to add
     * @param holidays List of holiday dates to exclude
     * @return The resulting date
     */
    public LocalDate addBusinessDays(LocalDate startDate, int businessDays,
                                     List<LocalDate> holidays) {
        LocalDate result = startDate;
        int addedBusinessDays = 0;
        while (addedBusinessDays < businessDays) {
            result = result.plusDays(1);
// Skip weekends and holidays
            if (!(result.getDayOfWeek() == DayOfWeek.SATURDAY ||
                    result.getDayOfWeek() == DayOfWeek.SUNDAY ||
                    holidays.contains(result))) {
                addedBusinessDays++;
            }
        }
        return result;
    }
}
