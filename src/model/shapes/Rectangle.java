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
 * This class represents a 2D Rectangle shape object. This extends the abstract 2D Shape class and
 * utilizes the super constructor. A rectangle object is created with x and y coordinates of the
 * minimum corner, dimensions including width and height, and RBG color values.
 */
public class Rectangle extends AbstractShape2D {


  /**
   * This is a constructor that creates the rectangle object.
   *
   * @param name      - the string identifier of the object.
   * @param position  - the position of the oval shape object.
   * @param color     - the color intensity of the shape object.
   * @param startTime - the start time of the object appearance.
   * @param endTime   - the end time of the object appearance.
   * @param width     - the width of the object.
   * @param height    - the height of the object.
   */
  public Rectangle(String name, Position position, Color color, int startTime, int endTime,
                   double width, double height) {
    super(name, position, color, startTime, endTime, width, height);
    this.type = Shape2DType.Rectangle;
  }


  /**
   * The getDescription method returns the description of the shape.
   *
   * @return a string description of the shape 2D object.
   */
  @Override
  public String getDescription() {

    return String.format("Min corner: (%s,%s), Width: %s, Height: %s, Color: (%d, %d, %d)\n",
            this.position.getX(), this.position.getY(),
            this.sizeArg1, this.sizeArg2,
            Math.round(this.color.getRed()),
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
    Rectangle rectangle = new Rectangle(this.name, this.position,
            this.color, this.startTime, this.endTime, this.sizeArg1, this.sizeArg2);


    ArrayList<IAnimation> list = this.animationList;
    int i = 0;
    if (i < list.size()) {
      do {
        IAnimation animation = list.get(i);

        AnimationType animationType = animation.getType();
        if (animationType == AnimationType.Move) {
          moveCalculatorRectangle(tick, rectangle, animation);
        }
        if (animationType == AnimationType.Color) {
          colorCalculatorRectangle(tick, rectangle, animation);
        }
        if (animationType == AnimationType.Scale) {
          dimensionCalculatorRectangle(tick, rectangle, animation);
        }
        i++;
      }
      while (i < list.size());
    }

    return rectangle;
  }


  /**
   * Calculate the modified dimensions of an rectangle object based on animation and tick rate.
   *
   * @param rectangle is the shape object that is being animated.
   * @param animation the animation applied on the oval object.
   * @param tick      tick rate
   */
  private void dimensionCalculatorRectangle(int tick, Rectangle rectangle, IAnimation animation) {
    if (animation.getStartTime() < tick && tick < animation.getEndTime()) {
      rectangle.sizeArg2 = rectangle.generateAnimationSizeArg2((Scale) animation,
              rectangle.sizeArg2, rectangle.sizeArg2
                      + ((Scale) animation).getDeltaY(), tick);
      rectangle.sizeArg1 = rectangle.generateAnimationSizeArg1((Scale) animation,
              rectangle.sizeArg1, rectangle.sizeArg1
                      + ((Scale) animation).getDeltaY(),
              tick);
    }
    if (animation.getEndTime() <= tick) {
      rectangle.sizeArg2 = rectangle.generateAnimationSizeArg2((Scale) animation,
              rectangle.sizeArg2, rectangle.sizeArg2
                      + ((Scale) animation).getDeltaX(), animation.getEndTime());
      rectangle.sizeArg1 = rectangle.generateAnimationSizeArg1((Scale) animation,
              rectangle.sizeArg1, rectangle.sizeArg1
                      + ((Scale) animation).getDeltaX(), animation.getEndTime());
    }
  }

  /**
   * Calculate the modified color of an rectangle object based on animation and tick rate.
   *
   * @param rectangle is the shape object that is being animated.
   * @param animation the animation applied on the oval object.
   * @param tick      tick rate
   */
  private void colorCalculatorRectangle(int tick, Rectangle rectangle, IAnimation animation) {
    if (animation.getStartTime() <= tick && animation.getEndTime() > tick) {
      rectangle.color = rectangle.generateAnimatedColor((ColorChange) animation,
              rectangle.color, ((ColorChange) animation).getColor(), tick);
    }
    if (animation.getEndTime() <= tick) {
      rectangle.color = rectangle.generateAnimatedColor((ColorChange) animation,
              rectangle.color, ((ColorChange) animation).getColor(),
              animation.getEndTime());
    }
  }

  /**
   * Calculate the modified position of an rectangle object based on animation and tick rate.
   *
   * @param rectangle is the oval object that is being animated.
   * @param animation the animation applied on the oval object.
   * @param tick      tick rate
   */
  private void moveCalculatorRectangle(int tick, Rectangle rectangle, IAnimation animation) {
    if (animation.getStartTime() <= tick && animation.getEndTime() > tick) {
      rectangle.position = rectangle.generateAnimatedPosition((Move) animation,
              rectangle.position, ((Move) animation).getFinalPosition(), tick);
    }
    if (animation.getEndTime() < tick) {
      rectangle.position = rectangle.generateAnimatedPosition((Move) animation,
              rectangle.position, ((Move) animation).getFinalPosition(),
              animation.getEndTime());
    }
  }


}