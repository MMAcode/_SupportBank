package training.supportbank;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Transaction {
    String from;
    String to;
    BigDecimal amount;
    String narrative;
    LocalDate date;

    public Transaction(String date,  String amount, String from, String to, String narrative) {
        this.date = convertToDateType(date);
        this.from = from;
        this.to = to;
        this.amount = new BigDecimal(amount.toString());
        this.narrative = narrative;
    }

    public String getInfo() {
        return "£" + amount + " FROM: '" + from + "' TO: '" + to + "' " + date +" Narrative: '" + narrative + "'";
    }
    public String getFrom() {
        return from;
    }
    public String getTo() {
        return to;
    }
    public BigDecimal getAmount() {
        return amount;
    }

    private LocalDate convertToDateType (String dateString){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(dateString, formatter);
    }
}
