import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;

import controller.IAnimationController;
import controller.IAnimationControllerImpl;
import model.IAnimationModel;
import model.AnimationModelImpl;
import util.AnimationBuilder;
import util.AnimationReader;


/**
 * This class represents the main method for MVC animation. Based on the user input, this method
 * transfers information to the controller, which is then processed through the model to generate a
 * view based on the input files.
 */
public class EasyAnimator {

  /**
   * This is the main method that also serves as the start point for the program. The String[] args
   * parameter takes in user arguments that runs different types of animations.
   *
   * @param args arguments passed to the main method that determine which file be read for the
   *             animation, what type of view to generate, speed of the animation, and the file name
   *             to store the text view output
   */
  public static void main(String[] args) {

    final AnimationBuilder<IAnimationModel> builder;

    builder = new AnimationModelImpl.Builder();

    IAnimationModel model = builder.build();

    IAnimationController controller = new IAnimationControllerImpl(model, args);

    try {
      String inputFile = controller.getInputFile();
      InputStream inputStream = new FileInputStream(inputFile);
      AnimationReader.parseFile(new InputStreamReader(inputStream), builder);
    } catch (FileNotFoundException ex) {
      throw new IllegalArgumentException("File is not valid.");
    }

    controller.executeAnimation();
  }
}
