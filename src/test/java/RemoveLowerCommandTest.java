import command.AddCommand;
import command.RemoveLowerCommand;
import invoker.CommandInvoker;
import manager.MusicBandManager;
import org.junit.Before;
import org.junit.Test;

import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class RemoveLowerCommandTest {

    private MusicBandManager manager;
    private CommandInvoker invoker;
    private Scanner scanner;

    @Before
    public void setUp() {
        manager = new MusicBandManager("test.json");
        invoker = new CommandInvoker();

        // Добавляем элемент в коллекцию
        String input = "The Beatles\n50\n20\n4\nPOP\nJohn Lennon\n180\nBLACK\nGERMANY\n1\n2\n3\nLiverpool\n";
        scanner = new Scanner(input);
        AddCommand addCommand = new AddCommand(manager, scanner);
        invoker.executeCommand(addCommand);
    }

    @Test
    public void testRemoveLowerCommand() {
        // Добавляем команду remove_lower с элементом, который выше текущего
        String input = "The Rolling Stones\n60\n30\n5\nPSYCHEDELIC_ROCK\nMick Jagger\n175\nBROWN\nFRANCE\n2\n3\n4\nParis\n";
        scanner = new Scanner(input);
        RemoveLowerCommand removeLowerCommand = new RemoveLowerCommand(manager, scanner);
        invoker.executeCommand(removeLowerCommand);

        // Проверяем, что элемент был удалён
        assertEquals(0, manager.getCollection().size());
    }
}
