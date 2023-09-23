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
public class Main {

    public static void main(String[] args){

        String filePath =  getFileName();
        if (filePath.isBlank()){return;}

        DataSet dataSet = new DataSet(filePath);

        displayStats(dataSet, dataSet.accountList);

        searchByAccount(dataSet);

        searchByNeighborhood(dataSet);

    }


    /**
     * Displays various stats about a given List of Accounts
     * */
    public static void displayStats(DataSet dataSet, ArrayList<Account> accounts){
        if (accounts.isEmpty()){System.out.println("No Accounts Found...");return;}


        DecimalFormat dollar = new DecimalFormat("$#,##0");

        System.out.println("Number of Entries: " + accounts.size());
        System.out.println("Highest Value: " + dollar.format(dataSet.getHighestValue(accounts)));
        System.out.println("Lowest Value: " + dollar.format(dataSet.getLowestValue(accounts)));
        System.out.println("Range: " + dollar.format((dataSet.getHighestValue(accounts) - dataSet.getLowestValue(accounts))));
        System.out.println("Mean Value: " + dollar.format(dataSet.getMean(accounts)));
        System.out.println("Median Value: " + dollar.format(dataSet.getMedian(accounts)));
    }


    public static void searchByAccount(DataSet dataSet){

        System.out.print("Find property by account number: ");
        String accountId = getUserInput();

        for (Account account : dataSet.accountList){
            if (account.accountNumber.equals(accountId)){
                System.out.println(account);
                return;
            }
        }
        System.out.println("Account Not Found...");
    }

    public static void searchByNeighborhood(DataSet dataSet){
        System.out.print("Neighborhood: ");
        String neighborhood = getUserInput();

        NeighborhoodFilter neighborhoodFilter = new NeighborhoodFilter(neighborhood);
        dataSet.sortAccounts(neighborhoodFilter);

        displayStats(dataSet, dataSet.filteredAccountList);





    }

    // Takes one line from the user and returns it as a string
    public static String getUserInput(){
        Scanner sc = new Scanner(System.in);
        //sc.close();
        return sc.nextLine();
    }


    public static String getFileName(){

        System.out.print("CSV Filename: "); // Prompt user to input local csv file name
        String input = getUserInput();

        if (input.isBlank()){input = "test.csv";} // TODO REMOVE For easier debugging, remove in future

        Path path = Paths.get("src", input);
        while(!Files.exists(path)){ // While file does not exist
            System.out.println("File Not Found");
            System.out.print("CSV Filename ('n' to quit): "); // Prompt user to input local csv file name again
            input = getUserInput();
            if (input.equals("n")){return "";} // Allow user to quit program

        }
        return path.toAbsolutePath().toString(); // Return absolute path to file as a string
    }

}
