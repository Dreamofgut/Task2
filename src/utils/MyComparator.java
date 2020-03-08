package utils;
import bank.Account;

import java.util.Comparator;

public class MyComparator implements Comparator<Account> {
    public int compare(Account o1, Account o2) {
        return -1*Long.compare(o1.getAmmount(), o2.getAmmount());
    }
}