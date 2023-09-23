import javax.xml.crypto.Data;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.Scanner;

public class Main {

    public static void main(String[] args){

        String filePath =  getFileName();
        if (filePath.isBlank()){return;}

        DataSet dataSet = new DataSet(filePath);


        displayDataSetStats(dataSet);

        displayAccountStats(dataSet);

        displayNeighborhoodStats(dataSet);



    }


    public static void displayDataSetStats(DataSet dataSet){

        DecimalFormat dollar = new DecimalFormat("$#,##0");

        System.out.println("Number of Entries: " + dataSet.entries);
        System.out.println("Highest Value: " + dollar.format(dataSet.getHighestValue()));
        System.out.println("Lowest Value: " + dollar.format(dataSet.getLowestValue()));
        System.out.println("Range: " + dollar.format((dataSet.maxValue - dataSet.minValue)));
        System.out.println("Mean Value: " + dollar.format(dataSet.getMean()));
        System.out.println("Median Value: " + dollar.format(dataSet.getMedian()));
    }


    public static void displayAccountStats(DataSet dataSet){

        System.out.print("Find property by account number: ");
        String accountId = getUserInput();

        for (Account account : dataSet.accountList){
            if (account.accountNumber.equals(accountId)){
                System.out.println(account);
                return;
            }
        }
        System.out.println("Account Not Found...");
    }

    public static void displayNeighborhoodStats(DataSet dataSet){
        System.out.print("Neighborhood: ");
        String neighborhood = getUserInput();

        NeighborhoodFilter neighborhoodFilter = new NeighborhoodFilter(neighborhood);
        dataSet.sortAccounts(neighborhoodFilter);

        DecimalFormat dollar = new DecimalFormat("$#,##0");


        System.out.println("Highest Value: " + dollar.format(dataSet.getHighestValue(dataSet.filteredAccountList)));




    }

    // Takes one line from the user and returns it as a string
    public static String getUserInput(){
        Scanner sc = new Scanner(System.in);
        //sc.close();
        return sc.nextLine();
    }


    public static String getFileName(){

        System.out.print("CSV Filename: "); // Prompt user to input local csv file name
        String input = getUserInput();

        if (input.isBlank()){input = "test.csv";} // TODO REMOVE For easier debugging, remove in future

        Path path = Paths.get("src", input);
        while(!Files.exists(path)){ // While file does not exist
            System.out.println("File Not Found");
            System.out.print("CSV Filename ('n' to quit): "); // Prompt user to input local csv file name again
            input = getUserInput();
            if (input.equals("n")){return "";} // Allow user to quit program

        }
        return path.toAbsolutePath().toString(); // Return absolute path to file as a string
    }

}
