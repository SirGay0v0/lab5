package invoker;

import command.Command;

import java.util.LinkedList;

public class CommandInvoker {
    private final LinkedList<Command> commandHistory = new LinkedList<>();  
    private final int maxHistorySize = 5;  

    public void executeCommand(Command command) {
        command.execute();
        addToHistory(command);
    }

    private void addToHistory(Command command) {
        if (commandHistory.size() == maxHistorySize) {
            commandHistory.removeFirst();  
        }
        commandHistory.addLast(command);  
    }

    public void showHistory() {
        if (commandHistory.isEmpty()) {
            System.out.println("История команд пуста.");
            return;
        }

        System.out.println("Последние " + commandHistory.size() + " команд:");
        for (Command cmd : commandHistory) {
            System.out.println(cmd.getClass().getSimpleName());  
        }
    }
}