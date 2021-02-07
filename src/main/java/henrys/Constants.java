package henrys;

public class Constants {
  static enum ItemName {
    SOUP(0), BREAD(1), MILK(2), APPLE(3);
    private int value = 0;

    ItemName(int value) {
      this.value = value;
    }
    public int getValue() {
      return value;
    }
  }
}
