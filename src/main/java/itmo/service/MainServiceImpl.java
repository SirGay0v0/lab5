package itmo.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import itmo.creator.MusicBandCreator;
import itmo.model.MusicBand;
import itmo.repository.Repository;
import itmo.repository.RepositoryImpl;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.Scanner;

import static itmo.utils.Constants.filePath;

public class MainServiceImpl implements Service {

    private final Repository repository = new RepositoryImpl();
    private final MusicBandCreator creator = new MusicBandCreator();
    private final Deque<String> historyList = new ArrayDeque<>();
    private final ExecuteScriptService executeScriptService = new ExecuteScriptService();


    @Override
    public void help() {
        System.out.println(
                "help : вывести справку по доступным командам \n" +
                        "info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.) \n " +
                        "show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении \n" +
                        "add {element} : добавить новый элемент в коллекцию\n" +
                        "update id {element} : обновить значение элемента коллекции, id которого равен заданному\n" +
                        "remove_by_id id : удалить элемент из коллекции по его id\n" +
                        "clear : очистить коллекцию\n" +
                        "save : сохранить коллекцию в файл\n" +
                        "execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.\n" +
                        "exit : завершить программу (без сохранения в файл)\n" +
                        "add_if_max {element} : добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции\n" +
                        "remove_lower {element} : удалить из коллекции все элементы, меньшие, чем заданный\n" +
                        "history : вывести последние 5 команд (без их аргументов)\n" +
                        "print_ascending : вывести элементы коллекции в порядке возрастания\n" +
                        "print_unique_front_man : вывести уникальные значения поля frontMan всех элементов в коллекции\n" +
                        "print_field_descending_genre : вывести значения поля genre всех элементов в порядке убывания");
    }

    @Override
    public void info() {
        System.out.println(repository.info());
    }

    @Override
    public void show() {
        List<MusicBand> bandList = repository.returnAllElementsAsList();
        bandList.forEach(System.out::println);
    }

    @Override
    public void add() {
        MusicBand musicBand = creator.create();
        repository.add(musicBand);
    }

    @Override
    public void updateId(long id) {
    }

    @Override
    public void removeById(long id) {
        repository.deleteElementById(id);
    }

    @Override
    public void clear() {
        repository.clearCollection();
    }

    @Override
    public void save() {
        // Создаем объект Gson для преобразования коллекции в JSON
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(repository.returnAllElementsAsList()); // Преобразуем коллекцию в JSON-строку

        // Записываем JSON-строку в файл
        try (Writer writer = new OutputStreamWriter(new FileOutputStream(filePath), "UTF-8")) {
            writer.write(json);
            System.out.println("Коллекция успешно сохранена в файл.");
        } catch (IOException e) {
            System.out.println("Ошибка при записи коллекции в файл: " + e.getMessage());
        }
    }

    @Override
    public void executeScript(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String command;
            // Чтение команд построчно
            while ((command = reader.readLine()) != null) {
                // Выполняем каждую команду так, как будто она введена пользователем
                System.out.println("Выполнение команды: " + command);
                handleCommand(command.trim(), new Scanner(System.in)); // Исполнение команды
            }
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла скрипта: " + e.getMessage());
        }
    }

    @Override
    public void addIfMax() {

    }

    @Override
    public void removeLower() {

    }

    @Override
    public void history() {

    }

    @Override
    public void printAscending() {

    }

    @Override
    public void printUniqueFrontMan() {

    }

    @Override
    public void printFieldDescendingGenre() {

    }

    @Override
    public void readFromFile(String filePath) {

        // Чтение файла и преобразование его содержимого в коллекцию объектов MusicBand
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            // Создание объекта Gson
            Gson gson = new Gson();

            // Определение типа для десериализации
            Type musicBandListType = new TypeToken<List<MusicBand>>() {
            }.getType();

            // Преобразование JSON-данных в коллекцию
            List<MusicBand> musicBands = gson.fromJson(reader, musicBandListType);

        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
        }
    }

    private void handleCommand(String command, Scanner scanner) {
        addCommandToHistory(command);

        switch (command) {
            case "help":
                help();
                break;
            case "info":
                info();
                break;
            case "show":
                show();
                break;
            case "save":
                save();
                break;
            case "exit":
                System.exit(0);
                break;
            case "add":
                addElement(scanner);
                break;
            // Добавьте другие команды, которые вам нужны
            default:
                System.out.println("Неизвестная команда: " + command);
                break;
        }
    }

    private void addCommandToHistory(String command) {
        int maxSize = 5;
        historyList.addLast(command);
        if (historyList.size() >= maxSize) {
            historyList.removeFirst();
        }
    }
}

