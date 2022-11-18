package phonebook;

public class Contact {

    private String name;

    private String phoneNumber;

    public Contact(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public Contact() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this ) {
            return true;
        }
        if(!(obj instanceof Contact)) {
            return false;
        }

        Contact c = (Contact) obj;

        return c.getName().equals(this.getName());
    }

    public boolean equals(String name) {

        return name.equals(this.getName());
    }

    @Override
    public String toString() {
        return "Contact{" +
                "name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}'+"\n";
    }
}
