package aero.champ.exercise.patternmatching.switchcase;

import java.time.LocalDate;

public final class DateElement implements DataElement {
    private final LocalDate date;
    public DateElement(LocalDate date) {
        this.date = date;
    }
    public LocalDate getDate() {
        return date;
    }
    @Override
    public String getDescription() {
        return "Date: " + date;
    }
}
