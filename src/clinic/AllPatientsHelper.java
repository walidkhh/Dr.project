
package clinic;

public class AllPatientsHelper  {
    String name ;
    String phoneNumber;
    String address;
    String sickCondition;
    String notes;

    public AllPatientsHelper() {
    }

    
    public AllPatientsHelper(String name, String phoneNumber, String address, String sickCondition, String notes) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.sickCondition = sickCondition;
        this.notes = notes;
    }
    
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSickCondition() {
        return sickCondition;
    }

    public void setSickCondition(String sickCondition) {
        this.sickCondition = sickCondition;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
    
}
