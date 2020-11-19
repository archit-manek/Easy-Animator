import org.junit.Test;

import model.AnimationModelImpl;
import model.IAnimationModel;
import model.animations.ColorChange;
import model.animations.IAnimation;
import model.animations.Move;
import model.animations.Scale;
import model.shapes.IShape2D;
import model.shapes.Oval;
import model.shapes.Rectangle;
import util.Color;
import util.Position;

import static org.junit.Assert.assertEquals;

/**
 * This class represents the test suite for the methods present in IShape2D Interface and tests all
 * of its public method and exceptions.
 */
public class IShape2DTest {

  AnimationModelImpl model = new AnimationModelImpl();
  IShape2D rectangleTest1 = new Rectangle("R1", new Position(1, 2),
          new Color(255, 0, 0), 2, 3, 3, 5);
  IShape2D ovalTest = new Oval("Oval", new Position(2, 0),
          new Color(0, 100, 200), 10, 10, 10, 100);

  @Test
  public void getNameTest() {
    assertEquals("R1", rectangleTest1.getName());
    assertEquals("Oval", ovalTest.getName());
  }

  @Test
  public void getTypeTest() {
    assertEquals("rectangle", rectangleTest1.getType().toString());
    assertEquals("oval", ovalTest.getType().toString());
  }

  @Test
  public void getPositionTest() {
    assertEquals(1.0, rectangleTest1.getPosition().getX(), 0.001);
    assertEquals(2, ovalTest.getPosition().getX(), 0.001);
    assertEquals(2.0, rectangleTest1.getPosition().getY(), 0.001);
    assertEquals(0, ovalTest.getPosition().getY(), 0.001);
  }

  @Test
  public void setPositionTest() {
    rectangleTest1.setPosition(new Position(2, 3));
    assertEquals(2, rectangleTest1.getPosition().getX(), 0.001);
    assertEquals(3, rectangleTest1.getPosition().getY(), 0.001);
    ovalTest.setPosition(new Position(4, 5));
    assertEquals(4, ovalTest.getPosition().getX(), 0.001);
    assertEquals(5, ovalTest.getPosition().getY(), 0.001);
  }

  @Test
  public void getColorTest() {
    assertEquals(255, rectangleTest1.getColor().getRed(), 0.001);
    assertEquals(0, rectangleTest1.getColor().getGreen(), 0.001);
    assertEquals(0, rectangleTest1.getColor().getBlue(), 0.001);
    assertEquals(0, ovalTest.getColor().getRed(), 0.001);
    assertEquals(100, ovalTest.getColor().getGreen(), 0.001);
    assertEquals(200, ovalTest.getColor().getBlue(), 0.001);
  }

  @Test
  public void setColorTest() {
    rectangleTest1.setColor(new Color(0, 0, 255));
    assertEquals("(0,0,255)", rectangleTest1.getColor().getRGBString());
    ovalTest.setColor(new Color(0, 0, 200));
    assertEquals("(0,0,200)", ovalTest.getColor().getRGBString());
  }

  @Test
  public void getStartTimeTest() {
    assertEquals(2, rectangleTest1.getStartTime());
    assertEquals(10, ovalTest.getStartTime());
  }

  @Test
  public void getEndTimeTest() {
    assertEquals(3, rectangleTest1.getEndTime());
    assertEquals(10, ovalTest.getEndTime());
  }

  @Test
  public void getAnimationTest() {
    IShape2D rectangleTest1 = new Rectangle("Rectangle", new Position(0, 0),
            new Color(255, 0, 0), 4, 5, 10, 60);
    model.addShape(this.rectangleTest1);
    IAnimation move = new Move("Rectangle", 20, 30, new Position(100, 0),
            new Position(110, 100));
    IAnimation scale = new Scale("Rectangle", 40, 50, 10,
            10, 5, 5);
    model.addAnimation(move);
    model.addAnimation(scale);
    assertEquals(this.rectangleTest1.getAnimation(), model.getModelShapes().get(0).getAnimation());
  }

  @Test
  public void getSizeArg1Test() {
    assertEquals(3.0, rectangleTest1.getSizeArg1(), 0.001);
    assertEquals(10.0, ovalTest.getSizeArg1(), 0.001);
  }

  @Test
  public void setSizeArg1Test() {
    rectangleTest1.setSizeArg1(5.0);
    assertEquals(5.0, rectangleTest1.getSizeArg1(), 0.001);
    ovalTest.setSizeArg1(6.0);
    assertEquals(6.0, ovalTest.getSizeArg1(), 0.001);
  }

  @Test
  public void getSizeArg2Test() {
    assertEquals(5.0, rectangleTest1.getSizeArg2(), 0.001);
    assertEquals(100.0, ovalTest.getSizeArg2(), 0.001);
  }

  @Test
  public void setSizeArg2Test() {
    rectangleTest1.setSizeArg2(6.0);
    assertEquals(6.0, rectangleTest1.getSizeArg2(), 0.001);
    ovalTest.setSizeArg2(7.0);
    assertEquals(7.0, ovalTest.getSizeArg2(), 0.001);
  }

  @Test
  public void getDescriptionTest() {
    assertEquals("Min corner: (1.0,2.0), Width: 3.0, Height: 5.0, Color: (255, 0, 0)\n",
            rectangleTest1.getDescription());
    assertEquals("Center: (2.0,0.0), X radius: 10.00, Y radius: 100.00, "
                    + "Color: (0, 100, 200)\n",
            ovalTest.getDescription());
  }

  @Test
  public void generateAnimatedShapeRectangleMoveTest() {
    IAnimationModel model = new AnimationModelImpl();

    IShape2D rectangleTest1 = new Rectangle("R1", new Position(0, 0),
            new Color(255, 0, 0), 0, 100, 20, 30);
    IAnimation moveRect1 = new Move("R1", 6, 10, new Position(0, 0),
            new Position(3, 3));

    model.addShape(rectangleTest1);
    model.addAnimation(moveRect1);

    assertEquals(0.0, rectangleTest1.generateAnimatedShape(1).getPosition().getX(), 0.001);
    assertEquals(0.0, rectangleTest1.generateAnimatedShape(1).getPosition().getY(), 0.001);
    assertEquals(3.0, rectangleTest1.generateAnimatedShape(11).getPosition().getX(), 0.001);
    assertEquals(3.0, rectangleTest1.generateAnimatedShape(11).getPosition().getY(), 0.001);

    // Quarter way through the move.
    assertEquals(0.75, rectangleTest1.generateAnimatedShape(7).getPosition().getX(), 0.001);
    assertEquals(0.75, rectangleTest1.generateAnimatedShape(7).getPosition().getY(), 0.001);

  }


  @Test
  public void generateAnimatedShapeOvalMoveTest() {
    IAnimationModel model = new AnimationModelImpl();

    IShape2D ovalTest = new Oval("O1", new Position(1, 2),
            new Color(255, 0, 0), 0, 100, 20, 30);
    IAnimation moveOval = new Move("O1", 4, 8, new Position(0, 0),
            new Position(3, 4));

    model.addShape(ovalTest);
    model.addAnimation(moveOval);

    assertEquals(1.0, ovalTest.generateAnimatedShape(1).getPosition().getX(), 0.001);
    assertEquals(2.0, ovalTest.generateAnimatedShape(1).getPosition().getY(), 0.001);
    assertEquals(3.0, ovalTest.generateAnimatedShape(11).getPosition().getX(), 0.001);
    assertEquals(4.0, ovalTest.generateAnimatedShape(11).getPosition().getY(), 0.001);

    // Quarter way through the move.
    assertEquals(2.5, ovalTest.generateAnimatedShape(7).getPosition().getX(), 0.001);
    assertEquals(3.5, ovalTest.generateAnimatedShape(7).getPosition().getY(), 0.001);

  }

  @Test
  public void generateAnimatedShapeRectangleScaleTest() {
    IAnimationModel model = new AnimationModelImpl();

    IShape2D rectangleTest1 = new Rectangle("R1", new Position(0, 0),
            new Color(255, 0, 0), 0, 100, 20, 30);
    IAnimation scaleRect1 = new Scale("R1", 6, 10, 20, 30,
            50, 60);

    model.addShape(rectangleTest1);
    model.addAnimation(scaleRect1);

    assertEquals(20.0, rectangleTest1.generateAnimatedShape(1).getSizeArg1(), 0.001);
    assertEquals(30.0, rectangleTest1.generateAnimatedShape(1).getSizeArg2(), 0.001);
    assertEquals(70.0, rectangleTest1.generateAnimatedShape(11).getSizeArg1(), 0.001);
    assertEquals(80.0, rectangleTest1.generateAnimatedShape(11).getSizeArg2(), 0.001);

    // Quarter way through the move.
    assertEquals(35.0, rectangleTest1.generateAnimatedShape(7).getSizeArg1(), 0.001);
    assertEquals(45.0, rectangleTest1.generateAnimatedShape(7).getSizeArg2(), 0.001);
  }

  @Test
  public void generateAnimatedShapeOvalScaleTest() {
    IAnimationModel model = new AnimationModelImpl();

    IShape2D ovalTest = new Rectangle("O1", new Position(0, 0),
            new Color(255, 0, 0), 0, 100, 20, 30);
    IAnimation scaleOval = new Scale("O1", 6, 10, 20, 30,
            60, 80);

    model.addShape(ovalTest);
    model.addAnimation(scaleOval);

    assertEquals(20.0, ovalTest.generateAnimatedShape(1).getSizeArg1(), 0.001);
    assertEquals(30.0, ovalTest.generateAnimatedShape(1).getSizeArg2(), 0.001);
    assertEquals(80.0, ovalTest.generateAnimatedShape(11).getSizeArg1(), 0.001);
    assertEquals(90.0, ovalTest.generateAnimatedShape(11).getSizeArg2(), 0.001);

    // Quarter way through the move.
    assertEquals(40.0, ovalTest.generateAnimatedShape(7).getSizeArg1(), 0.001);
    assertEquals(50.0, ovalTest.generateAnimatedShape(7).getSizeArg2(), 0.001);
  }

  @Test
  public void generateAnimatedShapeRectangleColorTest() {
    IAnimationModel model = new AnimationModelImpl();

    IShape2D rectangleTest1 = new Rectangle("R1", new Position(0, 0),
            new Color(255, 0, 0), 0, 100, 20, 30);
    IAnimation colorChangeRect = new ColorChange("R1", 6, 10,
            new Color(255, 0, 0), new Color(100, 100, 100));

    model.addShape(rectangleTest1);
    model.addAnimation(colorChangeRect);

    assertEquals("(255,0,0)", rectangleTest1.generateAnimatedShape(1).getColor().
            getRGBString());
    assertEquals("(100,100,100)", rectangleTest1.generateAnimatedShape(11).getColor().
            getRGBString());

    // Half way through the move.
    assertEquals("(178,50,50)", rectangleTest1.generateAnimatedShape(8).getColor().
            getRGBString());
  }

  @Test
  public void generateAnimatedShapeOvalColorTest() {
    IAnimationModel model = new AnimationModelImpl();

    IShape2D ovalTest = new Oval("O1", new Position(0, 0),
            new Color(255, 0, 0), 0, 100, 20, 30);
    IAnimation colorChangeOval = new ColorChange("O1", 6, 10,
            new Color(255, 0, 0), new Color(50, 50, 50));

    model.addShape(ovalTest);
    model.addAnimation(colorChangeOval);

    assertEquals("(255,0,0)", ovalTest.generateAnimatedShape(1).getColor().getRGBString());
    assertEquals("(50,50,50)", ovalTest.generateAnimatedShape(11).getColor().getRGBString());

    // Half way through the move.
    assertEquals("(153,25,25)", ovalTest.generateAnimatedShape(8).getColor().getRGBString());
  }


  @Test
  public void moveTest() {
    ovalTest.move(new Position(4, 3));
    assertEquals(4, ovalTest.getPosition().getX(), 0.001);
    assertEquals(3, ovalTest.getPosition().getY(), 0.001);

    rectangleTest1.move(new Position(6, 7));
    assertEquals(6, rectangleTest1.getPosition().getX(), 0.001);
    assertEquals(7, rectangleTest1.getPosition().getY(), 0.001);
  }


  @Test(expected = IllegalArgumentException.class)
  public void testNegativeStartTime() {
    IShape2D illegalRectangle = new Rectangle("Rectangle", new Position(0, 0),
            new Color(255, 0, 0), -1, 4, 4, 5);

    IShape2D illegalOval = new Oval("Oval", new Position(2, 0),
            new Color(0, 100, 200), -10, 100, 10, 10);

    assertEquals(0, illegalRectangle.getStartTime(), 0.001);
    assertEquals(0, illegalOval.getStartTime(), 0.001);

  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeEndTime() {
    IShape2D illegalRectangle = new Rectangle("Rectangle", new Position(0, 0),
            new Color(255, 0, 0), 1, -4, 4, 5);

    IShape2D illegalOval = new Oval("Oval", new Position(2, 0),
            new Color(0, 100, 200), 10, -100, 10, 10);

    assertEquals(0, illegalRectangle.getEndTime(), 0.001);
    assertEquals(0, illegalOval.getEndTime(), 0.001);

  }

  @Test(expected = IllegalArgumentException.class)
  public void testStartTimeGreaterThanEndTime() {
    IShape2D illegalRectangle = new Rectangle("Rectangle", new Position(0, 0),
            new Color(255, 0, 0), 4, 1, 4, 5);

    IShape2D illegalOval = new Oval("Oval", new Position(2, 0),
            new Color(0, 100, 200), 100, 10, 10, 10);

    assertEquals("", illegalRectangle.getDescription());
    assertEquals("", illegalOval.getDescription());

  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeSizeArgument1() {
    IShape2D illegalRectangle = new Rectangle("Rectangle", new Position(0, 0),
            new Color(255, 0, 0), 4, 1, -4, 5);

    IShape2D illegalOval = new Oval("Oval", new Position(2, 0),
            new Color(0, 100, 200), 100, 10, -10, 10);

    assertEquals(0, illegalRectangle.getSizeArg1(), 0.001);
    assertEquals(0, illegalOval.getSizeArg2(), 0.001);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeSizeArgument2() {
    IShape2D illegalRectangle = new Rectangle("Rectangle", new Position(0, 0),
            new Color(255, 0, 0), 4, 1, 4, -5);

    IShape2D illegalOval = new Oval("Oval", new Position(2, 0),
            new Color(0, 100, 200), 100, 10, 10, -10);

    assertEquals(0, illegalRectangle.getSizeArg2(), 0.001);
    assertEquals(0, illegalOval.getSizeArg2(), 0.001);
  }


}