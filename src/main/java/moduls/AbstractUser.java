package moduls;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Objects;

@Getter
@Setter
public abstract class AbstractUser {


        private int userId;
        private String lastName;
        private String firstName;
        private String middleName;
        private String login;
        private String password;
        private String role;
        private Date dateOfRegistration;

        public AbstractUser(String lastName, String firstName, String middleName) {
                this.lastName = lastName;
                this.firstName = firstName;
                this.middleName = middleName;
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

        public String getFullName(){
                return lastName + " " + firstName + " " + middleName;
        }

        @Override
        public String toString() {
                return userId + " " + lastName + " " + firstName + " " + middleName;
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


