package command;
/**
 * Интерфейс для всех команд в системе.
 * Каждая команда должна реализовывать метод {@code execute()} для выполнения своей логики.
 */
public interface Command {
    void execute();
}
