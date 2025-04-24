package aero.champ.training.records;

public record Employee(String id, String name) {

    public boolean isHighEarner() {
        return true;
    }
}
