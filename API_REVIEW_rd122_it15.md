John Dai, Inan Tainwala

*Suggestions: look in to Observable/Observers*

Part 1

What about your API/design is intended to be flexible?

MVC structure, shared memory object that contains all shared information that both the front and back ends have references to (observables)

How is your API/design encapsulating your implementation decisions?

Using Observers/Observable

What exceptions (error cases) might occur in your part and how will you handle them (or not, by throwing)?

Catch all errors fron backend or parser and handles those

Why do you think your API/design is good (also define what your measure of good is)?

You can switch out the backend as long as it references the same memory

Part 2

Come up with at least four use cases for your part (it is absolutely fine if they are useful for both teams).

Adding a new turtle: back end creates a new turtle, front end listens for this and updates its display

Draw a new line: back end creates or changes path objects, and listener gets the change and notifies the front end

Change background color: the front end registers an event in the palette pane, notifies and changes the background color

Update history of commands: Enter a command, if parser doesn't throw an exception add that string to the List of strings for history

What feature/design problem are you most excited to work on?

Adding functionality that involves just the front end since it seems relatively easy

What feature/design problem are you most worried about working on?

Interfacing with the back end since design choices might change and things could get complicated
