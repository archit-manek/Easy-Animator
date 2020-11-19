package view;

import model.IAnimationModel;


/**
 * This interface represents a view that displays or projects a view from the model. The interface
 * methods include creating and displaying the view. This interface is extended to both classes.
 */
public interface IView {

  /**
   * This method gets a string representation of a model.
   *
   * @param model model that is required to get information to generate a view.
   */
  String createView(IAnimationModel model);


  /**
   * This method is a getter that returns the view type.
   */
  ViewType getViewType();


}
