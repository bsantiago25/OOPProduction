class AudioPlayer extends Product implements MultimediaControl {
  final String supportedAudioFormats;
  final String supportedPlaylistFormats;



  public AudioPlayer(String name, String manufacturer, String supportedAudioFormats,
      String supportedPlaylistFormats) {

    super(name, manufacturer, ItemType.AUDIO);
    this.supportedAudioFormats = supportedAudioFormats;
    this.supportedPlaylistFormats = supportedPlaylistFormats;
  }


  public void play() {
    System.out.println("Playing");
  }

  public void stop() {
    System.out.println("Stopping");
  }


  public void previous() {
    System.out.println("Previous");
  }

  public void next() {
    System.out.println("Next");
  }


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