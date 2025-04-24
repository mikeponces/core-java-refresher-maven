package aero.champ.exercise.sequencedcollection;

public class Song {
    private final String title;
    private final String artist;
    private final int durationInSeconds;

    public Song(String title, String artist, int durationInSeconds) {
        this.title = title;
        this.artist = artist;
        this.durationInSeconds = durationInSeconds;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public int getDurationInSeconds() {
        return durationInSeconds;
    }

    @Override
    public String toString() {
        return String.format("%s by %s (%d:%02d)",
                title, artist, durationInSeconds / 60, durationInSeconds % 60);
    }
}
