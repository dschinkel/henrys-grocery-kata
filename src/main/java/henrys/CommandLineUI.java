package henrys;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Map;
import java.util.Scanner;

public class CommandLineUI implements RegisterUI {
  private final PrintStream outputStream;
  private final InputStream inputStream;
  private Scanner scanner;

  public CommandLineUI(InputStream input, OutputStream output) {
    this.inputStream = input;
    this.outputStream = new PrintStream(output);
    this.scanner = new Scanner(inputStream);
  }

  public CommandLineUI() {
    this.inputStream = System.in;
    this.outputStream = System.out;
    this.scanner = new Scanner(inputStream);
  }

  public void displayStartMessage() {
    outputStream.println("READY FOR CHECKOUT!");
  }

  public void displayItemsForSelection(Map<Integer, String> stockItems) {
    StringBuilder message = new StringBuilder("Items in Stock: ");
    stockItems.forEach((itemIndex, itemName) ->
      message.append(String.format("%d:%s ", itemIndex, itemName))
    );
    outputStream.println(message.toString());
  }

  @Override
  public String promptForInventoryItemSelection() {
    promptForInventoryItem();
    return getUserInput();
  }

  private void promptForInventoryItem() {
    outputStream.println("Please Specify an Item by Number:");
  }

  private String getUserInput() {
    while (scanner.hasNextLine()) {
      return scanner.nextLine();
    }
    scanner.close();
    return null;
  }
}
