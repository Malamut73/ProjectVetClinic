package moduls;

import java.util.Date;
import java.util.Objects;

public abstract class AbstractUser {

//        SimpleDateFormat dateCreationFormat = new SimpleDateFormat("yyyy-MM-dd");

        private int userId;
        private String lastName;
        private String firstName;
        private String middleName;
        private String login;
        private String password;
        private String role;
        private Date dateOfRegistration;

        public AbstractUser() {
        }

        public AbstractUser(String lastName, String firstName, String middleName) {
                this.lastName = lastName;
                this.firstName = firstName;
                this.middleName = middleName;
        }

        public AbstractUser(String lastName, String firstName, String middleName, String login, String password, String role) {
                this.lastName = lastName;
                this.firstName = firstName;
                this.middleName = middleName;
                this.login = login;
                this.password = password;
                this.role = role;
        }

        public AbstractUser(int userId, String lastName, String firstName, String middleName, String login, String password, String role) {
                this.userId = userId;
                this.lastName = lastName;
                this.firstName = firstName;
                this.middleName = middleName;
                this.login = login;
                this.password = password;
                this.role = role;
        }

        public void setUserId(int userId) {
                this.userId = userId;
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
        public String getLogin() {
                return login;
        }
        public void setLogin(String login) {
                this.login = login;
        }
        public String getPassword() {
                return password;
        }
        public void setPassword(String password) {
                this.password = password;
        }
        public String getRole() {
                return role;
        }
        public void setRole(String role) {
                this.role = role;
        }
        public void setAll(int userId, String lastName, String firstName, String middleName, String login, String password, String role){
                this.userId = userId;
                this.lastName = lastName;
                this.firstName = firstName;
                this.middleName = middleName;
                this.login = login;
                this.password = password;
                this.role = role;
        }

        @Override
        public String toString() {

                return userId + " " + lastName + " " + firstName + " " + middleName + " " + login + " " + password + " " + role;

        }
        @Override
        public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                AbstractUser user = (AbstractUser) o;
                return Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && Objects.equals(middleName, user.middleName);
        }
        @Override
        public int hashCode() {
                return Objects.hash(firstName, lastName, middleName);
        }
}


