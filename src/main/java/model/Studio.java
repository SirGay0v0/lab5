package model;

public class Studio {
    private String name; // Поле может быть null
    private String address; // Поле не может быть null

    public Studio(String name, String address) {
        if (address == null || address.trim().isEmpty()) {
            throw new IllegalArgumentException("Адрес не может быть null или пустым.");
        }
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "Studio{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
