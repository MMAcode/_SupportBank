package training.supportbank;

//to do
//restrict creating class userAccount to only through/in a bank.

public class Main {
    public static void main(String args[]) {

        Bank hsbc = new Bank("HSBC");
        hsbc.processCSVFile("Transactions2014.csv");

    }
}
