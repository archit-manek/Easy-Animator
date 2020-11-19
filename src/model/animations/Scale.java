package model.animations;

import model.IAnimationModel;

/**
 * This class represents a scale animation. It extends the Abstract Animation class and uses a super
 * constructor.
 */
public class Scale extends AbstractAnimation {
  private double deltaX;
  private double deltaY;

  /**
   * This constructor creates a scale or dimension change object by calling the super constructor
   * for the name, start and end time of the animation. In addition, the constructor takes in the
   * various factors that scale the object's height and width.
   *
   * @param name        - the name of the shape
   * @param startTime   - the start time for the animation
   * @param endTime     - the end time for the animation
   * @param oldSizeArg1 - old width/xRadius
   * @param oldSizeArg2 - old height/yRadius
   * @param deltaX      - the width/xRadius change
   * @param deltaY      - the height/yRadius change
   */
  public Scale(String name, int startTime, int endTime, double oldSizeArg1, double oldSizeArg2,
               double deltaX, double deltaY) {
    super(name, startTime, endTime);
    this.oldSizeArg1 = oldSizeArg1;
    this.oldSizeArg2 = oldSizeArg2;
    this.deltaX = deltaX;
    this.deltaY = deltaY;
    this.type = AnimationType.Scale;
  }

  /**
   * A getter method for the change in x value.
   *
   * @return the difference in the value of x
   */
  public double getDeltaX() {
    return this.deltaX;
  }

  /**
   * A getter method for the change in y value.
   *
   * @return the difference in the value of y
   */
  public double getDeltaY() {
    return this.deltaY;
  }

  /**
   * This method is the animateShape method. It takes in a shape and stores the animations that
   * happen on the shape.
   *
   * @param model - animation model
   * @throws IllegalArgumentException - if the animation parameters are invalid
   */
  @Override
  public String animationDescription(IAnimationModel model) {
    if (this.shapeAnimation.isEmpty()) {
      throw new IllegalArgumentException("Scale animation type has not been used to animate "
              + "the object.");
    }
    if ((this.shapeAnimation.get(0).getSizeArg1() + this.deltaX <= 0)
            || (this.shapeAnimation.get(0).getSizeArg2() + this.deltaY <= 0)) {
      throw new IllegalArgumentException("Changes in shape dimensions are not valid.");
    }
    double newWidth = this.shapeAnimation.get(0).getSizeArg1() + this.deltaX;
    double newHeight = this.shapeAnimation.get(0).getSizeArg2() + this.deltaY;
    return (String.format("Shape %s scales from Width: %s,"
                    + " Height: %s to Width: %s, Height: %s from t=%d to t=%d\n",
            this.shapeAnimation.get(0).getName(),
            oldSizeArg1,
            oldSizeArg2,
            newWidth, newHeight,
            this.startTime,
            this.endTime));
  }


}
