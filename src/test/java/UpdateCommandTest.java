import command.AddCommand;
import command.UpdateCommand;
import invoker.CommandInvoker;
import manager.MusicBandManager;
import model.MusicBand;
import org.junit.Before;
import org.junit.Test;


import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class UpdateCommandTest {

    private MusicBandManager manager;
    private CommandInvoker invoker;
    private Scanner scanner;

    @Before
    public void setUp() {
        manager = new MusicBandManager("test.json"); // Инициализация менеджера
        invoker = new CommandInvoker();

        // Добавляем тестовый элемент
        String input = "The Beatles\n50\n20\n4\nPOP\nJohn Lennon\n180\nBLACK\nGERMANY\n1\n2\n3\nLiverpool\n";
        scanner = new Scanner(input);
        AddCommand addCommand = new AddCommand(manager, scanner);
        invoker.executeCommand(addCommand);
    }

    @Test
    public void testUpdateCommand() {
        // Устанавливаем новые данные для обновления
        String input = "The Rolling Stones\n60\n30\n5\nPSYCHEDELIC_ROCK\nMick Jagger\n175\nBROWN\nFRANCE\n2\n3\n4\nParis\n";
        scanner = new Scanner(input);

        // Выполняем команду update для обновления элемента с id 1
        UpdateCommand updateCommand = new UpdateCommand(manager, 1, scanner);
        invoker.executeCommand(updateCommand);

        // Проверяем, что элемент обновлён
        MusicBand band = manager.getCollection().first();
        assertEquals("The Rolling Stones", band.getName());
        assertEquals(60.0, band.getCoordinates().getX(), 0.001);
        assertEquals(30, band.getCoordinates().getY().longValue());
        assertEquals(5, band.getNumberOfParticipants());
    }
}
