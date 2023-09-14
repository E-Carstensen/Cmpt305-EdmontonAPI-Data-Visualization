
public class Main {

    public static void main(String[] args){

        String filePath = "/home/eric/IdeaProjects/CMPT305Lab1/src/data.csv";
        DataSet dataSet = new DataSet(filePath);

        System.out.println("Number of Entries: " + dataSet.arrayLen);
        System.out.println("Highest Value: " + dataSet.getHighestValue());
        System.out.println("Lowest Value: " + dataSet.getLowestValue());
        System.out.println("Number of Unique Wards: " + dataSet.getNumberUniqueWards());
        System.out.println("Number of Assessment Classes: " + dataSet.getNumberAssessmentClasses());

    }
}
