/**********************************************************************************************************************
 * Eric Carstensen - 3070801
 * CMPT 305 - X01L - Milestone 1
 **********************************************************************************************************************/

/**
 * Main class for Lab 2
 * Takes path to a csv file, creates a DataSet object, and displays various information using the Menu class
 */
public class Lab2Main {

    public static void main(String[] args){

        String filePath =  CSVPropertyAssessmentDAO.getFileName();
        if (filePath.isBlank()){return;}

        DataSet dataSet = new DataSet(filePath);

        CSVPropertyAssessmentDAO.displayStats(dataSet, dataSet.accountList);

        CSVPropertyAssessmentDAO.searchByAccount(dataSet);

        CSVPropertyAssessmentDAO.searchByNeighborhood(dataSet);

    }



    /**
     * Prompts user to input an account number and searches for a property with matching accountNumber
     * If match is found, prints account info using Account.toString() override
     * If no match is found, prints "Account Not Found..."
     * @param dataSet to search for accounts within
     */
    public static void searchByAccount(DataSet dataSet){
        CSVPropertyAssessmentDAO.searchByAccount(dataSet);

        CSVPropertyAssessmentDAO.searchByNeighborhood(dataSet);
        System.out.print("Find property by account number: ");
        String accountId = CSVPropertyAssessmentDAO.getUserInput();

    }




}
