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
public class StatisticCommand implements ConsoleHandler {
    private static final Employee.Degree PROFESSOR = Employee.Degree.PROFESSOR;
    private static final Employee.Degree ASSISTANT = Employee.Degree.ASSISTANT;
    private static final Employee.Degree ASSOCIATE_PROFESSOR = Employee.Degree.ASSOCIATE_PROFESSOR;
    private final DepartmentService departmentService;

    @Autowired
    public StatisticCommand(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @Override
    public void handleCommand() {
        System.out.println("Please specify the name of the "
                + "department or print 'menu' to back to menu.");
        Scanner scanner = new Scanner(System.in);
        String nameOfDepartment = scanner.nextLine();
        if (nameOfDepartment.equalsIgnoreCase("menu")) {
            return;
        }
        try {
            Department department = departmentService.findByName(nameOfDepartment);
            List<Employee> employees = department.getEmployees();
            int assistants = 0;
            int associateProfessors = 0;
            int professors = 0;
            for (Employee employee : employees) {
                if (PROFESSOR.equals(employee.getTitle())) {
                    professors++;
                } else if (ASSISTANT.equals(employee.getTitle())) {
                    assistants++;
                } else if (ASSOCIATE_PROFESSOR.equals(employee.getTitle())) {
                    associateProfessors++;
                }
            }
            System.out.println("assistants: " + assistants + "\n"
                    + "associate professors: " + associateProfessors + "\n"
                    + "professors: " + professors);
        } catch (NoResultException e) {
            System.out.println("No departments with name " + nameOfDepartment + " was found."
                    + "Please try again.");
            handleCommand();
        }
    }
}
