import command.AddCommand;
import command.RemoveByIdCommand;
import invoker.CommandInvoker;
import manager.MusicBandManager;
import org.junit.Before;
import org.junit.Test;

import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RemoveByIdCommandTest {

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
    public void testRemoveByIdCommand() {
        // Проверяем, что элемент существует
        assertEquals(1, manager.getCollection().size());

        // Пытаемся удалить элемент с id 1
        scanner = new Scanner("1");
        RemoveByIdCommand removeByIdCommand = new RemoveByIdCommand(manager, scanner);
        invoker.executeCommand(removeByIdCommand);

        // Проверяем, что элемент был удален
        assertTrue(manager.getCollection().isEmpty());
    }
}
