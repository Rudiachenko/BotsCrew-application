package application.console.answer;

import application.console.ConsoleHandler;
import application.model.Department;
import application.model.Employee;
import application.service.DepartmentService;
import java.util.Scanner;
import javax.persistence.NoResultException;
import org.springframework.stereotype.Component;

@Component
public class DepartmentHeadNameCommand implements ConsoleHandler {
    private final DepartmentService departmentService;

    public DepartmentHeadNameCommand(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @Override
    public void handleCommand() {
        System.out.println("Please specify the name of the "
                + "department or enter 'menu' to back to menu");
        Scanner scanner = new Scanner(System.in);
        String nameOfDepartment = scanner.nextLine();
        if (nameOfDepartment.equalsIgnoreCase("menu")) {
            return;
        }
        try {
            Department department = departmentService.findByName(nameOfDepartment);
            Employee theHeadOfDepartment = department.getHeadOfDepartment();
            String answer = " " + "Head of " + nameOfDepartment
                    + " department is "
                    + theHeadOfDepartment.getName() + " "
                    + theHeadOfDepartment.getLastName();
            System.out.println(answer);
        } catch (NoResultException e) {
            System.out.println("No departments with name " + nameOfDepartment + " was found."
                    + "Please try again.");
            handleCommand();
        }
    }
}
