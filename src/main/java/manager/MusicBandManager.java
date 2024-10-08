package manager;

import model.Coordinates;
import model.MusicBand;
import model.MusicGenre;
import model.Studio;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * Класс для управления коллекцией музыкальных групп.
 */
public class MusicBandManager {
    private final List<MusicBand> bands;
    private final LocalDateTime initializationDate;

    public long generateId() {
        // Если коллекция пуста, начнем с id 1
        if (bands.isEmpty()) {
            return 1L;
        }

        return bands.stream()
                .mapToLong(MusicBand::getId)
                .max()
                .orElse(0L) + 1;
    }

    /**
     * Конструктор класса MusicBandManager.
     * Инициализирует коллекцию музыкальных групп и дату инициализации.
     */
    public MusicBandManager() {
        this.bands = new LinkedList<>();
        this.initializationDate = LocalDateTime.now();
    }

    /**
     * Возвращает коллекцию музыкальных групп.
     *
     * @return коллекция музыкальных групп
     */
    public List<MusicBand> getBands() {
        return bands;
    }

    /**
     * Возвращает дату инициализации коллекции.
     *
     * @return дата инициализации коллекции
     */
    public LocalDateTime getInitializationDate() {
        return initializationDate;
    }

    /**
     * Добавляет музыкальную группу в коллекцию.
     *
     * @param band музыкальная группа для добавления
     */
    public void addBand(MusicBand band) {
        bands.add(band);
    }

    /**
     * Удаляет музыкальную группу по id.
     *
     * @param id уникальный идентификатор музыкальной группы
     * @return true, если группа была успешно удалена, иначе false
     */
    public boolean removeById(long id) {
        return bands.removeIf(band -> band.getId() == id);
    }

    /**
     * Очищает коллекцию музыкальных групп.
     */
    public void clear() {
        bands.clear();
    }

    /**
     * Возвращает элемент с максимальными координатами.
     *
     * @return музыкальная группа с максимальными координатами
     */
    public MusicBand getBandWithMaxCoordinates() {
        return bands.stream()
                .max(Comparator.comparing(MusicBand::getCoordinates))
                .orElse(null);
    }

    /**
     * Возвращает минимальный элемент в коллекции.
     *
     * @return минимальный элемент коллекции
     */
    public MusicBand getMinElement() {
        return bands.stream()
                .min(Comparator.naturalOrder())
                .orElse(null);
    }

    /**
     * Возвращает элемент в коллекции с заданным ID.
     *
     * @return Элемент с ID равным заданному
     */
    public MusicBand getBandById(long id) {
        return bands.stream()
                .filter(band -> band.getId() == id)
                .findFirst()
                .orElse(null);
    }

    /**
     * Загружает коллекцию из файла в формате CSV.
     *
     * @param filePath путь к файлу для загрузки
     * @throws IOException если произошла ошибка при чтении из файла
     */
    public void loadFromCsv(String filePath) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int lineNumber = 0;
            while ((line = br.readLine()) != null) {
                lineNumber++;
                try {
                    MusicBand band = parseBandFromCsv(line);
                    bands.add(band);
                } catch (Exception e) {
                    System.err.println("Ошибка при обработке строки " + lineNumber + ": " + e.getMessage());
                }
            }
        }
    }

    /**
     * Парсит строку CSV и возвращает объект MusicBand.
     *
     * @param csv строка CSV
     * @return объект музыкальной группы
     */
    private MusicBand parseBandFromCsv(String csv) {
        String[] fields = csv.split(",");
        long id = Long.parseLong(fields[0]);
        String name = fields[1];
        Double x = Double.parseDouble(fields[2]);
        Long y = Long.parseLong(fields[3]);
        Coordinates coordinates = new Coordinates(x, y);

        LocalDateTime creationDate = LocalDateTime.parse(fields[4]);

        Integer numberOfParticipants = fields[5].isEmpty() ? null : Integer.parseInt(fields[5]);
        long singlesCount = Long.parseLong(fields[6]);
        MusicGenre genre = MusicGenre.valueOf(fields[7]);
        Studio studio = new Studio(fields[8], fields[9]);

        return new MusicBand(id, name, coordinates, creationDate, numberOfParticipants, singlesCount, genre, studio);
    }
}
