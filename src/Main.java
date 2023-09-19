import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {

    public static void main(String[] args){

        String filePath =  getFileName();
        DataSet dataSet = new DataSet(filePath);

        displayStats(dataSet);
    }


    public static void displayStats(DataSet dataSet){
        System.out.println("Number of Entries: " + dataSet.entries);
        System.out.println("Highest Value: " + dataSet.getHighestValue());
        System.out.println("Lowest Value: " + dataSet.getLowestValue());
        System.out.println("Number of Unique Wards: " + dataSet.countUniqueWards());
        System.out.println("Number of Assessment Classes: " + dataSet.countAssessmentClasses());
    }

    public static String getFileName(){
        System.out.print("CSV Filename: ");

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        Path path = Paths.get("src", input);

        if(!Files.exists(path)){
            System.out.println("File Not Found... Exiting");
            return "";
        }

        return path.toAbsolutePath().toString();
    }

}
