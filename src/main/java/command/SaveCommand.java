package command;

import manager.MusicBandManager;
import model.MusicBand;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Команда для сохранения коллекции в файл.
 */
public class SaveCommand implements Command {
    private final MusicBandManager manager;
    private final String filePath;

    /**
     * Конструктор команды save.
     *
     * @param manager  объект для управления коллекцией
     * @param filePath путь к файлу для сохранения
     */
    public SaveCommand(MusicBandManager manager, String filePath) {
        this.manager = manager;
        this.filePath = filePath;
    }

    /**
     * Метод для выполнения команды save.
     * Сохраняет коллекцию в файл в формате CSV.
     */
    @Override
    public void execute() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (MusicBand band : manager.getBands()) {
                writer.write(convertBandToCsv(band));
                writer.newLine();
            }
            System.out.println("Коллекция успешно сохранена в файл.");
        } catch (IOException e) {
            System.out.println("Ошибка при сохранении коллекции: " + e.getMessage());
        }
    }

    /**
     * Преобразует объект MusicBand в строку формата CSV.
     *
     * @param band объект музыкальной группы
     * @return строка с данными музыкальной группы в формате CSV
     */
    private String convertBandToCsv(MusicBand band) {
        return band.getId() + "," +
                band.getName() + "," +
                band.getCoordinates().getX() + "," +
                band.getCoordinates().getY() + "," +
                band.getCreationDate() + "," +
                (band.getNumberOfParticipants() != null ? band.getNumberOfParticipants() : "") + "," +
                band.getSinglesCount() + "," +
                band.getGenre() + "," +
                band.getStudio().getName() + "," +
                band.getStudio().getAddress();
    }
}

