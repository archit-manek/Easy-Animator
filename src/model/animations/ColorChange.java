package model.animations;

import model.IAnimationModel;
import util.Color;


/**
 * This class represents a color change animation. It extends the Abstract Animation class and uses
 * a super constructor.
 */
public class ColorChange extends AbstractAnimation {
  private Color newColor;

  /**
   * This constructor creates a color change object by calling the super constructor for the name,
   * start and end time of the animation. In addition, the color constructor takes in the existing
   * color of the 2D shape object.
   *
   * @param name      - the name of the shape
   * @param startTime - the start time for the animation
   * @param endTime   - the end time for the animation
   * @param oldColor  - the old color of the animation
   * @param newColor  - the new color of the animation
   */
  public ColorChange(String name, int startTime, int endTime, Color oldColor, Color newColor) {
    super(name, startTime, endTime);
    this.newColor = newColor;
    this.oldColor = oldColor;
    this.type = AnimationType.Color;
  }

  /**
   * The getColor method gets the new color of the shape.
   *
   * @return - the new color of the shape.
   */
  public Color getColor() {
    return this.newColor;
  }


  /**
   * This is the animationDescription method. It returns the current description of the animation.
   *
   * @param model - model that is passed through the method to return the string description
   * @return - current description of the animation
   * @throws IllegalArgumentException - if color change animation type has not been used to animate
   *                                  the object
   */
  @Override
  public String animationDescription(IAnimationModel model) throws IllegalArgumentException {
    if (!this.shapeAnimation.isEmpty()) {
      return String.format("Shape %s changes color from %s to %s from t=%d to t=%d\n",
              this.shapeAnimation.get(0).getName(),
              this.oldColor.getRGBString(),
              this.newColor.getRGBString(), this.startTime, this.endTime);
    }
    throw new IllegalArgumentException("Color Change animation type has not been used to animate "
            + "the object.");
  }


}
