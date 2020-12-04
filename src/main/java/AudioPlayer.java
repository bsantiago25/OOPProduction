/**
 * This is the audio player class for the program.
 * Basically plays,stops, plays next track, plays previous track.
 * @author Brandon Santiago
 *
 */

class AudioPlayer extends Product implements MultimediaControl {
  final String supportedAudioFormats;
  final String supportedPlaylistFormats;



  public AudioPlayer(String name, String manufacturer, String supportedAudioFormats,
      String supportedPlaylistFormats) {

    super(name, manufacturer, ItemType.AUDIO);
    this.supportedAudioFormats = supportedAudioFormats;
    this.supportedPlaylistFormats = supportedPlaylistFormats;
  }

  /**
   * This method controls the audio player to play.
   */
  public void play() {
    System.out.println("Playing");
  }

  /**
   * This method controls the audio player to stop.
   */
  public void stop() {
    System.out.println("Stopping");
  }

  /**
   * This method controls the audio player to previous track.
   */
  public void previous() {
    System.out.println("Previous");
  }

  /**
   * This method controls the audio player to next track.
   */
  public void next() {
    System.out.println("Next");
  }

  /**
   * This to string returns all the audio players info.
   * @return audio player info.
   */
  @Override
  public String toString() {
    return
        "Name = " + getName() + "\n"
            + "Manufacturer = " + getManufacturer() + "\n"
            + "Type = " + ItemType.AUDIO + "\n"
            + "supportedAudioFormats = " + supportedAudioFormats + "\n"
            + "supportedPlaylistFormats = " + supportedPlaylistFormats;

  }
}