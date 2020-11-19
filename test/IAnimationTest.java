import org.junit.Test;

import model.AnimationModelImpl;
import model.IAnimationModel;
import model.animations.AnimationType;
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
 * This class represents the test suite for the methods present in IAnimation Interface and tests
 * all of its public method and exceptions.
 */
public class IAnimationTest {

  IAnimation moveTest1 = new Move("R1", 1, 10, new Position(0, 0),
          new Position(1, 1));
  IAnimation moveTest2 = new Move("O1", 10, 20, new Position(0, 0),
          new Position(2, 1));
  IAnimation scaleTest1 = new Scale("R1", 12, 20, 1, 5,
          3, 5);
  IAnimation scaleTest2 = new Scale("O1", 25, 30, 1, 2,
          1, 4);
  IAnimation colorChange1 = new ColorChange("R1", 30, 50, new Color(100,
          0, 0),
          new Color(255, 0, 0));
  IAnimation colorChange2 = new ColorChange("O1", 50, 100, new Color(100,
          100, 0),
          new Color(0, 255, 0));

  @Test
  public void animateShapeAndDescription() {
    IAnimationModel model = new AnimationModelImpl();
    IShape2D rectangle = new Rectangle("R", new Position(200, 200),
            new Color(1.00, 0.0, 0.0), 5, 100, 50, 100);
    IShape2D oval = new Rectangle("R", new Position(200, 200),
            new Color(1.00, 0.0, 0.0), 5, 100, 50, 100);

    model.addShape(rectangle);
    moveTest2.animateShape(rectangle);
    model.addShape(oval);
    scaleTest1.animateShape(oval);
    model.addShape(rectangle);
    colorChange1.animateShape(rectangle);

    assertEquals("Shape R moves from (0.0,0.0) to (2.0,1.0) from t=10 to t=20\n",
            moveTest2.animationDescription(model));
    assertEquals("Shape R scales from Width: 1.0, Height: 5.0 to Width: 53.0, "
                    + "Height: 105.0 from t=12 to t=20\n",
            scaleTest1.animationDescription(model));
    assertEquals("Shape R changes color from (100,0,0) to (255,0,0) from t=30 to t=50\n",
            colorChange1.animationDescription(model));

  }

  @Test
  public void getStartTime() {
    assertEquals(1, moveTest1.getStartTime());
  }

  @Test
  public void getEndTime() {
    assertEquals(10, moveTest1.getEndTime());
  }

  @Test
  public void getShapeName() {
    assertEquals("R1", moveTest1.getShapeName());
  }

  @Test
  public void getType() {
    assertEquals(AnimationType.Color.toString(), colorChange1.getType().toString());
    assertEquals(AnimationType.Color.toString(), colorChange2.getType().toString());
    assertEquals(AnimationType.Move.toString(), moveTest1.getType().toString());
    assertEquals(AnimationType.Move.toString(), moveTest2.getType().toString());
    assertEquals(AnimationType.Scale.toString(), scaleTest1.getType().toString());
    assertEquals(AnimationType.Scale.toString(), scaleTest2.getType().toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeStartTime() {
    IAnimation illegalMove = new Move("R1", -1, 10, new Position(1, 1),
            new Position(2, 3));
    IAnimation illegalScale = new Scale("O1", -25, 30, 1,
            2, 4, 5);
    IAnimation illegalColorChange = new ColorChange("R1", -30, 50,
            new Color(255, 0, 0), new Color(100, 0, 0));

    assertEquals(0, illegalMove.getStartTime());
    assertEquals(0, illegalScale.getStartTime());
    assertEquals(0, illegalColorChange.getStartTime());


  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeEndTime() {
    IAnimation illegalMove = new Move("R1", 1, -10, new Position(1, 1),
            new Position(3, 3));
    IAnimation illegalScale = new Scale("O1", 25, -30, 1,
            2, 3, 5);
    IAnimation illegalColorChange = new ColorChange("R1", 30, -50,
            new Color(255, 0, 0), new Color(100, 0, 0));

    assertEquals(0, illegalMove.getEndTime());
    assertEquals(0, illegalScale.getEndTime());
    assertEquals(0, illegalColorChange.getEndTime());

  }

  @Test(expected = IllegalArgumentException.class)
  public void testStartTimeGreaterThanEndTime() {

    IAnimation illegalMove = new Move("R1", 10, 1, new Position(1, 1),
            new Position(3, 3));
    IAnimation illegalScale = new Scale("O1", 30, 25, 1,
            2, 3, 5);
    IAnimation illegalColorChange = new ColorChange("R1", 50, 30,
            new Color(255, 0, 0), new Color(100, 0, 0));

    assertEquals("", illegalMove.getShapeName());
    assertEquals("", illegalScale.getShapeName());
    assertEquals("", illegalColorChange.getShapeName());

  }

  @Test(expected = IllegalArgumentException.class)
  public void testAnimationDescriptionNoMove() {
    AnimationModelImpl model = new AnimationModelImpl();
    IShape2D rectangle = new Rectangle("R", new Position(200, 200),
            new Color(1.00, 0.0, 0.0), 1, 100, 50, 100);
    model.addShape(rectangle);
    IAnimation illegalMove = new Move("R1", 1, 10, new Position(1, 1),
            new Position(3, 3));
    assertEquals("",
            illegalMove.animationDescription(model));

  }

  @Test(expected = IllegalArgumentException.class)
  public void testAnimationDescriptionNoScale() {
    AnimationModelImpl model = new AnimationModelImpl();
    IShape2D oval = new Rectangle("R", new Position(200, 200),
            new Color(0, 0, 1), 6, 100, 60, 30);
    model.addShape(oval);
    IAnimation illegalScale = new Scale("O1", 25, 30, 1,
            2, 4, 5);
    assertEquals("",
            illegalScale.animationDescription(model));

  }

  @Test(expected = IllegalArgumentException.class)
  public void testAnimationDescriptionNoColorChange() {
    AnimationModelImpl model = new AnimationModelImpl();
    IShape2D rectangle = new Rectangle("R", new Position(200, 200),
            new Color(1.00, 0.0, 0.0), 1, 100, 50, 100);
    model.addShape(rectangle);
    IAnimation illegalColorChange = new ColorChange("R1", 30, 50,
            new Color(255, 0, 0), new Color(100, 0, 0));
    assertEquals("",
            illegalColorChange.animationDescription(model));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testOverlappingAnimation() {
    AnimationModelImpl newModel = new AnimationModelImpl();
    IShape2D oval = new Oval("O1", new Position(200, 200),
            new Color(1.00, 0.0, 0.0), 10, 60, 50, 100);
    newModel.addShape(oval);


    newModel.addAnimation(new Scale("O1", 20, 50,
            400, 400, 4, 4));
    newModel.addAnimation(new ColorChange("O1", 25, 45,
            new Color(255, 115, 0), new Color(100, 0, 0)));
  }


}