package pro.sky.homeworke25;

import org.apache.coyote.BadRequestException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public String employeeBook() {
        return "employeeBook";
    }


    @GetMapping(path = "/add")
    public String addEmployee(@RequestParam("firstName") String firstName,
                              @RequestParam("lastName") String lastName,
                              @RequestParam("department") String department,
                              @RequestParam("salary") int salary) throws BadRequestException {
        return employeeService.addEmployee(firstName, lastName, department, salary);
    }

    @GetMapping(path = "/remove")
    public String removeEmployee(@RequestParam("firstName") String firstName,
                                 @RequestParam("lastName") String lastName,
                                 @RequestParam("department") String department,
                                 @RequestParam("salary") int salary) throws BadRequestException {
        return employeeService.removeEmployee(firstName, lastName, department, salary);
    }

    @GetMapping(path = "/find")
    public String findEmployee(@RequestParam("firstName") String firstName,
                               @RequestParam("lastName") String lastName,
                               @RequestParam("department") String department,
                               @RequestParam("salary") int salary) throws BadRequestException {
        return employeeService.findEmployee(firstName, lastName, department, salary);
    }

    @GetMapping(path = "/printAll")
    public String printEmployee() {
        return employeeService.printEmployee();
    }
}
