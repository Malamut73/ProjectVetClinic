package vetClinic;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class Client {

//        public static HashMap<Integer, vetClinic.Client> clients = new HashMap<>();


        private static int nextId = 1;
        private final int id;
        private String firstName;
        private String lastName;
        private String middleName;
        private String fullName;
        private Date dateOfRegistration;

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        public Client(String lastName, String firstName, String middleName) {
                this.id = nextId;
                nextId++;
                this.firstName = firstName;
                this.lastName = lastName;
                this.middleName = middleName;
                this.fullName = getFullName();
                this.dateOfRegistration = new Date();
        }




        public void putToClients (HashMap<Integer, Client> hashMap){
                hashMap.put(this.id, this);
        }

        public static Client createClient(String lastName, String firstName, String middleName){
                Client client = new Client(lastName, firstName, middleName);
                return client;
        }





        public int getId() {
                return id;
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
                return id + " " + fullName + " " + simpleDateFormat.format(dateOfRegistration);
        }
}
