package model.animations;


import model.IAnimationModel;
import model.shapes.IShape2D;

/**
 * This interface represents the various animations that are possible to make within the model. This
 * includes animations such as ColorChange, Move and Scale.
 */
public interface IAnimation {

  /**
   * This method is the animateShape method. It takes in a shape and stores the animations that
   * happen on the shape.
   *
   * @param shapeObject - 2D shape that the animation will happen on
   * @throws IllegalArgumentException - if the animation parameters are invalid
   */

  void animateShape(IShape2D shapeObject) throws IllegalArgumentException;

  /**
   * This is the animationDescription method. It returns the current description of the animation.
   *
   * @param model - model that is passed through the method to return the string description
   * @return - current description of the animation
   * @throws IllegalArgumentException - if the animation occurs on an invalid shape.
   */

  String animationDescription(IAnimationModel model) throws IllegalArgumentException;

  /**
   * This is the getStartTime method. It gets the start time of the animation.
   *
   * @return - start time of the animation
   */
  int getStartTime();

  /**
   * This is the getEndTime method. It gets the end time of the animation.
   *
   * @return - end time of the animation
   */
  int getEndTime();

  /**
   * This is the getShapeName method that returns the name of the shape.
   *
   * @return - the name of the shape
   */
  String getShapeName();

  /**
   * This is the getType method. This returns the type of the animation.
   *
   * @return - type of animation.
   */
  AnimationType getType();


}
