import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu {


    /**
     * Displays various stats about a given List of Accounts
     * If List of Accounts is empty, prints "No Accounts Found..."
     * @param dataSet to call stat functions
     * @param accounts List of Accounts to display stats about
     * */
    public static void displayStats(DataSet dataSet, ArrayList<Account> accounts){
        if (accounts.isEmpty()){System.out.println("No Accounts Found...");return;}


        DecimalFormat dollar = new DecimalFormat("$#,##0");

        System.out.println("Number of Entries: " + accounts.size());
        System.out.println("Highest Value: " + dollar.format(dataSet.getHighestValue(accounts)));
        System.out.println("Lowest Value: " + dollar.format(dataSet.getLowestValue(accounts)));
        System.out.println("Range: " + dollar.format((dataSet.getHighestValue(accounts) - dataSet.getLowestValue(accounts))));
        System.out.println("Mean Value: " + dollar.format(dataSet.getMean(accounts)));
        System.out.println("Median Value: " + dollar.format(dataSet.getMedian(accounts)));
    }


    // Takes one line from the user and returns it as a string - Allows for empty input
    public static String getUserInput(){
        Scanner sc = new Scanner(System.in);
        //sc.close(); // Dont close or will be closed for entire run
        return sc.nextLine();
    }


    /**
     * Prompts user to input local path to a csv file
     * While file does not exist, re-prompts user for file name, if user enters 'n' then quit
     * @return absolute path to csv file as a string
     *         Empty sting if user quits program
     */
    public static String getFileName(){

        System.out.print("CSV Filename: "); // Prompt user to input local csv file name
        String input = getUserInput();

        if (input.isBlank()){input = "test.csv";} // TODO: REMOVE, For easier debugging, if input empty, open test file

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
