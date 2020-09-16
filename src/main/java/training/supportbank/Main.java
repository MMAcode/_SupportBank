package training.supportbank;

public class Main {
    public static void main(String args[]) {

        Bank hsbc = new Bank("HSBC");
        hsbc.processCSVFile("Transactions2014.csv");

    }
}
