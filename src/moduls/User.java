package moduls;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public abstract class User{

//        SimpleDateFormat dateCreationFormat = new SimpleDateFormat("yyyy-MM-dd");

        private int userId;
        private String firstName;
        private String lastName;
        private String middleName;
        private Date dateOfRegistration;

        public User(int userId, String lastName, String firstName, String middleName, Date dateOfRegistration) {
                this.userId = userId;
                this.firstName = firstName;
                this.lastName = lastName;
                this.middleName = middleName;
                this.dateOfRegistration = dateOfRegistration;
        }

        public User(String lastName, String firstName, String middleName) {
                this.firstName = firstName;
                this.lastName = lastName;
                this.middleName = middleName;
        }
        public String getFullName(){
                return lastName + " " + firstName + " " + middleName;
        }
        public String getFirstName() {
                return firstName;
        }
        public String getLastName() {
                return lastName;
        }
        public String getMiddleName() {
                return middleName;
        }
        public int getUserId() {
                return userId;
        }

        @Override
        public String toString() {

                return userId + " " + lastName + " " + firstName + " " + middleName + " " + dateOfRegistration;

        }
        @Override
        public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                User user = (User) o;
                return Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && Objects.equals(middleName, user.middleName);
        }
        @Override
        public int hashCode() {
                return Objects.hash(firstName, lastName, middleName);
        }
}


