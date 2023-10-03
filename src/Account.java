import java.util.*;

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
        try {
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
        }catch(Exception e){
            System.out.println("Invalid CSV formatting: " + e);
        }
    }



    /*******************************************************
     * Begin Setters
     ********************************************************/


    // Sets assessed value - Enforces positive
    // Will convert strings and double to integer, if invalid will not change existing value and display error
    // If input is negative will not change existing assessedValue and return
    // @param new value for this.assessed value as integer, string or double
    public void setAssessedValue(Object obj){
        if (obj instanceof String value) {
            try {
                int valueInt = Integer.parseInt(value);
                if (valueInt > 0) {
                    this.assessedValue = valueInt;
                    return;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid Assessed Value: " + e);
            }
        }
        if (obj instanceof Integer value){
            if (value > 0) {
                this.assessedValue = value;
                return;
            }
        }
        if (obj instanceof Double value) {
            if (value > 0) {
                this.assessedValue = value.intValue();
            }
        }


    }

    public void setSuite(String suite){this.suite = suite;}

    // Sets this.accountNumber to String acctNum
    // If input is blank will not change value
    public void setAccountNumber(String acctNum){
        if(!acctNum.isBlank()){this.accountNumber = acctNum;}
    }
    public void setHouseNumber(String houseNumber){this.houseNumber = houseNumber;}
    public void setStreetName(String streetName){this.streetName = streetName;}

    // Takes String column from csv and converts string "Y/N" to boolean
    // Y == true; Any other value is false
    public void setGarage(String garage){this.garage = garage.equals("Y");}
    public void setNeighborhoodId(String neighborhoodId){this.neighborhoodId = neighborhoodId;}
    public void setNeighborhood(String neighborhood){this.neighborhood = neighborhood;}
    public void setWard(String ward){this.ward = ward;}
    public void setPoint(String point){this.point = point;}

     /************************************
     * Takes String[] of split csv line and assigns a map of assessment classes to percentage
     * Not all properties have multiple assessment classes, so we need to check if the column contains a percentage
     * Some classes are repeated, if so we sum the percentage
     * Attempts to parse String percentage into an integer
     * Will display error message if percentage is not an integer and return
     * @param assessmentClasses String[] of split csv line
     */
    public void setAssessmentClasses(String[] assessmentClasses){

        List<String> classes = new ArrayList<>();
        List<String> percentages = new ArrayList<>();

        if (assessmentClasses.length == 18){ // If input is a full line from the csv file
            classes = List.of(assessmentClasses[15], assessmentClasses[16], assessmentClasses[17]);
            percentages = List.of(assessmentClasses[12], assessmentClasses[13], assessmentClasses[14]);
        }else {
            // Array of classes and percentages pairs of some length
            for (String assessmentClass : assessmentClasses) {
                if (assessmentClass.matches("[0-9]+")) {
                    percentages.add(assessmentClass);
                } else if (!assessmentClass.isEmpty()) {
                    classes.add(assessmentClass);
                }
            }
        }

        if (percentages.size()!= classes.size()) { // Must be equal number of classes and percentages
        System.out.println("Number of classes and percentages do not match");
        return;}

        this.assessmentClasses = parseAssessmentClasses(classes, percentages);

    }

    /**
     * Takes a list of classes and percentages or equal size and returns a map of class to percentage
     * Parses percentages into integers, will display error message if percentage is not an integer and return map at time
     * @param classes List of each assessed class
     * @param percentages List of each assessed class's percentage in order respectively
     * @return Map of AssessmentClass to percentage as integer
     */
    private Map<String, Integer> parseAssessmentClasses(List<String>classes, List<String>percentages){
        Map<String, Integer> assessmentClasses = new HashMap<>();
        try {

            for (int i = 0; i < classes.size(); i++) {
                if (!(percentages.get(i).isEmpty())) { // If column contained a percentage
                    int newValue = Integer.parseInt(percentages.get(i)); // Attempt to parse percentage to integer
                    // If class is already in map, add new value to existing value, else add new value to map
                    assessmentClasses.put(classes.get(i), assessmentClasses.getOrDefault(classes.get(i), 0) + newValue);
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid Assessment Class Percentage: " + percentages);
        }

        return assessmentClasses;
    }

    /********************************************
    // Sets this.latitude and this.longitude to doubles
    // Allows input to be doubles or strings
    // If input is blank will not change value
    // If input is invalid will not change value and will display error message
    // @param newLatitude double or string
     ******************************************/
    public void setLatitude(Object obj){
        if(obj instanceof Double newLatitude){
            this.latitude = newLatitude;
            return;
        }
        if (obj instanceof String newLatitude) {
            try{
                this.latitude = Double.parseDouble(newLatitude);

            }catch (NumberFormatException e){
                System.out.println("Invalid latitude: " + newLatitude);

            }
        }


    }
    public void setLongitude(Object obj){
        if(obj instanceof Double newLongitude){
            this.latitude = newLongitude;
            return;
        }
        if (obj instanceof String newLongitude) {
            try {
                this.longitude = Double.parseDouble(newLongitude);
            } catch (NumberFormatException e) {
                System.out.println("Invalid longitude: " + longitude);
            }
        }
    }

    /*****************************
     * End Setters - Begin Getters
     ******************************/

    public String getWard(){return this.ward;}
    public String getNeighborhood(){return this.neighborhood;}
    public String getNeighborhoodId(){return this.neighborhoodId;}
    public String getStreetName(){return this.streetName;}
    public String getHouseNumber(){return this.houseNumber;}
    public String getSuite(){return this.suite;}
    public String getAccountNumber(){return this.accountNumber;}
    public int getAssessedValue(){return this.assessedValue;}
    public double getLatitude(){return this.latitude;}
    public double getLongitude(){return this.longitude;}

    public Map<String, Integer> getAssessmentClasses(){return this.assessmentClasses;}


    /************************************************************************************************
     * Overrides Comparable to compare assessed value of two accounts
     * @param other Account object to be compared.
     * @return a negative int if this < other, 0 if this == other, a positive int if this > other.
     *******************************************************************************************/
    @Override
    public int compareTo(Account other) {
        return Integer.compare(this.assessedValue, other.assessedValue);
    }

   /*****************************************************************************************
    * Overrides toString() method to return multiline String descriptor of property for printout
    * Includes account number, address, assessed value, assessment classes, neighborhood, ward, and point
    * Adds string header to identify each variable
    * @return multiline String descriptor of property for printout Overrides toString
    * ************************************************************************************/
   @Override
    public String toString(){

        return "\nAccount ID:        " + this.accountNumber +
                "\nAddress:          " + this.address +
                "\nAssessed Value:   " + this.assessedValue +
                "\nAssessment Class: " + this.assessmentClasses +
                "\nNeighborhood:     " + this.neighborhood +
                " - (" + this.ward + ")" +
                "\nLocation:         " + this.point;
    }

}
