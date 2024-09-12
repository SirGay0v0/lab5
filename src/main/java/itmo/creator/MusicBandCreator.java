package itmo.creator;

import itmo.model.MusicBand;

public class MusicBandCreator {

    public MusicBand create() {
        CoordinatesCreator coordinatesCreator = new CoordinatesCreator();
        MusicGenreCreator musicGenreCreator = new MusicGenreCreator();
        PersonCreator personCreator = new PersonCreator();
        MusicBand newMusicBand = new MusicBand();

        newMusicBand.setNumberOfParticipants(createNumberOfParticipants())
                .setName(createName())
                .setCoordinates(coordinatesCreator.create())
                .setGenre(musicGenreCreator.createGenre())
                .setFrontMan(personCreator.createPerson());

        return newMusicBand;
    }


    private long createNumberOfParticipants() {
        long number;
        try {
            do {
                System.out.print("Введите numberOfParticipants: ");
                number = Long.parseLong(System.console().readLine());
            } while (number <= 0);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            number = createNumberOfParticipants();
        }
        return number;
    }

    private String createName() {
        String name;
        System.out.print("Name: ");
        name = System.console().readLine();
        if (name == null || name.isBlank()) {
            System.out.println("Name is blank or null. Try again.");
            name = createName();
        }
        return name;
    }
}
