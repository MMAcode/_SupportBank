package training.supportbank;

public class Transaction {
    String from;
    String to;
    double amount;
    String narrative;

    public Transaction(String from, String to, double amount, String narrative) {
        this.from = from;
        this.to = to;
        this.amount = amount;
        this.narrative = narrative;
    }

    public String getInfo() {
        return "From: " + from + ", To: " + to + ", Amount: " + amount + ", Narrative: " + narrative;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public double getAmount() {
        return amount;
    }

    public String getNarrative() {
        return narrative;
    }
}
