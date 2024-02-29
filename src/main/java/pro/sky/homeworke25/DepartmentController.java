package pro.sky.homeworke25;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    public final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }
    @GetMapping(path = "/{id}/salary/sum")                           //Возвращаем сумму зарплат по департаменту
    public String sumSalaryOfDepartment(@PathVariable String id) {
        return "Сумма зарплат в департаменте " + id + " = " + departmentService.getSumSalaryOfDepartment(id)+".";
    }
    @GetMapping(path = "/{id}/salary/max")                          //Возвращаем максимальную зарплату по департаменту
    public Employee maxSalaryOfDepartment(@PathVariable String id) {
        return departmentService.getMaxSalaryOfDepartment(id);
    }
   /* @GetMapping(path = "/max-salary")
    public Employee maxSalaryOfDepartment(@RequestParam("department") String department) {
        return departmentService.getMaxSalaryOfDepartment(department);
    }*/
    @GetMapping(path = "/{id}/salary/min")                          //Возвращаем минимальную зарплату по департаменту
    public Employee minSalaryOfDepartment(@PathVariable String id) {
        return departmentService.getMinSalaryOfDepartment(id);
    }
   /* @GetMapping(path = "/min-salary")
    public Employee minSalaryOfDepartment(@RequestParam("department") String department) {
        return departmentService.getMinSalaryOfDepartment(department);
    }*/
    @GetMapping(path = "/{id}/employees")                     //Возвращаем список сотрудников по департаменту
    public String printEmployeeDep(@PathVariable String id) {
        return departmentService.printEmployeeDep(id);
    }
    /*@GetMapping(value = "all", params = "departmentId")
    public String printEmployeeDep(@RequestParam String department) {
        return departmentService.printEmployeeDep(department);
    }*/

    @GetMapping("/employees")                    //Возвращаем всех сотрудников по отделам
    public String groupByDepartment() {
        return departmentService.groupByDepartment().toString();
    }
}

