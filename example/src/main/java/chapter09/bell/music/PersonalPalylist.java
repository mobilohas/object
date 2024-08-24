package chapter09.bell.music;

public class PersonalPalylist extends Playlist {

  public void remove(Song song) {
    getTracks().remove(song);
    getSingers().remove(song.getSinger());
  }
}
