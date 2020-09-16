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

    public Transaction(LocalDate date, String from, String to, BigDecimal amount, String narrative) {
        this.date = date;
        this.from = from;
        this.to = to;
        this.amount = amount;
        this.narrative = narrative;
    }
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

    public LocalDate getDate() {
        return date;
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

    public String getNarrative() {
        return narrative;
    }


    private LocalDate convertToDateType (String dateString){
//        System.out.println("received date string: "+dateString);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy",Locale.forLanguageTag("GB"));
//        LocalDate dd = LocalDate.parse(dateString, formatter);
//        System.out.println("Converted date: "+dd);

        return LocalDate.parse(dateString, formatter);
    }
}
