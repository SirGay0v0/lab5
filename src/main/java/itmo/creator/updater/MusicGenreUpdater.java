package itmo.creator.updater;

import itmo.model.MusicGenre;

public class MusicGenreUpdater {

    public MusicGenre createGenre() {

        MusicGenre genre;
        System.out.print("Genre: ");
        try {
            genre = MusicGenre.valueOf(System.console().readLine());
            if (genre != null && (genre.equals(MusicGenre.POP) || genre.equals(MusicGenre.BRIT_POP)
                    || genre.equals(MusicGenre.PSYCHEDELIC_ROCK))) {
                System.out.println("Genre must be null or equals POP or BRIT_POP or PSYCHEDELIC_ROCK. Try again.");
                genre = createGenre();
            }
        } catch (EnumConstantNotPresentException e) {
            System.out.println("Genre must be null or equals POP or BRIT_POP or PSYCHEDELIC_ROCK. Try again.");
            genre = createGenre();
        }
        return genre;
    }
}
