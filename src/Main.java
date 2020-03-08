import bank.Account;
import bank.Client;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import utils.CollectionUtils;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args){
        try {
            File fXml=new File("resources/input.xml");
            List container = new ArrayList<Client>();
            List accounts = null;
            String surname = null;
            int ammount = 0;
            boolean isLocked = false;
            String temp = null;

            DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
            DocumentBuilder db=dbf.newDocumentBuilder();
            Document doc=db.parse(fXml);

            doc.getDocumentElement().normalize();

            NodeList clientsList = doc.getElementsByTagName("client");
            NodeList accountsList;
            for(int i=0;i < clientsList.getLength();i++) {
                Node clientNode = clientsList.item(i);
                if (clientNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element elements = (Element) clientNode;
                    temp = elements.getAttribute("name");
                    surname = temp;
                    String temporary = "account" + i;
                    accountsList = doc.getElementsByTagName(temporary);
                    accounts = new ArrayList<Account>();
                    for (int j = 0; j < accountsList.getLength(); j++) {
                        Node accountNode = accountsList.item(j);

                        if (clientNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element accountelement = (Element) accountNode;
                            temp = accountelement.getAttribute("ammount");
                            ammount = Integer.parseInt(temp);
                            temp = accountelement.getAttribute("isLocked");
                            isLocked = Boolean.parseBoolean(temp);
                        }
                        Account account = new Account(ammount, isLocked);
                        accounts.add(account);
                    }
                }
                Client client = new Client(surname , accounts);
                container.add(client);
            }

            Scanner scanner = new Scanner(new InputStreamReader(System.in));
            int point = 0;
            while (point != 9) {
                System.out.println("Program Menu:");
                System.out.println("1. Блокировать счёт.");
                System.out.println("2. Поиск счетов заданного человека.");
                System.out.println("3. Сортировать счета.");
                System.out.println("4. Сумма по всем счетам.");
                System.out.println("5. Сумма по положительным счетам.");
                System.out.println("6. Сумма по отрицательным счетам.");
                System.out.println("9. Выход из программы.");
                int sum = 0;
                int pointLock = 0;
                String surnameLockString = "";
                List accountsListTemp;
                point = scanner.nextInt();

                if (point == 1){
                    System.out.println("Введите фамилию владельца счета для блокировки.");
                    int i = 1;
                    for (var elem: container){
                        System.out.println(i + ". " + ((Client) elem).getName());
                        i++;
                    }
                    surnameLockString = scanner.next();
                    i = 1;
                    for (var elem : container){
                        if (((Client) elem).getName().equals(surnameLockString)) {
                            System.out.println("Введите номер счета для блокировки.");
                            accountsListTemp = ((Client) elem).getAccountList();
                            for (var element : accountsListTemp){
                                System.out.println(i + "." + element.toString());
                                i++;
                            }
                        }
                    }

                    pointLock = scanner.nextInt();
                    i = 1;
                    for (var elem: container){
                        if (((Client) elem).getName().equals(surnameLockString)){
                            accountsListTemp = ((Client) elem).getAccountList();
                            for (var element : accountsListTemp){
                                if (i == pointLock){
                                    if (((Account) element).isLocked() == true){
                                        ((Account) element).setLocked(false);
                                    } else ((Account) element).setLocked(true);
                                }
                                i++;
                            }
                            ((Client) elem).setAccountList(accountsListTemp);
                        }
                    }
                    System.out.println("Done!");
                }

                if (point == 2){
                    System.out.println("Введите фамилию человека для поиска его счетов.");
                    int i = 1;
                    for (var elem: container){
                        System.out.println(i + ". " + ((Client) elem).getName());
                        i++;
                    }
                    surnameLockString = scanner.next();
                    i = 1;
                    for (var elem : container){
                        if (((Client) elem).getName().equals(surnameLockString)) {
                            accountsListTemp = ((Client) elem).getAccountList();
                            for (var element : accountsListTemp){
                                System.out.println(i + "." + element.toString());
                                i++;
                            }
                        }
                    }
                }

                if (point == 3){
                    List<Client> sortContainer = CollectionUtils.getSortedList(container);
                    for (var elem : sortContainer){
                        System.out.println(elem.toString());
                    }
                }

                if (point == 4){
                    sum = 0;
                    sum = CollectionUtils.sumAll(container);
                    System.out.println("Sum of all accounts: " + sum);
                }

                if (point == 5){
                    sum = 0;
                    sum = CollectionUtils.sumRegular(container);
                    System.out.println("Sum of regular accounts: " + sum);
                }

                if (point == 6){
                    sum = 0;
                    sum = CollectionUtils.sumIrregular(container);
                    System.out.println("Sum of irregular accounts: " + sum);
                }

            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
