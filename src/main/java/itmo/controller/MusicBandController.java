package itmo.controller;

import itmo.service.Service;
import itmo.service.MainServiceImpl;

import static itmo.utils.Constants.filePath;

public class MusicBandController {
    private final Service service;

    public MusicBandController() {
        this.service = new MainServiceImpl();
    }

    public void controllerRun() {
        service.readFromFile(filePath);
        String command;

        do {
            System.out.print("Введите команду: ");
            command = System.console().readLine();
            String[] commandParts = command.split(" ");

            switch (commandParts[0]) {
                case "help": {
                    service.help();
                    break;
                }
                case "show": {
                    service.show();
                    break;
                }
                case "add": {
                    service.add();
                    break;
                }
                case "info": {
                    service.info();
                    break;
                }
                case "update": {
                    service.updateId(Long.parseLong(commandParts[1]));
                    break;
                }
                case "remove_by_id": {
                    service.removeById(Long.parseLong(commandParts[1]));
                    break;
                }
                case "clear": {
                    service.clear();
                    break;
                }
                case "save": {
                    service.save();
                    break;
                }
                case "execute_script": {
                    service.executeScript(commandParts[1]);
                    break;
                }
                case "add_if_max": {
                    service.addIfMax();
                    break;
                }
                case "remove_lower": {
                    service.removeLower();
                    break;
                }
                case "history": {
                    service.history();
                    break;
                }
                case "print_ascending": {
                    service.printAscending();
                    break;
                }
                case "print_unique_front_man": {
                    service.printUniqueFrontMan();
                    break;
                }
                case "print_field_descending_genre": {
                    service.printFieldDescendingGenre();
                    break;
                }
                default: {
                    System.out.println("Такой команды не существует. Для справки испольлзуйте команду \"help\"");
                    break;
                }
            }
        } while (!command.equals("exit"));
    }
}
