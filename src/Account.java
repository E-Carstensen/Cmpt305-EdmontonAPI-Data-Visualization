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

        String[] classes;
        String[] percentages;

        if(assessmentClasses.length == 6){
            classes = new String[]{assessmentClasses[3], assessmentClasses[4], assessmentClasses[5]};
            percentages = new String[]{assessmentClasses[0], assessmentClasses[1], assessmentClasses[2]};
        }
        else if (assessmentClasses.length == 18){
            classes = new String[]{assessmentClasses[15], assessmentClasses[16], assessmentClasses[17]};
            percentages = new String[]{assessmentClasses[12], assessmentClasses[13], assessmentClasses[14]};
        }else {
            System.out.println("Invalid csv Format, Len should equal 6 or 18, actual: " + assessmentClasses.length);
            return;
        }
        try {

            for (int i = 0; i < classes.length; i++) {
                if (!percentages[i].isEmpty()) { // If column contained a percentage
                    int newValue = Integer.parseInt(percentages[i]); // Attempt to parse percentage to integer
                        // If class is already in map, add new value to existing value, else add new value to map
                        this.assessmentClasses.put(classes[i], this.assessmentClasses.getOrDefault(classes[i], 0) + newValue);
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid Assessment Class Percentage: " + assessmentClasses[12] + ", " + assessmentClasses[13] + ", " + assessmentClasses[14]);
        }catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid csv Format, Len should equal 18, actual: " + assessmentClasses.length);
        }
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
