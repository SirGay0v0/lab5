import command.AddCommand;
import command.AddIfMaxCommand;
import command.ClearCommand;
import command.ExecuteScriptCommand;
import command.InfoCommand;
import command.PrintAscendingCommand;
import command.PrintFieldDescendingGenreCommand;
import command.PrintUniqueFrontManCommand;
import command.RemoveByIdCommand;
import command.RemoveLowerCommand;
import command.SaveCommand;
import command.ShowCommand;
import command.UpdateCommand;
import command.HelpCommand;
import invoker.CommandInvoker;
import manager.MusicBandManager;

import java.util.Scanner;

/**
 * Основной класс программы для управления коллекцией музыкальных групп.
 * Предоставляет интерактивный режим, в котором пользователи могут вводить команды для работы с коллекцией.
 */
public class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java Main <file>");
            return;
        }

        MusicBandManager manager = new MusicBandManager(args[0]);
        CommandInvoker invoker = new CommandInvoker();
        Scanner scanner = new Scanner(System.in);

        String command;

        System.out.println("Введите команду (help для списка команд):");
        while (true) {
            System.out.print("> ");
            command = scanner.nextLine().trim();

            switch (command) {
                case "help":
                    invoker.executeCommand(new HelpCommand());
                    break;
                case "info":
                    invoker.executeCommand(new InfoCommand(manager));
                    break;

                case "history":
                    invoker.showHistory();
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

                case "execute_script":
                    System.out.print("Введите имя файла: ");
                    String fileName = scanner.nextLine().trim();
                    invoker.executeCommand(new ExecuteScriptCommand(manager, invoker, scanner, fileName));
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
                    System.out.println("Завершение работы.");
                    return;

                default:
                    System.out.println("Неизвестная команда. Введите 'help' для списка команд.");
                    break;
            }
        }
    }
}
