import command.ExecuteScriptCommand;
import invoker.CommandInvoker;
import manager.MusicBandManager;
import model.MusicBand;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;


public class ExecuteScriptCommandTest {

    private MusicBandManager manager;
    private CommandInvoker invoker;
    private Scanner scanner;

    @Before
    public void setUp() {
        manager = new MusicBandManager("test.json");
        invoker = new CommandInvoker();
    }

    @Test
    public void testExecuteScriptCommand() throws IOException {
        // Создаём временный файл для скрипта
        File tempFile = File.createTempFile("script", ".txt");
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
        writer.write("add\nshow\n");
        writer.close();

        // Заготовка для ввода, имитирующая ввод пользователя
        String input = "The Beatles\n50\n20\n4\nPOP\nJohn Lennon\n180\nBLACK\nGERMANY\n1\n2\n3\nLiverpool\n";
        scanner = new Scanner(input); // Симуляция пользовательского ввода

        // Выполняем команду execute_script с файлом
        ExecuteScriptCommand executeScriptCommand = new ExecuteScriptCommand(manager, invoker, scanner, tempFile.getAbsolutePath());
        invoker.executeCommand(executeScriptCommand);

        // Проверяем, что команды были выполнены
        assertEquals(1, manager.getCollection().size());

        MusicBand band = manager.getCollection().first();
        assertEquals("The Beatles", band.getName());
        assertEquals(50.0, band.getCoordinates().getX(), 0.001);

        // Удаляем временный файл после выполнения
        tempFile.delete();
    }
}
