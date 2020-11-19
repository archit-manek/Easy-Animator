package view.listeners;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.InteractiveView;

/**
 * This is the PausePlayListener class that implements ActionListener. This class adds the pause and
 * play buttons to the animation.
 */
public class PausePlayListener implements ActionListener {
  InteractiveView view;

  /**
   * This constructor enables the user to play and pause the animation. The button switches between
   * play and pause everytime it is clicked.
   *
   * @param view the view passed in as a parameter.
   */
  public PausePlayListener(InteractiveView view) {
    this.view = view;
  }

  /**
   * This method pauses the counter everytime the pause button is clicked. This changes the button
   * depending on the current status to make the animation interactive.
   *
   * @param event is the event, i.e. button click.
   */
  @Override
  public void actionPerformed(ActionEvent event) {
    view.setPausePlay("PAUSE"
            .equalsIgnoreCase(view.getPausePlay()) ? "PLAY" : "PAUSE");
    this.view.getPanel().setPauseCounter();
  }
}
