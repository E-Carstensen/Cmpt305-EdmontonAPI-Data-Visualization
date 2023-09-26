import java.util.ArrayList;

public class Lab3Main {
    public static void main(String[] args) {
        String filePath =  Menu.getFileName();
        if (filePath.isBlank()){return;}

        DataSet dataSet = new DataSet(filePath);

        dataSet.readFile(filePath);

        searchByAssessmentClass(dataSet);




    }


    public static void searchByAssessmentClass(DataSet dataSet){

        String assessmentClass = Menu.getUserInput();

        AssessmentClassFilter filter = new AssessmentClassFilter(assessmentClass);

        ArrayList<Account> filteredAccounts = dataSet.filterAccounts(filter);

        Menu.displayStats(dataSet, filteredAccounts);



    }




}
