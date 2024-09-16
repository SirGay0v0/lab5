import command.InfoCommand;
import invoker.CommandInvoker;
import manager.MusicBandManager;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertTrue;

public class InfoCommandTest {

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
    }

    @Test
    public void testInfoCommand() {
        // Выполняем команду info
        InfoCommand infoCommand = new InfoCommand(manager);
        invoker.executeCommand(infoCommand);

        // Проверяем, что вывод содержит информацию о коллекции
        String output = outputStream.toString();
        assertTrue(output.contains("Тип коллекции"));
        assertTrue(output.contains("Количество элементов"));
    }
}