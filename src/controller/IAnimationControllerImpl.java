package controller;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;

import model.IAnimationModel;
import view.IView;
import view.ViewFactory;


/**
 * This class implements the controller interface and is responsible for fulfilling requests made by
 * the user by controlling execution flow. The main parameters are the model and the arguments
 * passed in by the user.
 */
public class IAnimationControllerImpl implements IAnimationController {
  private final IAnimationModel model;
  private final IView view;
  private int tick;
  private String viewType;
  private boolean outputValidator;
  private String inputFile;
  private Appendable appendable = System.out;

  /**
   * This constructor creates an instance of the controller. The controller parameters are the model
   * and the arguments passed in by the user.
   *
   * @param model - the IAnimation Model passed in
   * @param args  - user arguments passed in the main method
   * @throws IllegalArgumentException if null model is passed
   */
  public IAnimationControllerImpl(IAnimationModel model, String[] args)
          throws IllegalArgumentException {
    Objects.requireNonNull(model);
    this.model = model;
    this.userInputReader(args);
    model.setTick(tick);
    ViewFactory viewCall = new ViewFactory();
    view = viewCall.viewFactoryImpl(viewType, model);
  }

  /**
   * The userInput method figures out what view should be displayed based on the users input. The
   * input filled is passed in as an argument.
   *
   * @param args - arguments passed in by the user
   * @throws IllegalArgumentException if the file passed in is invalid or if tick rate is negative
   */
  private void userInputReader(String[] args) throws IllegalArgumentException {


    if (args.length > 0) {

      int i = 0;
      while (i < args.length) {

        if ((args[i + 1].length() > 0)) {
          switch (args[i]) {
            case "-in":
              if ((args[i + 1].endsWith(".txt"))) {
                inputFile = args[i + 1];
              } else {
                throw new
                        IllegalArgumentException(args[i + 1].substring(args[i + 1].length() - 4)
                        + " Filetype is invalid");
              }
              break;

            case "-out":
              if (!args[i + 1].equalsIgnoreCase("out")) {
                try {
                  appendable = new FileWriter(args[i + 1]);
                  outputValidator = false;
                } catch (IOException ex) {
                  throw new IllegalArgumentException("Output file could not be created");
                }
              } else {
                appendable = System.out;
              }
              break;

            case "-view":
              viewType = args[i + 1];
              break;


            case "-speed":
              try {
                tick = Integer.parseInt(args[i + 1]);
              } catch (NumberFormatException ex) {
                throw new IllegalArgumentException(args[i + 1] + " Input is invalid.");
              }
              if (tick < 1) {
                throw new IllegalArgumentException("Tick rate values cannot be 0 or negative");
              }
              break;

            default:
              throw new IllegalArgumentException("Input parameters are invalid.");
          }
        }
        i += 2;
      }
    }
    if (this.inputFile.isEmpty()) {
      throw new IllegalArgumentException("Input file is invalid.");
    }

  }


  /**
   * This is the executeAnimation method that executes the animation.
   *
   * @throws IllegalArgumentException if the view could not be created or if the output is invalid
   */
  @Override
  public void executeAnimation() throws IllegalArgumentException {
    try {
      appendable.append(view.createView(model));
    } catch (IOException ex) {
      throw new IllegalArgumentException("View could not be created.");
    }

    if (!outputValidator) {
      try {
        ((FileWriter) appendable).flush();
        ((FileWriter) appendable).close();
      } catch (IOException ex) {
        throw new IllegalStateException("Output is invalid.");
      }
    }
  }

  /**
   * Returns an input file, in the form of a String, based on user input.
   *
   * @return an input file in the form of a String
   */
  @Override
  public String getInputFile() {
    return this.inputFile;
  }


}



