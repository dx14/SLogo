# SLogo: Design Document

## Introduction
For this project, we are creating a interactive development environment that supports various Simple Logo commands. We will be using a Model-View-Controller design framework, with separate GUI, Controller, and Parser classes that work together to receive user input, interpret/parse the input and check its validity, and perform whatever action(s) given the parameter(s). The frontend and backend will be connected together through the controller, and will be restricted on which methods they have access to through use of interfaces. By doing so, the GUI and parser will have minimal interference with each other and will each be closed for modification (as much as possible). The GUI and parser will each have their own unique responsibilities, such as changing the position of a turtle visually or interpreting a command with its given parameters respectively, and these changes will be communicated across through method calls in the controller. Each possible Logo command will have its own class and properties associated with the command, and thus adding new commands in SLogo will be open for extension. We will also utilize reflection within our parser while checking for valid commands and parameters, and also we plan to use binding properties in order to keep the locations of each turtle aligned between the GUI and backend coordinates.

## Overview

## User Interface
Our user interface in the large scale will resemble a BorderLayout. The top section will hold a toolbar of buttons including a “language” dropdown to select the input language, as well as other configuration and display-related buttons such as “load image” and “help”. The left section will hold different color palettes for the GUI background color, line color, etc. that will be selectable by clicking on them. The right side will contain the command history list with a scroll bar that will implement syntax highlighting and will be able to respond to click events. The right side will also display the values of variables such as line width, language, etc. The bottom will contain the text box representing the console where the user will type commands. On the side of it there will be a scrollbar and buttons such as “run” and “clear”. The center of the layout will contain the main display output of the program, the turtle and its drawn paths.

There can be errors when the user inputs malformed or nonexistent commands into the console. These errors will be detected in the backend where an exception will be thrown, and the GUI will catch the exception by displaying a popup window describing the nature of the error.

## Design Details



## API Example Code

Example: user types ‘fd 50’ in command window.

##### FRONTEND:
1. ``GUI`` listens for text input. Goes to step 2 when “enter” is pressed (depending on whether “multiline” checkbox is activated or not) or when “Run” is clicked. 
2. ``GUI`` calls ``myController.runCommand(String command)``
3. ``SlogoController`` calls ``myParser.runCommand(String command)``

##### BACKEND:
4. ``SlogoParser`` creates a ``new CommandString(String command)``
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

SlogoController class will be developed somewhat jointly by the two teams, to ensure we are on the same page. However, the bulk of its implementation will be the responsibility of the front-end team.

##### BACK-END TEAM:
Ted Yavuzkurt and Dennis Xu
Responsible for implementing parsing functions for all commands and communicating with SlogoController as necessary.
