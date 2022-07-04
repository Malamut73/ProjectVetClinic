package users;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public abstract class User implements Serializable {

        SimpleDateFormat dateCreationFormat = new SimpleDateFormat("yyyy-MM-dd");

        private static int nextId = 1;
        private final int userId;
        private String firstName;
        private String lastName;
        private String middleName;
        private String fullName;
        private final Date dateOfRegistration;


        public User(String lastName, String firstName, String middleName) {
                this.userId = nextId;
                ++nextId;
                this.firstName = firstName;
                this.lastName = lastName;
                this.middleName = middleName;
                this.dateOfRegistration = new Date();
                this.fullName = lastName + " " + firstName + " " + middleName;
        }

        public String getFullName(){
                return fullName;
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
        public void setFullName(String fullName) {
                StringBuilder stringBuilder = new StringBuilder();
                String[] FIO = fullName.split(" ");
                this.setLastName(FIO[0]);
                this.setFirstName(FIO[1]);
                this.setMiddleName(FIO[2]);
                this.fullName = fullName;
        }
        public static void setNextId(int nextId) {
                User.nextId = nextId;
        }

        @Override
        public String toString() {
                return userId + " " + fullName + " " + dateCreationFormat.format(dateOfRegistration);
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
