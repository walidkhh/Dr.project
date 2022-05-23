package clinic;

public class AllPatientsHelper {

    String Name;
    String phoneNumber;
    String address;
    String sickCondition;
    String notes;
    String age;
    String gender;
    String id;
    String date;
    public AllPatientsHelper(String name, String phoneNumber, String address, String sickCondition, String notes,
            String id, String age , String gender, String date) {
        this.Name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.sickCondition = sickCondition;
        this.notes = notes;
        this.id = id;
        this.age = age;
        this.gender = gender;
        this.date = date;
    }

    public AllPatientsHelper(String name, String phoneNumber, String address, String sickCondition,
            String notes, String age, String gender, String date) {
        this.Name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.sickCondition = sickCondition;
        this.notes = notes;
        this.age = age;
        this.gender = gender;
        this.date = date;
    }

    public AllPatientsHelper(String Name, String age,String gender,String phoneNumber, String date) {
        this.Name = Name;
        this.phoneNumber = phoneNumber;
        this.age = age;
        this.gender = gender;
        this.date = date;
    }
    

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
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

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
        
    public AllPatientsHelper() {
    }
}
