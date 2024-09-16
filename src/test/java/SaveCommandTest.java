import command.AddCommand;
import command.SaveCommand;
import invoker.CommandInvoker;
import manager.MusicBandManager;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.Scanner;

import static org.junit.Assert.assertTrue;

public class SaveCommandTest {

    private MusicBandManager manager;
    private CommandInvoker invoker;

    @Before
    public void setUp() {
        manager = new MusicBandManager("test.json");
        invoker = new CommandInvoker();

        String input = "The Beatles\n50\n20\n4\nPOP\nJohn Lennon\n180\nBLACK\nGERMANY\n1\n2\n3\nLiverpool\n";
        Scanner scanner = new Scanner(input);
        AddCommand addCommand = new AddCommand(manager, scanner);
        invoker.executeCommand(addCommand);
    }

    @Test
    public void testSaveCommand() {
        SaveCommand saveCommand = new SaveCommand(manager);
        invoker.executeCommand(saveCommand);

        File file = new File("test.json");
        assertTrue(file.exists());
        assertTrue(file.length() > 0);

        file.delete();
    }
}