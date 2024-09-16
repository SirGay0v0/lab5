import command.AddCommand;
import command.PrintFieldDescendingGenreCommand;
import invoker.CommandInvoker;
import manager.MusicBandManager;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.Assert.assertTrue;

public class PrintFieldDescendingGenreCommandTest {

    private MusicBandManager manager;
    private CommandInvoker invoker;
    private ByteArrayOutputStream outputStream;

    @Before
    public void setUp() {
        manager = new MusicBandManager("test.json");
        invoker = new CommandInvoker();

        // Перенаправляем вывод в память
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Добавляем элементы в коллекцию
        String input = "The Beatles\n50\n20\n4\nPOP\nJohn Lennon\n180\nBLACK\nGERMANY\n1\n2\n3\nLiverpool\n";
        Scanner scanner = new Scanner(input);
        AddCommand addCommand = new AddCommand(manager, scanner);
        invoker.executeCommand(addCommand);
    }

    @Test
    public void testPrintFieldDescendingGenreCommand() {
        // Выполняем команду print_field_descending_genre
        PrintFieldDescendingGenreCommand printFieldDescendingGenreCommand = new PrintFieldDescendingGenreCommand(manager);
        invoker.executeCommand(printFieldDescendingGenreCommand);

        // Проверяем, что вывод содержит жанры в порядке убывания
        String output = outputStream.toString();
        assertTrue(output.contains("POP"));
    }
}