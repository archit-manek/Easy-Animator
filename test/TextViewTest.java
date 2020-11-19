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
import view.IView;
import view.TextView;

import static org.junit.Assert.assertEquals;

/**
 * This class represents the test suite for the methods present in TextView class and tests all of
 * its public methods.
 */
public class TextViewTest {


  @Test
  public void testGetViewType() {
    IView view = new TextView();
    assertEquals("text", view.getViewType().toString());
  }

  @Test
  public void testCreateView() {

    IAnimationModel model = new AnimationModelImpl();


    IShape2D rectangle1 = new Rectangle("R1", new Position(1, 1),
            new Color(255, 0, 0), 5, 10, 3, 4);
    IShape2D rectangle2 = new Rectangle("R2", new Position(0, 0),
            new Color(0, 255, 0), 20, 30, 5, 4);
    IShape2D oval = new Oval("O1", new Position(1, 1),
            new Color(255, 0, 0), 5, 10, 3, 4);

    model.addShape(rectangle1);
    model.addShape(rectangle2);
    model.addShape(oval);

    IAnimation moveRect1 = new Move("R1", 6, 8, new Position(1, 1),
            new Position(3, 3));
    IAnimation changeColorRect2 = new ColorChange("R2", 25, 28,
            new Color(0, 255, 0), new Color(100, 100, 100));
    IAnimation scaleOval = new Scale("O1", 7, 9, 3, 4, 0, 8);

    model.addAnimation(moveRect1);
    model.addAnimation(changeColorRect2);
    model.addAnimation(scaleOval);

    TextView view = new TextView();

    assertEquals("Shapes:\n"
                    + "Name: R1\n"
                    + "Type: rectangle\n"
                    + "Min corner: (1.0,1.0), Width: 3.0, Height: 4.0, Color: (255, 0, 0)\n"
                    + "Appears at t=5\n"
                    + "Disappears at t=8\n"
                    + "\n"
                    + "Name: O1\n"
                    + "Type: oval\n"
                    + "Center: (1.0,1.0), X radius: 3.00, Y radius: 4.00, Color: (255, 0, 0)\n"
                    + "Appears at t=5\n"
                    + "Disappears at t=9\n"
                    + "\n"
                    + "Name: R2\n"
                    + "Type: rectangle\n"
                    + "Min corner: (0.0,0.0), Width: 5.0, Height: 4.0, Color: (0, 255, 0)\n"
                    + "Appears at t=20\n"
                    + "Disappears at t=28\n"
                    + "\n"
                    + "Shape R1 moves from (1.0,1.0) to (3.0,3.0) from t=6 to t=8\n"
                    + "Shape O1 scales from Width: 3.0, Height: 4.0 to Width: 3.0, Height: 12.0 "
                    + "from t=7 "
                    + "to t=9\n"
                    + "Shape R2 changes color from (0,255,0) to (100,100,100) from t=25 to t=28\n",
            view.createView(model));

  }
}