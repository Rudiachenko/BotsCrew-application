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
public class AverageSalaryCommand implements ConsoleHandler {
    private final DepartmentService departmentService;

    @Autowired
    public AverageSalaryCommand(DepartmentService departmentService) {
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
            List<Employee> employees = department.getEmployees();
            double salary = 0;
            for (Employee employee : employees) {
                salary = salary + employee.getSalary();
            }
            double averageSalary = Math.round(salary / employees.size() * 100.0) / 100.0;
            System.out.println("The average salary of " + nameOfDepartment + " " + averageSalary);
        } catch (NoResultException e) {
            System.out.println("No departments with name " + nameOfDepartment + " was found."
                    + "Please try again.");
            handleCommand();
        }
    }
}
