import command.AddCommand;
import command.ClearCommand;
import invoker.CommandInvoker;
import manager.MusicBandManager;
import org.junit.Before;
import org.junit.Test;

import java.util.Scanner;

import static org.junit.Assert.assertTrue;

public class ClearCommandTest {

    private MusicBandManager manager;
    private CommandInvoker invoker;

    @Before
    public void setUp() {
        manager = new MusicBandManager("test.json");
        invoker = new CommandInvoker();

        // Добавляем элемент в коллекцию
        String input = "The Beatles\n50\n20\n4\nPOP\nJohn Lennon\n180\nBLACK\nGERMANY\n1\n2\n3\nLiverpool\n";
        Scanner scanner = new Scanner(input);
        AddCommand addCommand = new AddCommand(manager, scanner);
        invoker.executeCommand(addCommand);
    }

    @Test
    public void testClearCommand() {
        // Выполняем команду очистки
        ClearCommand clearCommand = new ClearCommand(manager);
        invoker.executeCommand(clearCommand);

        // Проверяем, что коллекция пуста
        assertTrue(manager.getCollection().isEmpty());
    }
}