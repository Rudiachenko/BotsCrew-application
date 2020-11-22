package application.console.answer;

import application.console.ConsoleHandler;
import application.model.Department;
import application.model.Employee;
import application.service.DepartmentService;
import java.util.List;
import java.util.Scanner;
import javax.persistence.NoResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CountOfEmployeeCommand implements ConsoleHandler {
    private final DepartmentService departmentService;

    @Autowired
    public CountOfEmployeeCommand(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @Override
    public void handleCommand() {
        System.out.println("Please specify the name of the "
                + "department or print 'menu' to back to menu");
        Scanner scanner = new Scanner(System.in);
        String nameOfDepartment = scanner.nextLine();
        if (nameOfDepartment.equalsIgnoreCase("menu")) {
            return;
        }
        try {
            Department department = departmentService.findByName(nameOfDepartment);
            List<Employee> employeesOfDepartment = department.getEmployees();
            System.out.println("Count of employee " + employeesOfDepartment.size());
        } catch (NoResultException e) {
            System.out.println("No departments with name " + nameOfDepartment + " was found."
                    + "Please try again.");
            handleCommand();
        }
    }
}
