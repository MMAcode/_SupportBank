//////////////to clarify/ questions:
//initialising (default values of) variables: in class definition or in constructor? (private String bankName = "HSBC";)
//casting:   UserAccount fromUserAccount = (UserAccount) this.userAccounts.stream().filter(account->account.getUserName().equals(userAccountName));


//////////////to do
//***later
//-simplify processing row data from file (in bank class - processDataReceivedFromFile f.): record[titles.indexOf("From")]
//-change userAccount class from ArrayList<UserAccount> to String[]?
//***may be later
//-make userAccount constructor visible only for bank class, but not elsewhere = restrict creating class userAccount to only through/in a bank.
///Simple solution: make (UserAccount) class inside a (Bank) class and make it private
///ans:https://www.quora.com/How-can-I-enable-only-one-Java-class-to-instantiate-objects-of-another-class
//- the same for transaction constructor
//- read all lines using file.readAllLines (recommended by Alex)

package training.supportbank;

public class Main {
    public static void main(String args[]) {

        Bank hsbc = new Bank("HSBC");
        hsbc.processCSVFile("Transactions2014.csv");

    }
}
