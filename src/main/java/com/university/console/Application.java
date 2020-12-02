package com.university.console;

import com.university.console.answer.AverageSalaryCommand;
import com.university.console.answer.CalculateStatisticsCommand;
import com.university.console.answer.DepartmentHeadNameCommand;
import com.university.console.answer.GlobalSearchCommand;
import com.university.console.answer.MenuCommand;
import com.university.console.answer.NumberOfEmployeeCommand;
import com.university.console.answer.QuitCommand;
import com.university.service.DepartmentService;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Application {
    private static final String HEAD_OF_DEPARTMENT = "who is head of department";
    private static final String STATISTICS = "show statistics";
    private static final String AVERAGE_SALARY = "show the average salary for the department";
    private static final String COUNT_OF_EMPLOYEE = "show number of employees";
    private static final String GLOBAL_SEARCH = "global search";
    private static final String QUIT = "quit";
    private static final String MENU = "menu";
    private final Map<String, ConsoleHandler> operation = new HashMap<>();
    private final DepartmentService departmentService;

    @Autowired
    public Application(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    public void start() {
        new Menu().showMenu();
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();
        handleCommand(command);
    }

    public void handleCommand(String command) {
        operation.put(HEAD_OF_DEPARTMENT, new DepartmentHeadNameCommand(departmentService));
        operation.put(STATISTICS, new CalculateStatisticsCommand(departmentService));
        operation.put(AVERAGE_SALARY, new AverageSalaryCommand(departmentService));
        operation.put(COUNT_OF_EMPLOYEE, new NumberOfEmployeeCommand(departmentService));
        operation.put(GLOBAL_SEARCH, new GlobalSearchCommand(departmentService));
        operation.put(QUIT, new QuitCommand());
        operation.put(MENU, new MenuCommand(departmentService));

        command = command.toLowerCase();
        while (true) {
            if (!operation.containsKey(command)) {
                System.out.println("Enter correct data please or enter "
                        + "'quit' to close application");
                start();
            } else if (operation.containsKey(command)) {
                operation.get(command).handleCommand();
                command = "menu";
            }
        }
    }
}
