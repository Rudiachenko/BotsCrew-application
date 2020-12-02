package com.university.console.answer;

import com.university.console.Application;
import com.university.console.ConsoleHandler;
import com.university.service.DepartmentService;
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
