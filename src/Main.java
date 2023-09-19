import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {

    public static void main(String[] args){

        String filePath =  getFileName();
        if (filePath.isBlank()){return;}

        DataSet dataSet = new DataSet(filePath);
        displayStats(dataSet);




    }


    public static void displayStats(DataSet dataSet){
        if(dataSet.max == -1){dataSet.getStats();} // If stats have not yet been calculated

        System.out.println("Number of Entries: " + dataSet.entries);
        System.out.println("Highest Value: " + dataSet.max);
        System.out.println("Lowest Value: " + dataSet.min);
        System.out.println("Range: " + (dataSet.max - dataSet.min));
        System.out.println("Mean Value: " + dataSet.mean);
        System.out.println("Median Value: " + dataSet.median);
    }

    public static String getUserInput(){
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        sc.close();
        return input;
    }


    public static String getFileName(){

        System.out.print("CSV Filename: "); // Prompt user to input local csv file name
        String input = getUserInput();

        if (input.isBlank()){input = "test.csv";} // For easier debugging, remove in future

        Path path = Paths.get("src", input);
        if(!Files.exists(path)){ // Ensure file exists
            System.out.println("File Not Found... Exiting");
            return "";
        }
        return path.toAbsolutePath().toString();
    }

}
