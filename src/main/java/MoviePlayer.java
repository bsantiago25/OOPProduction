/**
 * This is a class for movie players such as blu-ray players.
 * Controls playing, stopping, previous and next.
 * @author Brandon Santiago
 */
class MoviePlayer extends Product implements MultimediaControl {

  final Screen screen;
  final MonitorType monitorType;

  public MoviePlayer(String name, String manufacturer, Screen screen,
      MonitorType monitorType) {
    super(name, manufacturer, ItemType.VISUAL);
    this.screen = screen;
    this.monitorType = monitorType;
  }

  /**
   * This method controls the movie player to play.
   */
  @Override
  public void play() {
    System.out.println("Playing");
  }

  /**
   * This method controls the movie player to stop.
   */
  @Override
  public void stop() {
    System.out.println("Stopping");
  }

  /**
   * This method controls the movie player to go to previous movie.
   */
  @Override
  public void previous() {
    System.out.println("Previous");
  }

  /**
   * This method controls the movie player to go to next movie.
   */
  @Override
  public void next() {
    System.out.println("Next");

  }

  /**
   * This toString prints out info for movie player.
   * @return Movie player info.
   */
  @Override
  public String toString() {
    return
        "Name = " + getName() + "\n"
            + "Manufacturer = " + getManufacturer() + "\n"
            + "Type = " + ItemType.VISUAL + "\n"
            + "Screen: " + '\n'
            + "Resolution: " + screen.getResolution() + '\n'
            + "Refresh rate: " + screen.getRefreshRate() + '\n'
            + "Response time: " + screen.getResponseTime() + '\n'
            + "Monitor Type: " + monitorType;

  }
}

