package util;


/**
 * This class represents a color object that takes in RGB values.
 */
public class Color {
  private final double red;
  private final double green;
  private final double blue;

  /**
   * This represents a constructor for a RGB Color object that has three color values.
   *
   * @param red   - is the red value of a color.
   * @param green - is the green value of a color.
   * @param blue  - is the blue value of a color.
   * @throws IllegalArgumentException - if RGB color value arguments are invalid.
   */
  public Color(double red, double green, double blue) {
    this.red = red;
    this.green = green;
    this.blue = blue;
  }

  /**
   * This is a getter method for the red color value.
   *
   * @return the red color value.
   */
  public double getRed() {
    return red;
  }

  /**
   * This is a getter method for the green color value.
   *
   * @return the blue color value.
   */
  public double getGreen() {
    return green;
  }

  /**
   * This is a getter method for the blue color value.
   *
   * @return the green color value.
   */
  public double getBlue() {
    return blue;
  }

  /**
   * Color object with the red, blue and green color values in the form of a String.
   *
   * @return a string description of the color.
   */
  public String getRGBString() {
    return String.format("(%d,%d,%d)",
            Math.round(this.getRed()),
            Math.round(this.getGreen()),
            Math.round(this.getBlue()));
  }
}
