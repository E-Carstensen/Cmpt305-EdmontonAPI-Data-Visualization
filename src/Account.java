import javax.xml.crypto.Data;
import java.util.HashMap;
import java.util.Map;

public class Account implements Comparable<Account> {
    public String accountNumber, suite, houseNumber, streetName;
    public String neighborhoodId, neighborhood, ward;
    public int assessedValue;
    public double longitude, latitude;
    public boolean garage;
    String point;
    public Address address;

    // Maps assessment class to percentage of that account for this property
    Map<String, Integer> assessmentClasses = new HashMap<>();

    Account(String[] data) {
        assignData(data);
    }
    Account() {

    }


    //Takes a line from csv data set and assigns values to object variables
    public void assignData(String[] data){
        // Order of data points in csv file
        // Account Number,Suite,House Number,Street Name,Garage,Neighbourhood ID,
        // Neighbourhood,Ward,Assessed Value,Latitude,Longitude,Point Location,
        // Assessment Class % 1,Assessment Class % 2,Assessment Class % 3,
        // Assessment Class 1,Assessment Class 2,Assessment Class 3
        setAccountNumber(data[0]);
        setSuite(data[1]);
        setHouseNumber(data[2]);
        setStreetName(data[3]);
        setGarage(data[4]); // Converts Y/N into bool
        setNeighborhoodId(data[5]);
        setNeighborhood(data[6]);
        setWard(data[7]);
        setAssessedValue(data[8]);
        setLatitude(data[9]);
        setLongitude(data[10]);
        setPoint(data[11]);
        setAssessmentClasses(data);

        address = new Address(this);
    }

    // Returns multiline String descriptor of property for printout
    public String toString(){

        return "\nAccount ID: " + this.accountNumber +
                "\nAddress: " + this.address +
                "\nAssessed Value: " + this.assessedValue +
                "\nAssessment Class: " + this.assessmentClasses +
                "\nNeighborhood: " + this.neighborhood +
                " - (" + this.ward + ")" +
                "\nLocation: " + this.point;
    }


    // Sets assessed value - Enforces positive
    // Will convert strings and double to integer
    // If value is negative will not change existing assessedValue
    public void setAssessedValue(Object obj){
        if (obj instanceof Integer value){
            if (value > 0) {
                this.assessedValue = value;
                return;
            }
        }
        if (obj instanceof Double value) {
            if (value > 0) {
                this.assessedValue = value.intValue();
                return;
            }
        }
        if (obj instanceof String value) {
            try {
                int valueInt = Integer.parseInt(value);
                if (valueInt > 0) {
                    this.assessedValue = valueInt;
                    return;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid Assessed Value: " + e);
                throw new RuntimeException(e);
            }
        }
        System.out.println("Invalid Assessed Value: " + obj);

    }

    public void setSuite(String suite){this.suite = suite;}

    // Sets this.accountNumber to String acctNum
    // If input is blank will not change value
    public void setAccountNumber(String acctNum){
        if(!acctNum.isBlank()){this.accountNumber = acctNum;}
    }
    public void setHouseNumber(String houseNumber){if(!houseNumber.isBlank()){this.houseNumber = houseNumber;}}
    public void setStreetName(String streetName){if(!streetName.isBlank()){this.streetName = streetName;}}

    // Takes String
    public void setGarage(String garage){this.garage = garage.equals("Y");}
    public void setNeighborhoodId(String neighborhoodId){if(!neighborhoodId.isBlank()){this.neighborhoodId = neighborhoodId;}}
    public void setNeighborhood(String neighborhood){if(!neighborhood.isBlank()){this.neighborhood = neighborhood;}}
    public void setWard(String ward){if(!ward.isBlank()){this.ward = ward;}}
    public void setPoint(String point){if(!point.isBlank()){this.point = point;}}

    // Takes String[] of split csv line, and assigns values to object variables
    // Attempts to parse percentage into an integer
    public void setAssessmentClasses(String[] assessmentClasses){
        try {
            // Dictionary mapping an Assessment Type to a Percentage
            if(!assessmentClasses[12].isEmpty()){
                this.assessmentClasses.put(assessmentClasses[15], Integer.parseInt(assessmentClasses[12]));
            }
            if (!assessmentClasses[13].isEmpty()) {
                this.assessmentClasses.put(assessmentClasses[16], Integer.parseInt(assessmentClasses[13]));
            }
            if (!assessmentClasses[14].isEmpty()) {
                this.assessmentClasses.put(assessmentClasses[17], Integer.parseInt(assessmentClasses[14]));
            }

        } catch (NumberFormatException e) {
            System.out.println("Invalid Assessment Class Percentage: " + e);
            throw new RuntimeException(e);
        }
    }
    public void setLatitude(String latitude){
        try{
            this.latitude = Double.parseDouble(latitude);
        }catch (NumberFormatException e){
            System.out.println("Invalid latitude: " + latitude);
        }
    }
    public void setLongitude(String longitude){
        try{
            this.longitude = Double.parseDouble(longitude);
        }catch (NumberFormatException e){
            System.out.println("Invalid longitude: " + longitude);
        }
    }

    // Overrides compareTo method to compare two accounts by assessment values
    @Override
    public int compareTo(Account other) {
        return Integer.compare(this.assessedValue, other.assessedValue);
    }


}
