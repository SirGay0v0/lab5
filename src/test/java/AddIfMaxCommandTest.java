import command.AddIfMaxCommand;
import invoker.CommandInvoker;
import manager.MusicBandManager;
import org.junit.Before;
import org.junit.Test;

import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class AddIfMaxCommandTest {

    private MusicBandManager manager;
    private CommandInvoker invoker;
    private Scanner scanner;

    @Before
    public void setUp() {
        manager = new MusicBandManager("test.json");
        invoker = new CommandInvoker();
    }

    @Test
    public void testAddIfMaxCommandWhenMax() {
        // Добавляем элемент, который станет максимальным
        String input = "The Rolling Stones\n60\n30\n5\nPSYCHEDELIC_ROCK\nMick Jagger\n175\nBROWN\nFRANCE\n2\n3\n4\nParis\n";
        scanner = new Scanner(input);
        AddIfMaxCommand addIfMaxCommand = new AddIfMaxCommand(manager, scanner);
        invoker.executeCommand(addIfMaxCommand);

        assertEquals(1, manager.getCollection().size());
    }

    @Test
    public void testAddIfMaxCommandWhenNotMax() {
        // Добавляем первый элемент (максимальный)
        String input1 = "The Rolling Stones\n60\n30\n5\nPSYCHEDELIC_ROCK\nMick Jagger\n175\nBROWN\nFRANCE\n2\n3\n4\nParis\n";
        scanner = new Scanner(input1);
        AddIfMaxCommand addFirstCommand = new AddIfMaxCommand(manager, scanner);
        invoker.executeCommand(addFirstCommand);

        // Добавляем второй элемент, который не должен быть добавлен, так как он меньше
        String input2 = "The Beatles\n50\n20\n4\nPOP\nJohn Lennon\n180\nBLACK\nGERMANY\n1\n2\n3\nLiverpool\n";
        scanner = new Scanner(input2);
        AddIfMaxCommand addIfMaxCommand = new AddIfMaxCommand(manager, scanner);
        invoker.executeCommand(addIfMaxCommand);

        // Проверяем, что коллекция содержит только первый элемент (так как второй меньше)
        assertEquals(1, manager.getCollection().size());
    }
}