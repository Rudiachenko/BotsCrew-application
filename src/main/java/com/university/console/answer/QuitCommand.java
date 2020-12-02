package com.university.console.answer;

import com.university.console.ConsoleHandler;
import org.springframework.stereotype.Component;

@Component
public class QuitCommand implements ConsoleHandler {
    @Override
    public void handleCommand() {
        System.exit(0);
    }
}
