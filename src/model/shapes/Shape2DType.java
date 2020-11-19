package model.shapes;

/**
 * This ENUM represents the various types of 2D shapes that can be animated.
 */
public enum Shape2DType {
  Rectangle("rectangle"), Oval("oval");

  private final String type;


  /**
   * This is the constructor that creates an ENUM object.
   *
   * @param type type of shape
   */
  Shape2DType(String type) {
    this.type = type;
  }

  /**
   * This is a toString method for the animation type ENUM values.
   *
   * @return the string describing the animation type ENUM values
   */
  public String toString() {
    return this.type;
  }
}
