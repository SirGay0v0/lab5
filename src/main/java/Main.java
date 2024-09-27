import command.AddCommand;

import command.AddIfMinCommand;
import command.ClearCommand;
import command.ExecuteScriptCommand;
import command.ExitCommand;
import command.FilterContainsNameCommand;
import command.InfoCommand;
import command.MaxByCoordinatesCommand;
import command.PrintFieldAscendingNumberOfParticipantsCommand;
import command.RemoveByIdCommand;
import command.RemoveGreaterCommand;
import command.RemoveHeadCommand;
import command.SaveCommand;
import command.ShowCommand;
import command.UpdateCommand;
import command.HelpCommand;
import invoker.CommandInvoker;
import manager.MusicBandManager;

import java.io.File;
import java.util.Scanner;

/**
 * Главный класс для запуска консольного приложения.
 */
public class Main {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Ошибка: не указан файл для загрузки данных.");
            System.out.println("Использование: java -jar main.jar <file.csv>");
            return;
        }

        String filePath = args[0];
        File file = new File(filePath);

        if (!file.exists() || !file.isFile()) {
            System.out.println("Ошибка: файл не найден или указан неверный путь.");
            return;
        }

        MusicBandManager manager = new MusicBandManager();

        // Загрузка данных из CSV файла
        try {
            manager.loadFromCsv(filePath);
            System.out.println("Данные успешно загружены из файла: " + filePath);
        } catch (Exception e) {
            System.out.println("Ошибка при загрузке данных: " + e.getMessage());
            return;
        }

        // Далее запускается основной цикл работы программы (ввод команд)
        CommandInvoker invoker = new CommandInvoker();
        Scanner scanner = new Scanner(System.in);

        // Регистрация команд
        invoker.register("help", new HelpCommand());
        invoker.register("info", new InfoCommand(manager));
        invoker.register("show", new ShowCommand(manager));
        invoker.register("add", new AddCommand(manager, scanner));
        invoker.register("update", new UpdateCommand(manager, scanner));
        invoker.register("remove_by_id", new RemoveByIdCommand(manager, scanner));
        invoker.register("clear", new ClearCommand(manager));
        invoker.register("save", new SaveCommand(manager, filePath));
        invoker.register("execute_script", new ExecuteScriptCommand(invoker, scanner));
        invoker.register("exit", new ExitCommand());
        invoker.register("remove_head", new RemoveHeadCommand(manager));
        invoker.register("add_if_min", new AddIfMinCommand(manager, scanner));
        invoker.register("remove_greater", new RemoveGreaterCommand(manager, scanner));
        invoker.register("max_by_coordinates", new MaxByCoordinatesCommand(manager));
        invoker.register("filter_contains_name", new FilterContainsNameCommand(manager, scanner));
        invoker.register("print_field_ascending_number_of_participants", new PrintFieldAscendingNumberOfParticipantsCommand(manager));

        // Основной цикл приложения
        while (true) {
            System.out.print("> ");
            String commandName = scanner.nextLine().trim();
            invoker.executeCommand(commandName);
        }
    }
}
