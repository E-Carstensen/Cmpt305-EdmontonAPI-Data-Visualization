import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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
                // Read Line from file and split data
                addEntry(line.split(splitChar));
                index++;

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int countLines(String filePath)  {

        int counter = 0;

        try{
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            while(br.readLine()!=null){
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

    public float getHighestValue(){
        float max = 0;
        for (int i = 0; i < arrayLen; i++){
            if (accountList[i].assessedValue > max){
                max = accountList[i].assessedValue;
            }
        }
        return max;
    }

    public float getLowestValue(){
        float min = 0;
        for (int i = 0; i <= arrayLen; i++){
            if (accountList[i].assessedValue > min){
                min = accountList[i].assessedValue;
            }
        }
        return min;
    }

    public int getUniqueWards(){
        int count = 0;
        boolean found = false;
        String[] wards = new String[25];

        for (int i = 0; i <= arrayLen; i++){

            for (int o = 0; o<=25; o++){
                if (accountList[i].ward.equals(wards[o])) {
                    found = true;
                    break;
                } if(wards[o] == null){break;}
            }

            if(!(found)){
                wards[count] = accountList[i].ward;
                count++;
            }
            found = false;
        }

        return count;
    }

}
