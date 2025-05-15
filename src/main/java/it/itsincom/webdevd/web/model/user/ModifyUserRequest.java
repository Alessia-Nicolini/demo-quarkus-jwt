package it.itsincom.webdevd.web.model.user;

public class    ModifyUserRequest {
    private String firstName;
    private String lastName;
    private String address;
    private String role;



    public ModifyUserRequest(String firstName, String lastName, String address, String role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.role = role;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
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
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
