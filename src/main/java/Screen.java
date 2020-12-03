class Screen implements ScreenSpec {

  final String resolution;
  final int refreshRate;
  final int responseTime;

  public Screen(String resolution, int refreshRate, int responseTime) {
    this.resolution = resolution;
    this.refreshRate = refreshRate;
    this.responseTime = responseTime;
  }

  @Override
  public String getResolution() {
    return resolution;
  }

  @Override
  public int getRefreshRate() {
    return refreshRate;
  }

  @Override
  public int getResponseTime() {
    return responseTime;
  }


  @Override
  public String toString() {
    return "Screen: " + '\n'
        + "Resolution: " + resolution + '\n'
        + "Refresh rate: " + refreshRate + '\n'
        + "Response time: " + responseTime + '\n'
        ;
  }
}
