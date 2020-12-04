/**
 * This is for the enums of itemtype. It is there for the
 * user to select if product is a visual product etc.
 * @author Brandon Santiago
 */
public enum ItemType {

  AUDIO("AU"), VISUAL("VI"), AUDIO_MOBILE("AM"), VISUAL_MOBILE("VM");

  public final String label;
  ItemType(String c) {
    label = c;
  }
}