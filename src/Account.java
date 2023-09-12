public class Account {
    public String accountNumber, suite, houseNumber, streetName;
    public String neighborhoodId, neighborhood, ward;
    public int val;
    public double longitude, latitude;
    public boolean garage;



    public static void main(String[] args) {
    }

    //Takes a line from csv data set and assigns values to object variables
    public void assignData(String[] data){
        // Order of data points in csv file
        // Account Number,Suite,House Number,Street Name,Garage,Neighbourhood ID,
        // Neighbourhood,Ward,Assessed Value,Latitude,Longitude,Point Location,
        // Assessment Class % 1,Assessment Class % 2,Assessment Class % 3,
        // Assessment Class 1,Assessment Class 2,Assessment Class 3
        accountNumber = data[0];
        suite = data[1];
        houseNumber = data[2];
        streetName = data[3];
        garage = data[4].equals("Y"); // Converts Y/N into bool
        neighborhoodId = data[5];
        neighborhood = data[6];
        ward =  data[7];

        // try to convert assessed value to int
        try {
            val = Integer.parseInt(data[8]);
        } catch (NumberFormatException e) {
            System.out.println("Invalid Assessed Value");
            throw new RuntimeException(e);
        }



    }


}
