package clinic;

public class ReservationHelper {

    String reservationNumber;
    String name;
    String gender;
    String age;
    String phoneNumber;
    String reservationDate;
    String reservationType;
    int id;

    public ReservationHelper() {
    }

    public ReservationHelper(String reservationNumber, String pName, String gender,
            String age, String phoneNumber, String reservationDate, String reservationType) {
        this.reservationNumber = reservationNumber;
        this.name = pName;
        this.gender = gender;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.reservationDate = reservationDate;
        this.reservationType = reservationType;
    }

    public ReservationHelper(String reservationNumber, String name, String gender, String age, String phoneNumber, String reservationDate, String reservationType, int id) {
        this.reservationNumber = reservationNumber;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.reservationDate = reservationDate;
        this.reservationType = reservationType;
        this.id = id;
    }

    public String getReservationNumber() {
        return reservationNumber;
    }

    public void setReservationNumber(String reservationNumber) {
        this.reservationNumber = reservationNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(String reservationDate) {
        this.reservationDate = reservationDate;
    }

    public String getReservationType() {
        return reservationType;
    }

    public void setReservationType(String reservationType) {
        this.reservationType = reservationType;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
