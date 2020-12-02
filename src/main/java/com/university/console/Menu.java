package com.university.console;

public class Menu {
    public void showMenu() {
        System.out.println("Choose the next step:");
        System.out.println("1. Enter 'Who is head of department' to show head of department");
        System.out.println("2. Enter 'Show statistics' to show statistics");
        System.out.println("3. Enter 'Show the average salary for "
                + "the department' to show average salary");
        System.out.println("4. Enter 'Show number of employees' to show count of employee");
        System.out.println("5. Enter 'Global search' to show employees who contains {template}");
        System.out.println("6. Enter 'quit' to close application");
    }
}
