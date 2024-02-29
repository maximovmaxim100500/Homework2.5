package pro.sky.homeworke25;

import org.apache.coyote.BadRequestException;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EmployeeServiceTest {

    private final EmployeeService out = new EmployeeService();

    @Test
    void getEmployeeBookTest() throws BadRequestException {
        EmployeeService employeeService = new EmployeeService();

        Employee employee1 = new Employee("Maks1", "LastMaks1", "1", 100000);
        Employee employee2 = new Employee("Maks2", "LastMaks2", "2", 200000);

        employeeService.addEmployee(employee1.getFirstName(), employee1.getLastName(), employee1.getDepartment(), employee1.getSalary());
        employeeService.addEmployee(employee2.getFirstName(), employee2.getLastName(), employee2.getDepartment(), employee2.getSalary());

        Map<String, Employee> expected = employeeService.getEmployeeBook();

        Map<String, Employee> actual = new HashMap<>();
        actual.put(EmployeeService.makeKey(employee1.getFirstName(), employee1.getLastName()), employee1);
        actual.put(EmployeeService.makeKey(employee2.getFirstName(), employee2.getLastName()), employee2);

        assertEquals(expected, actual);
    }

   /* @Test
    void addEmployee() {
        Map<String, Employee> expectedMap = new HashMap<>();
        Employee employee1 = new Employee("Maks1", "LastMaks1", "1", 100000);
        Employee employee2 = new Employee("Maks2", "LastMaks2", "2", 200000);
        expectedMap.put("maks1 lastmaks1", employee1);
        expectedMap.put("maks2 lastmaks2", employee2);
        out.addEmployee(employee1);

    }*/

   /* @Test
    void removeEmployee() {
    }

    @Test
    void findEmployee() {
    }

    @Test
    void getAll() {
    }

    @Test
    void printEmployee() {
    }*/
}