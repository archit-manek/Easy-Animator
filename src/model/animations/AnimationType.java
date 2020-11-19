package model.animations;

/**
 * This ENUM represents the various types of animations that can be applied to a shape.
 */
public enum AnimationType {
  Move("move"), Color("color change"), Scale("scale");

  private final String type;


  /**
   * This is the constructor that creates an ENUM object.
   *
   * @param type type of animation
   */
  AnimationType(String type) {
    this.type = type;
  }

  /**
   * This is a toString method for the animation type ENUM values.
   *
   * @return the string describing the animation type ENUM values
   */
  @Override
  public String toString() {
    return this.type;
  }
}
