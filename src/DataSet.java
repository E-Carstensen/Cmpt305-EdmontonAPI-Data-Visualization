/**********************************************************************************************************************
 * Eric Carstensen - 3070801
 * CMPT 305 - X01L - Milestone 1
 **********************************************************************************************************************/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class DataSet{
    ArrayList<Account> accountList = new ArrayList<>();

    // Array of account objects sorted by assessedValue, filtered by most recently queried filter
    public ArrayList<Account> filteredAccountList = new ArrayList<>();

    public boolean isSorted = false;
    int entries = 0;
    int maxValue = -1;
    int minValue = Integer.MAX_VALUE;
    double mean = 0;
    double median = 0;
    Set<String> wards = new HashSet<>();
    Set<String> uniqueClasses = new HashSet<>();







    DataSet(String filePath){
        readFile(filePath); // Read csv file at filePath, creates Account objects and adds them to the ArrayList
        sortAccounts(); // Sorts Account objects by assessedValue and stores them in sortedAccounts
    }

    DataSet(){

    }

    /***********************************************************************************************
     * Read CSV File at filePath, creates Account objects and adds them to the ArrayList
     * Calls sortAccounts() to sort Account objects by assessedValue and stores them in sortedAccounts
     * @param filePath String absolute path to csv file
     **********************************************************************************************/
    public void readFile(String filePath){

        String line;
        String splitChar = ",";

        try{
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            // Check if first line is a header, if it isn't then create a new entry
            if ((line=br.readLine())!= null && !line.split(splitChar)[0].equals("Account Number")) {
                String[] entry = line.split(splitChar, -1); // Keep any leading empty columns to avoid index errors
                addEntry(entry); // Create Account object and append to array
            }
            // After add every line
            while ((line=br.readLine())!= null){
                // Read Line from file and split data
                String[] entry = line.split(splitChar, -1);
                addEntry(entry); // Create Account object and append to array

            }
        } catch (IOException e) {
            System.out.println("Failed to read file in Dataset.readFile() after " + entries + " lines");
        }
    }


    /******************************
    * Creates a new Account object and adds it to the ArrayList
     * Increments entries by 1
     * @param data String[] of a split line from a csv file
     *             String[] entry = line.split(splitChar, -1);
    * ****************************/

    public void addEntry(String[] data){
        Account newAccount = new Account(data);
        accountList.add(newAccount);
        this.entries++;
        this.isSorted = false;
    }

    public void addEntry(Account account){
        accountList.add(account);
        this.entries++;
        this.isSorted = false;
    }




    /**
     * Sorts given List of Account objects by assessedValue
     * If no List given, sort entire dataSet accountList
     * @param accounts List of Account objects to be sorted
     * @return List of Account objects sorted by assessedValue
     */
    public ArrayList<Account> sortAccounts(ArrayList<Account> accounts){
        Collections.sort(accounts);
        return accounts;
    }

    public void sortAccounts(){
        Collections.sort(this.accountList);
        this.isSorted = true;
    }


    /**
     * Filters Accounts within accountList by given filter interface
     * Parameter filtered by depends on implementation of filter interface
     * Returns an ArrayList of Accounts that match the filter, sorted by assessedValue
     * @param filter AccountFilter interface to be used to filter accountList
     * @return ArrayList of Accounts that match the filter sorted by assessedValue
     */
    public ArrayList<Account> filterAccounts(AccountFilter filter){
        this.filteredAccountList = new ArrayList<>();
        for (Account account : accountList) {
            if (filter.filter(account)) {
                this.filteredAccountList.add(account);
            }
        }

        Collections.sort(this.filteredAccountList);
        return this.filteredAccountList;
    }

    /**************************
     * Calculate the mean of given List of Account objects, Assumes List is already sorted by assessedValue
     * @param accounts List of Account objects sorted by AssessedValue
     * @return the mean assessed value of given List of Account objects
     */

    public double getMean(ArrayList<Account> accounts) {
        if (accounts.isEmpty()){return 0;} // Ensure that sortedAccounts is populated
        mean = 0;
        for (Account account : accounts) {
            mean += account.assessedValue;
        }
        mean /= accounts.size();
        return mean;
    }

    /******************************
     * If no filter given to getMean(), calculate for entire dataset and saves it as this.mean
     * @returns the mean assessedValue of the entire dataset as double
     * *******************************/
    public double getMean() {
        if(!isSorted) {this.sortAccounts();}
        this.mean = getMean(this.accountList);
        return this.mean;
    }


    public int getEntries(){return this.entries;}



    /*************************************************************************
     * Finds median assessedValue for given List of Account objects
     * Returns middle assessedValue of the List if odd size, otherwise average of both sides
     * Assumes List is already sorted
     * @returns  double median of ArrayList<Account>
     ****************************************************************************************> */
    public double getMedian(ArrayList<Account> accounts) {
        if (accounts.isEmpty()){return 0;} // Ensure that sortedAccounts is populated
        if (accounts.size() % 2 == 0) { // If n is even, take the average of both sides of the middle
            median = ((accounts.get(accounts.size() / 2 - 1).assessedValue + accounts.get(accounts.size() / 2).assessedValue) / 2.0);
        } else { // Else take the exact middle value
            median = accounts.get((accounts.size() / 2)).assessedValue;
        }
        return median;
    }
    /**************************************************************************
     * If no variable given to getMedian(), calculates median of entire dataset
     * Saves value in this.median
     * ************************************************************************/
    public double getMedian() {
        if(!isSorted) {this.sortAccounts();}
        this.median = getMedian(this.accountList);
        return this.median;
    }


    /**
     * Returns the highest assessedValue for given List of Account objects
     * If no variable given to getHighestValue(), calculates highest assessedValue of entire dataset
     * Assumes List is already sorted
     * @param accounts list of Account objects sorted by assessedValue
     * @return int highest assessedValue of given List of Account objects
     */
    public int getHighestValue(ArrayList<Account> accounts) {
        maxValue = accounts.get(accounts.size()-1).assessedValue;
        return maxValue;
    }
    public int getHighestValue(){
        if(!isSorted) {this.sortAccounts();}
        this.maxValue = accountList.get(accountList.size()-1).assessedValue;
        return this.maxValue;
    }


    /**
     * Returns the lowest assessedValue for given List of Account objects
     * If no variable given to getLowestValue(), calculates lowest assessedValue of entire dataset
     * Assumes list is already sorted
     * @param accounts list of Account objects sorted by assessedValue
     * @return minValue lowest assessedValue of given List of Account objects
     */
    public int getLowestValue(ArrayList<Account> accounts) {
        minValue = accounts.get(0).assessedValue;
        return minValue;
    }

    public int getLowestValue(){
        if(!isSorted) {this.sortAccounts();}
        this.minValue = getLowestValue(this.accountList);
        return this.minValue;
    }


    /**
     * Adds wards to HashSet, keeping only unique wards
     * @return int number of unique wards in DataSet
     */
    public int countUniqueWards(){
        for (Account account : accountList) {
            this.wards.add(account.getWard());
        }
        return this.wards.size();
    }


    /**
     * Iterates through accounts in DataSet
     * Adds all assessment classes to HashSet, keeping only unique assessment classes
     * @return int number of unique assessment classes in DataSet
     */
    public int countAssessmentClasses() {
        for (Account account : accountList) {
            this.uniqueClasses.addAll(account.getAssessmentClasses().keySet());
        }
        return this.uniqueClasses.size();
    }
}
