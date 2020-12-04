/**
 * This is the class for screens for products that are tv's.
 * @author Brandon Santiago
 */
class Screen implements ScreenSpec {

  final String resolution;
  final int refreshRate;
  final int responseTime;

  public Screen(String resolution, int refreshRate, int responseTime) {
    this.resolution = resolution;
    this.refreshRate = refreshRate;
    this.responseTime = responseTime;
  }

  /**
   * This method gets the resolution of screen.
   * @return screen resolution.
   */
  @Override
  public String getResolution() {
    return resolution;
  }

  /**
   * This gets the refresh rate of a monitor.
   * @return refresh rate of monitor.
   */
  @Override
  public int getRefreshRate() {
    return refreshRate;
  }

  /**
   * This gets the response time of the monitor.
   * @return response rate of monitor.
   */
  @Override
  public int getResponseTime() {
    return responseTime;
  }

  /**
   * This is the toString for this class and just returns details
   * of the monitor.
   * @return all details of monitor.
   */
  @Override
  public String toString() {
    return "Screen: " + '\n'
        + "Resolution: " + resolution + '\n'
        + "Refresh rate: " + refreshRate + '\n'
        + "Response time: " + responseTime + '\n'
        ;
  }
}
