package pro.sky.homeworke25;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class EmployeeService {
    int maxQuantityEmployees = 10;
    Map<Employee, Integer> employeeBook = new HashMap<>();

    public String addEmployee(String firstName, String lastName) throws EmployeeStorageIsFullException, EmployeeAlreadyAddedException {
        Employee employee = new Employee(firstName, lastName);
        if (employeeBook.containsKey(employee)) {
            throw new EmployeeAlreadyAddedException();
        }
        if (employeeBook.size() < maxQuantityEmployees) {
            employeeBook.put(employee, 1);
            return employee.toString();
        } else throw new EmployeeStorageIsFullException();
    }

    public String removeEmployee(String firstName, String lastName) throws EmployeeNotFoundException {
        Employee employee = new Employee(firstName, lastName);
        for (int i = 0; i < employeeBook.size(); i++) {
            if (employeeBook.containsKey(employee)) {
                employeeBook.remove(employee);
                return employee.toString();
            } else if (!employeeBook.containsKey(employee)) {
                throw new EmployeeNotFoundException();
            }
        }
        return employee.toString();
    }

    public String findEmployee(String firstName, String lastName) throws EmployeeNotFoundException {
        Employee employee = new Employee(firstName, lastName);
        if (employeeBook.containsKey(employee)) {
            return employee.toString();
        } else if (!employeeBook.containsKey(employee)) {
            throw new EmployeeNotFoundException();
        }
        return employee.toString();
    }

    public String printEmployee() {
        return employeeBook.toString();
    }
}
