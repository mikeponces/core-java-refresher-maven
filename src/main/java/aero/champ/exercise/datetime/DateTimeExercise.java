package aero.champ.exercise.datetime;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class DateTimeExercise {
    public static void main(String[] args) {
        DateTimeManager manager = new DateTimeManager();
// Section 1: Basic date and time operations
        System.out.println("SECTION 1: BASIC DATE AND TIME OPERATIONS");
        System.out.println("Current date: " + manager.getCurrentDate());
        System.out.println("Current time: " + manager.getCurrentTime());
        System.out.println("Current date and time: " + manager.getCurrentDateTime());
        LocalDate customDate = manager.createDate(2023, 5, 15);
        System.out.println("Custom date: " + customDate);
        LocalTime customTime = manager.createTime(14, 30, 0);
        System.out.println("Custom time: " + customTime);
        LocalDateTime customDateTime = manager.createDateTime(2023, 5, 15, 14, 30, 0);
        System.out.println("Custom date and time: " + customDateTime);
// Section 2: Date manipulation
        System.out.println("\nSECTION 2: DATE MANIPULATION");
        System.out.println("Date after adding 10 days: " +
                manager.addDaysToDate(customDate, 10));
        System.out.println("Date after subtracting 5 days: " +
                manager.subtractDaysFromDate(customDate, 5));
        System.out.println("Date after adding 3 months: " +
                manager.addMonthsToDate(customDate, 3));
        System.out.println("Next Monday after custom date: " +
                manager.getNextDayOfWeek(customDate, DayOfWeek.MONDAY));
        System.out.println("First day of month for custom date: " +
                manager.getFirstDayOfMonth(customDate));
        System.out.println("Last day of month for custom date: " +
                manager.getLastDayOfMonth(customDate));
// Section 3: Time zone operations
        System.out.println("\nSECTION 3: TIME ZONE OPERATIONS");
        System.out.println("Current date and time in New York: " +
                manager.getCurrentDateTimeInZone("America/New_York"));
        ZonedDateTime converted = manager.convertToTimeZone(
                LocalDateTime.now(),
                ZoneId.systemDefault().toString(),
                "Asia/Tokyo"
        );
        System.out.println("Current date and time in Tokyo: " + converted);
        List<String> europeanZones = manager.getAvailableTimeZoneIds("Europe");
        System.out.println("Some European time zones: " + europeanZones.subList(0,
                5));
        int timeDifference =
                manager.getTimeDifferenceBetweenZones("America/Los_Angeles", "Europe/London");
        System.out.println("Time difference between LA and London (hours): " +
                timeDifference);
// Section 4: Duration and period calculations
        System.out.println("\nSECTION 4: DURATION AND PERIOD CALCULATIONS");
        LocalTime startTime = LocalTime.of(9, 0);
        LocalTime endTime = LocalTime.of(17, 30);
        Duration duration = manager.calculateDuration(startTime, endTime);
        System.out.println("Duration between " + startTime + " and " + endTime + ": "
                + duration.toHours() + " hours and " + (duration.toMinutes() % 60) + " minutes");
        LocalDate startDate = LocalDate.of(2023, 1, 1);
        LocalDate endDate = LocalDate.of(2023, 12, 31);
        Period period = manager.calculatePeriod(startDate, endDate);
        System.out.println("Period between " + startDate + " and " + endDate + ": " +
                period.getYears() + " years, " + period.getMonths() + " months, and " +
                period.getDays() + " days");
        long daysBetween = manager.calculateDaysBetween(startDate, endDate);
        System.out.println("Total days between dates: " + daysBetween);
        long workDays = manager.calculateWorkDaysBetween(startDate, endDate);
        System.out.println("Work days between dates: " + workDays);
// Section 5: Date parsing and formatting
        System.out.println("\nSECTION 5: DATE PARSING AND FORMATTING");
        String formattedDate = manager.formatDate(LocalDate.now(), "EEEE, MMMM dd, yyyy");
                System.out.println("Formatted current date: " + formattedDate);
        String formattedDateTime = manager.formatDateTime(LocalDateTime.now(), "yyyy-MM-dd HH:mm:ss");
        System.out.println("Formatted current date and time: " + formattedDateTime);
        LocalDate parsedDate = manager.parseDate("15-05-2023", "dd-MM-yyyy");
        System.out.println("Parsed date: " + parsedDate);
        LocalDateTime parsedDateTime = manager.parseDateTime("2023-05-15 14:30:00",
                "yyyy-MM-dd HH:mm:ss");
        System.out.println("Parsed date and time: " + parsedDateTime);
// Section 6: Appointment scheduler
        System.out.println("\nSECTION 6: APPOINTMENT SCHEDULER");
        AppointmentScheduler scheduler = new AppointmentScheduler();
// Schedule some appointments
        boolean scheduled1 = scheduler.scheduleAppointment(LocalDateTime.of(2023, 5,
                15, 10, 0));
        System.out.println("Appointment 1 scheduled: " + scheduled1);
        boolean scheduled2 = scheduler.scheduleAppointment(LocalDateTime.of(2023, 5,
                15, 11, 0));
        System.out.println("Appointment 2 scheduled: " + scheduled2);
// Try to schedule an overlapping appointment
        boolean scheduled3 = scheduler.scheduleAppointment(LocalDateTime.of(2023, 5,
                15, 10, 30));
        System.out.println("Overlapping appointment scheduled: " + scheduled3);
// Get appointments for a specific date
        List<LocalDateTime> appointmentsOnDate =
                scheduler.getAppointmentsForDate(LocalDate.of(2023, 5, 15));
        System.out.println("Appointments on 2023-05-15: " +
                appointmentsOnDate.size());
// Get next available slot
        LocalDateTime nextSlot = scheduler.getNextAvailableSlot(LocalDateTime.of(2023,
                5, 15, 10, 0));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        System.out.println("Next available slot: " + nextSlot.format(formatter));
// Print all appointments
        System.out.println("\nAll appointments:");
        System.out.println(scheduler.getAppointmentsAsString());
// Reschedule an appointment
        boolean rescheduled = scheduler.rescheduleAppointment(
                LocalDateTime.of(2023, 5, 15, 10, 0),
                LocalDateTime.of(2023, 5, 16, 14, 0)
        );
        System.out.println("Appointment rescheduled: " + rescheduled);
// Print updated appointments
        System.out.println("\nAppointments after rescheduling:");
        System.out.println(scheduler.getAppointmentsAsString());
    }
}
