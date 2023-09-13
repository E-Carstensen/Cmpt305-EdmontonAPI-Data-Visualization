import java.io.*;

public class Main {

    public static void main(String[] args){

        String path = "/home/eric/IdeaProjects/CMPT305Lab1/src/data.csv";
        DataSet dataSet = readFile(path);



    }

    public static DataSet readFile(String filePath){

        String line;
        String splitChar = ",";

        // Init counter and array for Accounts
        int index = 0;
        int max = countLines(filePath);
        DataSet dataSet = new DataSet(max);

        try{

            BufferedReader br = new BufferedReader(new FileReader(filePath));

            while ((line=br.readLine())!= null & index < max){
                // Read Line from file and split data
                dataSet.addEntry(line.split(splitChar));
                index++;

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return dataSet;
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


