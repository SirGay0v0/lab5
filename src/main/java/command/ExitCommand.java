package command;

/**
 * Команда для завершения работы программы.
 */
public class ExitCommand implements Command {

    /**
     * Метод для выполнения команды exit.
     * Завершает выполнение программы.
     */
    @Override
    public void execute() {
        System.out.println("Завершение работы программы.");
        System.exit(0); // Завершает выполнение программы с кодом 0 (успешное завершение)
    }
}
