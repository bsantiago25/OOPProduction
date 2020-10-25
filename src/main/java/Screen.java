class Screen implements ScreenSpec {

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
  String resolution;
  int refreshRate;
  int responseTime;

  @Override
  public String toString() {
    return "Screen: " + '\n' +
        "Resolution: " + resolution + '\n' +
        "Refresh rate: " + refreshRate + '\n' +
        "Response time: " + responseTime + '\n'
        ;
  }
}
