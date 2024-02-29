package pro.sky.homeworke25;

import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DepartmentService {
    private final EmployeeService employeeService;

    public DepartmentService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public String printEmployeeDep(String id) {                          //Находим сотрудников отдела
        return employeeService.getEmployeeBook().values().stream()
                .filter(employee -> employee.getDepartment().equals(id))
                .collect(Collectors.toList()).toString();
    }

    public Employee getMaxSalaryOfDepartment(String id) {           //Находим максимальную зарплату в отделе
    /*    int maxSalary = -1;
        List<Employee> employees = new ArrayList<>();
        for (Employee employee : employeeBook.values()) {
            if (employee.getDepartment().contains(department)) {
                employees.add(employee);
            }
        }
        String maxEmployeeSalary = "";
        for (var employee : employees) {
            if (employee.getSalary() > maxSalary) {
                maxSalary = employee.getSalary();
            }
        }
        for (Employee employee : employees) {
            if (employee.getSalary() == maxSalary) {
                maxEmployeeSalary = employee.getFirstName() + " " + employee.getLastName() + ", department = " +
                        employee.getDepartment() + ", salary = " + employee.getSalary() + ".";
            }
        }
        return maxEmployeeSalary;*/
        return employeeService.getAll().stream()
                .filter(employee -> employee.getDepartment().equals(id))
                .max(Comparator.comparingInt(Employee::getSalary))
                .orElse(null);
    }

    public Employee getMinSalaryOfDepartment(String id) {           //Находим минимальную зарплату в отделе
        return employeeService.getAll().stream()
                .filter(employee -> employee.getDepartment().equals(id))
                .min(Comparator.comparingInt(Employee::getSalary))
                .orElse(null);
    }

    public Map<String, List<Employee>> groupByDepartment() {                //Находим всех сотрудников по отделам
        return employeeService.getAll().stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }

    public int getSumSalaryOfDepartment(String id) {
        int sumSalaryOfDep = 0;
        for (Employee employee :employeeService.getEmployeeBook().values()){
            int SalaryOfDep = employee.getSalary();
            sumSalaryOfDep += SalaryOfDep;
        }
        return sumSalaryOfDep;
    }

}
