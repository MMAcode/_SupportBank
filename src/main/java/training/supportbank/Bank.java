package training.supportbank;

import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class Bank {
    private String name;
    private ArrayList<UserAccount> userAccounts = new ArrayList<>();
    private ArrayList<Transaction> transactions = new ArrayList<>();

    public Bank(String name) {
        this.name = name;
        System.out.println("Bank " + this.name + " created.");
    }

    public Bank() {
        this("Amex");

    }

    public void processCSVFile(String filePath, boolean showChanges) {
        if (showChanges) System.out.println("Getting data from '" + filePath + "'...");

        ArrayList<String[]> records = new ArrayList<>();
        List<String> titles = new ArrayList<>();
//        String[] firstLine={};
        try (BufferedReader br = new BufferedReader(new FileReader("/Users/mmakarov/Miro/bootcamp/java_projects/_SupportBank/src/docs/Transactions2014.csv"))) {
            String line;

            if ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                titles = Arrays.asList(values);
            }
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                records.add(values);
            }
            processDataReceivedFromFile(titles, records, showChanges);
        } catch (Exception e) {
            System.out.println("Error during reading file: " + e);
        }
    }
    private void processDataReceivedFromFile(List<String> titles, ArrayList<String[]> records, boolean showChanges) {
        //to do (urgency 2/10): check if received variables are ot empty, throw error if yes

        if (showChanges) {
            System.out.println("TITLES: \n" + titles + "\n");
            System.out.println("Following transactions added into the bank database:");
        }

        records.forEach(record -> {
//        String[] record = records.get(0);
            String userAccountName = record[titles.indexOf("From")];
            if (getUserAccountByUserName(userAccountName) == null) createUserAccount(userAccountName);

            userAccountName = record[titles.indexOf("To")];
            if (getUserAccountByUserName(userAccountName) == null) createUserAccount(userAccountName);

            //save transaction also in bank itself

            this.transactions.add(new Transaction(
                    record[titles.indexOf("Date")],
                    record[titles.indexOf("Amount")],
                    record[titles.indexOf("From")],
                    record[titles.indexOf("To")],
                    record[titles.indexOf("Narrative")]));

            if (showChanges) System.out.println(transactions.get(transactions.size() - 1).getInfo());
        });

    }
    private void createUserAccount(String userName) {
        //received name was checked against the 'db', therefore this check is not needed now. We know this user doesn't exist.
        this.userAccounts.add(new UserAccount(userName));
    }

    public void showOneUserBalance(String userName) {
        System.out.println(userName + " has currently £ " + getAccountTotal(userName) + ".");
    }
    public void showOneUserTransactions(String userName){
        System.out.println("Transactions of '" + userName + ":");
        for (int i = 0; i<transactions.size();i++){
            Transaction transaction = transactions.get(i);
            if (transaction.getFrom().equals(userName)) System.out.println(" -" + transaction.getInfo());
            if (transaction.getTo().equals(userName)) System.out.println(" +" + transaction.getInfo());
        }
    }

    public void showAllUsersNames() {
        System.out.println("\n" + name + "bank users:");
        userAccounts.forEach(account -> System.out.println(account.getUserName()));
    }
    public void showAllUsersAndBalance() {
        userAccounts.forEach(account -> {
            String userName = account.getUserName();
            System.out.println(userName + ": £" + getAccountTotal(userName));
        });
    }



    //just to check if account exists
    private UserAccount getUserAccountByUserName(String userName) {
        for (UserAccount account : userAccounts) {
            if (account.getUserName().equals(userName)) {
                return account;
            }
        }
        return null;
    }

    public BigDecimal getAccountTotal(String name) {
        BigDecimal sum = new BigDecimal("0");
        for (int i = 0; i < transactions.size(); i++) {
            Transaction transaction = transactions.get(i);
            if (transaction.getFrom().equals(name)) sum = sum.subtract(transaction.getAmount());
            if (transaction.getTo().equals(name)) sum = sum.add(transaction.getAmount());
        }
        return sum;
    }

}
