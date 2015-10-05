# SLogo: Design Document

## Introduction
For this project, we are creating a interactive development environment that supports various Simple Logo commands. We will be using a Model-View-Controller design framework, with separate GUI, Controller, and Parser classes that work together to receive user input, interpret/parse the input and check its validity, and perform whatever action(s) given the parameter(s). The frontend and backend will be connected together through the controller, and will be restricted on which methods they have access to through use of interfaces. By doing so, the GUI and parser will have minimal interference with each other and will each be closed for modification (as much as possible). The GUI and parser will each have their own unique responsibilities, such as changing the position of a turtle visually or interpreting a command with its given parameters respectively, and these changes will be communicated across through method calls in the controller. Each possible command will extend an abstract parent class and have its own properties associated with the command, and thus adding new commands in SLogo will be open for extension. We will also utilize reflection within our parser while checking for valid commands and parameters, and also we plan to use binding properties in order to keep the locations of each turtle aligned between the GUI and backend coordinates.

## Overview

##### Front-End+Controller: External API

``void addTurtle(Turtle turtle)`` - adds a Turtle to the List of turtles stored in the controller <br>
``Turtle getTurtle(int turtleId)`` - returns a Turtle from the List of turtles specified by the given id <br>
``void moveTurtle(int turtleId, SlogoPath path)`` - calls the moveTurtle method in the GUI <br>
``void updateHistory(int turtleId, String command)`` - adds to the List of past commands

##### Front-End+Controller: Internal API

The internal API of the front end allows for extension through creation GUI components that can be placed in the main layout, as well as methods for manipulating the turtle’s display parameters.

``Node returnNodeToDraw()`` - returns a ``Node`` to draw depending on what new component will be drawn on the screen <br>
``Image getImage()`` - gets the turtle’s display image <br>
``void setImage(Image image)`` - sets the turtle’s display image <br>
``double getXOnGrid()`` - gets the turtle’s x-position <br>
``void setXOnGrid(double x)`` - sets the turtle’s x-position <br>
``double getYOnGrid()`` - gets the turtle’s y-position <br>
``void setYOnGrid(double y)`` - sets the turtle’s y-position <br>
``void setWidth(double width)`` - sets the turtle’s pen width <br>
``Color getPenColor()`` - gets the turtle’s pen color <br>
``void setPenColor(Color penCol)`` - sets the turtle’s pen color <br>
``double getWidth()`` - gets the turtle’s pen width <br>
``double getAngle()`` - sets the turtle’s heading angle <br>
``boolean getIsVisible()`` - gets if the turtle is visible in the GUI 

#####Front-End+Controller Main Classes:

``MainGUI`` - the outermost GUI element that holds all other GUI elements and variables <br>
``GUIComponent`` - an abstract class that all other SLogo GUI elements extend <br>
``GUIConsole`` - the GUI element that displays and allows user input and sends that input to the SlogoController <br>
``GUIHistory`` - the GUI element that displays the command history and allows the user to interact by clicking which calls the ``SlogoController`` <br>
``GUIPalette`` - an abstract GUI element that displays the color chooser for different parts of the GUI and listens for the user’s clicks <br>
``GUIToolbar`` - the GUI element that holds the buttons at the top of the screen to configure the program <br>
``GUITurtleArea`` - the GUI element that displays the turtle and its path determened by the SlogoController <br>
``GUITurtle`` - a representation of the turtle which is stripped down to parts that only the GUI needs to know about <br>
``GUIController`` - a representation of the Slogo controller which is stripped down to parts that only the GUI needs to know about <br>
``SLogoLanguage`` - just holds a string but allows for more control over pointer accesses <br>
``SlogoController`` - provides the interface through which the front and back ends communicate <br>


###GUI UML Diagram

![GUI UML Diagram]
(sampleUML.png)




##### Back-End: External API
The external API for the backend will be fairly limited, and will be accessed through the SlogoController class’s myParser link. myParser will be a link to the SlogoParser class through the ParserInterface interface. This interface will provide the following methods:

``runCommand(String command)`` - executes command and adds it to the history, throws ParserException if the command is unable to be executed. <br>
``runCommand(CommandString command)`` - executes command and adds it to the history (used to execute previously executed commands) <br>
``setLanguage(String language)`` - sets the current input language to the resource bundle specified by language. If the string is invalid, throws a ``ParserException``

#####Back-End: Internal API
The internal API for the backend will be more robust, providing functionality for adding new commands. It will consist of several classes, which can be extended to add new functionality.

### Back-End UML Diagram:
![Backend UML Diagram]
(SlogoParser.jpg)

##### Main Classes

#####**SlogoParser.java** implements ParserInterface
	
This will be the primary class responsible for implementing back-end functionality. It will maintain a link to the Controller and will be responsible for interacting with the Controller and the GUI. It will provide several functions:
	
```addTurtle(int turtleId)``` - creates a new turtle, if one does not already exist
```setTurtle(int turtleId)``` - sets the current turtle
```getCurrentTurtle()``` - returns a reference to the current turtle
```getTurtles()``` - returns a list of all turtles

```moveCurrentTurtle(SlogoPath path)``` - moves the current turtle using path
```moveTurtle(int turtleId, SlogoPath path)``` - moves the specified turtle using path

#####**CommandString.java**
	
This is an interface which is passed to the GUI to access ```CommandMap``` objects - allows them to be accessed without the GUI executing them or modifying their contents by accident.

```getFormattedString()``` - returns formatted string
```getNativeString()``` - returns native formatted string

#####**CommandInterpreter.java**
	
Class which actually executes commands. Iterates through a ```CommandMap```, creating new ```Evaluable``` objects as necessary. Uses reflection to instantiate objects, throwing a ```ParserException``` if a class is not found.

```public void prepareMap (CommandMap map) throws ParserException``` - steps through map and builds its Evaluation list.

```public void evaluateMap(CommandMap map) throws ParserException``` - executes map.

```public static Evaluable reflect(String command, CommandMap map) throws ParserException``` - returns a new ```Evaluable``` instance based on command, providing map as an argument.

#####**ParserResource.java**

Helper class which interacts with resource files.

```static getNativeCommand(String command)``` - returns a native version of command. Throws exception if it wasn’t found.

```static setCurrentLanguage(String language)``` - sets the current language, throwing exception if resource not found.

#####**Argument.java** implements Evaluable

Abstract class for all primitives, shapes, colors, symbols, etc which a user may type. Each subclass of ```Argument``` implements a specific type of element, providing functions to convert it into native class names.

#####**Primitive.java** extends Argument implements Evaluable

Wrapper class for primitive types (numbers, strings, etc) which is created via Reflection and allows them to be evaluated.

```public static String getNativeText(String primitiveString)``` - returns primitiveString - implemented to make abstraction possible with other types of arguments.

#####**Symbol.java** extends Argument implements Evaluable

Wrapper class for symbols (i.e. [, (, etc) which may be in code 

```public static String getNativeText(String symbolString)``` - returns the class name associated with this symbol, for building a native string map.

#####**Command.java** implements Executable, Evaluable
	
Abstract parent class for all commands. Implements ```Executable``` and ```Evaluable``` interfaces.

Each concrete command class which extends this parent class will be responsible for knowing how many arguments of type ```Evaluable``` it takes. Abstract child classes for each category of commands will be created (i.e. ```TurtleCommand```, ```MoveCommand```, etc). By implementing each type of command in a category, each command can specify what type of commands it will accept as arguments. For example, while ```fd 50``` returns 50, ```fd fd 50``` throws an exception in an online logo interpreter, suggesting that “fd 50” is not a valid way to pass 50 to the first fd command.

Each command will be contained in its own class, and will implement the ```execute()``` method, responsible for running the command. Each command will also have a ```getRemainder()``` function, which will return unused portions of its CommandMap, allowing them to be evaluated separately.

```public Command(CommandMap map)``` - creates a new Command object with the CommandMap map.

```public static String getNativeString(String command)``` - returns a native string for this command, using ParserResource class to help.

```public double evaluate() throws ParserException``` - calls execute() on the command and returns any numeric value from the command.

```public void execute() throws ParserException``` - runs the command.

#####**TurtleCommand/GUICommand** etc etc
Parent classes for each category of command.

#####**CommandMap.java** implements Evaluable, CommandString

Class used to parse commands - links every element of a command with its corresponding ```SyntaxElement``` type. Impelements ```CommandString``` interface to pass to GUI.. Also builds a ```List<Evaluable>``` which can be evaluated, once the ```CommandInterpreter``` has determined that the ```CommandMap``` is valid. Useful also for defining user commands, which are stored as a command map which has already been evaluated.

```public CommandMap(String command) throws ParserException``` - parses command into its corresponding Class pairs. Builds map. Throws Exception if the process failed.

```public void addEvaluable(Evaluable e)``` - adds e to the next position in the Evaluation list.

```public CommandMap subMap(int startPos)``` - returns a new CommandMap starting at the specified position. Used by Executable objects to return the remainder of their CommandString for evaluation.

```public String getFirst()``` - returns the first element of the map in String form for reflecting.

```public boolean isEmpty()``` - returns true if map is empty, false otherwise.

```public void evaluate() throws ParserException ```- iterates through Evaluation list, throwing an exception if anything fails to evaluate. Does nothing if evaluation list has not been defined (as in the case of a submap).

#####**SyntaxElement.java**
	
Helper class used to assist with code formatting and addition of new syntax elements. Defines several constants, used to map to types of syntax elements (for code highlighting). Each of these constants corresponds to a class string instance which can be used to reflect and create a new instance of the class for each element. New syntactical elements can be added in this manner.

```Java
SyntaxElement.COMMAND
SyntaxElement.USERCOMMAND
SyntaxElement.VARIABLE
SyntaxElement.SYMBOL
SyntaxElement.PRIMITIVE
SyntaxElement.COLOR
SyntaxElement.SHAPE
```

```static Color getColor(String element)``` - returns a Color object for the given element.

#####**Regex.java**

Helper class used by other Parser classes. As of now, there are no public methods (it is used internally by ```CommandMap``` and other classes, however).


#####**ParserResource.java**

Helper class which converts raw command strings to their native class names by interacting with 

```public static String getNativeString(String command) throws ParserException``` - returns native String name for command or throws an exception.

```public static void setLanguage(String language) throws ParserException``` - sets language to language or throws exception.

#####**Evaluable.java**
Interface which specifies the following functions:
```public double evaluate()``` - evaluates this command and returns the double result.

#####**Executable.java** extends Evaluable
Interface which specifies the following functions:

```public double execute()``` - runs the current command and returns the result of its evaluation. Used for commands which can be executed, as opposed to just evaluated. Calling execute actually modifies state.

## User Interface
Our user interface in the large scale will resemble a BorderLayout. The top section will hold a toolbar of buttons including a “language” dropdown to select the input language, as well as other configuration and display-related buttons such as “load image” and “help”. The left section will hold different color palettes for the GUI background color, line color, etc. that will be selectable by clicking on them. The right side will contain the command history list with a scroll bar that will implement syntax highlighting and will be able to respond to click events. The right side will also display the values of variables such as line width, language, etc. The bottom will contain the text box representing the console where the user will type commands. On the side of it there will be a scrollbar and buttons such as “run” and “clear”. The center of the layout will contain the main display output of the program, the turtle and its drawn paths.

![GUI Design]
(schematic.png)

There can be errors when the user inputs malformed or nonexistent commands into the console. These errors will be detected in the backend where an exception will be thrown, and the GUI will catch the exception by displaying a popup window describing the nature of the error.

## Design Details

###**Frontend**
We tried to simplify down the external API so that the backend can see only the methods it likely needs such as being able to have access to changing the state of the turtle and history items. Other components and methods internal to the frontend should be hidden from the backend. In our external API, however, we wanted to provide a good amount of extensibility so the idea was to let future users be able to access values of the state variables as well as being able to extend the ```SlogoComponent``` class to create a custom GUI component.

Each GUI component is self-explanatory for how it functions an relates to the assignment specifications. We would use such resources as different language files for both the text displayed in the frontend as well as the text sent to the parser. Each of our classes represent a different GUI element, using polymorphism where it makes sense. Also our interface classes also provide a specification for the API between the front and backend classes.

###**Backend**
Our backend is designed to be maximally extensible and uses recursion/reflection to iterate through a command string provided by the GUI. Every command we’ve been asked to implement has its own class, so adding new commands is a relatively trivial process consisting of creating a new Class for it, extending the proper super class (depending on the type of command) and then implementing the ```execute()``` and ```evaluate()``` functions.

By wrapping all elements of a Command String in classes, we allow for the language to be easily extended to include new symbols, operators, and so forth. All elements implement the ```Evaluable``` interface, meaning they are interchangeable as far as the ```CommandInterpreter``` class is concerned.

Division of labor is thus accomplished cleanly. ```ParserResource``` allows for localization. ```Regex``` contains all regular expression code. ```CommandInterpreter``` steps through execution of ```CommandMap```'s. It uses reflection to go through a ```CommandMap``` and built native code, which can be executed.

All of these processes can throw a ```ParserException``` if there is a problem, which will return control to the GUI along with an explanation of what went wrong, so that the user can be informed of what corrective action to take.

The heart of the backend lies in the interaction between the ```CommandInterpreter``` and ```CommandMap``` classes. This interaction proceeds in a few stages. First, the ```CommandInterpreter``` creates a new ```CommandMap``` based on the provided string. During this part, the ```CommandMap``` generates itself by using the ```Regex``` helper class to split the string into elements, which are then paired with first their corresponding ```SyntaxElement``` category, and then their specific ```Evaluable``` implementation.

Once the ```CommandMap``` has been generated, ```CommandInterpreter``` steps through it sequentially. On each iteration, the ```CommandInterpreter``` takes the first element of the Map and uses reflection to create a new class instance. This class instance is then provided with the current ```CommandMap```. This class instance automatically processes its arguments from the ```CommandMap```, recursively executing any of them if necessary (by using ```CommandInterpreter.reflect```). If any of these fails, it will throw a ```ParserException``` with a message corresponding to the cause of the problem. Once that command has finished preparing itself for execution, the ```CommandInterpreter``` adds it to the evaluation list in the current ```CommandMap```. The ```CommandInterpreter``` then calls ```getRemainder()``` on the command, which will return any unused portion of the ```CommandMap```, which may contain further code to execute. The ```CommandInterpreter``` then proceeds through this new ```CommandMap```.

Once the ```CommandInterpreter``` reaches the end of the ```CommandMap```, it will evaluate the ```CommandMap```. This steps through the evaluation list prepared previously, evaluating all of the commands. If any of these throw an exception, it is passed to the GUI. However, it is unlikely that an exception will be thrown at this stage, as each command is responsible upon creation for checking that its arguments are sensible.

This recursive interpretation implementation allows for any type of command to be created, within reason. Each ```Evaluable``` element is responsible for taking the elements it needs from the ```CommandMap```, then passing on the rest of the map for the next command to use. This will make extension simple.


## API Example Code

Example: user types ``fd 50`` in command window.

##### FRONTEND:
1. ``SlogoGUI`` listens for text input. Goes to step 2 when “enter” is pressed (depending on whether “multiline” checkbox is activated or not) or when “Run” is clicked. 
2. ``SlogoGUI`` calls ``myController.runCommand(String command)``
3. ``SlogoController`` calls ``myParser.runCommand(String command)``

##### BACKEND: 
*(note: after writing this, a more robust backend implementation was conceived, however the basic flow is very much the same, using CommandMaps instead of CommandStrings)*

4.  ``SlogoParser`` creates a ``new CommandString(String command)``
5. ``CommandString`` class verifies that command is contained in current language resource bundle by calling ``ParserResource.getNativeCommandString(String command)``
6. ``SlogoParser`` class creates a new ``CommandInterpreter`` object by calling ``new CommandInterpreter(CommandString currentCommand)``
7. ``CommandInterpreter`` class calls ``Regex.split(nativeCommand)``, uses reflection to create new instance of first half of split (in this case ``new ForwardCommand(String remainder)``)
8. ``ForwardCommand`` class creates ``new Argument (String remainder)``, in this case remainder is 50
9. ``ForwardCommand`` class returns control to ``CommandInterpreter``
10. ``CommandInterpreter`` calls ``currentCommand.getRemainder()`` - receives empty string, knows that this means it has reached the end of the ``CommandString`` without an exception being thrown, therefore command is valid
11. ``CommandInterpreter`` calls ``currentCommand.execute()``
12. ``ForwardCommand`` calls ``PathFactory.makePath(myParser, 50)``
13. ``PathFactory`` class calls ``myParser.getCurrentTurtle().getStartCoordinate()`` and ``myParser.getCurrentTurtle().getCurrentHeading()``, returns new ``StraightPath`` object with turtle moving 50 units in current direction
14. ``ForwardCommand`` class calls ``myParser.moveCurrentTurtle(Path currentPath)``
15. ``SlogoParser`` calls ``myController.moveTurtle(currentTurtleId, currentPath)``

##### FRONTEND:
16 .``SlogoController`` calls ``myGUI.moveTurtle(currentTurtleId, currentPath)`` <br>
16b. // ``SlogoGUI`` calls ``drawPath(currentPath)`` <br>
16c. // ``SlogoGUI`` calls ``updateTurtlePosition(currentTurtleId, currentPath)`` <br>

##### BACKEND:
17 . ``CommandInterpreter`` returns control to ``CommandString``, returns control to ``SlogoParser`` <br>
18 . ``SlogoParser`` calls ``myController.updateHistory(currentTurtleId, currentCommandString)`` 

##### FRONTEND:
19 . // ``SlogoController`` calls ``myGUI.updateHistoryView(currentTurtleId, currentCommandString)``
20 . DONE!

## Design Considerations
#####How to store and communicate turtle movement
- We need a way to store turtle movement so that the back end can generate that information and pass it to the user interface. We initially decided to create our own class to do this with the variables start point and end point. The parser will create a new object of this type and pass it to the GUI where a line can be drawn with that information. We then realized that this would not work for curves, so we decided to make the Path class abstract and have the parser generate LinePaths for now, so that we can have the ability to extend Path to implement curved paths. 

##### Having separate turtles, paths, etc. each for the front and back ends
- The two design decisions came down to either have the front and back ends share the same turtle and path variables or let each part have their own variables and make sure that they are kept in sync. The advantage of having one set of variables is reducing duplicated code, while the advantage of separating them into two sets is to make the front and back ends more independent of each other. We decided to have one set of shared variables since we discovered that the back end does not need a large amount of references to the state variables, although we will have the turtle implement interfaces, for example, so that some methods are hidden to the back end when it tries to access it. <br>

##### How to enable the front and back end to access each other
- We first considered passing references of each other into each other, but design-wise this seemed to allow too much coupling and visibility across the two parts. We explored the idea of a model-view-controller design and thought that having a Controller class was just what we needed. The two parts will have a reference to the Controller, and the Controller will have a reference to each of the two parts through interfaces which will hide public methods that should not be shared. Another advantage is that the controller can handle most of of the API interfacing so that each of the two teams don’t have to worry too much about the details of the other’s API implementation yet.

##### How and when to convert from a language-specific command to the native command <br>
- We decided to have different classes representing the language-specific version and the native version and do the translation in the back end before the parsing. <br>

##### How to divide the GUI into classes
- We are still not completely sure, but this is internal to the GUI team so we decided to start working in a “main” GUI class and split components into classes as we progress. One idea is that, assuming that we can extend Node, each separate GUI component (i.e. the history window, the command console) will be its own class. This should be resolved a few days into coding the GUI. <br>

##### How to grant different GUI panes access to each other’s variables
- Two options we thought about were having the “main” GUI class store the variables needed to be shared, and we would pass those variables into the separate GUI components by reference, or we could have the components have listeners that would react somehow to events generated when a method is called in another component. Since the first option seems simpler, we will start with the first option assuming that the amount of variables doesn’t end up being too large.


## Team Responsibilities
##### FRONT-END TEAM:
Efe Aras and John Dai
Responsible for implementing GUI related classes, interfaces, and communicating with JavaFX library functions. 

SlogoController class will be developed somewhat jointly by the two teams, to ensure we are on the same page. The both teams will gather together to code that in the way both teams want it and both teams will be held equally accountable for that class.

##### BACK-END TEAM:
Ted Yavuzkurt and Dennis Xu
Responsible for implementing parsing functions for all commands and communicating with SlogoController as necessary.
