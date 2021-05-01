
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class App {
 
    public static void main(String[] args) {
        FullName fio1 = new FullName("Иванка", "Иванова", "Ивановна");
        FullName fio2 = new FullName("Прасковья", "Петрова", "Петровна");
        FullName fio3 = new FullName("Сидр", "Сидоров", "Сидрович");
        FullName fio4 = new FullName("Ололошка", "Боширов", "Ололошевич");

        HashSet<String> skills1 = new HashSet<>();
        skills1.add("C++");
        skills1.add("Java");
        skills1.add("SQL");
        LocalDate birthDay1 = LocalDate.of(1983, 5, 15);
        LocalDate date1 = LocalDate.of(2005, 5, 1);

        HashSet<String> skills2 = new HashSet<>();
        skills2.add("consider");
        skills2.add("write");
        skills2.add("read");
        LocalDate birthDay2 = LocalDate.of(1995, 10, 20);
        LocalDate date2 = LocalDate.of(2018, 3, 23);

        HashSet<String> skills3 = new HashSet<>();
        skills3.add("Linux");
        skills3.add("C++");
        skills3.add("bash");
        LocalDate birthDay3 = LocalDate.of(1990, 3, 4);
        LocalDate date3 = LocalDate.of(2015, 8, 7);

        HashSet<String> skills4 = new HashSet<>();
        skills4.add("wash");
        skills4.add("sweep");
        skills4.add("wipe");
        LocalDate birthDay4 = LocalDate.of(1999, 1, 23);
        LocalDate date4 = LocalDate.of(2018, 6, 1);

        List<Employee> employees = List.of(
                new Employee(fio1, birthDay1, Sex.FEMALE, "Engineer", new BigDecimal(1000.15), skills1, date1),
                new Employee(fio2, birthDay2, Sex.FEMALE, "Accountant", new BigDecimal(500.15), skills2, date2),
                new Employee(fio3, birthDay3, Sex.MALE, "Engineer", new BigDecimal(700.15), skills3, date3),
                new Employee(fio4, birthDay4, Sex.MALE, "Сleaner", new BigDecimal(300.15), skills4, date4)
        );

        EmployeeRepository employeeRepository = new EmployeeRepository(employees);

        System.out.println(employeeRepository.getByLastName("Иванова"));
        System.out.println("===========================================");
        System.out.println(employeeRepository.getOlderThan(24));
        System.out.println("===========================================");
        System.out.println(employeeRepository.getByGender(Sex.FEMALE));
        System.out.println("===========================================");
        System.out.println(employeeRepository.getByStates("Accountant", "Сleaner"));
        System.out.println("===========================================");
        System.out.println(employeeRepository.getBySalaryLessThan(600));
        System.out.println("===========================================");
        System.out.println(employeeRepository.getBySkill("C++"));
        System.out.println("===========================================");
        System.out.println(employeeRepository.getWorkMoreThan(10));
        System.out.println("===========================================");
        System.out.println(employeeRepository.getBirthDayMates(LocalDate.of(1995, 10, 20)));
        System.out.println("===========================================");
        System.out.println(employeeRepository.amount());
        System.out.println("===========================================");
        System.out.println(employeeRepository.totalSalary());
        System.out.println("===========================================");
        Map<Integer, Employee> idToEmployee = employeeRepository.mapIdToEmployee();
        System.out.println(idToEmployee);
    }
}
 