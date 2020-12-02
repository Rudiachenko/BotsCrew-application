package com.university.console.answer;

import com.university.console.ConsoleHandler;
import com.university.model.Department;
import com.university.model.Employee;
import com.university.service.DepartmentService;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import javax.persistence.NoResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CalculateStatisticsCommand implements ConsoleHandler {
    private final DepartmentService departmentService;

    @Autowired
    public CalculateStatisticsCommand(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @Override
    public void handleCommand() {
        System.out.println("Please specify the name of the "
                + "department or enter 'menu' to back to menu.");
        Scanner scanner = new Scanner(System.in);
        String nameOfDepartment = scanner.nextLine();
        if (nameOfDepartment.equalsIgnoreCase("menu")) {
            return;
        }
        try {
            calculateStatistics(nameOfDepartment);
        } catch (NoResultException e) {
            System.out.println("No departments with name " + nameOfDepartment + " was found."
                    + "Please try again.");
            handleCommand();
        }
    }

    private void calculateStatistics(String nameOfDepartment) {
        Department department = departmentService.findByName(nameOfDepartment);
        List<Employee> employees = department.getEmployees();
        Map<Employee.Degree, List<Employee>> statistics = employees.stream()
                .collect(Collectors.groupingBy(Employee::getTitle));

        statistics.entrySet().stream()
                .map(entry -> entry.getKey() + ": " + entry.getValue().size())
                .forEach(System.out::println);
    }
}
