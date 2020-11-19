package model.shapes;

import java.util.ArrayList;

import model.animations.AnimationType;
import model.animations.ColorChange;
import model.animations.IAnimation;
import model.animations.Move;
import model.animations.Scale;
import util.Color;
import util.Position;

/**
 * This class represents a 2D Oval shape object. This extends the abstract 2D Shape class and
 * utilizes the super constructor. An oval object is created using an x and y radius, color, name,
 * position, start and end time.
 */
public class Oval extends AbstractShape2D {
  /**
   * This is a constructor that creates the oval object.
   *
   * @param name      - the string identifier of the object.
   * @param position  - the position of the oval shape object.
   * @param color     - the color value of the shape object.
   * @param startTime - the start time of the object appearance.
   * @param endTime   - the end time of the object appearance.
   * @param xRadius   - the xRadius of the object.
   * @param yRadius   - the yRadius of the object.
   */
  public Oval(String name, Position position, Color color, int startTime,
              int endTime, double xRadius, double yRadius) {
    super(name, position, color, startTime, endTime, xRadius, yRadius);
    this.type = Shape2DType.Oval;
  }


  /**
   * The getDescription method returns the description of the shape.
   *
   * @return a string description of the shape 2D object.
   */
  @Override
  public String getDescription() {
    return String.format("Center: (%s,%s), X radius: %.2f, Y radius: %.2f, Color: (%d, %d, %d)\n",
            this.position.getX(), this.position.getY(),
            this.sizeArg1, this.sizeArg2, Math.round(this.color.getRed()),
            Math.round(this.color.getGreen()),
            Math.round(this.color.getBlue()));
  }

  /**
   * Evaluate the status of animation on a shape object at a particular tick value and return the
   * shape object.
   *
   * @param tick time duration
   * @return state of the shape object at the tick value
   */
  @Override
  public IShape2D generateAnimatedShape(int tick) {

    Oval oval = new Oval(this.name, this.position, this.color, this.startTime,
            this.endTime, this.sizeArg1, this.sizeArg2);

    ArrayList<IAnimation> list = this.animationList;
    int i = 0;
    if (i < list.size()) {
      do {
        IAnimation animation = list.get(i);
        if (animation.getType().equals(AnimationType.Move)) {
          moveCalculatorOval(tick, oval, animation);
        }
        if (animation.getType().equals(AnimationType.Color)) {
          colorCalculatorOval(tick, oval, animation);
        }
        if (animation.getType().equals(AnimationType.Scale)) {
          if (animation.getStartTime() <= tick && tick < animation.getEndTime()) {
            dimensionCalculatorOval(oval, (Scale) animation, tick);
          }
          if (animation.getEndTime() <= tick) {
            dimensionCalculatorOval(oval, (Scale) animation, animation.getEndTime());
          }
        }
        i++;
      }
      while (i < list.size());
    }
    return oval;
  }


  /**
   * Calculate the modified position of an oval object based on animation and tick rate.
   *
   * @param oval      is the oval object that is being animated.
   * @param animation the animation applied on the oval object.
   * @param tick      tick rate
   */
  private void moveCalculatorOval(int tick, Oval oval, IAnimation animation) {
    if (animation.getStartTime() <= tick && animation.getEndTime() > tick) {
      oval.position = oval.generateAnimatedPosition((Move) animation,
              oval.position, ((Move) animation).getFinalPosition(), tick);
    }
    if (animation.getEndTime() <= tick) {
      oval.position = oval.generateAnimatedPosition((Move) animation,
              oval.position, ((Move) animation).getFinalPosition(),
              animation.getEndTime());
    }
  }

  /**
   * Calculate the modified color of an oval object based on animation and tick rate.
   *
   * @param oval      is the shape object that is being animated.
   * @param animation the animation applied on the oval object.
   * @param tick      tick rate
   */
  private void colorCalculatorOval(int tick, Oval oval, IAnimation animation) {
    if (animation.getStartTime() <= tick && animation.getEndTime() > tick) {
      oval.color = oval.generateAnimatedColor((ColorChange) animation,
              oval.color, ((ColorChange) animation).getColor(), tick);
    }
    if (animation.getEndTime() <= tick) {
      oval.color = oval.generateAnimatedColor((ColorChange) animation,
              oval.color, ((ColorChange) animation).getColor(),
              animation.getEndTime());
    }
  }

  /**
   * Calculate the modified dimensions of an oval object based on animation and tick rate.
   *
   * @param oval      is the shape object that is being animated.
   * @param animation the animation applied on the oval object.
   * @param tick      tick rate
   */
  protected void dimensionCalculatorOval(Oval oval, Scale animation, int tick) {
    oval.sizeArg1 = this.generateAnimationSizeArg1(animation, oval.sizeArg1,
            oval.sizeArg1
                    + (animation.getDeltaX() / 2), tick);
    oval.sizeArg2 = this.generateAnimationSizeArg2(animation, oval.sizeArg2,
            oval.sizeArg2
                    + (animation.getDeltaY() / 2), tick);

  }

  /**
   * The setColor method sets the color of the shape which is of ENUM type: Red, Green and Blue.
   *
   * @param updatedColor - new color to be set to
   */
  @Override
  public void setColor(Color updatedColor) {
    this.color = updatedColor;

  }

  /**
   * The setPosition method sets the position of the shape (x, y).
   *
   * @param updatedPos the new position of the shape (x, y).
   */
  @Override
  public void setPosition(Position updatedPos) {
    this.position = updatedPos;

  }
}

