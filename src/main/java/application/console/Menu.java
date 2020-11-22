package application.console;

public class Menu {
    public void showMenu() {
        System.out.println("Choose the next step:");
        System.out.println("Print 'Who is head of department' to show head of department");
        System.out.println("Print 'Show statistics' to show statistics");
        System.out.println("Print 'Show the average salary for "
                + "the department' to show average salary");
        System.out.println("Print 'Show count of employee' to show count of employee");
        System.out.println("Print 'Global search' to show employees who contains {template}");
        System.out.println("Print 'quit' to quit");
    }
}
