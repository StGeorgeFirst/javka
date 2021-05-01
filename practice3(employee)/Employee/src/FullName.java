public class FullName {
    private final String firstName;
    private final String lastName;
    private final String fatherName;

    public FullName(String firstName, String lastName, String fatherName) {
        String pattern = "0-9";
        if (firstName.matches(pattern)) {
            throw new IllegalArgumentException("Entered firstName error");
        } else {
            this.firstName = firstName;
        }

        if (lastName.matches(pattern)) {
            throw new IllegalArgumentException("Entered lastName error");
        } else {
            this.lastName = lastName;
        }

        if (fatherName.matches(pattern)) {
            throw new IllegalArgumentException("Entered fatherName error");
        } else {
            this.fatherName = fatherName;
        }
    }

    public String getFullName() {
        return toString();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFatherName() {
        return fatherName;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + " " + fatherName;
    }
}
