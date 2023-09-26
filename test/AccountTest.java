import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    private Account a1;
    private Account a2;

    @BeforeEach
    void setUp() {
        this.a1 = new Account();
        this.a2 = new Account();
    }

    @Test
    void assignData() {
        String[] data = "1034339,,9420,92 STREET NW,N,6710,STRATHEARN,Métis Ward,47030000,53.53094866121484,-113.46927857387664,POINT (-113.46927857387664 53.53094866121484),100,,,OTHER RESIDENTIAL,,".split(",", -1);
        this.a1.assignData(data);
        assertEquals("1034339", this.a1.getAccountNumber());
        assertEquals(100, this.a1.getAssessmentClasses().get("OTHER RESIDENTIAL"));

        String[] incompleteData = "1034339,,9420,92 ST".split(",",-1);
        this.a2.assignData(incompleteData);
        assertEquals("1034339", this.a2.getAccountNumber());
        assertEquals("92 ST", this.a2.getStreetName());
        assertEquals(0, this.a2.getAssessmentClasses().size());

    }

    @Test
    void setAssessedValue() {
        this.a1.setAssessedValue(100);
        assertEquals(100, this.a1.assessedValue);

        this.a1.setAssessedValue(-1); // negative value will not change the value
        assertEquals(100, this.a1.assessedValue);

        double value = 123.456;
        this.a1.setAssessedValue(value);
        assertEquals((int) value, this.a1.assessedValue);

        this.a2.setAssessedValue("123");
        assertEquals(123, this.a2.assessedValue);

        this.a2.setAssessedValue("Not an Integer");
        assertEquals(123, this.a2.assessedValue);

    }

    @Test
    void setSuite() {
        this.a1.setSuite("Suite 1");
        assertEquals("Suite 1", this.a1.suite);
    }

    @Test
    void setAccountNumber() {
        this.a1.setAccountNumber("1034339");
        assertEquals("1034339", this.a1.accountNumber);
    }

    @Test
    void setHouseNumber() {
        this.a1.setHouseNumber("9420");
        assertEquals("9420", this.a1.houseNumber);
    }

    @Test
    void setStreetName() {
        this.a1.setStreetName("92 STREET NW");
        assertEquals("92 STREET NW", this.a1.streetName);
    }

    @Test
    void setGarage() {
        this.a1.setGarage("Y");
        assertTrue(this.a1.garage);

        this.a1.setGarage("N");
        assertFalse(this.a1.garage);

        this.a1.setGarage("Anything other than 'Y'");
        assertFalse(this.a1.garage);
    }

    @Test
    void setNeighborhoodId() {
        this.a1.setNeighborhoodId("6710");
        assertEquals("6710", this.a1.neighborhoodId);
    }

    @Test
    void setNeighborhood() {
        this.a1.setNeighborhood("Neighborhood 1");
        assertEquals("Neighborhood 1", this.a1.neighborhood);
    }

    @Test
    void setWard() {
        this.a1.setWard("Ward 1");
        assertEquals("Ward 1", this.a1.ward);
    }

    @Test
    void setPoint() {
        this.a1.setPoint("POINT (123)");
        assertEquals("POINT (123)", this.a1.point);
    }

    @Test
    void setAssessmentClasses() {
        String[] incompleteClasses = {"100", "","","RESIDENTIAL","",""};
        this.a1.setAssessmentClasses(incompleteClasses);

        assertEquals(100, this.a1.getAssessmentClasses().get("RESIDENTIAL"));

        String[] classes = {"50","25","25", "RES","COM", "IND"};
        this.a2.setAssessmentClasses(classes);

        assertEquals(50, this.a2.getAssessmentClasses().get("RES"));
        assertEquals(25, this.a2.getAssessmentClasses().get("COM"));
        assertEquals(25, this.a2.getAssessmentClasses().get("IND"));


    }

    @Test
    void setLatitude() {
        this.a1.setLatitude(53.53094866121484);
        assertEquals(53.53094866121484, this.a1.latitude);

        this.a2.setLatitude("123.456");
        assertEquals(123.456, this.a2.latitude);

    }

    @Test
    void setLongitude() {
        this.a1.setLongitude(-113.46927857387664);
        assertEquals(-113.46927857387664, this.a1.longitude);

        this.a2.setLongitude("123.456");
        assertEquals(123.456, this.a2.longitude);
    }

    @Test
    void getWard() {
        this.a1.setWard("Ward 1");
        assertEquals("Ward 1", this.a1.getWard());
    }

    @Test
    void getNeighborhood() {
        this.a1.setNeighborhood("Neighborhood 1");
        assertEquals("Neighborhood 1", this.a1.getNeighborhood());
    }

    @Test
    void getNeighborhoodId() {
        this.a1.setNeighborhoodId("6710");
        assertEquals("6710", this.a1.getNeighborhoodId());
    }

    @Test
    void getStreetName() {
        this.a1.setStreetName("92 STREET NW");
        assertEquals("92 STREET NW", this.a1.getStreetName());
    }

    @Test
    void getHouseNumber() {
        this.a1.setHouseNumber("9420");
        assertEquals("9420", this.a1.getHouseNumber());
    }

    @Test
    void getSuite() {
        this.a1.setSuite("Suite 1");
        assertEquals("Suite 1", this.a1.getSuite());
    }

    @Test
    void getAccountNumber() {
        this.a1.setAccountNumber("1034339");
        assertEquals("1034339", this.a1.getAccountNumber());

    }

    @Test
    void getAssessedValue() {
        this.a1.setAssessedValue(100);
        assertEquals(100, this.a1.getAssessedValue());
    }

    @Test
    void getLatitude() {
        this.a1.setLatitude(53.53094866121484);
        assertEquals(53.53094866121484, this.a1.getLatitude());
    }

    @Test
    void getLongitude() {
        this.a1.setLongitude(-113.46927857387664);
        assertEquals(-113.46927857387664, this.a1.getLongitude());
    }

    @Test
    void compareTo() {
        this.a1.setAssessedValue(100);
        this.a2.setAssessedValue(100);
        assertEquals(0, this.a1.compareTo(this.a2));

        this.a1.setAssessedValue(101);
        assertEquals(-1, this.a1.compareTo(this.a2));

        this.a2.setAssessedValue(102);
        assertEquals(1, this.a1.compareTo(this.a2));
    }

    @Test
    void testToString() {
        this.a1.assignData("1034339,,9420,92 ST".split(","));
        System.out.println(this.a1.toString());
        assertEquals("\nAccount ID:        1034339\nAddress:          null\nAssessed Value:   0\nAssessment Class: {}\nNeighborhood:     null - (null)\nLocation:         null", this.a1.toString());
    }
}