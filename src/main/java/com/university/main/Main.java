package com.university.main;

import com.university.config.AppConfig;
import com.university.console.Application;
import com.university.service.DataInjector;
import com.university.service.DepartmentService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);
        DepartmentService departmentService = context.getBean(DepartmentService.class);

        new DataInjector(departmentService).injectData();

        Application application = new Application(departmentService);
        application.start();
    }
}
