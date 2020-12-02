package com.university.console.answer;

import com.university.console.ConsoleHandler;
import com.university.model.Employee;
import com.university.service.DepartmentService;
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
        System.out.println("Please specify template or enter 'menu' to back to menu");
        Scanner scanner = new Scanner(System.in);
        String template = scanner.nextLine();
        if (template.equalsIgnoreCase("menu")) {
            return;
        }
        findEmployeesByContainsTemplate(template);
    }

    private void findEmployeesByContainsTemplate(String template) {
        List<Employee> answer = departmentService.findByContains(template);
        if (answer.size() == 0) {
            System.out.printf("No employees with template '%s'%n", template);
        } else {
            System.out.printf("All employees whose name contains '%s':%n", template);
            answer.stream()
                    .map(employee -> employee.getName() + " " + employee.getLastName())
                    .forEach(System.out::println);
        }
    }
}
