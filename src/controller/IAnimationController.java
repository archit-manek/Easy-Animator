package controller;

/**
 * This is the interface for the Animation Controller for the model. The controller handles user
 * inputs and returns the view as a response to the user input. The controller serves as a bridge
 * between the model and the view and facilitates communication.
 */
public interface IAnimationController {
  /**
   * Creates the animation based on arguments defined by user for the input file, view type,
   * intended output and tick rate of the animation model.
   */
  void executeAnimation();

  /**
   * Returns an input file, in the form of a String, based on user input.
   *
   * @return an input file that converts the information.
   */
  String getInputFile();


}
