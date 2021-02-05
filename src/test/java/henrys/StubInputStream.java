package henrys;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class StubInputStream extends InputStream {
  private InputStream inputStream;

  public void setInputStream(String inputString) {
    inputStream = new ByteArrayInputStream(inputString.getBytes());
  }

  @Override
  public int read() throws IOException {
    return inputStream.read();
  }
}
