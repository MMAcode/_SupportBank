//////////////to clarify/ questions:
//declarative vs functional syntax. F.expects immutable variables (for vs forEach and BigDecimal which is immutable
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

import java.util.Scanner;

public class Main {
    public static void main(String args[]) {

        Bank bank = new Bank();
        bank.processCSVFile("Transactions2014.csv",false);

        //USER INTERFACE
        int choice = 0;
        System.out.println("\nWelcome in '"+bank.getName()+"' bank.");
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("\nWhat would you like to do?");
            System.out.println("a) List all account users. (press '1')");
            System.out.println("b) List all transactions of an user (type user name).");
            System.out.println("c) Finish. (press '9'");
            System.out.print("Your choice: ");
            if (scanner.hasNextInt()){
                choice = scanner.nextInt();
                if ( choice == 1) bank.showAllUsersAndBalance();
                if ( choice == 9) System.out.println("It was a pleasure to serve you today.");
                scanner.nextLine();
            } else {
                String userName = scanner.nextLine();
                bank.showOneUserTransactions(userName);
            }
        } while (choice!=9);

//        bank.showOneUserBalance("Ben B");
//        bank.showAllUsersAndBalance();
//        bank.showOneUserTransactions("Rob S");
    }
}
