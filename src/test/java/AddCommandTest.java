import command.AddCommand;
import invoker.CommandInvoker;
import manager.MusicBandManager;
import model.MusicBand;
import org.junit.Before;
import org.junit.Test;

import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class AddCommandTest {

    private MusicBandManager manager;
    private CommandInvoker invoker;
    private Scanner scanner;

    @Before
    public void setUp() {
        manager = new MusicBandManager("test.json"); // Инициализация менеджера
        invoker = new CommandInvoker();
        scanner = new Scanner(System.in); // Заглушка для симуляции пользовательского ввода
    }

    @Test
    public void testAddCommand() {
        // Устанавливаем примерный ввод данных
        String input = "The Beatles\n50\n20\n4\nPOP\nJohn Lennon\n180\nBLACK\nGERMANY\n1\n2\n3\nLiverpool\n";
        scanner = new Scanner(input);

        AddCommand addCommand = new AddCommand(manager, scanner);
        invoker.executeCommand(addCommand);

        // Проверка, что элемент был добавлен
        assertFalse(manager.getCollection().isEmpty());
        assertEquals(1, manager.getCollection().size());

        MusicBand band = manager.getCollection().first();
        assertEquals("The Beatles", band.getName());
        assertEquals(50.0, band.getCoordinates().getX(), 0.001);
    }
}