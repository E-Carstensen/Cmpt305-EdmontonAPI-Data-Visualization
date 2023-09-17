import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class DataSet{
    ArrayList<Account> accountList = new ArrayList<>();
    int entries = 0;
    int arrayLen;

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
            System.out.println(" ");
            throw new RuntimeException(e);
        }
    }

    // Counts number of entries in csv file
    // Skips header line
    public int countLines(String filePath)  {

        int counter = 0;

        try{
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String line;
            while((line=br.readLine())!=null){
                //if(line.split(",")[0].equals("Account Number")){continue;} //Skip header Line
                counter++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return counter;

    }

    public void addEntry(String[] data){
        Account newAccount = new Account();
        newAccount.assignData(data);
        accountList.add(newAccount);
        entries++;
    }

    public int getHighestValue(){
        int max = -1;
        for (Account account : accountList){
            if (account.assessedValue > max){
                max = account.assessedValue;
            }
        }
        return max;
    }

    public int getLowestValue(){
        int min = Integer.MAX_VALUE;
        for (Account account : accountList){
            if (account.assessedValue < min){
                min = account.assessedValue;
            }
        }
        return min;
    }

    public int countUniqueWards(){
        Set<String> wards = new HashSet<>();
        for (Account account : accountList){
            wards.add(account.ward);
        }

        return wards.size();
    }

    public int countAssessmentClasses() {
        Set<String> uniqueClasses = new HashSet<>();

        for (Account account : accountList) {
            uniqueClasses.addAll((account.assessmentClasses.keySet()));

        }
        return uniqueClasses.size();
    }
}
