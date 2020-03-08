package utils;

import bank.Account;
import bank.Client;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CollectionUtils {
    public static List<Client> getSortedList(List<Client> container){
        MyComparator comp = new MyComparator();
        List<Account> accountListTemp;
        for (var elem : container){
            accountListTemp = elem.getAccountList();
            Collections.sort(accountListTemp, comp);
            elem.setAccountList(accountListTemp);
        }
        return container;
    }

    public static int sumAll(List<Client> container){
        int sum = 0;
        List<Account> accountListTemp;
        for (var elem : container){
            accountListTemp = elem.getAccountList();
            for (var element : accountListTemp){
                sum += element.getAmmount();
            }
        }
        return sum;
    }

    public static int sumRegular(List<Client> container){
        int sum = 0;
        List<Account> accountListTemp;
        for (var elem : container){
            accountListTemp = elem.getAccountList();
            for (var element : accountListTemp){
                if (element.getAmmount() > 0){
                    sum += element.getAmmount();
                }
            }
        }
        return sum;
    }

    public static int sumIrregular(List<Client> container){
        int sum = 0;
        List<Account> accountListTemp;
        for (var elem : container){
            accountListTemp = elem.getAccountList();
            for (var element : accountListTemp){
                if (element.getAmmount() < 0){
                    sum += element.getAmmount();
                }
            }
        }
        return sum;
    }
}
