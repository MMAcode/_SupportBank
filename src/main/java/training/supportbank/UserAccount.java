package training.supportbank;

import java.util.ArrayList;

public class UserAccount {
    private String userName;
    double sum = 0;
//    private double balance;
//    private ArrayList<Transaction> transactions = new ArrayList<>();

    public UserAccount (String name){
        this.userName = name;
        this.sum = 0;
    }

    public String getUserName() {
        return userName;
    }


}
