package henrys;

import java.io.OutputStream;
import java.io.PrintStream;

public class CommandLineUI implements RegisterUI {
  private final PrintStream outputStream;

  public CommandLineUI(OutputStream output) {
    this.outputStream = new PrintStream(output);
  }

  public CommandLineUI() {
    this.outputStream = System.out;
  }

  public void displayStartMessage() {
    outputStream.print("Ready for Checkout");
  }
}
