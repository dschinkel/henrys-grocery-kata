package henrys;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Map;

public class CommandLineUI implements RegisterUI {
  private final PrintStream outputStream;

  public CommandLineUI(OutputStream output) {
    this.outputStream = new PrintStream(output);
  }

  public CommandLineUI() {
    this.outputStream = System.out;
  }

  public void displayStartMessage() {
    outputStream.print("READY FOR CHECKOUT!\n");
  }

  public void displayItemsForSelection(Map<Integer, String> stockItems) {
    StringBuilder message = new StringBuilder("Please specify an item by typing the item number: ");
    stockItems.forEach((itemIndex, itemName) ->
      message.append(String.format("%d:%s ", itemIndex, itemName))
    );
    outputStream.print(message.toString());
  }
}
