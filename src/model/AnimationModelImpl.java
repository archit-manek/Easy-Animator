package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import model.animations.ColorChange;
import model.animations.IAnimation;
import model.animations.Move;
import model.animations.Scale;
import model.shapes.IShape2D;
import model.shapes.Oval;
import model.shapes.Rectangle;
import util.AnimationBuilder;
import util.Color;
import util.Position;

/**
 * This class represents an instance of an Animation Model and implements all of the methods in the
 * interface IAnimationModel. These methods include adding and removing a shape, adding animations,
 * getting the model shape, getting and setting the tick, and getting end time of the animation.
 */
public class AnimationModelImpl implements IAnimationModel {

  private ArrayList<IShape2D> shapesList;
  private List<String> validModelShapes;
  private ArrayList<IAnimation> animationList;
  public final static int WIDTH = 800;
  public final static int HEIGHT = 800;
  private int tick;
  private HashMap<String, String> builderShapes;

  /**
   * Constructs an animation model implementation object and initialises array lists that store
   * shapes and animations, and a hashmap that stores shapes to be used by the builder.
   */
  public AnimationModelImpl() {
    this.tick = 1;
    this.shapesList = new ArrayList<>();
    this.animationList = new ArrayList<>();
    validModelShapes = new ArrayList<>();
    builderShapes = new HashMap<>();
  }

  /**
   * This is the addShape method that adds a shape, of type IShape2D, to the model.
   *
   * @param shape - shape to be added to the model.
   */
  @Override
  public void addShape(IShape2D shape) {
    this.shapesList.add(shape);
  }

  /**
   * This is the removeShape method that removes a shape, of type IShape2D, from the model.
   *
   * @param shape - shape to be removed from the model
   * @throws IllegalArgumentException - when the given shape could not be found/is invalid
   */
  @Override
  public void removeShape(IShape2D shape) throws IllegalArgumentException {
    if (shapesList.contains(shape)) {
      this.shapesList.remove(shape);
    } else {
      throw new IllegalArgumentException("Shape not found. Please enter a valid shape to be "
              + "removed.");
    }
  }

  /**
   * This is the addAnimation method that adds an animation, of type IAnimation, to the model.
   *
   * @param animation - animation that needs to be added to the model
   */
  @Override
  public void addAnimation(IAnimation animation) {
    Objects.requireNonNull(animation);
    this.animationList.add(animation);
    for (IShape2D shape : this.getModelShapes()) {
      if (animation.getShapeName().equals(shape.getName())) {
        animation.animateShape(shape);
      }
    }
  }

  /**
   * Returns the tick rate or speed of an animation model.
   *
   * @return tick or integer representing the timeless time element of the model.
   */
  @Override
  public int getTick() {
    return this.tick;
  }

  /**
   * Sets the tick rate or speed, which is an int in model.
   *
   * @param tick tick rate.
   */
  @Override
  public void setTick(int tick) {
    this.tick = tick;
  }

  /**
   * This is the getModelShape method that returns a list of shapes in the animation.
   *
   * @return - a list of shapes in the animation.
   */
  @Override
  public ArrayList<IShape2D> getModelShapes() {
    return shapesList;
  }

  /**
   * Returns the end time of the animation, by checking for the last operation or shape exit times.
   *
   * @return the end time of the animation
   */
  @Override
  public int getEndTime() {

    ArrayList<Integer> shapeTimes = new ArrayList<>();
    ArrayList<IAnimation> allAnimations = new ArrayList<>();
    ArrayList<Integer> validAnimationTime = new ArrayList<>();

    for (IShape2D shape : this.shapesList) {
      shapeTimes.add(shape.getEndTime());
      allAnimations.addAll(shape.getAnimation());
    }
    int shapesEndTime = Collections.max(shapeTimes);


    for (IAnimation animation : allAnimations) {
      validAnimationTime.add(animation.getEndTime());
    }
    int modelEndTime = Collections.max(validAnimationTime);

    return Math.max(shapesEndTime, modelEndTime);
  }

  /**
   * This builder class implements the AnimationBuilder and has the functionality to be compatible
   * with the model that was created.
   */
  public static class Builder implements AnimationBuilder<IAnimationModel> {

    AnimationModelImpl model = new AnimationModelImpl();
    int x;
    int y;
    int width;
    int height;

    /**
     * Constructs a final document.
     *
     * @return the newly constructed document
     */

    @Override
    public IAnimationModel build() {
      return model;
    }

    /**
     * Specify the bounding box to be used for the animation.
     *
     * @param x      The leftmost x value
     * @param y      The topmost y value
     * @param width  The width of the bounding box
     * @param height The height of the bounding box
     * @return This {@link AnimationBuilder}
     */

    @Override
    public AnimationBuilder<IAnimationModel> setBounds(int x, int y, int width, int height) {
      this.x = x;
      this.y = y;
      this.width = width;
      this.height = height;
      return this;
    }

    /**
     * Adds a new shape to the growing document.
     *
     * @param name The unique name of the shape to be added. No shape with this name should already
     *             exist.
     * @param type The type of shape (e.g. "ellipse", "rectangle") to be added. The set of supported
     *             shapes is unspecified, but should include "ellipse" and "rectangle" as a
     *             minimum.
     * @return This {@link AnimationBuilder}
     */

    @Override
    public AnimationBuilder<IAnimationModel> declareShape(String name, String type) {
      switch (type) {
        case "rectangle":
        case "ellipse":
          model.builderShapes.put(name, type);
          break;
        default:
          break;
      }
      return this;
    }

    /**
     * Adds a animation to the growing document.
     *
     * @param name The name of the shape (added with {@link AnimationBuilder#declareShape})
     * @param t1   The start time of this animation
     * @param x1   The initial x-position of the shape
     * @param y1   The initial y-position of the shape
     * @param w1   The initial width of the shape
     * @param h1   The initial height of the shape
     * @param r1   The initial red color-value of the shape
     * @param g1   The initial green color-value of the shape
     * @param b1   The initial blue color-value of the shape
     * @param t2   The end time of this animation
     * @param x2   The final x-position of the shape
     * @param y2   The final y-position of the shape
     * @param w2   The final width of the shape
     * @param h2   The final height of the shape
     * @param r2   The final red color-value of the shape
     * @param g2   The final green color-value of the shape
     * @param b2   The final blue color-value of the shape
     * @return This {@link AnimationBuilder}
     */


    @Override
    public AnimationBuilder<IAnimationModel> addMotion(String name, int t1, int x1,
                                                       int y1, int w1, int h1, int r1,
                                                       int g1, int b1, int t2, int x2,
                                                       int y2, int w2, int h2,
                                                       int r2, int g2, int b2) {
      if (!model.validModelShapes.contains(name)) {
        if (model.builderShapes.get(name).equals("rectangle")) {
          model.addShape(new Rectangle(name, new Position(x1, y1), new Color(r1, g1, b1),
                  t1, 1000, w1, h1));
        } else if (model.builderShapes.get(name).equals("ellipse")) {
          model.addShape(new Oval(name, new Position(x1, y1), new Color(r1, g1, b1),
                  t1, 1000, w1, h1));

        }
        model.validModelShapes.add(name);
      } else {
        if (r1 != r2 || g1 != g2 || b1 != b2) {
          model.addAnimation(new ColorChange(name, t1, t2, new Color(r1, g1, b1),
                  new Color(r2, g2, b2)));
        } else if (w1 != w2 || h1 != h2) {
          model.addAnimation(new Scale(name, t1, t2, w1, h1, (w2 - w1), (h2 - h1)));
        }
        model.addAnimation(new Move(name, t1, t2, new Position(x1, y1), new Position(x2, y2)));

      }
      return this;
    }
  }

}






