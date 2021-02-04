package henrys;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CalculatorUITest {
  private ByteArrayOutputStream output;

  @BeforeEach
  void setUp() {
    output = new ByteArrayOutputStream();
  }

  @Test
  void displayStartMessage() {
    CommandLineUI ui = new CommandLineUI(output);
    ui.displayStartMessage();
    assertTrue(output.toString().contains("Ready for Checkout"));
  }
}
