package command;

import invoker.CommandInvoker;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * Команда для выполнения команд из указанного файла скрипта.
 */
public class ExecuteScriptCommand implements Command {
    private final CommandInvoker invoker;
    private final Scanner scanner;

    /**
     * Конструктор команды execute_script.
     *
     * @param invoker объект инвокера для выполнения команд
     * @param scanner объект для считывания ввода
     */
    public ExecuteScriptCommand(CommandInvoker invoker, Scanner scanner) {
        this.invoker = invoker;
        this.scanner = scanner;
    }

    /**
     * Метод для выполнения команды execute_script.
     * Выполняет команды, содержащиеся в указанном файле.
     */
    @Override
    public void execute() {
        System.out.print("Введите имя файла для выполнения скрипта: ");
        String fileName = scanner.nextLine().trim();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("Выполнение команды: " + line);
                invoker.executeCommand(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Ошибка: файл не найден.");
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла скрипта: " + e.getMessage());
        }
    }
}