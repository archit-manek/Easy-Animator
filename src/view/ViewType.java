package view;

/**
 * This enum represents the views supported by the animation.
 */
public enum ViewType {
  INTERACTIVE("interactive"),VISUAL("visual"), TEXT("text");

  private final String viewType;

  /**
   * This is the enum constructor.
   *
   * @param viewType is the type.
   */
  ViewType(String viewType) {
    this.viewType = viewType;
  }

  /**
   * This is a method that gives the representation of the view as a string.
   *
   * @return the string describing for the Enum.
   */
  public String toString() {
    return viewType;
  }
}
