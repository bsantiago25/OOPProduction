class MoviePlayer extends Product implements MultimediaControl{

  final Screen screen;
  final MonitorType monitorType;

  public MoviePlayer(String name, String manufacturer, Screen screen,
      MonitorType monitorType) {
    super(name, manufacturer, ItemType.VISUAL);
    this.screen = screen;
    this.monitorType = monitorType;
  }

  @Override
  public void play() {
    System.out.println("Playing");
  }

  @Override
  public void stop() {
    System.out.println("Stopping");
  }

  @Override
  public void previous() {
    System.out.println("Previous");
  }

  @Override
  public void next() {
    System.out.println("Next");

  }

  @Override
  public String toString() {
    return
        "Name = " + getName() + "\n" +
            "Manufacturer = " + getManufacturer() + "\n" +
            "Type = " +ItemType.VISUAL + "\n" +
            "Screen: " + '\n' +
            "Resolution: " + screen.getResolution() + '\n' +
            "Refresh rate: " + screen.getRefreshRate() + '\n' +
            "Response time: " + screen.getResponseTime() + '\n' +
            "Monitor Type: " + monitorType;

  }
}

