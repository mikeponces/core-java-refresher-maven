package aero.champ.exercise.sequencedcollection;

import java.util.LinkedHashSet;
import java.util.SequencedCollection;
import java.util.SequencedSet;

public class Playlist {
    private final String name;
    private final SequencedSet<Song> songs;

    public Playlist(String name) {
        this.name = name;
// Using LinkedHashSet which implements SequencedSet
        this.songs = new LinkedHashSet<>();
    }

    public String getName() {
        return name;
    }

    // Add a song to the end of the playlist
    public void addSong(Song song) {
        songs.add(song);
    }

    // Add a song to the beginning of the playlist
    public void addSongToBeginning(Song song) {
// TODO: Implement this method using SequencedCollection methods
        songs.addFirst(song);
    }

    // Get the first song in the playlist
    public Song getFirstSong() {
// TODO: Implement this method using SequencedCollection methods
        return songs.getFirst();
    }

    // Get the last song in the playlist
    public Song getLastSong() {
// TODO: Implement this method using SequencedCollection methods
        return songs.getLast();
    }

    // Remove and return the first song in the playlist
    public Song removeFirstSong() {
// TODO: Implement this method using SequencedCollection methods
        return songs.removeFirst();
    }

    // Remove and return the last song in the playlist
    public Song removeLastSong() {
// TODO: Implement this method using SequencedCollection methods
        return songs.removeLast();
    }

    // Get a reversed view of the playlist
    public SequencedCollection<Song> getReversedPlaylist() {
// TODO: Implement this method using SequencedCollection methods
        return songs.reversed();
    }

    // Print the playlist in forward order
    public void printPlaylist() {
        System.out.println("Playlist: " + name);
        songs.forEach(song -> System.out.println("- " + song));
    }

    // Print the playlist in reverse order
    public void printPlaylistReversed() {
        System.out.println("Playlist (reversed): " + name);
// TODO: Implement this method to print the songs in reverse order
        getReversedPlaylist().forEach(song -> System.out.println("- " + song));
    }

    // Get the total duration of the playlist in seconds
    public int getTotalDuration() {
        return songs.stream().mapToInt(Song::getDurationInSeconds).sum();
    }

    // Get the number of songs in the playlist
    public int size() {
        return songs.size();
    }
}
