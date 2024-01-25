package pro.sky.homeworke25;

import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmployeeService {
    int maxQuantityEmployees = 10;

    private final Map<String, Employee> employeeBook = new HashMap<>();

    public Map<String, Employee> getEmployeeBook() {
        return employeeBook;
    }

    public String addEmployee(String firstName, String lastName, String department, int salary) throws EmployeeStorageIsFullException, EmployeeAlreadyAddedException {
        Employee employee = new Employee(firstName, lastName, department, salary);
        if (employeeBook.containsKey(employee)) {
            throw new EmployeeAlreadyAddedException();
        }
        String key = makeKey(firstName, lastName);
        if (employeeBook.size() < maxQuantityEmployees) {
            employeeBook.put(key, employee);
            return employee.toString();
        } else throw new EmployeeStorageIsFullException();
    }

    public String removeEmployee(String firstName, String lastName, String department, int salary) throws EmployeeNotFoundException {
        Employee employee = new Employee(firstName, lastName, department, salary);
        String key = makeKey(firstName, lastName);
        for (int i = 0; i < employeeBook.size(); i++) {
            if (employeeBook.containsKey(key)) {
                employeeBook.remove(employee);
                return employee.toString();
            } else if (!employeeBook.containsKey(key)) {
                throw new EmployeeNotFoundException();
            }
        }
        return employee.toString();
    }

    public String findEmployee(String firstName, String lastName, String department, int salary) throws EmployeeNotFoundException {
        Employee employee = new Employee(firstName, lastName, department, salary);
        String key = makeKey(firstName, lastName);
        if (employeeBook.containsKey(key)) {
            return employee.toString();
        } else if (!employeeBook.containsKey(key)) {
            throw new EmployeeNotFoundException();
        }
        return employee.toString();
    }

    public Collection<Employee> getAll() {
        return employeeBook.values();
    }

    public String printEmployee() {
        return employeeBook.toString();
    }

    private static String makeKey(String firstName, String lastName) {
        return (firstName + " " + lastName).toLowerCase();
    }
}
