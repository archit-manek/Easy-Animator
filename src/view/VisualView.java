package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.WindowConstants;

import model.AnimationModelImpl;
import model.IAnimationModel;

/**
 * This class is a the visual view of the shapes and animations, and extends the JFrame class and
 * therefore it implements the IView and ActionListener interface.
 */
public class VisualView extends JFrame implements IView, ActionListener {
  private final ViewPanel viewPanel;


  /**
   * This constructs a visual view object that works with the MVC framework.
   */
  public VisualView() {
    super();
    this.viewPanel = new ViewPanel();
    this.setTitle("Easy Animator Final Project (Siddhartha Pant and Archit Manek)");


    this.setLayout(new BorderLayout());
    viewPanel.setPreferredSize(new Dimension(AnimationModelImpl.WIDTH,
            AnimationModelImpl.HEIGHT));
    this.add(viewPanel, BorderLayout.NORTH);

    this.pack();
    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    this.executeView();
  }

  /**
   * Executes the view by changing the visibility.
   */
  public void executeView() {
    this.setVisible(true);
  }

  /**
   * This is a getter method for the panel of the animation.
   *
   * @return - the animation panel
   */
  public ViewPanel getPanel() {
    return this.viewPanel;
  }

  /**
   * This is a method that returns the type of the view.
   *
   * @return - the view type
   */
  @Override
  public ViewType getViewType() {
    return ViewType.VISUAL;
  }

  /**
   * This method creates a view for the animation based on the model.
   *
   * @param model animation model required to create a view.
   * @return - a string
   */
  @Override
  public String createView(IAnimationModel model) {
    Timer timer = new Timer(1000 / model.getTick(), this);
    timer.start();
    return "";
  }

  /**
   * The actionPerformed method makes objects update based on the repaint method.
   *
   * @param event - object of event that is working on the animation
   */
  @Override
  public void actionPerformed(ActionEvent event) {
    repaint();
  }
}
