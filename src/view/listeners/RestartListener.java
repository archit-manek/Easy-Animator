package view.listeners;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.InteractiveView;

/**
 * This restart listener class implements the ActionListener class. It handles the restart feature
 * of the animation in the interactive view.
 */
public class RestartListener implements ActionListener {
  InteractiveView view;

  /**
   * This constructor enables the user to restart the animation. The button restarts the animation
   * every time it is clicked.
   *
   * @param view view passed in.
   */
  public RestartListener(InteractiveView view) {
    this.view = view;
  }

  /**
   * This method restarts the counter everytime the restart button is clicked.
   *
   * @param event is the event, i.e. button click.
   */
  @Override
  public void actionPerformed(ActionEvent event) {
    this.view.getPanel().setTick(0);
  }
}
