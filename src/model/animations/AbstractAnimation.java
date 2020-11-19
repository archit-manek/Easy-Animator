package model.animations;

import java.util.ArrayList;

import model.shapes.IShape2D;
import util.Color;
import util.Position;

/**
 * This is an abstract class for the animation and it implements the IAnimation interface. It
 * contains common methods between move, scale and color change.
 */

public abstract class AbstractAnimation implements IAnimation {
  protected String name;
  protected int startTime;
  protected int endTime;
  protected ArrayList<IShape2D> shapeAnimation;
  protected AnimationType type;
  protected Position oldPosition;
  protected Color oldColor;
  protected double oldSizeArg1;
  protected double oldSizeArg2;

  /**
   * This constructor represents animation objects including move, scale and color change.
   *
   * @param name      - the name of the shape animation
   * @param startTime - the start time for the animation
   * @param endTime   - the end time for the animation
   * @throws IllegalArgumentException - if start time is more than end time
   */

  public AbstractAnimation(String name, int startTime, int endTime)
          throws IllegalArgumentException {
    if (startTime >= 0 && endTime >= 0) {
      if (endTime >= startTime) {
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.shapeAnimation = new ArrayList<>();
      } else {
        throw new IllegalArgumentException("End time cannot be less than start time.");
      }
    } else {
      throw new IllegalArgumentException("Start and end time cannot be negative.");
    }
  }

  /**
   * This is the getStartTime method. It gets the start time of the animation.
   *
   * @return - start time of the animation
   */
  @Override
  public int getStartTime() {
    return this.startTime;
  }


  /**
   * This is the getEndTime method. It gets the end time of the animation.
   *
   * @return - end time of the animation
   */
  @Override
  public int getEndTime() {
    return this.endTime;
  }

  /**
   * This is the getName method that returns the name of the shape.
   *
   * @return - the name of the shape
   */
  @Override
  public String getShapeName() {
    return this.name;
  }

  /**
   * This is the getType method. This returns the type of the animation.
   *
   * @return - type of animation.
   */

  @Override
  public AnimationType getType() {
    return this.type;
  }


  /**
   * This is the animateShape class that adds an animation to a shape object.
   *
   * @param shape - 2D shape that the animation will happen on
   * @throws IllegalArgumentException - if the animations are overlapping or if animation appears
   *                                  before start time or after end time of the shape.
   */
  @Override
  public void animateShape(IShape2D shape) throws IllegalArgumentException {
    ArrayList<IAnimation> animations = new ArrayList<>();

    int i = 0;
    while (i < shape.getAnimation().size()) {
      switch (shape.getAnimation().get(i).getType()) {
        case Scale:
        case Move:
        case Color:
          animations.add(shape.getAnimation().get(i));
          if (this.startTime > shape.getAnimation().get(i).getStartTime()
                  && this.startTime < shape.getAnimation().get(i).getEndTime()) {
            throw new IllegalArgumentException("Overlapping animations cannot be executed.");
          }
          break;
        default:
          return;
      }
      i++;
    }


    if (this.startTime < shape.getStartTime() && (this.startTime > shape.getEndTime())) {
      throw new IllegalArgumentException("Shape cannot be animated before it appears and after "
              + "it disappears.");
    }


    this.shapeAnimation.add(shape);
    shape.getAnimation().add(this);
  }


}



