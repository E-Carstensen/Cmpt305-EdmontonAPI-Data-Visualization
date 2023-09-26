/**********************************************************************************************************************
 * Eric Carstensen - 3070801
 * CMPT 305 - X01L
 **********************************************************************************************************************/

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;
public class Lab2Main {

    public static void main(String[] args){

        String filePath =  Menu.getFileName();
        if (filePath.isBlank()){return;}

        DataSet dataSet = new DataSet(filePath);

        Menu.displayStats(dataSet, dataSet.accountList);

        searchByAccount(dataSet);

        searchByNeighborhood(dataSet);

    }



    /**
     * Prompts user to input an account number and searches for a properties with matching accountNumber
     * If match is found, prints account info using Account.toString()
     * If no match is found, prints "Account Not Found..."
     * @param dataSet to search for accounts within
     */
    public static void searchByAccount(DataSet dataSet){

        System.out.print("Find property by account number: ");
        String accountId = Menu.getUserInput();

        for (Account account : dataSet.accountList){
            if (account.accountNumber.equals(accountId)){
                System.out.println(account);
                return;
            }
        }
        System.out.println("Account Not Found...");
    }


    /**
     * Asks user for input and searches for a properties with matching neighborhood
     * Receives ArrayList of Account objects and calls displayStats() to display stats about the accounts
     *
     * @param dataSet to search for accounts within
     */
    public static void searchByNeighborhood(DataSet dataSet){
        System.out.print("Neighborhood: ");
        String neighborhood = Menu.getUserInput();

        NeighborhoodFilter neighborhoodFilter = new NeighborhoodFilter(neighborhood);
        ArrayList<Account> filtered = dataSet.filterAccounts(neighborhoodFilter);
        Menu.displayStats(dataSet, filtered);

    }



}
