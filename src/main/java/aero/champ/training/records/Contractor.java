package aero.champ.training.records;

public record Contractor (String id, String name, double hourlyRate) implements Payable {
    public Contractor {
        System.out.println("Contractor constructor");
    }

    @Override
    public double calculatePay() {
        return hourlyRate;
    }
}