public class Address {

    String suite;
    String houseNumber;
    String streetName;

    public Address(Account account){
        this.suite = account.suite;
        this.houseNumber = account.houseNumber;
        this.streetName = account.streetName;

    }

    public String toString(){
        return houseNumber + " " + streetName + " " + suite;
    }


}
