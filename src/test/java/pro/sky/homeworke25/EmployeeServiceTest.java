package pro.sky.homeworke25;

import org.apache.coyote.BadRequestException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceTest {
    private Employee employee1;
    private Employee employee2;
    private EmployeeService employeeService;

    @BeforeEach
    public void setUp() throws BadRequestException {
        employee1 = new Employee("Maks", "Lastmaks", "1", 100000);
        employee2 = new Employee("Elena", "Lastelena", "2", 200000);
        employeeService = new EmployeeService();
        employeeService.addEmployee(employee1.getFirstName(), employee1.getLastName(), employee1.getDepartment(), employee1.getSalary());
        employeeService.addEmployee(employee2.getFirstName(), employee2.getLastName(), employee2.getDepartment(), employee2.getSalary());
    }


    @Test
    void getEmployeeBookTest() {

        Map<String, Employee> actual = employeeService.getEmployeeBook();

        Map<String, Employee> expected = new HashMap<>();

        expected.put(EmployeeService.makeKey(employee1.getFirstName(), employee1.getLastName()), employee1);
        expected.put(EmployeeService.makeKey(employee2.getFirstName(), employee2.getLastName()), employee2);

        assertEquals(expected, actual);
    }

    @Test
    void getEmployeeBookNotNull() {
        Map<String, Employee> actual = employeeService.getEmployeeBook();
        assertNotNull(actual);
    }

    @Test
    void addEmployee() {
        Map<String, Employee> actual = employeeService.getEmployeeBook();
        Map<String, Employee> expected = new HashMap<>();
        expected.put("maks lastmaks", employee1);
        expected.put("elena lastelena", employee2);
        assertNotNull(employeeService);
        assertEquals(expected, actual);
    }

    @Test
    void shouldReturnExceptionWhenAnExistsEmployeeIsAdd() {
        Assertions.assertThrows(EmployeeAlreadyAddedException.class, () -> employeeService.addEmployee(
                employee1.getFirstName(),
                employee1.getLastName(),
                employee1.getDepartment(),
                employee1.getSalary()))
        ;
    }

    @Test
    void removeEmployee() {
        Map<String, Employee> actual = employeeService.getEmployeeBook();
        Map<String, Employee> expected = new HashMap<>();
        actual.remove("elena lastelena");
        expected.put("maks lastmaks", employee1);
        assertNotNull(employeeService);
        assertEquals(expected, actual);
    }

    @Test
    void shouldReturnAnExceptionWhenThereIsNoEmployeeToBeRemoved() {
        Assertions.assertThrows(EmployeeNotFoundException.class, () -> employeeService.removeEmployee(
                "Oksana",
                "LastOksana",
                "IT",
                100000));
    }

    @Test
    void findEmployee() throws BadRequestException {
        String actual = employeeService.findEmployee("Maks", "Lastmaks", "1", 100000);
        String expected = employee1.toString();
        assertNotNull(employeeService);
        assertEquals(expected, actual);
    }

    @Test
    void shouldReturnAnExceptionWhenThereIsNoEmployeeToBeFind() {
        Assertions.assertThrows(EmployeeNotFoundException.class, () -> employeeService.findEmployee(
                "Oksana",
                "LastOksana",
                "IT",
                100000));
    }

    @Test
    void getAll() {
        Collection<Employee> actual = employeeService.getAll();
        Collection<Employee> expected = new ArrayList<>();
        expected.add(employee2);
        expected.add(employee1);

        assertNotNull(employeeService);
        assertIterableEquals(expected, actual);
    }

    @Test
    void printEmployee() {
        String actual = employeeService.printEmployee();
        Map<String, Employee> expected = new HashMap<>();
        expected.put("maks lastmaks", employee1);
        expected.put("elena lastelena", employee2);
        assertNotNull(employeeService);
        assertEquals(expected.toString(), actual);
    }

    @Test
    void makeKey() {
        String actual = EmployeeService.makeKey(employee1.getFirstName(), employee1.getLastName());
        String expected = "maks lastmaks";
        assertNotNull(actual);
        assertEquals(expected, actual);
    }
}