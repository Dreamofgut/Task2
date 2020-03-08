package bank;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Client {
    private String name;
    private List<Account> accountList = new ArrayList<Account>();

    public Client(String name, List<Account> accountList) {
        this.name = name;
        this.accountList = accountList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Account> getAccountList() {
        return accountList;
    }

    public void setAccountList(List<Account> accountList) {
        this.accountList = accountList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(name, client.name) &&
                Objects.equals(accountList, client.accountList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, accountList);
    }

    @Override
    public String toString() {
        String finalString = "";
        for (var elem : accountList)
        {
            finalString +=
                    "name='" + name + '\'' +
                    "," + elem.toString() +
                     '\n';
        }
        return finalString;
    }
}
