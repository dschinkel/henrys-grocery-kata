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
    outputStream.print("READY FOR CHECKOUT!\n");
  }

  public void displayItemsForSelection(Map<Integer, String> stockItems) {
    StringBuilder message = new StringBuilder("Items in Stock: ");
    stockItems.forEach((itemIndex, itemName) ->
      message.append(String.format("%d:%s ", itemIndex, itemName))
    );
    outputStream.print(message.toString());
  }

  @Override
  public String promptForInventoryItemSelection() {
    outputStream.print("\nPlease Specify an Item by Number:\n");
    while (scanner.hasNextLine()) {
      return scanner.nextLine();
    }
    scanner.close();
    return null;
  }

}
