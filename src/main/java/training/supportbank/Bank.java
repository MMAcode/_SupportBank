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

    public String getName() {
        return name;
    }

    public void processCSVFile(String filePath, boolean showChanges) {
        if (showChanges) System.out.println("Getting data from '" + filePath + "'...");
        ArrayList<String[]> records = new ArrayList<>();
        List<String> titles = new ArrayList<>();
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

            if (showChanges) {
                System.out.println("TITLES: \n" + titles + "\n");
                System.out.println("Following transactions added into the bank database:");
            }


            for (int i=0; i< records.size();i++){
                String[] record = records.get(i);
                String userAccountName = record[titles.indexOf("From")];
                if (getUserAccountByUserName(userAccountName) == null) this.userAccounts.add(new UserAccount(userAccountName));
                userAccountName = record[titles.indexOf("To")];
                if (getUserAccountByUserName(userAccountName) == null) this.userAccounts.add(new UserAccount(userAccountName));

                this.transactions.add(new Transaction(
                        record[titles.indexOf("Date")],
                        record[titles.indexOf("Amount")],
                        record[titles.indexOf("From")],
                        record[titles.indexOf("To")],
                        record[titles.indexOf("Narrative")]));

                if (showChanges) System.out.println(transactions.get(transactions.size() - 1).getInfo());
            };

        } catch (Exception e) {
            System.out.println("Error during reading file: " + e);
        }
    }

    public void showOneUserBalance(String userName) {
        System.out.println(userName + " has currently £" + getAccountTotal(userName) + ".");
    }
    public void showOneUserTransactions(String userName){
        boolean userExists = false;
        for (int i = 0; i<transactions.size();i++){
            Transaction transaction = transactions.get(i);
            if (transaction.getFrom().equals(userName)) {
                System.out.println(" -" + transaction.getInfo());
                userExists = true;
            }
            if (transaction.getTo().equals(userName)) {
                System.out.println(" +" + transaction.getInfo());
                userExists = true;
            }
        }
        if (!userExists) System.out.println("This user is not in our database. Please try again.");
    }
    public void showAllUsersAndBalance() {
        userAccounts.forEach(account -> {
//            String userName = account.getUserName();
//            System.out.println(userName + ": £" + getAccountTotal(userName));
                showOneUserBalance(account.getUserName());
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
