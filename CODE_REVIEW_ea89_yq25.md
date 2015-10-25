#Code Review
####Ying Qi (yq25), Efe Aras(ea89)

## From STUPID to SOLID

### STUPID

Singletons: No singletons

Tight Coupling: I am unsure about having event handlers in the controller since that creates a heavy dependence on multiple methods on controller for GUI. Instead, we discussed using interfaces to make sure that when the correct interfaces are implemented, there is very little coupling between the interfaces since the correct methods to implement are very clear for different classes.

Untestability: No issues there

Premature Optimization: No optimization yet; the code is very clear

Indescriptive Naming: The methods are named just fine; it is clear

Duplication: Very minor duplication; we discussed how to fix it using resource files.

### SOLID

Single Responsibility Principle: This class right now is doing an extra stuff by trying to add Event Handlers to the View, which creates a heavy dependence on Controller since for the buttons to function, they need event handlers from Controller

Open/Closed Interface: To add a new Pane, all she had to do was to create a new class! 

Liskov Substitution Principle: Doesn't apply since all the super classes are abstract. 

Interface Segregation Principle: We had a long discussion on why interfaces are super useful

Dependency Inversion Principle: Sadly, there aren't many interfaces (i.e. 0) so we cannot talk about this one that much :( . 

