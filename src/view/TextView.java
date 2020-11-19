package view;

import java.util.ArrayList;
import java.util.Comparator;

import model.IAnimationModel;
import model.animations.IAnimation;
import model.shapes.IShape2D;


/**
 * This class represents the text view of an animation and it implements the view interface. The
 * view will display a text version of the animation.
 */
public class TextView implements IView {

  /**
   * Getter method to get the view type.
   *
   * @return - view type
   */
  @Override
  public ViewType getViewType() {
    return ViewType.TEXT;
  }


  /**
   * Creates the view based on the model passed in.
   *
   * @param model - animation model that is needed to create the view.
   * @return - the view in the form of a string
   */
  public String createView(IAnimationModel model) {


    ArrayList<IAnimation> animations = new ArrayList<>();
    model.getModelShapes().sort(Comparator.comparingInt(IShape2D::getStartTime));

    StringBuilder stringBuilder = new StringBuilder();
    if (!model.getModelShapes().isEmpty()) {

      stringBuilder.append("Shapes:\n");

      for (int i = 0; i < model.getModelShapes().size(); i++) {

        int getLastVal = (model.getModelShapes().get(i).getAnimation().size() - 1);
        animations.addAll(model.getModelShapes().get(i).getAnimation());
        stringBuilder.append("Name: ");
        stringBuilder.append(model.getModelShapes().get(i).getName());
        stringBuilder.append("\nType: ");
        stringBuilder.append(model.getModelShapes().get(i).getType());
        stringBuilder.append("\n");
        stringBuilder.append(model.getModelShapes().get(i).getDescription());
        stringBuilder.append("Appears at t=");
        stringBuilder.append((model.getModelShapes().get(i).getStartTime()));
        stringBuilder.append("\n");
        stringBuilder.append("Disappears at t=");
        stringBuilder.append((model.getModelShapes().get(i).getAnimation()
                .get(getLastVal)).getEndTime());
        stringBuilder.append("\n\n");

      }
      for (int j = 0; j < animations.size(); j++) {
        animations.sort(Comparator.comparingInt(IAnimation::getStartTime));
        stringBuilder.append(animations.get(j).animationDescription(model));
      }
    } else {
      stringBuilder.append("\nThere are no more 2D shape objects in this model.");
    }
    return stringBuilder.toString();
  }
}
