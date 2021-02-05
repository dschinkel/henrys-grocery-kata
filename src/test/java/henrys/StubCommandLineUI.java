package henrys;

import java.util.Hashtable;
import java.util.Map;

public class StubCommandLineUI implements RegisterUI {

  private boolean showedStartMessage;
  boolean showedItemsList;

  public void displayStartMessage() {
    showedStartMessage = true;
  }

  @Override
  public void displayItemsForSelection(Map<Integer, String> stockItems) {
    showedItemsList = true;
  }

  public boolean showedStartMessage(){
    return showedStartMessage;
  }

  public boolean showedItemsList() {
    return showedItemsList;
  }
}
