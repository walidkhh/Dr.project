package clinic;

public class AllPatientsHelper {

    String Name;
    String phoneNumber;
    String address;
    String sickCondition;
    String notes;
    String id;

    public AllPatientsHelper(String name, String phoneNumber, String address, String sickCondition, String notes,
            String id) {
        this.Name = Name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.sickCondition = sickCondition;
        this.notes = notes;
        this.id = id;
    }

    public AllPatientsHelper(String name, String phoneNumber, String address, String sickCondition, String notes) {
        this.Name = Name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.sickCondition = sickCondition;
        this.notes = notes;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = Name;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public AllPatientsHelper() {
    }
}
