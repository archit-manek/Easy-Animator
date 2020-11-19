package view;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

import model.IAnimationModel;
import model.shapes.IShape2D;

/**
 * This is the ViewPanel class that extends the JPanel class. This class calls the paint and
 * repaint in built methods of the JPanel class to project the image.
 */
public class ViewPanel extends JPanel {
  private IAnimationModel model;
  private int tick;
  private int pauseCounter;

  /**
   * This is a constructor for the animation that sets the background color to white by default.
   */
  public ViewPanel() {
    super();
    this.setBackground(Color.WHITE);
    this.tick = 0;
    this.pauseCounter = 0;
  }

  /**
   * This is a getter method for the model that is used by the animation panel.
   *
   * @return model for the animation panel
   */
  public IAnimationModel getModel() {
    return this.model;
  }

  /**
   * This is a setter method for the pause counter.
   */
  public void setPauseCounter() {
    this.pauseCounter++;
  }


  /**
   * This is a setter method for the model. This sets the model that will be used in implementation.
   *
   * @param model model for the animation panel
   */
  public void setModel(IAnimationModel model) {
    this.model = model;
  }


  /**
   * This is a getter method for the tick value of the animation.
   *
   * @return the tick value of the animation.
   */
  public int getTick() {
    return this.tick;
  }

  /**
   * This is a setter method for the tick value for the panel in the animator.
   */
  public void setTick(int tick) {
    this.tick = tick;
  }

  /**
   * The paintComponent method paints the objects/shapes based on RGB values.
   *
   * @param graphics - graphics object passed in
   */
  public void paintComponent(Graphics graphics) {
    super.paintComponent(graphics);


    ArrayList<IShape2D> initialShapeList = model.getModelShapes();
    int i = 0;
    while (i < initialShapeList.size()) {
      IShape2D shape = initialShapeList.get(i);

      switch (shape.getType()) {
        case Rectangle:
          shape = shape.generateAnimatedShape(tick);
          graphics.setColor(new Color((int) shape.getColor().getRed(),
                  (int) shape.getColor().getGreen(),
                  (int) shape.getColor().getBlue()));
          graphics.fillRect((int) shape.getPosition().getX(), (int) shape.getPosition().getY(),
                  (int) shape.getSizeArg1(), (int) shape.getSizeArg2());

          break;


        case Oval:
          shape = shape.generateAnimatedShape(tick);

          graphics.setColor(new Color((int) shape.getColor().getRed(),
                  (int) shape.getColor().getGreen(),
                  (int) shape.getColor().getBlue()));

          graphics.fillOval((int) shape.getPosition().getX(), (int) shape.getPosition().getY(),
                  (int) shape.getSizeArg1(), (int) shape.getSizeArg2());
          break;
        default:
          return;
      }
      i++;
    }

    if (this.pauseCounter % 2 == 0) {
      this.tick ++;
    }

  }
}
