import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){

        String path = "/home/eric/IdeaProjects/CMPT305Lab1/src/data.csv";
        readFile(path);

    }

    public static void readFile(String filePath){

        String line = "";
        String splt = ",";

        try{

            // Init counter and array for Accounts
            int temp = 0;
            Account[] accountList = new Account[100];
            BufferedReader br = new BufferedReader(new FileReader(filePath));

            while ((line=br.readLine())!= null & temp < 100){
                // Read Line from file and split data
                String[] data = line.split(splt);

                // Init Account object and assign data
                accountList[temp] = new Account();
                accountList[temp].assignData(data);

                //System.out.println("Account #: " + accountList[temp].AccountNumber);

                temp++;

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}


