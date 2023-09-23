import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class DataSet{
    ArrayList<Account> accountList = new ArrayList<>();

    // Array of account objects sorted by assessedValue, filtered by most recently queried filter
    public ArrayList<Account> filteredAccountList = new ArrayList<>();


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
    }

    public void addEntry(Account account){
        accountList.add(account);
        this.entries++;
    }



    public void sortAccounts(){
        Collections.sort(this.accountList);
    }


    public ArrayList<Account> sortAccounts(AccountFilter filter){
        this.filteredAccountList = new ArrayList<>();
        for (Account account : accountList) {
            if (filter.filter(account)) {
                this.filteredAccountList.add(account);
            }
        }

        Collections.sort(this.filteredAccountList);
        return this.filteredAccountList;
    }


    /******************************
    * If no filter given to getMean(), calculate for entire dataset and saves it as this.mean
     * @ returns the mean assessedValue of the entire dataset as double
     * *******************************/
    public double getMean() {
        this.mean = getMean(this.accountList);
        return this.mean;
    }

    public double getMean(ArrayList<Account> accounts) {
        if (accounts.isEmpty()){return 0;} // Ensure that sortedAccounts is populated
        mean = 0;
        for (Account account : accounts) {
            mean += account.assessedValue;
        }
        mean /= accounts.size();
        return mean;
    }

    public int getEntries(){return this.entries;}

    /**************************************************************************
     * If no variable given to getMedian(), calculates median of entire dataset
     * Saves value in this.median
     * ************************************************************************/
    public double getMedian() {
        this.median = getMedian(this.accountList);
        return this.median;
    }

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
            median = accounts.get(accounts.size() / 2).assessedValue;
        }
        return median;
    }

    public int getHighestValue(){
        this.maxValue = accountList.get(accountList.size()-1).assessedValue;
        return this.maxValue;
    }
    public int getHighestValue(ArrayList<Account> accounts) {
        maxValue = accounts.get(accounts.size()-1).assessedValue;
        return maxValue;
    }

    public int getLowestValue(){
        this.minValue = accountList.get(0).assessedValue;
        return this.minValue;
    }
    public int getLowestValue(ArrayList<Account> accounts) {
        minValue = accounts.get(0).assessedValue;
        return minValue;

    }

    public int countUniqueWards(){return this.wards.size();}

    public int countAssessmentClasses() {
        return this.uniqueClasses.size();
    }
}
