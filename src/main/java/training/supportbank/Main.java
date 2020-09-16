package training.supportbank;

//to do
//restrict creating class userAccount to only through/in a bank.

//to clarify/ questions:
//ENCAPSULATION: how to make userAccount constructor visible only for bank class, but not elsewhere?
////ans:https://www.quora.com/How-can-I-enable-only-one-Java-class-to-instantiate-objects-of-another-class
//casting:   UserAccount fromUserAccount = (UserAccount) this.userAccounts.stream().filter(account->account.getUserName().equals(userAccountName));

public class Main {
    public static void main(String args[]) {

        Bank hsbc = new Bank("HSBC");
        hsbc.processCSVFile("Transactions2014.csv");

    }
}
