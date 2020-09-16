package training.supportbank;

import java.util.ArrayList;

public class UserAccount {
    private String userName;
//    private double balance;
//    private ArrayList<Transaction> transactions = new ArrayList<>();

    public UserAccount (String name){
        this.userName = name;
    }

    public String getUserName() {
        return userName;
    }


}
