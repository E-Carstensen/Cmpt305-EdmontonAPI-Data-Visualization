import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DataSetTest {

    private DataSet dataSet;

    @BeforeEach
    void setUp() {
        dataSet = new DataSet();
    }

    @Test
    void readFile() {
        String filePath = "src/test/resources/test.csv";
        String invalidFilePath = "src/test/resources/invalid.csv";

        dataSet.readFile(filePath);

    }

    @Test
    void addEntry() {
        Account account = new Account();
        dataSet.addEntry(account);
        assertEquals(account, dataSet.accountList.get(0));
    }


    @Test
    void sortAccounts() {
        Account a1 = new Account();
        Account a2 = new Account();
        Account a3 = new Account();

        a1.setAssessedValue(1);
        a2.setAssessedValue(2);
        a3.setAssessedValue(3);

        // Insert properties into dataset Out of order
        dataSet.accountList.add(a3);
        dataSet.accountList.add(a1);
        dataSet.accountList.add(a2);

        // Create a list of Account objects in order to compare to
        ArrayList<Account> a = new ArrayList<>();
        a.add(a1);
        a.add(a2);
        a.add(a3);

        // Ensure that the list of Account objects is not in order to begin with
        assertNotEquals(a, dataSet.accountList);
        // Sort default dataSet accountList
        dataSet.sortAccounts();
        // Ensure that the list of Account objects is now in order
        assertEquals(a, dataSet.accountList);


    }

    @Test
    void testSortAccounts() {
    }

    @Test
    void getMean() {
        Account a1 = new Account();
        Account a2 = new Account();

        a1.setAssessedValue(0);
        a2.setAssessedValue(10);

        dataSet.accountList.add(a1);
        dataSet.accountList.add(a2);

        assertEquals(5, dataSet.getMean());

    }


    @Test
    void getEntries() {
        assertEquals(0, dataSet.getEntries());
        dataSet.addEntry(new Account());
        assertEquals(1, dataSet.getEntries());
    }

    @Test
    void getMedian() {
        Account a1 = new Account();
        Account a2 = new Account();

        a1.setAssessedValue(0);
        a2.setAssessedValue(10);

        dataSet.accountList.add(a1);
        dataSet.accountList.add(a2);

        assertEquals(5, dataSet.getMedian());

        Account a3 = new Account();
        a3.setAssessedValue(3);
        dataSet.addEntry(a3);

        assertEquals(3, dataSet.getMedian());

        Account a4 = new Account();
        a4.setAssessedValue(5);
        dataSet.addEntry(a4);

        assertEquals(4, dataSet.getMedian());
    }


    @Test
    void getHighestValue() {
        assertEquals(0, dataSet.getHighestValue());
        Account a1 = new Account();
        a1.setAssessedValue(0);
        dataSet.addEntry(a1);
        assertEquals(0, dataSet.getHighestValue());

        Account a2 = new Account();
        a2.setAssessedValue(10);
        dataSet.addEntry(a2);
        assertEquals(10, dataSet.getHighestValue());
    }

    @Test
    void getLowestValue() {
        Account a1 = new Account();
        a1.setAssessedValue(0);
        dataSet.addEntry(a1);

        assertEquals(0, dataSet.getLowestValue());

        Account a2 = new Account();
        a2.setAssessedValue(10);
        dataSet.addEntry(a2);

        assertEquals(0, dataSet.getLowestValue());
    }

    @Test
    void countUniqueWards() {
        assertEquals(0, dataSet.countUniqueWards());
        Account a1 = new Account();
        a1.setWard("Ward 1");
        dataSet.addEntry(a1);
        assertEquals(1, dataSet.countUniqueWards());

        Account a2 = new Account();
        a2.setWard("Ward 2");
        dataSet.addEntry(a2);
        assertEquals(2, dataSet.countUniqueWards());
    }

    @Test
    void countAssessmentClasses() {
    }
}