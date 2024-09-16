package command;

import invoker.CommandInvoker;
import manager.MusicBandManager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
/**
 * Класс для команды выполнения скрипта из файла.
 * Считывает команды из указанного файла и выполняет их последовательно.
 */
public class ExecuteScriptCommand implements Command {
    private MusicBandManager manager;
    private CommandInvoker invoker;
    private Scanner scanner;
    private String fileName;

    public ExecuteScriptCommand(MusicBandManager manager, CommandInvoker invoker, Scanner scanner, String fileName) {
        this.manager = manager;
        this.invoker = invoker;
        this.scanner = scanner;
        this.fileName = fileName;
    }

    @Override
    public void execute() {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("> " + line);
                executeCommandFromScript(line.trim());
            }
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
        }
    }

    private void executeCommandFromScript(String command) {
        
        switch (command) {
            case "help":
                invoker.executeCommand(new HelpCommand());
                break;

            case "info":
                invoker.executeCommand(new InfoCommand(manager));
                break;

            case "show":
                invoker.executeCommand(new ShowCommand(manager));
                break;

            case "add":
                invoker.executeCommand(new AddCommand(manager, scanner));
                break;

            case "update":
                System.out.print("Введите id элемента, который хотите обновить: ");
                long idToUpdate = Long.parseLong(scanner.nextLine());
                invoker.executeCommand(new UpdateCommand(manager, idToUpdate, scanner));
                break;

            case "remove_by_id":
                System.out.print("Введите id элемента, который нужно удалить: ");
                long idToRemove = Long.parseLong(scanner.nextLine());
                invoker.executeCommand(new RemoveByIdCommand(manager, scanner));
                break;

            case "clear":
                invoker.executeCommand(new ClearCommand(manager));
                break;

            case "save":
                invoker.executeCommand(new SaveCommand(manager));
                break;

            case "add_if_max":
                invoker.executeCommand(new AddIfMaxCommand(manager, scanner));
                break;

            case "remove_lower":
                invoker.executeCommand(new RemoveLowerCommand(manager, scanner));
                break;

            case "print_ascending":
                invoker.executeCommand(new PrintAscendingCommand(manager));
                break;

            case "print_unique_front_man":
                invoker.executeCommand(new PrintUniqueFrontManCommand(manager));
                break;

            case "print_field_descending_genre":
                invoker.executeCommand(new PrintFieldDescendingGenreCommand(manager));
                break;

            case "exit":
                System.out.println("Завершение выполнения скрипта.");
                return;

            default:
                System.out.println("Неизвестная команда в скрипте: " + command);
                break;
        }
    }
}
