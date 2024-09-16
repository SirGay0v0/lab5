package manager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.MusicBand;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.TreeSet;
/**
 * Класс для управления коллекцией музыкальных групп.
 * Обеспечивает добавление, удаление, обновление, сохранение и загрузку музыкальных групп в/из файла.
 * Коллекция хранится в виде отсортированного множества {@link TreeSet}, которое обеспечивает уникальность и автоматическую сортировку элементов.
 *
 * <p>При запуске приложение автоматически загружает коллекцию из указанного файла.</p>
 *
 * @author SirGay0v0
 */
public class MusicBandManager {
    private TreeSet<MusicBand> collection;
    private final String filePath;  
    private final LocalDateTime initTime;
    private long currentId = 1;

    public MusicBandManager(String filePath) {
        this.filePath = filePath;
        this.collection = new TreeSet<>();
        this.initTime = LocalDateTime.now();
        loadCollectionFromFile();
    }

    /**
     * Загружает коллекцию музыкальных групп из файла в формате JSON.
     * Использует библиотеку Gson для десериализации данных.
     *
     * @throws FileNotFoundException если файл с указанным именем не найден.
     * @throws IOException если произошла ошибка при чтении файла.
     */
    private void loadCollectionFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            Gson gson = new Gson();
            Type collectionType = new TypeToken<TreeSet<MusicBand>>() {
            }.getType();
            this.collection = gson.fromJson(reader, collectionType);
            if (collection == null) {
                this.collection = new TreeSet<>();
            }
            System.out.println("Коллекция успешно загружена.");
        } catch (IOException e) {
            System.out.println("Ошибка загрузки коллекции: " + e.getMessage());
        }
    }

    /**
     * Возвращает коллекцию музыкальных групп.
     *
     * @return {@link TreeSet} коллекция музыкальных групп.
     */
    public TreeSet<MusicBand> getCollection() {
        return collection;
    }

    /**
     * Удаляет все элементы меньше, чем заданный
     */
    public int removeLower(MusicBand newBand) {
        int removedCount = 0;
        Iterator<MusicBand> iterator = collection.iterator();
        while (iterator.hasNext()) {
            MusicBand band = iterator.next();
            if (band.compareTo(newBand) < 0) {
                iterator.remove();  
                removedCount++;
            }
        }
        return removedCount;  
    }

    /**
     * Удаляет музыкальную группу из коллекции по ее идентификатору.
     *
     * @param id Идентификатор музыкальной группы, которую нужно удалить.
     * @return true, если группа была успешно удалена, иначе false.
     */
    public boolean removeById(long id) {
        Iterator<MusicBand> iterator = collection.iterator();
        while (iterator.hasNext()) {
            MusicBand band = iterator.next();
            if (band.getId() == id) {
                iterator.remove();  
                return true;  
            }
        }
        return false;  
    }

    public long generateId() {
        return currentId++;
    }

    /**
     * Добавляет новую музыкальную группу в коллекцию.
     * Если группа с таким же идентификатором уже существует, она не будет добавлена.
     *
     * @param band Музыкальная группа для добавления.
     * @return true, если группа была успешно добавлена, иначе false.
     */
    public void addElement(MusicBand band) {
        collection.add(band);
    }

    /**
     * Сохраняет текущую коллекцию музыкальных групп в файл в формате JSON.
     * Все изменения, внесенные в коллекцию, будут сохранены.
     *
     * @throws IOException если возникает ошибка при записи в файл.
     */
    public void saveCollection() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            Gson gson = new Gson();
            writer.write(gson.toJson(collection));
            System.out.println("Коллекция успешно сохранена.");
        } catch (IOException e) {
            System.out.println("Ошибка при сохранении коллекции: " + e.getMessage());
        }
    }

    /**
     * Выводит в консоль все элементы коллекции
     */
    public void showCollection() {
        if (collection.isEmpty()) {
            System.out.println("Коллекция пуста.");
        } else {
            System.out.println("Элементы коллекции:");
            for (MusicBand band : collection) {
                System.out.println(band);
            }
        }
    }

    /**
     * Очищает коллекцию музыкальных групп, удаляя все элементы.
     */
    public void clearCollection() {
        collection.clear();
        System.out.println("Коллекция очищена.");
    }

    /**
     * Вщзвращает информацию о коллекции.
     */
    public String getInfo() {
        return String.format(
                "Тип коллекции: %s%nДата инициализации: %s%nКоличество элементов: %d",
                collection.getClass().getName(),
                initTime.toString(),
                collection.size()
        );
    }

    /**
     * Возвращает жлемент с заданным id или null если такогого нет
     */
    public MusicBand findById(long id) {
        for (MusicBand band : collection) {
            if (band.getId() == id) {
                return band;  
            }
        }
        return null;  
    }

    /**
     * Обновляет информацию о музыкальной группе с указанным идентификатором.
     *
     * @param id Идентификатор группы, которую нужно обновить.
     * @param updatedBand Объект {@link MusicBand}, содержащий обновленные данные.
     * @return true, если группа была успешно обновлена, иначе false.
     */
    public void updateElement(long id, MusicBand updatedBand) {
        MusicBand band = findById(id);
        if (band != null) {
            collection.remove(band);  
            collection.add(updatedBand);  
            System.out.println("Элемент с id " + id + " обновлен.");
        } else {
            System.out.println("Элемент с id " + id + " не найден.");
        }
    }

    /**
     * Проверяет, является ли указанная музыкальная группа самой большой по количеству участников в коллекции.
     * Используется в команде {@code add_if_max}.
     *
     * @param band Музыкальная группа для сравнения.
     * @return true, если группа больше всех остальных по количеству участников, иначе false.
     */
    public boolean isMax(MusicBand newBand) {
        if (collection.isEmpty()) {
            return true;  
        }

        MusicBand maxBand = collection.last();  
        return newBand.compareTo(maxBand) > 0;  
    }
}
