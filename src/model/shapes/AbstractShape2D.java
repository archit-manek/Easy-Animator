package model.shapes;


import java.util.ArrayList;

import model.animations.ColorChange;
import model.animations.IAnimation;
import model.animations.Move;
import model.animations.Scale;
import util.Color;
import util.Position;

/**
 * This is an abstract class for the 2D shape. It implements the IShape2D interface and the common
 * methods between Rectangle and Oval.
 */
public abstract class AbstractShape2D implements IShape2D {

  protected Position position;
  protected String name;
  protected Color color;
  protected int startTime;
  protected int endTime;
  protected double sizeArg1;
  protected double sizeArg2;
  protected ArrayList<IAnimation> animationList;
  protected Shape2DType type;

  /**
   * This is a constructor that takes in a minimum corner coordinate, the dimensions and the RGB
   * color, in addition to a name and type.
   *
   * @param position  - is the (x,y) coordinates of the minimum corner of the shape.
   * @param name      - is the name of the shape.
   * @param sizeArg1  - is either the width (rectangle) or xRadius (oval) of the shape.
   * @param sizeArg2  - is either the height (rectangle) or yRadius (oval) of the shape.
   * @param color     - is the color of the shape
   * @param startTime - is the start time of the shape object.
   * @param endTime   - is the end time of the shape object.
   * @throws IllegalArgumentException - if start time or end time are negative or if end time is
   *                                  less than the start time.
   */
  public AbstractShape2D(String name, Position position, Color color, int startTime, int endTime,
                         double sizeArg1, double sizeArg2) {

    if (startTime >= 0 && endTime >= 0) {
      if (endTime >= startTime) {
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.animationList = new ArrayList<>();
        setPosition(position);
        setColor(color);
        setSizeArg1(sizeArg1);
        setSizeArg2(sizeArg2);
      } else {
        throw new IllegalArgumentException("End time cannot be less than the start time.");
      }
    } else {
      throw new IllegalArgumentException("Start time or end time cannot be negative.");
    }
  }

  /**
   * The getName method gets the name of the shape.
   *
   * @return - name of the shape
   */
  public String getName() {
    return this.name;
  }

  /**
   * The getType method gets the type of the shape.
   *
   * @return - the type of the shape.
   */
  public Shape2DType getType() {
    return this.type;
  }

  /**
   * The getPosition method gets the position (x, y) of the shape.
   *
   * @return - the position (x, y) of the shape.
   */
  public Position getPosition() {
    return this.position;
  }

  /**
   * The getColor method gets the color of the shape which is of ENUM type: Red, Green and Blue.
   *
   * @return - color of the shape
   */
  public Color getColor() {
    return this.color;
  }

  /**
   * A getter that returns the appear time for the 2D shape object.
   *
   * @return the appear time of the shape object.
   */
  public int getStartTime() {
    return this.startTime;
  }

  /**
   * The getEndTime method returns the end time of the shape animation.
   *
   * @return - the end time of the shape animation.
   */
  public int getEndTime() {
    return this.endTime;
  }

  /**
   * The getAnimation method returns a list of animations on the shape.
   *
   * @return - a list of animations on the shape.
   */
  public ArrayList<IAnimation> getAnimation() {
    return this.animationList;
  }

  /**
   * The getSizeArg1 method returns either the width (rectangle) or xRadius (oval).
   *
   * @return - either the width (rectangle) or xRadius (oval).
   */
  @Override
  public double getSizeArg1() {
    return this.sizeArg1;
  }

  /**
   * The getSizeArg2 method returns either the height (rectangle) or yRadius (oval).
   *
   * @return - either the height (rectangle) or yRadius (oval).
   */
  @Override
  public double getSizeArg2() {
    return this.sizeArg2;
  }

  /**
   * The setSizeArg1 method updates the width (rectangle) or xRadius (oval).
   *
   * @param updatedArg1 - the new width (rectangle) or xRadius (oval).
   * @throws IllegalArgumentException - if the size argument is zero or negative.
   */
  @Override
  public void setSizeArg1(double updatedArg1) throws IllegalArgumentException {
    if (updatedArg1 <= 0) {
      throw new IllegalArgumentException("Size argument has to be positive");
    }
    this.sizeArg1 = updatedArg1;
  }

  /**
   * The setSizeArg2 method updates the height (rectangle) or yRadius (oval).
   *
   * @param updatedArg2 - the new height (rectangle) or yRadius (oval).
   * @throws IllegalArgumentException - if the size argument is zero or negative.
   */
  @Override
  public void setSizeArg2(double updatedArg2) throws IllegalArgumentException {
    if (updatedArg2 <= 0) {
      throw new IllegalArgumentException("Size argument has to be positive");
    }
    this.sizeArg2 = updatedArg2;
  }

  /**
   * This method moves the shape object to a new position with updated x and y values.
   *
   * @param updatedPos is the new desired updatedPos.
   */
  @Override
  public void move(Position updatedPos) {
    this.position.setX(updatedPos.getX());
    this.position.setY(updatedPos.getY());
  }

  /**
   * Evaluates the color of the new position post animation.
   *
   * @param animationColor the animationColor that is added to the shape
   * @param begColor       the starting color, before the animation
   * @param endColor       the ending color, after the animation
   * @param tick           the tick rate at which this change happens
   * @return the shape animationColor at a particular tick rate.
   */
  @Override
  public Color generateAnimatedColor(ColorChange animationColor, Color begColor, Color endColor,
                                     int tick) {

    double updatedRed = begColor.getRed() * ((double) (animationColor.getEndTime() - tick)
            / (animationColor.getEndTime() - animationColor.getStartTime()))
            + endColor.getRed() * ((double) (tick - animationColor.getStartTime()) /
            (animationColor.getEndTime() - animationColor.getStartTime()));

    double updatedGreen = begColor.getGreen() * ((double) (animationColor.getEndTime() - tick)
            / (animationColor.getEndTime() - animationColor.getStartTime()))
            + endColor.getGreen() * ((double) (tick - animationColor.getStartTime())
            / (animationColor.getEndTime() - animationColor.getStartTime()));

    double updatedBlue = begColor.getBlue() * ((double) (animationColor.getEndTime() - tick)
            / (animationColor.getEndTime() - animationColor.getStartTime()))
            + endColor.getBlue() * ((double) (tick - animationColor.getStartTime())
            / (animationColor.getEndTime() - animationColor.getStartTime()));

    return new Color(updatedRed, updatedGreen, updatedBlue);

  }

  /**
   * Evaluates the updated position post animation.
   *
   * @param animationMove the animationMove that is added to the shape
   * @param begPosition   the starting position before the animation is applied
   * @param endPosition   the ending position after the animation is applied
   * @param tick          the tick rate at which this change happens
   * @return the shape animationMove at a particular tick rate.
   */
  @Override
  public Position generateAnimatedPosition(Move animationMove, Position begPosition,
                                           Position endPosition, int tick) {


    double updatedX = begPosition.getX() * ((double) (animationMove.getEndTime() - tick)
            / (animationMove.getEndTime() - animationMove.getStartTime()))
            + endPosition.getX() * ((double) (tick - animationMove.getStartTime())
            / (animationMove.getEndTime() - animationMove.getStartTime()));

    double updatedY = begPosition.getY() * ((double) (animationMove.getEndTime() - tick)
            / (animationMove.getEndTime() - animationMove.getStartTime()))
            + endPosition.getY() * ((double) (tick - animationMove.getStartTime())
            / (animationMove.getEndTime() - animationMove.getStartTime()));

    return new Position(updatedX, updatedY);
  }

  /**
   * valuates the updated SizeArg1 (xRadius for oval and width for rectangle) post animation.
   *
   * @param animationScale the animationScale added to the shape
   * @param begSizeArg1    the starting SizeArg1 before the animation
   * @param endSizeArg1    the ending SizeArg1 after the animation
   * @param tick           the tick rate at which this change happens
   * @return the shape animationScale at a particular tick rate.
   */
  @Override
  public double generateAnimationSizeArg1(Scale animationScale, double begSizeArg1,
                                          double endSizeArg1, int tick) {
    return begSizeArg1 * ((double) (animationScale.getEndTime() - tick)
            / (animationScale.getEndTime() - animationScale.getStartTime()))
            + endSizeArg1 * ((double) (tick - animationScale.getStartTime())
            / (animationScale.getEndTime()
            - animationScale.getStartTime()));
  }

  /**
   * Evaluates the updated SizeArg1 (yRadius for oval and height for rectangle) post animation.
   *
   * @param animateScale the animationScale added to the shape
   * @param begSizeArg2  the starting SizeArg1 before the animation
   * @param endSizeArg2  the ending SizeArg1 after the animation
   * @param tick         the tick rate at which this change happens
   * @return the shape animationScale at a particular tick rate.
   */
  @Override
  public double generateAnimationSizeArg2(Scale animateScale, double begSizeArg2,
                                          double endSizeArg2, int tick) {
    return begSizeArg2 * ((double) (animateScale.getEndTime() - tick)
            / (animateScale.getEndTime() - animateScale.getStartTime()))
            + endSizeArg2 * ((double) (tick - animateScale.getStartTime())
            / (animateScale.getEndTime()
            - animateScale.getStartTime()));
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

  /**
   * The setColor method sets the color of the shape which is of ENUM type: Red, Green and Blue.
   *
   * @param updatedColor - new color to be set to
   */
  @Override
  public void setColor(Color updatedColor) {
    this.color = updatedColor;
  }
}
