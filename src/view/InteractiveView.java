package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.WindowConstants;

import model.IAnimationModel;
import model.AnimationModelImpl;

/**
 * This is the Interactive view class that adds the pause, play and restart functionality.
 */
public class InteractiveView extends JFrame implements IView, ActionListener {
  private final ViewPanel panel;
  private IAnimationModel model;
  private final JButton play;
  private String pausePlay;
  private final JButton restart;


  /**
   * Constructor for an interactive playback view that offers functionality. Implements a grid
   * layout with the buttons at the top of the screen.
   */

  /**
   * This constructor creates a grid layout with the pause/play and restart button on the top of the
   * screen of the animation.
   */
  public InteractiveView() {
    super();
    this.panel = new ViewPanel();
    this.setTitle("Easy Animator Final Project (Siddhartha Pant and Archit Manek)");
    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    this.setLayout(new GridLayout());

    panel.setPreferredSize(new Dimension(AnimationModelImpl.WIDTH,
            AnimationModelImpl.HEIGHT));
    this.add(panel, BorderLayout.CENTER);


    this.pausePlay = "PAUSE";
    this.play = new JButton(this.pausePlay);
    panel.add(this.play, BorderLayout.SOUTH);
    this.restart = new JButton("RESTART");
    panel.add(this.restart, BorderLayout.SOUTH);
    this.setVisible(true);
    this.pack();
  }

  /**
   * The getModel method returns the current model of the animation.
   *
   * @return animation model
   */
  public IAnimationModel getModel() {
    return this.model;
  }

  /**
   * The getPlay method returns the JButton that plays/pauses the animation.
   *
   * @return button.
   */
  public JButton getPlay() {
    return this.play;
  }

  /**
   * The getRestart method returns the JButton that restarts the animation.
   *
   * @return button.
   */
  public JButton getRestart() {
    return this.restart;
  }

  /**
   * This setter method can change the text of the button. Helps to change between pause and play.
   *
   * @param string - the new text of the button.
   */
  public void setPausePlay(String string) {
    this.play.setText(string);
    this.pausePlay = string;
  }

  /**
   * This method gets the play button if the string name is changed.
   *
   * @return the name of the play button.
   */
  public String getPausePlay() {
    return this.pausePlay;
  }

  /**
   * This method returns the type of view.
   *
   * @return type of view
   */
  @Override
  public ViewType getViewType() {
    return ViewType.INTERACTIVE;
  }

  /**
   * This method gets a string representation of a model.
   *
   * @param model model that is required to get information to generate a view.
   * @return empty string
   */
  @Override
  public String createView(IAnimationModel model) {
    this.model = model;
    Timer timer = new Timer(this.model.getTick(), this);
    timer.start();
    return "";

  }

  /**
   * This method gets the panel of the view.
   *
   * @return - view panel
   */
  public ViewPanel getPanel() {
    return this.panel;
  }

  /**
   * This is the actionPerformed method that listens for button clicks.
   *
   * @param event button clicks
   */
  @Override
  public void actionPerformed(ActionEvent event) {
    repaint();
  }
}
