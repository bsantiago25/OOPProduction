public enum ItemType {

  AUDIO("AU"), VISUAL("VI"), AUDIO_MOBILE("AM"), VISUAL_MOBILE("VM");

  public final String label;
  ItemType(String c) {
    label = c;
  }
}