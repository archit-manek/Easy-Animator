# Easy Animator
Animation of various shapes implemented using the mvc framework. JSwing used for the front-end. 

The scope of this assignment was to implement the MVC framework that divided
the functionalities of implementing animation into three components: model,
view and the controller to project animations such as:
    
-	Small Demonstration
-	Towers of Hanoi
-	The Night Sky
-	Big Bang and Crunch

The project has been structured into various packages that define various
components of the project submission. In this document, the methods and
functionalities implemented by each package including classes and methods
any sub-packages are explained. The packages in the project are:
    
-	model
-	controller
-	view
-	utils


model package:

The scope of this package is to implement the model component of the Easy
Animator project and is divided into the Animations and Shapes subpackages,
and includes an IAnimationModel interface and AnimationModelImpl class that
extends the IAnimationModelInterface interface.

animation subpackage:

The animations sub-package consists of the IAnimation interface that represents
the methods supported by the animation model. The IAnimation interface consists
of the following methods:
    
-	animateShape: takes in a shape and stores the animations that happen on the
        shape
-	animationDescription: returns the current description of the animation
-	getStartTime: getter method to get the start time of the animation
-	getEndTime: getter method to get the end time of the animation
-	getShapeName: getter method that returns the name of the Shape
-	getType: getter method that returns the type of the Animation


The IAnimation interface is implemented by the AbstractAnimation class and
is responsible for all of the data in the animation.The AbstractAnimationClass
is further extended by the concrete classes Move, Scale and Color Change that
implement specific methods that are specific to the Move, Scale and Color Change
animations. This also includes a custom toString method that is titled
animationDescription. The individual classes also have different parameters
that are the updated and desired changes on the shape objects.

Additionally, for all animation objects, there is an Enum AnimationType that
captures the type of animation that really allows us to continue to add other
types of animations to our assignment should we be required to do so.

shape subpackage:

The shape subpackage consists of the IShape2D interface that represents the methods
supported by the animation model. The IShape2D interface consists of the following
major methods:
    
-	getName: getter method to get the name of the shape
-	getTypeL getter method to get the type of the shape
-	getPosition & setPosition: getter and setter methods to get and set the
        position
-	getColor & setColor: getter and setter methods to get and set the color
-	getStartTime: get start time of the shape as it appears
-	getEndTime: get end time of the shape as it disappears
-	getAnimation: get the animations in the shape in the form of an array list
-	getDescription: get the description of the shape
-	generateAnimatedShape: evaluate the status of an animation on a shape object
        at a particular tick value and returns the shape object.

The IShape2D interface is implemented by the AbstractShape2D class and is
responsible for storing all of the properties of the shape and its associated
animations. The AbstractShape2DClass is further extended by the concrete classes
Rectangle and Oval classes that implement specific methods that are specific to
the Rectangle and Oval shape objects.

Additionally we have an ENUM Shape2DType that captures the two different shapes
that we are currently dealing with: rectangles and oval. If the model were to be
elaborated to include further shape types, this ENUM can be easily updated to
accommodate for additional shape types.

IAnimationModel interface and IAnimationModelImpl class

IAnimationModel in the model package represents all of the methods that are
supported by an animation model. This model is responsible for all of the data
in the animation. It has the ability to add or remove shapes and animations.
We chose to use an array list to store all of the animation data and the shape
data in the model. This was the desired data structure as it was the optimal
data structure for the sake of easier access, appending and removal. The
IAnimationModelInterface consists of the following methods and is implemented
by the IAnimationModelImpl Class:

-	getModelShapes: returns a list of shapes in the animation.
-	addShape: adds a shape, of type IShape2D, to the model.
-	removeShape: removes a shape, of type IShape2D, from the model.
-	addAnimations: adds an animation, of type IAnimation, to the model.
-	getTick and setTick: getter and setter method for the tick rate
-	getEndTime: getter method that returns the end time of the animation

In the IAnimationModelImpl class we also implement the builder interface as a
static class within the class. This builder class uses the Builder Design
Pattern and implements the functionality of the builder to be compatible with
our existing model structure.

util package:

The util package consists of classes and interfaces that support the MVC
operations of the project. It consists of:
    
-	AnimationBuilder Interface: consists of methods that build that animation
-	AnimationReader Class: that acts as a helper class to read animation data
        and construct animation data from it through the main method
-	Color Class: represents a color object in the form of RGB values that is a
        component of shape and animation objects
-	Position Class: represents a position object in the form of (x,y) coordinates
        that is a component of shape and animation objects

view package:

The scope of this package is to implement the view component of the Easy
Animator project and implements the Visual and Text Views. The Visual view is
supported by the inbuilt functionalities of existing Java functions. We use
both JFrame, a heavy weight container used as the top-level window and also
JPanel a lightweight container generally used to organize Graphical user
interface components for the visual views.

A view factory is implemented using the Factory Pattern that creates the view
that should be generated by choosing the various view based on a string
representation of a model. There is also an Enum that classifies the viewType
based on text or visual view.


The IView interface in this package represents a view that displays or projects
a view from the model. The interface has two methods:
    
-	createView: generates the model data as a string.
-	getViewType: returns the type of view and this is utilized in the view
        factory.

This interface is extended to both classes: VisualView and TextView.

The class viewPanel extends the JPanel class and we implement the JFrame
functionalities by extending JFrame to the class. The text view is generated
by iterating over the model and determining what the correct output is for the
animation files.


controller package:

The scope of this package is to implement the controller and acts as a bridge
between the model and view functions. The IAnimationController Interface in
this package has the following methods:

-	executeAnimation: creates the animation based on arguments defined by user
        for the input file, view type, intended output and tick rate of the
        animation model.
-	getInputFile: gets an input file, in the form of a string, based on user
        input.

This IAnimationControllerImpl class implements the controller interface and is
responsible for fulfilling requests made by the user by controlling execution
flow. The main parameters are the model and the arguments passed in by the user.
The controller constructor creates an instance of the controller. The controller
parameters are the model and the arguments passed in by the user.

Controller:
This class implements the controller interface and is responsible for fulfilling
requests made by the user by controlling execution flow. The main parameters are
the model and the arguments passed in by the user. The controller constructor
creates an instance of the controller. The controller parameters are the model
and the arguments passed in by the user.

The main-method (EasyAnimator.java):
The main method is implemented by EasyAnimator.java, which based on the user
input, transfers information to the controller, which is then processed through
the model to generate a view based on the input files.

Testing:
We have written four test files that test all of the public methods in the three
model based Interfaces: IAnimation, IShape2D and IAnimationModel, and methods of
the TextView class along with all the exceptions. All methods in the Position
and Color classes and, the AnimationType and Shape2DType Enums have also been
implicitly tested by the test suite.

