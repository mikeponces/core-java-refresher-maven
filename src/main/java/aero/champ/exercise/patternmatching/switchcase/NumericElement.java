package aero.champ.exercise.patternmatching.switchcase;

public final class NumericElement implements DataElement {
    private final double value;
    public NumericElement(double value) {
        this.value = value;
    }
    public double getValue() {
        return value;
    }
    @Override
    public String getDescription() {
        return "Number: " + value;
    }
}
