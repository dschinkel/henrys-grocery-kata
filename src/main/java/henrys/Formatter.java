package henrys;

import java.text.DecimalFormat;

public class Formatter {
  public static Double formatDoubleToPrecisionOfTwo(Double value) {
    DecimalFormat formatDecimal = new DecimalFormat("##.00");
    Double formattedValue = Double.parseDouble(formatDecimal.format(value));
    return formattedValue;
  }
}
