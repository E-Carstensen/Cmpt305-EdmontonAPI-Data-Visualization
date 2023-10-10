/**********************************************************************************************************************
 * Eric Carstensen - 3070801
 * CMPT 305 - X01L - Milestone 1
 **********************************************************************************************************************/

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Contains methods for taking user input, searching through a given DataSet, and displaying stats about the filtered accounts
 *
 */
public class CSVPropertyAssessmentDAO implements PropertyAssessmentDAO {


    /**
     * Displays various stats about a given List of Accounts
     * If List of Accounts is empty, prints "No Accounts Found..."
     * @param dataSet to call stat functions
     * @param accounts List of Accounts to display stats about
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


    // Takes one line from the user and returns it as a string - Allows for empty input
    public static String getUserInput(){
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }


    /**
     * Prompts user to input local path to a csv file
     * While file does not exist, re-prompts user for file name, if user enters 'n' then quit
     * @return absolute path to csv file as a string
     *         Empty sting if user quits program
     */
    public static String getFileName(){

        System.out.print("CSV Filename: "); // Prompt user to input local csv file name
        String input = getUserInput();

        if (input.isBlank()){input = "test.csv";} // TODO: REMOVE, For easier debugging, if input empty, open test file

        Path path = Paths.get("src", input);
        while(!Files.exists(path)){ // While file does not exist
            System.out.println("File Not Found");
            System.out.print("CSV Filename ('n' to quit): "); // Prompt user to input local csv file name again
            input = getUserInput();
            if (input.equals("n")){return "";} // Allow user to quit program

        }
        return path.toAbsolutePath().toString(); // Return absolute path to file as a string
    }


    /**
     * Prompts user to input an account number and searches for a properties with matching accountNumber
     * Iterates over Accounts within dataSet.accountList and looks for a match
     * If match is found, prints multi-line account info using Account.toString() override
     * If no match is found, prints "Account Not Found..."
     * @param dataSet DataSet object to search for accounts within
     */
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


    /**
     * Asks user for input and searches for properties with matching neighborhood
     * Receives ArrayList of Account objects and calls displayStats() to display stats about the accounts
     *
     * @param dataSet to search for accounts within
     */
    public static void searchByNeighborhood(DataSet dataSet){
        System.out.print("Neighborhood: ");
        String neighborhood = getUserInput();

        NeighborhoodFilter neighborhoodFilter = new NeighborhoodFilter(neighborhood);
        ArrayList<Account> filtered = dataSet.filterAccounts(neighborhoodFilter);
        CSVPropertyAssessmentDAO.displayStats(dataSet, filtered);

    }


    /**
     * Prompts user for an assessment class to filter properties by
     * Then displays statistics about the filtered accounts
     *
     * @param dataSet DataSet object containing property information
     */
    public static void searchByAssessmentClass(DataSet dataSet){

        System.out.println("Enter the assessment class you wish to search for:");
        String assessmentClass = CSVPropertyAssessmentDAO.getUserInput();

        AssessmentClassFilter filter = new AssessmentClassFilter(assessmentClass);

        ArrayList<Account> filteredAccounts = dataSet.filterAccounts(filter);

        CSVPropertyAssessmentDAO.displayStats(dataSet, filteredAccounts);



    }




}
