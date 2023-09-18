import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args){

        Path path = Paths.get("src", "test.csv");

        if(!Files.exists(path)){
            System.out.println("File Not Found... Exiting");
            return;
        }

        String filePath = path.toAbsolutePath().toString();
        DataSet dataSet = new DataSet(filePath);

        System.out.println("Number of Entries: " + dataSet.entries);
        System.out.println("Highest Value: " + dataSet.getHighestValue());
        System.out.println("Lowest Value: " + dataSet.getLowestValue());
        System.out.println("Number of Unique Wards: " + dataSet.countUniqueWards());
        System.out.println("Number of Assessment Classes: " + dataSet.countAssessmentClasses());

    }


}
