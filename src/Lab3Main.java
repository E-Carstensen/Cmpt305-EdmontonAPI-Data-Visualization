/**********************************************************************************************************************
 * Eric Carstensen - 3070801
 * CMPT 305 - X01L - Milestone 1
 **********************************************************************************************************************/

/**
 * Demonstrates ability to filter properties by their Assessment Class
 * Prompts user for a filename for csv file containing property information
 * Reads data into DataSet object
 * Prompts user for an assessment class to filter properties by
 * Then displays statistics about the filtered accounts
 * If no properties match the assessment class, error displayed and ends program
 */
public class Lab3Main {
    public static void main(String[] args) {

        /**
        String filePath =  CSVPropertyAssessmentDAO.getFileName();
        if (filePath.isBlank()){return;}

        DataSet dataSet = new DataSet(filePath);

        dataSet.readFile(filePath);

        CSVPropertyAssessmentDAO.searchByAssessmentClass(dataSet);
*/

        System.out.println(APIPropertyAssessmentDAO.getByAccountId("5146sfd600"));

        APIPropertyAssessmentDAO.getByNeighborhood(" ");
        //System.out.println(APIPropertyAssessmentDAO.getAll());



    }



}
