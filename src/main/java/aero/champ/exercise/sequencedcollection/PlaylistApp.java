package aero.champ.exercise.sequencedcollection;

import java.util.SequencedCollection;

public class PlaylistApp {
    public static void main(String[] args) {
// Create a new playlist
        Playlist workout = new Playlist("Workout Mix");
// Add songs to the playlist
        workout.addSong(new Song("Eye of the Tiger", "Survivor", 251));
        workout.addSong(new Song("Stronger", "Kanye West", 312));
        workout.addSong(new Song("Till I Collapse", "Eminem", 298));
        workout.addSong(new Song("Power", "Kanye West", 292));
// Print the playlist
        workout.printPlaylist();
        System.out.println("Total duration: " +
                formatDuration(workout.getTotalDuration()));
        System.out.println();
// Add a song to the beginning
        workout.addSongToBeginning(new Song("Lose Yourself", "Eminem", 326));
        System.out.println("After adding 'Lose Yourself' to the beginning:");
        workout.printPlaylist();
        System.out.println();
// Print the first and last songs
        System.out.println("First song: " + workout.getFirstSong());
        System.out.println("Last song: " + workout.getLastSong());
        System.out.println();
// Print the playlist in reverse order
        workout.printPlaylistReversed();
        System.out.println();
// Remove songs from both ends
        System.out.println("Removed first song: " + workout.removeFirstSong());
        System.out.println("Removed last song: " + workout.removeLastSong());
        System.out.println();
// Print the updated playlist
        System.out.println("Updated playlist:");
        workout.printPlaylist();
        System.out.println();
// Create and print a reversed view
        System.out.println("Using reversed() to get a view:");
        SequencedCollection<Song> reversedView = workout.getReversedPlaylist();
        reversedView.forEach(song -> System.out.println("- " + song));
    }
    private static String formatDuration(int seconds) {
        int minutes = seconds / 60;
        int remainingSeconds = seconds % 60;
        return String.format("%d:%02d", minutes, remainingSeconds);
    }
}
