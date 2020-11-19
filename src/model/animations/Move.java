package model.animations;

import model.IAnimationModel;
import util.Position;

/**
 * This class represents a move animation. It extends the Abstract Animation class and uses a super
 * constructor.
 */

public class Move extends AbstractAnimation {
  private final Position finalPosition;

  /**
   * This constructor creates a color change object by calling the super constructor for the name,
   * start and end time of the animation.
   *
   * @param name          - the name of the shape
   * @param startTime     - the start time for the animation
   * @param endTime       - the end time for the animation
   * @param oldPosition   - the old position of the shape object
   * @param finalPosition - the final position of the shape object
   */
  public Move(String name, int startTime, int endTime, Position oldPosition,
              Position finalPosition) {
    super(name, startTime, endTime);
    this.oldPosition = oldPosition;
    this.finalPosition = finalPosition;
    this.type = AnimationType.Move;
  }

  /**
   * This is the animationDescription method. It returns the current description of the animation.
   *
   * @param model - model that is passed through the method to return the string description
   * @return - current description of the animation
   * @throws IllegalArgumentException - if move animation type has not been used to animate the
   *                                  object
   */
  public String animationDescription(IAnimationModel model) throws IllegalArgumentException {
    if (!this.shapeAnimation.isEmpty()) {
      if (this.oldPosition.getX() == this.finalPosition.getX()
              && this.oldPosition.getY() == this.finalPosition.getY()) {
        return "";
      }
      return String.format("Shape %s moves from (%s,%s) to (%s,%s) from t=%d to t=%d\n",
              this.shapeAnimation.get(0).getName(),
              this.oldPosition.getX(),
              this.oldPosition.getY(),
              this.finalPosition.getX(),
              this.finalPosition.getY(), this.startTime,
              this.endTime);
    }
    throw new IllegalArgumentException("Move animation type has not been used to animate the "
            + "object.");
  }

  /**
   * A getter method for the final position after the animation on a shape 2D object.
   *
   * @return final position for the animation 2D shape object.
   */
  public Position getFinalPosition() {
    return this.finalPosition;
  }

}
