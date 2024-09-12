package itmo.service;

public interface Service {

    void help();

    void info();

    void show();

    void add();

    void updateId(long id);

    void removeById(long id);

    void clear();

    void save();

    void executeScript(String fileName);

    void addIfMax();

    void removeLower();

    void history();

    void printAscending();

    void printUniqueFrontMan();

    void printFieldDescendingGenre();

    void readFromFile(String filePath);
}
