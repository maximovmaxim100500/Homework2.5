package pro.sky.homeworke25;

import org.apache.commons.lang3.StringUtils;
import org.apache.coyote.BadRequestException;
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

    public String addEmployee(String firstName, String lastName, String department, int salary)
            throws EmployeeStorageIsFullException, EmployeeAlreadyAddedException, BadRequestException {
        dataChecking(firstName, lastName);
        firstName = StringUtils.capitalize(firstName);
        lastName = StringUtils.capitalize(lastName);
        Employee employee = new Employee(firstName, lastName, department, salary);
        String key = makeKey(firstName, lastName);
        if (employeeBook.containsKey(key)) {
            throw new EmployeeAlreadyAddedException();
        }
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
        firstName = StringUtils.capitalize(firstName);
        lastName = StringUtils.capitalize(lastName);
        return (firstName + " " + lastName).toLowerCase();
    }

    private static void dataChecking(String firstName, String lastName) throws BadRequestException {
        if (!(StringUtils.isAlpha(firstName) && StringUtils.isAlpha(lastName))) {
            throw new BadRequestException();
        }
    }
}
