import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
 
public class EmployeeRepository {
 
    private final List<Employee> employees;
 
    public EmployeeRepository(List<Employee> employees) {
        this.employees = employees;
    }
 
    /**
     * Найти всех сотрудников с заданной фамилией
     */
    public List<Employee> getByLastName(String lastName) {
        List<Employee> result = new ArrayList<>();
        for (Employee e : employees) {
            if (e.getFio().getLastName().equals(lastName)) {
                result.add(e);
            }
        }
        return result;
    }
 
    /**
     * Найти всех сотрудников старше заданного возраста
     */
    public List<Employee> getOlderThan(int age) {
        List<Employee> result = new ArrayList<>();
        for (Employee e : employees) {
            if (e.getAgeOfLife() > age) {
                result.add(e);
            }
        }
        return result;
    }
 
    /**
     * Найти всех сотрудников заданного пола.
     * Вместо Object используйте ваш класс для пола
     */
    public List<Employee> getByGender(Sex gender) {
        List<Employee> result = new ArrayList<>();
        for (Employee e : employees) {
            if (e.getSex().equals(gender)) {
                result.add(e);
            }
        }
        return result;
    }
 
    /**
     * Найти всех сотрудников с указанными должностями
     */
    public List<Employee> getByStates(String... states) {
        List<Employee> result = new ArrayList<>();
        for (Employee e : employees) {
            for (String state : states) {
                if (e.getPost().equals(state)) {
                    result.add(e);
                }
            }
        }
        return result;
    }
 
    /**
     * Найти всех сотрудников с зарплатой меньше заднной в рублях
     */
    public List<Employee> getBySalaryLessThan(int salaryInRubbles) {
        List<Employee> result = new ArrayList<>();
        BigDecimal salary = BigDecimal.valueOf(salaryInRubbles);
        for (Employee e : employees) {
            if (e.getSalary().compareTo(salary) == -1) {
                result.add(e);
            }
        }
        return result;
    }
 
    /**
     * Найти всех сотрудников, обладающих заданным навыком
     */
    public List<Employee> getBySkill(String skill) {
        List<Employee> result = new ArrayList<>();
        for (Employee e : employees) {
            if (e.getSkills().contains(skill)) {
                result.add(e);
            }
        }
        return result;
    }
 
    /**
     * Найти всех сотрудников, работающих в компании больше заданного числа лет
     */
    public List<Employee> getWorkMoreThan(int years) {
        List<Employee> result = new ArrayList<>();
        for (Employee e : employees) {
            if (e.getAgeOfWork() > years) {
                result.add(e);
            }
        }
        return result;
    }
 
    /**
     * Найти всех сотрудников, у которых День рождения в указанную дату
     */
    public List<Employee> getBirthDayMates(LocalDate date) {
        List<Employee> result = new ArrayList<>();
        for (Employee e : employees) {
            if (e.getBirthDay().equals(date)) {
                result.add(e);
            }
        }
        return result;
    }
 
    /**
     * Получить сколько всего сотрудников работает в компании
     */
    public int amount() {
        return Employee.getNumOfEmployees();
    }
 
    /**
     * Получить сколько всего денег тратит компания на зарплаты в год.
     * Вместо Object используйте подходящий тип данных
     */
    public BigDecimal totalSalary() {
        return Employee.getAmountOfSalaries();
    }
 
    /**
     * Получить отображение идентификатора работника на сущность работника для удобства дальнейшего поиска по id
     */
    public Map<Integer, Employee> mapIdToEmployee() {
        Map <Integer, Employee> result = new HashMap<>();
        for (Employee e : employees) {
            result.put(e.getId(), e);
        }
        return result;
    }
}