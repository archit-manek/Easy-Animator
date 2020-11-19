package model.shapes;

import java.util.ArrayList;

import model.animations.ColorChange;
import model.animations.IAnimation;
import model.animations.Move;
import model.animations.Scale;
import util.Color;
import util.Position;

/**
 * This interface represents 2D shape objects such as a Rectangle and Oval. Various methods allow us
 * to get the shapes name, type, color, position, description, start and end time, and width &
 * height for the rectangle and xRadius and yRadius for the oval.
 */
public interface IShape2D {

  /**
   * The getName method gets the name of the shape.
   *
   * @return - name of the shape
   */
  String getName();

  /**
   * The getType method gets the type of the shape.
   *
   * @return - the type of the shape.
   */
  Shape2DType getType();

  /**
   * The getPosition method gets the position (x, y) of the shape.
   *
   * @return - the position (x, y) of the shape.
   */
  Position getPosition();


  /**
   * The setPosition method sets the position of the shape (x, y).
   *
   * @param updatedPos the new position of the shape (x, y).
   */
  void setPosition(Position updatedPos);

  /**
   * The getColor method gets the color of the shape which is of ENUM type: Red, Green and Blue.
   *
   * @return - color of the shape
   */
  Color getColor();

  /**
   * The setColor method sets the color of the shape which is of ENUM type: Red, Green and Blue.
   *
   * @param updatedColor - new color to be set to
   */
  void setColor(Color updatedColor);

  /**
   * A getter that returns the appear time for the 2D shape object.
   *
   * @return the appear time of the shape object.
   */
  int getStartTime();


  /**
   * The getEndTime method returns the end time of the shape animation.
   *
   * @return - the end time of the shape animation.
   */
  int getEndTime();


  /**
   * The getAnimation method returns a list of animations on the shape.
   *
   * @return - a list of animations on the shape.
   */
  ArrayList<IAnimation> getAnimation();

  /**
   * The getDescription method returns the description of the shape.
   *
   * @return a string description of the shape 2D object.
   */
  String getDescription();

  /**
   * The getSizeArg1 method returns either the width (rectangle) or xRadius (oval).
   *
   * @return - either the width (rectangle) or xRadius (oval).
   * @throws IllegalArgumentException - if the size argument is zero or negative.
   */
  double getSizeArg1();

  /**
   * The setSizeArg1 method updates the width (rectangle) or xRadius (oval).
   *
   * @param updatedArg1 - the new width (rectangle) or xRadius (oval).
   * @throws IllegalArgumentException - if the size argument is zero or negative.
   */
  void setSizeArg1(double updatedArg1) throws IllegalArgumentException;

  /**
   * The getSizeArg2 method returns either the height (rectangle) or yRadius (oval).
   *
   * @return - either the height (rectangle) or yRadius (oval).
   */
  double getSizeArg2();

  /**
   * The setSizeArg2 method updates the height (rectangle) or yRadius (oval).
   *
   * @param updatedArg2 - the new height (rectangle) or yRadius (oval).
   */
  void setSizeArg2(double updatedArg2) throws IllegalArgumentException;

  /**
   * Evaluate the status of animation on a shape object at a particular tick value and return the
   * shape object.
   *
   * @param tick time duration
   * @return state of the shape object at the tick value
   */
  IShape2D generateAnimatedShape(int tick);

  /**
   * This method moves the shape object to a new position with updated x and y values.
   *
   * @param updatedPos is the new desired updatedPos.
   */
  void move(Position updatedPos);

  /**
   * Evaluates the color of the new position post animation.
   *
   * @param animationColor the animationColor that is added to the shape
   * @param begColor       the starting color, before the animation
   * @param endColor       the ending color, after the animation
   * @param tick           the tick rate at which this change happens
   * @return the shape animationColor at a particular tick rate.
   */
  Color generateAnimatedColor(ColorChange animationColor, Color begColor, Color endColor, int tick);

  /**
   * Evaluates the updated position post animation.
   *
   * @param animationMove the animationMove that is added to the shape
   * @param begPosition   the starting position before the animation is applied
   * @param endPosition   the ending position after the animation is applied
   * @param tick          the tick rate at which this change happens
   * @return the shape animationMove at a particular tick rate.
   */
  Position generateAnimatedPosition(Move animationMove, Position begPosition, Position endPosition,
                                    int tick);

  /**
   * valuates the updated SizeArg1 (xRadius for oval and width for rectangle) post animation.
   *
   * @param animationScale the animationScale added to the shape
   * @param begSizeArg1    the starting SizeArg1 before the animation
   * @param endSizeArg1    the ending SizeArg1 after the animation
   * @param tick           the tick rate at which this change happens
   * @return the shape animationScale at a particular tick rate.
   */
  double generateAnimationSizeArg1(Scale animationScale, double begSizeArg1, double endSizeArg1,
                                   int tick);

  /**
   * Evaluates the updated SizeArg1 (yRadius for oval and height for rectangle) post animation.
   *
   * @param animationScale the animationScale added to the shape
   * @param begSizeArg2    the starting SizeArg1 before the animation
   * @param endSizeArg2    the ending SizeArg1 after the animation
   * @param tick           the tick rate at which this change happens
   * @return the shape animationScale at a particular tick rate.
   */
  double generateAnimationSizeArg2(Scale animationScale, double begSizeArg2, double endSizeArg2,
                                   int tick);


}