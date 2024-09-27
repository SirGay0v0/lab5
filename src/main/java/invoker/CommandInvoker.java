package invoker;

import command.Command;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Класс CommandInvoker управляет выполнением команд.
 * Он хранит зарегистрированные команды и позволяет выполнять их по имени.
 */
public class CommandInvoker {
    private final Map<String, Command> commandMap = new HashMap<>();
    private final LinkedList<String> commandHistory = new LinkedList<>();

    /**
     * Регистрация команды с именем.
     *
     * @param commandName имя команды
     * @param command объект команды
     */
    public void register(String commandName, Command command) {
        commandMap.put(commandName, command);
    }

    /**
     * Выполняет команду по её имени.
     *
     * @param commandName строка, содержащая имя команды
     */
    public void executeCommand(String commandName) {
        Command command = commandMap.get(commandName);
        if (command != null) {
            command.execute();
            addToHistory(commandName);
        } else {
            System.out.println("Команда не найдена: " + commandName);
        }
    }

    /**
     * Добавляет команду в историю с ограничением на количество элементов.
     *
     * @param commandName имя выполненной команды
     */
    private void addToHistory(String commandName) {
        // Максимальный размер истории
        int maxHistorySize = 5;
        if (commandHistory.size() == maxHistorySize) {
            commandHistory.removeFirst();
        }
        commandHistory.addLast(commandName);
    }

    /**
     * Выводит последние выполненные команды (максимум 5).
     */
    public void showHistory() {
        if (commandHistory.isEmpty()) {
            System.out.println("История команд пуста.");
            return;
        }

        System.out.println("Последние выполненные команды:");
        for (String cmd : commandHistory) {
            System.out.println(cmd);
        }
    }
}