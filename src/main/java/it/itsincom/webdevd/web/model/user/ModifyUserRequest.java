package it.itsincom.webdevd.web.model.user;

public class    ModifyUserRequest {
    private String firstName;
    private String lastName;
    private String address;

    public ModifyUserRequest(String firstName, String lastName, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddresse(String address) {
        this.address = address;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

}
