import org.junit.Test;

import controller.IAnimationController;
import controller.IAnimationControllerImpl;
import model.AnimationModelImpl;
import model.IAnimationModel;
import model.animations.IAnimation;
import model.animations.Move;
import model.shapes.IShape2D;
import model.shapes.Oval;
import model.shapes.Rectangle;
import util.Color;
import util.Position;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * This class represents the test suite for the methods present in IAnimationModel Interface and
 * tests all of its public method and exceptions.
 */
public class IAnimationModelTest {

  AnimationModelImpl model = new AnimationModelImpl();

  IShape2D rectangle = new Rectangle("R", new Position(200, 200),
          new Color(1.00, 0.0, 0.0), 1, 100, 50, 100);

  IShape2D oval = new Oval("C", new Position(500, 100),
          new Color(0, 0, 1.0), 6, 100, 60, 30);


  @Test
  public void getModelShapeTest() {
    assertTrue(model.getModelShapes().isEmpty());
    model.addShape(rectangle);
    assertEquals(1, model.getModelShapes().size());
    model.addShape(oval);
    assertEquals(2, model.getModelShapes().size());
    assertTrue(model.getModelShapes().contains(rectangle));
    assertTrue(model.getModelShapes().get(0).equals(rectangle));
    assertFalse(model.getModelShapes().isEmpty());
    assertEquals("C", model.getModelShapes().get(1).getName());

  }

  @Test
  public void addShapeTest() {
    model.addShape(rectangle);
    assertEquals(1, model.getModelShapes().size());
    assertTrue(model.getModelShapes().contains(rectangle));
  }

  @Test
  public void removeShapeTest() {
    model.addShape(oval);
    assertTrue(model.getModelShapes().contains(oval));
    model.removeShape(oval);
    assertFalse(model.getModelShapes().contains(oval));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRemoveNonExistentShape() {

    // adding oval shape to the model
    model.addShape(oval);

    // when attempting to remove a rectangle shape (that does not exist), throws the exception
    // cannot test using Assert as the method returns void
    model.removeShape(rectangle);
  }

  @Test
  public void getEndTimeTest() {
    IAnimation move = new Move("R", 5, 10, new Position(3, 3),
            new Position(6, 6));
    model.addShape(rectangle);
    model.addAnimation(move);
    assertEquals(100, model.getEndTime());
  }

  @Test
  public void getAndSetTickTest() {
    IAnimationModel model = new AnimationModelImpl();
    model.setTick(50);
    assertEquals(50, model.getTick());
  }

  @Test(expected = NullPointerException.class)
  public void emptyAnimationTest() {
    IAnimationModel model = new AnimationModelImpl();
    model.addAnimation(null);
  }

  @Test(expected = NullPointerException.class)
  public void nullAnimationControllerTest() {
    String[] args = {"-in", "files/toh-8.txt", "-view", "visual", "-out", "output.txt", "-speed",
        "80"};
    IAnimationController controller = new IAnimationControllerImpl(null, args);
  }
}