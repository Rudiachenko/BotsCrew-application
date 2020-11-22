package application.console.answer;

import application.console.Application;
import application.console.ConsoleHandler;
import application.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MenuCommand implements ConsoleHandler {
    private final DepartmentService departmentService;

    @Autowired
    public MenuCommand(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @Override
    public void handleCommand() {
        new Application(departmentService).start();
    }
}
