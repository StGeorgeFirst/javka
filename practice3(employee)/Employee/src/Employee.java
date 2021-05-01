import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;



public class Employee {
    private static int globalId;
    private static int numOfEmployees;
    private static BigDecimal amountOfSalaries = new BigDecimal(0);
    private int id;
    private FullName fio;
    private final LocalDate birthDay;
    private final Sex sex;
    private String post;
    private BigDecimal salary;
    private Set<String> skills;
    private final LocalDate dateOfStart;
    private boolean fired;

    public Employee(FullName fio, LocalDate birthDay, Sex sex, String post, BigDecimal salary, Set<String> skills, LocalDate dateOfStart) {
        this.id = globalId;
        globalId++;
        numOfEmployees++;
        this.fio = fio;
        this.birthDay = birthDay;
        this.sex = sex;
        this.post = post;
        checkNegative(salary);
        this.salary = salary;
        amountOfSalaries = amountOfSalaries.add(salary);
        this.skills = skills;
        checkDate(birthDay, dateOfStart);
        this.dateOfStart = dateOfStart;
        this.fired = false;
    }

    public static int getGlobalId() {
        return globalId;
    }

    public static int getNumOfEmployees() {
        return numOfEmployees;
    }

    public static BigDecimal getAmountOfSalaries() {
        return amountOfSalaries;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public FullName getFio() {
        return fio;
    }

    public void setFio(FullName fio) {
        this.fio = fio;
    }

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public Sex getSex() {
        return sex;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        checkNegative(salary);
        BigDecimal oldSalary = this.salary;
        amountOfSalaries = amountOfSalaries.subtract(oldSalary);
        amountOfSalaries = amountOfSalaries.add(salary);
        this.salary = salary;
    }

    public Set<String> getSkills() {
        return skills;
    }

    public void setSkills(Set<String> skills) {
        this.skills = skills;
    }

    public LocalDate getDateOfStart() {
        return dateOfStart;
    }

    public boolean isFired() {
        return fired;
    }

    public void setFired(boolean fired) {
        if (fired & (!this.fired)) {
            numOfEmployees--;
            amountOfSalaries = amountOfSalaries.subtract(salary);
            this.fired = fired;
            return;
        }
        if (fired ^ this.fired){
            numOfEmployees++;
            amountOfSalaries = amountOfSalaries.add(salary);
            this.fired = fired;
            return;
        }

    }

    public int getAgeOfLife() {
        return calculateAge(birthDay);
    }

    public int getAgeOfWork() {
        return calculateAge(dateOfStart);
    }

    private int calculateAge(LocalDate then) {
        LocalDate now = LocalDate.now();
        int age = now.getYear() - then.getYear();
        if (then.getDayOfYear() > now.getDayOfYear()) {
            age = age - 1;
        }
        return age;
    }



    @Override
    public String toString() {
        String s = "id: " + id + " - " + fio.getFullName() + "\n" +
                "birthDay - " + birthDay + "\n" +
                "sex - " + sex + "\n" +
                "post - " + post + "\n" +
                "salary - " + salary + "\n" +
                "skills - " + skills + "\n" +
                "dateOfStart - " + dateOfStart + "\n" +
                "fired - " + fired + "\n";
        return s;
    }

    private void checkNegative(BigDecimal value) {
        if (value.compareTo(new BigDecimal(0)) < 0) {
            throw new IllegalArgumentException("Negative value");
        }
    }

    private void checkDate(LocalDate checking, LocalDate verifiable) {
        if (verifiable.isBefore(checking)) {
            throw new IllegalArgumentException("Wrong date");
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        Employee e = (Employee) obj;
        return this.id == e.id;
    }
}
