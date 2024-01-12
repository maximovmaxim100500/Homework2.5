package pro.sky.homeworke25;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {
    int maxQuantityEmployees = 10;
    List<Employee> list = new ArrayList<>();

    public String addEmployee(String firstName, String lastName) throws EmployeeStorageIsFullException, EmployeeAlreadyAddedException {
        Employee employee = new Employee(firstName, lastName);
        for (int i = 0; i < list.size(); i++) {
            if (employee.equals(list.get(i))) {
                throw new EmployeeAlreadyAddedException();
            }
        }
        if (list.size() < maxQuantityEmployees) {
            list.add(employee);
            return employee.toString();
        } else throw new EmployeeStorageIsFullException();
    }

    public String removeEmployee(String firstName, String lastName) throws EmployeeNotFoundException {
        Employee employee = new Employee(firstName, lastName);
        String g = "";
        for (int i = 0; i < list.size(); i++) {
            if (employee.equals(list.get(i))) {
                g = employee.toString();
                list.remove(i);
                return g;
            } else if (i == list.size() - 1 && (!employee.equals(list.get(i)))) {
                throw new EmployeeNotFoundException();
            }
        }
        return g;
    }

    public String findEmployee(String firstName, String lastName) throws EmployeeNotFoundException {
        Employee employee = new Employee(firstName, lastName);
        String g = "";
        for (int i = 0; i < list.size(); i++) {
            if (employee.equals(list.get(i))) {
                g = employee.toString();
                return g;
            } else if (i == list.size() - 1 && (!employee.equals(list.get(i)))) {
                throw new EmployeeNotFoundException();
            }
        }
        return g;
    }

    public String printEmployee() {
        return list.toString();
    }
}
