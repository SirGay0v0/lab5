import command.HelpCommand;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertTrue;


public class HelpCommandTest {

    private ByteArrayOutputStream outputStream;

    @Before
    public void setUp() {
        // Перенаправляем вывод в память
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    public void testHelpCommand() {
        HelpCommand helpCommand = new HelpCommand();
        helpCommand.execute();

        // Проверяем, что вывод содержит основные команды
        String output = outputStream.toString();
        assertTrue(output.contains("info"));
        assertTrue(output.contains("add"));
        assertTrue(output.contains("remove_by_id"));
        assertTrue(output.contains("print_ascending"));
    }
}
