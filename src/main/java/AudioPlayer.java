class AudioPlayer extends Product implements MultimediaControl{
  String supportedAudioFormats;
  String supportedPlaylistFormats;



  public AudioPlayer(String name, String manufacturer,String SupportedAudioFormats,String SupportedPlaylistFormats)
  {

    super(name, manufacturer,ItemType.AUDIO);
    this.supportedAudioFormats = SupportedAudioFormats;
    this.supportedPlaylistFormats = SupportedPlaylistFormats;
  }


  public void play() {
    System.out.println("Playing");
  }

  public void stop()
  {
    System.out.println("Stopping");
  }


  public void previous() {
    System.out.println("Previous");
  }

  public void next(){
    System.out.println("Next");
  }


  @Override
  public String toString() {
    return
        "Name = " + getName() + "\n" +
            "Manufacturer = " + getManufacturer() + "\n" +
            "Type = " +ItemType.AUDIO + "\n" +
            "supportedAudioFormats = " + supportedAudioFormats + "\n" +
            "supportedPlaylistFormats = " + supportedPlaylistFormats;

  }
}