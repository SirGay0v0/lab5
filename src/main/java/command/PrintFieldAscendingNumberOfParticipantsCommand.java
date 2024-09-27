package command;

import manager.MusicBandManager;
import model.MusicBand;

import java.util.Comparator;

/**
 * Команда для вывода значений поля numberOfParticipants всех элементов в порядке возрастания.
 */
public class PrintFieldAscendingNumberOfParticipantsCommand implements Command {
    private final MusicBandManager manager;

    /**
     * Конструктор команды print_field_ascending_number_of_participants.
     *
     * @param manager объект для управления коллекцией
     */
    public PrintFieldAscendingNumberOfParticipantsCommand(MusicBandManager manager) {
        this.manager = manager;
    }

    /**
     * Метод для выполнения команды print_field_ascending_number_of_participants.
     * Выводит значения поля numberOfParticipants всех элементов в порядке возрастания.
     */
    @Override
    public void execute() {
        if (manager.getBands().isEmpty()) {
            System.out.println("Коллекция пуста.");
            return;
        }

        manager.getBands().stream()
                .filter(band -> band.getNumberOfParticipants() != null)
                .sorted(Comparator.comparing(MusicBand::getNumberOfParticipants))
                .forEach(band -> System.out.println("Number of participants: " + band.getNumberOfParticipants()));
    }
}
