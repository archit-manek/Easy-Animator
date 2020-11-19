package model;

import java.util.ArrayList;

import model.animations.IAnimation;
import model.shapes.IShape2D;

/**
 * This interface represents an Animation Model. This model will implement the functionality of an
 * animation including adding shapes and animations, removing shapes, and getting the model shape,
 * getting and setting tick and the end time.
 */
public interface IAnimationModel {


  /**
   * This is the getModelShape method that returns a list of shapes in the animation.
   *
   * @return - a list of shapes in the animation.
   */
  ArrayList<IShape2D> getModelShapes();

  /**
   * This is the addShape method that adds a shape, of type IShape2D, to the model.
   *
   * @param shape - shape to be added to the model.
   */
  void addShape(IShape2D shape);

  /**
   * This is the removeShape method that removes a shape, of type IShape2D, from the model.
   *
   * @param shape - shape to be removed from the model
   * @throws IllegalArgumentException - when the given shape could not be found/is invalid
   */
  void removeShape(IShape2D shape) throws IllegalArgumentException;

  /**
   * This is the addAnimation method that adds an animation, of type IAnimation, to the model.
   *
   * @param animation - animation that needs to be added to the model
   */
  void addAnimation(IAnimation animation);

  /**
   * Sets the tick rate or speed, which is an int in model.
   *
   * @param tick tick rate.
   */
  void setTick(int tick);

  /**
   * Returns the tick rate or speed of an animation model.
   *
   * @return tick or integer representing the timeless time element of the model.
   */
  int getTick();

  /**
   * Returns the end time of the animation, by checking for the last operation or shape exit times.
   *
   * @return the end time of the animation
   */
  int getEndTime();


}


