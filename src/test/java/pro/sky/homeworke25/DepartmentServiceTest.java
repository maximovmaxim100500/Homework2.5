package pro.sky.homeworke25;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceTest {
    private final Employee employee1 = new Employee("Maks", "Lastmaks", "1", 100000);
    private final Employee employee2 = new Employee("Elena", "Lastelena", "2", 200000);

    private final Employee employee3 = new Employee("Marina", "Lastmarina", "1", 300000);

    @Mock
    private EmployeeService employeeService;

    private DepartmentService departmentService;

    @BeforeEach
    public void setUp() {
        departmentService = new DepartmentService(employeeService);
    }

    @Test
    void printEmployeeDep() {
        Mockito.when(employeeService.getEmployeeBook()).thenReturn(Map.of(
                "1", employee1,
                "2", employee2,
                "3", employee3
        ));
        String expected = "[" + employee1.toString() + ", " + employee3.toString() + "]";
        String actual = departmentService.printEmployeeDep("1");
        assertEquals(expected, actual);

    }

    @Test
    void getMaxSalaryOfDepartment() {
        Mockito.when(employeeService.getAll()).thenReturn(Set.of(
                employee1,
                employee2,
                employee3
        ));
        Employee expected = employee3;
        Employee actual = departmentService.getMaxSalaryOfDepartment("1");
        assertEquals(expected,actual);
    }

    @Test
    void getMinSalaryOfDepartment() {
        Mockito.when(employeeService.getAll()).thenReturn(Set.of(
                employee1,
                employee2,
                employee3
        ));
        Employee expected = employee1;
        Employee actual = departmentService.getMinSalaryOfDepartment("1");
        assertEquals(expected,actual);
    }

    @Test
    void groupByDepartment() {
        Mockito.when(employeeService.getAll()).thenReturn(List.of(
                employee1,
                employee2,
                employee3
        ));
        Map<String, List<Employee>> expected = new HashMap<>();
        List<Employee> list1 = new ArrayList<>();
        List<Employee> list2 = new ArrayList<>();
        list1.add(employee1);
        list1.add(employee3);
        list2.add(employee2);
        expected.put("1", list1);
        expected.put("2", list2);
        Map<String, List<Employee>> actual = departmentService.groupByDepartment();
        assertEquals(expected,actual);
    }

    @Test
    void getSumSalaryOfDepartment() {
        Mockito.when(employeeService.getAll()).thenReturn(List.of(
                employee1,
                employee2,
                employee3
        ));
        int expected = 400000;
        int actual = departmentService.getSumSalaryOfDepartment("1");
        assertEquals(expected,actual);
    }
}