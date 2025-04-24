package aero.champ.exercise.datetime;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class AppointmentScheduler {
    private final List<LocalDateTime> appointments;
    private static final Duration DEFAULT_APPOINTMENT_DURATION = Duration.ofMinutes(60);

    public AppointmentScheduler() {
        appointments = new ArrayList<>();
    }

    /**
     * Schedule a new appointment
     */
    public boolean scheduleAppointment(LocalDateTime dateTime) {
// Check if appointment overlaps with existing appointments
        for (LocalDateTime appointment : appointments) {
            if (isOverlapping(appointment, dateTime)) {
                return false; // Cannot schedule due to overlap
            }
        }
        appointments.add(dateTime);
        return true;
    }

    /**
     * Check if two appointments overlap, considering the default duration
     */
    private boolean isOverlapping(LocalDateTime appointment1, LocalDateTime
            appointment2) {
        LocalDateTime end1 = appointment1.plus(DEFAULT_APPOINTMENT_DURATION);
        LocalDateTime end2 = appointment2.plus(DEFAULT_APPOINTMENT_DURATION);
// Check if appointment2 starts before appointment1 ends AND appointment2 ends after appointment1 starts
        return appointment2.isBefore(end1) && end2.isAfter(appointment1);
    }

    /**
     * Get all appointments for a specific date
     */
    public List<LocalDateTime> getAppointmentsForDate(LocalDate date) {
        List<LocalDateTime> result = new ArrayList<>();
        for (LocalDateTime appointment : appointments) {
            if (appointment.toLocalDate().equals(date)) {
                result.add(appointment);
            }
        }
        return result;
    }

    /**
     * Get the next available appointment slot after a given date and time
     */
    public LocalDateTime getNextAvailableSlot(LocalDateTime after) {
        LocalDateTime proposedSlot = after;
// Try to find an available slot
        while (true) {
// Check if the proposed slot is during business hours (9 AM to 5 PM)
            if (proposedSlot.getHour() < 9 || proposedSlot.getHour() >= 17) {
// Move to 9 AM the next day
                proposedSlot =
                        LocalDateTime.of(proposedSlot.toLocalDate().plusDays(1), LocalTime.of(9, 0));
                continue;
            }
// Check if the proposed slot is on a weekend
            if (proposedSlot.getDayOfWeek().getValue() > 5) {
// Move to 9 AM the next Monday
                proposedSlot = LocalDateTime.of(proposedSlot.toLocalDate().plusDays(8
                        - proposedSlot.getDayOfWeek().getValue()), LocalTime.of(9, 0));
                continue;
            }
// Check if the proposed slot overlaps with existing appointments
            boolean overlaps = false;
            for (LocalDateTime appointment : appointments) {
                if (isOverlapping(appointment, proposedSlot)) {
                    overlaps = true;
// Move to after the conflicting appointment
                    proposedSlot = appointment.plus(DEFAULT_APPOINTMENT_DURATION);
                    break;
                }
            }
            if (!overlaps) {
                return proposedSlot;
            }
        }
    }

    /**
     * Cancel an appointment
     */
    public boolean cancelAppointment(LocalDateTime dateTime) {
        return appointments.removeIf(appointment -> appointment.equals(dateTime));
    }

    /**
     * Reschedule an appointment
     */
    public boolean rescheduleAppointment(LocalDateTime oldDateTime, LocalDateTime
            newDateTime) {
        if (!appointments.contains(oldDateTime)) {
            return false; // Original appointment doesn't exist
        }
// Check if the new time is available
        if (scheduleAppointment(newDateTime)) {
            cancelAppointment(oldDateTime);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Get a string representation of all appointments
     */
    public String getAppointmentsAsString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        StringBuilder sb = new StringBuilder();
        appointments.sort(null); // Sort appointments chronologically
        for (LocalDateTime appointment : appointments) {
            sb.append(appointment.format(formatter)).append("\n");
        }
        return sb.toString();
    }

    /**
     * Schedule a recurring appointment (daily, weekly, monthly)
     *
     * @param startDateTime  The start date and time
     * @param recurrenceType Can be "DAILY", "WEEKLY", or "MONTHLY"
     * @param occurrences    Number of occurrences
     * @return True if all appointments were scheduled successfully
     */
    public boolean scheduleRecurringAppointment(LocalDateTime startDateTime, String
            recurrenceType, int occurrences) {
        boolean allScheduled = true;
        LocalDateTime currentDateTime = startDateTime;
        for (int i = 0; i < occurrences; i++) {
            boolean scheduled = scheduleAppointment(currentDateTime);
            if (!scheduled) {
                allScheduled = false;
            }
// Calculate next occurrence based on recurrence type
            switch (recurrenceType) {
                case "DAILY":
                    currentDateTime = currentDateTime.plusDays(1);
                    break;
                case "WEEKLY":
                    currentDateTime = currentDateTime.plusWeeks(1);
                    break;
                case "MONTHLY":
                    currentDateTime = currentDateTime.plusMonths(1);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid recurrence type");
            }
        }
        return allScheduled;
    }
}
