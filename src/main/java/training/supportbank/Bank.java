package training.supportbank;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class Bank {
    private String name;
    private ArrayList<UserAccount> userAccounts = new ArrayList<>();

    public Bank(String name) {
        this.name = name;
    }

    public void processCSVFile(String filePath) {
        System.out.println("Getting data from '" + filePath + "'...");

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
            processDataReceivedFromFile(titles,records);
        } catch (Exception e) {
            System.out.println("Error during reading file: " + e);
        }



    }

    private void processDataReceivedFromFile(List<String> titles, ArrayList<String[]> records) {
        //to do (urgency 2/10): check if received variables are ot empty, throw error if yes

        System.out.println("TITLES: \n" +titles +"\n");
//        System.out.println(records.get(0)[titles.indexOf("From")]);

//        records.forEach(record->{
        String[] record = records.get(0);
        System.out.println(record[titles.indexOf("From")]);
            //process a row
            ///from...
            String userAccountName = record[titles.indexOf("From")];
            //// find/create userAccount in bank
            UserAccount fromUserAccount = findUserAccountByUserName(userAccountName);
            System.out.println("fromUserAccount: "+fromUserAccount);

            if (fromUserAccount==null) createUserAccount(userAccountName);



            //make new record/transaction in that account
            //repeat for 'to'
            //save transaction also in bank itself
//        });

    }

    private UserAccount findUserAccountByUserName(String userName) {
        for(UserAccount account : userAccounts) {
            if(account.getUserName().equals(userName)) {
                return account;
            }
        }
        return null;
    }

    private void createUserAccount (String userName){
        //received name was checked against the 'db', therefore this check is not needed now. We know this user doesn't exist.
        this.userAccounts.add(new UserAccount())
    }

    }
