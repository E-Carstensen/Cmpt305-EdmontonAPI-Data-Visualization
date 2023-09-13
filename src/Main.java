import java.io.*;

public class Main {

    public static void main(String[] args){

        String path = "/home/eric/IdeaProjects/CMPT305Lab1/src/data.csv";
        readFile(path);

    }

    public static void readFile(String filePath){

        String line;
        String splitChar = ",";

        try{

            // Init counter and array for Accounts
            int index = 0;
            int max = countLines(filePath);
            Account[] accountList = new Account[max];

            BufferedReader br = new BufferedReader(new FileReader(filePath));

            while ((line=br.readLine())!= null & index < max){
                // Read Line from file and split data
                String[] data = line.split(splitChar);

                // Init Account object and assign data
                accountList[index] = new Account();
                accountList[index].assignData(data);

                //System.out.println("Account #: " + accountList[index].AccountNumber);
                index++;

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static int countLines(String filePath)  {

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
}


