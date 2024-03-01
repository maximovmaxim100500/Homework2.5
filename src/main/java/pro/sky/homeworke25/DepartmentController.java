package pro.sky.homeworke25;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    public final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping(path = "/{id}/salary/sum")                           //Возвращаем сумму зарплат по департаменту
    public String sumSalaryOfDepartment(@PathVariable String id) {
        return "Сумма зарплат в департаменте " + id + " = " + departmentService.getSumSalaryOfDepartment(id) + ".";
    }

    @GetMapping(path = "/{id}/salary/max")                          //Возвращаем максимальную зарплату по департаменту
    public Employee maxSalaryOfDepartment(@PathVariable String id) {
        return departmentService.getMaxSalaryOfDepartment(id);
    }

    @GetMapping(path = "/{id}/salary/min")                          //Возвращаем минимальную зарплату по департаменту
    public Employee minSalaryOfDepartment(@PathVariable String id) {
        return departmentService.getMinSalaryOfDepartment(id);
    }

    @GetMapping(path = "/{id}/employees")                     //Возвращаем список сотрудников по департаменту
    public String printEmployeeDep(@PathVariable String id) {
        return departmentService.printEmployeeDep(id);
    }

    @GetMapping("/employees")                    //Возвращаем всех сотрудников по отделам
    public String groupByDepartment() {
        return departmentService.groupByDepartment().toString();
    }
}

