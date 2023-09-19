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
    public Account[] sortedAccounts;





    DataSet(String filePath){ // Constructor
         // init Array
        readFile(filePath); // Read lines into array
    }

    public void readFile(String filePath){

        String line;
        String splitChar = ",";

        try{
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            while ((line=br.readLine())!= null){
                if(line.equals("Account Number,Suite,House Number,Street Name,Garage,Neighbourhood ID,Neighbourhood,Ward,Assessed Value,Latitude,Longitude,Point Location,Assessment Class % 1,Assessment Class % 2,Assessment Class % 3,Assessment Class 1,Assessment Class 2,Assessment Class 3")){
                    continue;
                }

                // Read Line from file and split data
                String[] entry = line.split(splitChar, -1);
                addEntry(entry); // Create Account object and append to array

            }
        } catch (IOException e) {
            System.out.println("Failed to read file in Dataset.readFile() after " + entries + " lines");
        }
    }


    public void addEntry(String[] data){
        Account newAccount = new Account();
        newAccount.assignData(data);
        accountList.add(newAccount);
        this.entries++;
    }

    public void getStats(){
        int n = 0;
        double total = 0;
        this.sortedAccounts = new Account[this.entries];
       // int[] values = new int[this.entries]; // Array for all assessed values to find median
        for (Account account : accountList) {
            //values[n] = account.assessedValue;
            sortedAccounts[n] = account;
            n++;
            // Check for and save min and max assessed values
            if (account.assessedValue > this.maxValue) {this.maxValue = account.assessedValue;}
            if (account.assessedValue < this.minValue) {this.minValue = account.assessedValue;}

            total = (total + account.assessedValue);
            // Keep track of unique wards and assessment classes
            this.wards.add(account.ward);
            this.uniqueClasses.addAll((account.assessmentClasses.keySet()));

        }

        // Sort assessment values
        //Arrays.sort(values);
        Arrays.sort(sortedAccounts);

        // Calculate the median
        if (n % 2 == 0) { // In n is even, take average of both sides of middle
            this.median = ((sortedAccounts[n / 2 - 1].assessedValue + sortedAccounts[n / 2].assessedValue) / 2.0);
        } else { // Else take exact middle
            this.median = sortedAccounts[n / 2].assessedValue;
        }
        this.mean = (total/(n));
    }

    public int getHighestValue(){
        return this.maxValue;
    }

    public int getLowestValue(){
        return this.minValue;
    }

    public int countUniqueWards(){return this.wards.size();}

    public int countAssessmentClasses() {
        return this.uniqueClasses.size();
    }
}
