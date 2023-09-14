import javax.security.auth.login.AccountLockedException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class DataSet{
    Account[] accountList;
    int entries = 0;
    int arrayLen;

    DataSet(String filePath){
        arrayLen = countLines(filePath);
        accountList = new Account[arrayLen];
        readFile(filePath);
    }

    public void readFile(String filePath){

        String line;
        String splitChar = ",";

        // Init counter and array for Accounts
        int index = 0;
        int max = arrayLen;

        try{

            BufferedReader br = new BufferedReader(new FileReader(filePath));

            while ((line=br.readLine())!= null & index < max){
                if (line.equals(("Account Number,Suite,House Number,Street Name,Garage,Neighbourhood ID,Neighbourhood,Ward,Assessed Value,Latitude,Longitude,Point Location,Assessment Class % 1,Assessment Class % 2,Assessment Class % 3,Assessment Class 1,Assessment Class 2,Assessment Class 3"))){

                }


                // Read Line from file and split data
                String[] entry = line.split(splitChar);

                if (entry[0].equals("Account Number")){continue;} // Skips Header

                addEntry(entry); // Create Account object and append to array
                index++;

            }
        } catch (IOException e) {
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
                if(line.split(",")[0].equals("Account Number")){continue;} //Skip header Line
                counter++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return counter;

    }

    public void addEntry(String[] data){
        accountList[entries] = new Account();
        accountList[entries].assignData(data);
        entries++;
    }

    public int getHighestValue(){
        int max = 0;
        for (int i = 0; i < arrayLen; i++){
            if (accountList[i].assessedValue > max){
                max = accountList[i].assessedValue;
            }
        }
        return max;
    }

    public int getLowestValue(){
        int min = 2147483646;
        for (int i = 0; i < arrayLen; i++){
            if (accountList[i].assessedValue < min){
                min = accountList[i].assessedValue;
            }
        }
        return min;
    }

    public int getNumberUniqueWards(){
        int count = 0;

        List<String> wards = new ArrayList<>();

        for (int i = 0; i < arrayLen; i++){
            if(!(wards.contains(accountList[i].ward))){
                wards.add(accountList[i].ward);
                count++;
            }

        }

        return count;
    }

    public int getNumberAssessmentClasses(){
        List<String> classes = new ArrayList<>();

        for (int i = 0; i < arrayLen; i++){
            if(!(classes.contains(accountList[i].class1)) & accountList[i].class1 != null){
                classes.add(accountList[i].class1);
            }
            if(!(classes.contains(accountList[i].class2)) & accountList[i].class2 != null){
                classes.add(accountList[i].class2);
            }
            if(!(classes.contains(accountList[i].class2)) & accountList[i].class3 != null){
                classes.add(accountList[i].class2);
            }
        }
        return classes.size();

    }

}
