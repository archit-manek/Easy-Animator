package util;

/**
 * This class represents a position object, which has two key values - an x coordinate and a y
 * coordinate.
 */

public class Position {
  private double x;
  private double y;


  /**
   * The constructor takes in an x coordinate value and a y coordinate value.
   *
   * @param x is the x value of the position.
   * @param y is the y value of the position.
   */

  public Position(double x, double y) {
    this.x = x;
    this.y = y;
  }


  /**
   * A method that gets the x coordinate value.
   *
   * @return the x value of the coordinate.
   */
  public double getX() {
    return this.x;
  }

  /**
   * A method that gets the y coordinate value.
   *
   * @return the y value of the coordinate.
   */
  public double getY() {
    return this.y;
  }

  /**
   * A method that sets the x coordinate for the position of an object.
   *
   * @param x is the x value that is being set.
   */
  public void setX(double x) {
    this.x = x;
  }

  /**
   * A method that sets the y coordinate for the position of an object.
   *
   * @param y is the y value that is being set.
   */
  public void setY(double y) {
    this.y = y;
  }
}
