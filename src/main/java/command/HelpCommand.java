package command;
/**
 * Класс для команды вывода справочной информации.
 * Отображает список всех доступных команд и краткое описание каждой из них.
 */
public class HelpCommand implements Command {

    @Override
    public void execute() {
        System.out.println("Доступные команды:");
        System.out.println("help : вывести справку по доступным командам");
        System.out.println("info : вывести информацию о коллекции");
        System.out.println("history : вывести последние 5 команд");
        System.out.println("show : вывести все элементы коллекции");
        System.out.println("add : добавить новый элемент");
        System.out.println("update id : обновить элемент по его id");
        System.out.println("remove_by_id id : удалить элемент по его id");
        System.out.println("clear : очистить коллекцию");
        System.out.println("save : сохранить коллекцию в файл");
        System.out.println("add_if_max : добавить новый элемент, если его значение превышает наибольший элемент");
        System.out.println("remove_lower : удалить все элементы, которые меньше заданного");
        System.out.println("print_ascending : вывести элементы в порядке возрастания");
        System.out.println("print_unique_front_man : вывести уникальные значения поля frontMan");
        System.out.println("print_field_descending_genre : вывести значения поля genre в порядке убывания");
        System.out.println("execute_script file_name : считать и исполнить скрипт из указанного файла");
        System.out.println("exit : завершить программу");
    }
}
