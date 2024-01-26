package pro.sky.homeworke25;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
    public final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping(path = "/max-salary")
    public Employee maxSalaryOfDepartment(@RequestParam("department") String department) {
        return departmentService.getMaxSalaryOfDepartment(department);
    }

    @GetMapping(path = "/min-salary")
    public Employee minSalaryOfDepartment(@RequestParam("department") String department) {
        return departmentService.getMinSalaryOfDepartment(department);
    }

    @GetMapping(value = "all", params = "departmentId")
    public String printEmployeeDep(@RequestParam String department) {
        return departmentService.printEmployeeDep(department);
    }

    @GetMapping("/all")
    public String groupByDepartment() {
        return departmentService.groupByDepartment().toString();
    }
}

