package application.console.answer;

import application.console.ConsoleHandler;
import application.model.Department;
import application.model.Employee;
import application.service.DepartmentService;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GlobalSearchCommand implements ConsoleHandler {
    private final DepartmentService departmentService;

    @Autowired
    public GlobalSearchCommand(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @Override
    public void handleCommand() {
        System.out.println("Please specify template or print 'menu' to back to menu");
        Scanner scanner = new Scanner(System.in);
        String template = scanner.nextLine();
        if (template.equalsIgnoreCase("menu")) {
            return;
        }
        List<String> answer = new ArrayList<>();
        List<Department> allDepartments = departmentService.findAll();
        for (Department department : allDepartments) {
            List<Employee> employees = department.getEmployees();
            employees.stream()
                    .map(employee -> employee.getName() + " " + employee.getLastName())
                    .filter(employee -> !answer.contains(employee) && employee.contains(template))
                    .forEach(answer::add);
        }
        if (answer.size() == 0) {
            System.out.printf("No employees with template '%s'%n", template);
        } else {
            System.out.println(answer);
        }
    }
}
