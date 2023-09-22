import javax.xml.crypto.Data;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class DataSet{
    ArrayList<Account> accountList = new ArrayList<>();
    int entries = 0;
    int maxValue = -1;
    int minValue = Integer.MAX_VALUE;
    double mean = 0;
    double median = 0;
    Set<String> wards = new HashSet<>();
    Set<String> uniqueClasses = new HashSet<>();

    // Array of account objects sorted by assessedValue, used for min, max, mean, median
    public Account[] sortedAccounts;





    DataSet(String filePath){
        readFile(filePath); // Read csv file at filePath, creates Account objects and adds them to the ArrayList
        setSortedAccounts(); // Sorts Account objects by assessedValue and stores them in sortedAccounts
    }

    DataSet(){

    }

    // Reads data from file and creates Account objects and adds them to the ArrayList
    // @param filePath String path to file csv file
    public void readFile(String filePath){

        String line;
        String splitChar = ",";

        try{
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            // Check if first line is a header, if it isn't then add it
            if ((line=br.readLine())!= null && !line.split(splitChar)[0].equals("Account Number")) {
                String[] entry = line.split(splitChar);
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

    // Takes a String[] of a split line from a csv file and creates an Account object and adds it to the ArrayList
    // Increments entries by 1

    public void addEntry(String[] data){
        Account newAccount = new Account(data);
        accountList.add(newAccount);
        this.entries++;
    }

    public void setSortedAccounts(){
        this.sortedAccounts = new Account[this.entries];
        for (int i = 0; i < this.entries; i++) {
            this.sortedAccounts[i] = accountList.get(i);
        }
        Arrays.sort(this.sortedAccounts);
    }


    public double getMean() {
        if (sortedAccounts.length == 0){return 0;} // Ensure that sortedAccounts is populated
        this.mean = 0;
        for (Account account : sortedAccounts) {
            this.mean += account.assessedValue;
        }
        this.mean /= sortedAccounts.length;
        return this.mean;
    }

    // Finds the median assessedValue from sortedAccounts array
    // @returns the exact middle of the array if odd, or the average of both sides if even
    // @returns 0 if array is empty
    public double getMedian() {
        if (sortedAccounts.length == 0){return 0;} // Ensure that sortedAccounts is populated
        if (sortedAccounts.length % 2 == 0) { // In n is even, take average of both sides of middle
            this.median = ((sortedAccounts[sortedAccounts.length / 2 - 1].assessedValue + sortedAccounts[sortedAccounts.length / 2].assessedValue) / 2.0);
        } else { // Else take exact middle
            this.median = sortedAccounts[sortedAccounts.length / 2].assessedValue;
        }
        return this.median;
    }

    public int getHighestValue(){
        this.maxValue = sortedAccounts[sortedAccounts.length-1].assessedValue;
        return this.maxValue;
    }

    public int getLowestValue(){
        this.minValue = sortedAccounts[0].assessedValue;
        return this.minValue;
    }

    public int countUniqueWards(){return this.wards.size();}

    public int countAssessmentClasses() {
        return this.uniqueClasses.size();
    }
}
