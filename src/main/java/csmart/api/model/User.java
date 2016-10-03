package csmart.api.model;

import csmart.api.db.JsonType;
import csmart.db.gen.tables.records.UsersRecord;

/**
 * Created by sethur on 1/10/2016.
 */
public class User {
    private String emailid;
    private String password;
    private Address address;
    private String practiceName;
    private boolean primaryUser;

    public User(UsersRecord user) {
        this.emailid = user.getEmailid();
        this.password = user.getPasswordHash();
        this.address = JsonType.fromJsonNode(user.getAddress(), Address.class);
        this.practiceName = user.getPracticeName();
        this.primaryUser = user.getPrimaryUser();
    }

    public User() {

    }

    public String getEmailid() {
        return emailid;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPracticeName() {
        return practiceName;
    }

    public void setPracticeName(String practiceName) {
        this.practiceName = practiceName;
    }

    public boolean isPrimaryUser() {
        return primaryUser;
    }

    public void setPrimaryUser(boolean primaryUser) {
        this.primaryUser = primaryUser;
    }
}
