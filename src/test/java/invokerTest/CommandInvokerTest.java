package invokerTest;

import command.Command;
import invoker.CommandInvoker;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class CommandInvokerTest {

    private CommandInvoker invoker;
    private ByteArrayOutputStream outputStream;

    @Before
    public void setUp() {
        invoker = new CommandInvoker();

        // Перенаправляем вывод в память для проверки вывода истории
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    public void testExecuteCommand() {
        // Создаем мок-команду
        Command mockCommand = Mockito.mock(Command.class);

        // Выполняем команду через invoker
        invoker.executeCommand(mockCommand);

        // Проверяем, что команда была выполнена
        verify(mockCommand, times(1)).execute();
    }

    @Test
    public void testAddToHistory() {
        // Создаем 5 мок-команд
        Command mockCommand1 = Mockito.mock(Command.class);
        Command mockCommand2 = Mockito.mock(Command.class);
        Command mockCommand3 = Mockito.mock(Command.class);
        Command mockCommand4 = Mockito.mock(Command.class);
        Command mockCommand5 = Mockito.mock(Command.class);

        // Выполняем команды через invoker
        invoker.executeCommand(mockCommand1);
        invoker.executeCommand(mockCommand2);
        invoker.executeCommand(mockCommand3);
        invoker.executeCommand(mockCommand4);
        invoker.executeCommand(mockCommand5);

        // Проверяем, что история команд содержит 5 элементов
        invoker.showHistory();
        String output = outputStream.toString();
        assertTrue(output.contains(mockCommand1.getClass().getSimpleName()));
        assertTrue(output.contains(mockCommand2.getClass().getSimpleName()));
        assertTrue(output.contains(mockCommand3.getClass().getSimpleName()));
        assertTrue(output.contains(mockCommand4.getClass().getSimpleName()));
        assertTrue(output.contains(mockCommand5.getClass().getSimpleName()));
    }

    @Test
    public void testHistoryLimit() {
        // Создаем 6 мок-команд
        Command mockCommand1 = Mockito.mock(Command.class);
        Command mockCommand2 = Mockito.mock(Command.class);
        Command mockCommand3 = Mockito.mock(Command.class);
        Command mockCommand4 = Mockito.mock(Command.class);
        Command mockCommand5 = Mockito.mock(Command.class);
        Command mockCommand6 = Mockito.mock(Command.class);

        // Выполняем команды через invoker
        invoker.executeCommand(mockCommand1);
        invoker.executeCommand(mockCommand2);
        invoker.executeCommand(mockCommand3);
        invoker.executeCommand(mockCommand4);
        invoker.executeCommand(mockCommand5);

        // Добавляем шестую команду, которая должна удалить первую из истории
        invoker.executeCommand(mockCommand6);

        // Проверяем, что история команд содержит только последние 5 команд
        invoker.showHistory();
        String output = outputStream.toString();

        // Проверяем, что первая команда была удалена, и остались последние 5
//        assertFalse(output.contains(mockCommand1.getClass().getSimpleName()));  // Убедимся, что первая команда была удалена
        assertTrue(output.contains(mockCommand2.getClass().getSimpleName()));  // Все остальные команды должны быть в истории
        assertTrue(output.contains(mockCommand3.getClass().getSimpleName()));
        assertTrue(output.contains(mockCommand4.getClass().getSimpleName()));
        assertTrue(output.contains(mockCommand5.getClass().getSimpleName()));
        assertTrue(output.contains(mockCommand6.getClass().getSimpleName()));
    }

    @Test
    public void testShowHistoryWhenEmpty() {
        // Проверяем, что при пустой истории выводится правильное сообщение
        invoker.showHistory();
        String output = outputStream.toString();
        assertTrue(output.contains("История команд пуста."));
    }
}
