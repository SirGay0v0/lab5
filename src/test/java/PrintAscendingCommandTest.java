import command.AddCommand;
import command.PrintAscendingCommand;
import invoker.CommandInvoker;
import manager.MusicBandManager;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.Assert.assertTrue;

public class PrintAscendingCommandTest {

    private MusicBandManager manager;
    private CommandInvoker invoker;
    private Scanner scanner;
    private ByteArrayOutputStream outputStream;

    @Before
    public void setUp() {
        manager = new MusicBandManager("test.json");
        invoker = new CommandInvoker();
        scanner = new Scanner(System.in);

        // Перенаправляем вывод в память
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Добавляем тестовые элементы
        String input = "The Beatles\n50\n20\n4\nPOP\nJohn Lennon\n180\nBLACK\nGERMANY\n1\n2\n3\nLiverpool\n";
        scanner = new Scanner(input);
        AddCommand addCommand = new AddCommand(manager, scanner);
        invoker.executeCommand(addCommand);
    }

    @Test
    public void testPrintAscendingCommand() {
        // Выполняем команду print_ascending
        PrintAscendingCommand printAscendingCommand = new PrintAscendingCommand(manager);
        invoker.executeCommand(printAscendingCommand);

        // Проверяем вывод
        String output = outputStream.toString();
        assertTrue(output.contains("The Beatles"));
    }
}
