
package clinic;

import java.time.LocalDate;

public class ReservationHelper {
    
    String reservationNumber;
    String pName;
    String gender;
    String age;
    String phoneNumber;
    LocalDate reservationDate;
    String reservationType;

    public ReservationHelper() {
    }

    public ReservationHelper(String reservationNumber, String pName, String gender, 
            String age, String phoneNumber, LocalDate reservationDate, String reservationType) {
        this.reservationNumber = reservationNumber;
        this.pName = pName;
        this.gender = gender;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.reservationDate = reservationDate;
        this.reservationType = reservationType;
    }

    public String getReservationNumber() {
        return reservationNumber;
    }

    public void setReservationNumber(String reservationNumber) {
        this.reservationNumber = reservationNumber;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
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

    public LocalDate getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(LocalDate reservationDate) {
        this.reservationDate = reservationDate;
    }

    public String getReservationType() {
        return reservationType;
    }

    public void setReservationType(String reservationType) {
        this.reservationType = reservationType;
    }
    
    
    
}