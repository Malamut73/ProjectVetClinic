package users;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public abstract class User {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        private static int nextId = 1;
        private final int userId;
        private String firstName;
        private String lastName;
        private String middleName;
        private String fullName;
        private Date dateOfRegistration;


        public User(String lastName, String firstName, String middleName) {
                this.userId = nextId;
                nextId++;
                this.firstName = firstName;
                this.lastName = lastName;
                this.middleName = middleName;
                this.fullName = getFullName();
                this.dateOfRegistration = new Date();
        }


        public int getUserId() {
                return userId;
        }
        public String getFullName(){
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(getLastName() + " ")
                        .append(getFirstName() + " ")
                        .append(getMiddleName());
                return stringBuilder.toString();
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
        public void setFirstName(String firstName) {
                this.firstName = firstName;
        }
        public void setLastName(String lastName) {
                this.lastName = lastName;
        }
        public void setMiddleName(String middleName) {
                this.middleName = middleName;
        }



        @Override
        public String toString() {
                return userId + " " + fullName + " " + simpleDateFormat.format(dateOfRegistration);
        }
        @Override
        public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                User user = (User) o;
                return Objects.equals(fullName, user.fullName);
        }
        @Override
        public int hashCode() {
                return Objects.hash(fullName);
        }
}
