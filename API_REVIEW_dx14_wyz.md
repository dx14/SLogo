#API Review
####Dennis Xu (dx14), William Yang (wyz)

### Easy to learn
Difficult to learn because of multiple instance variables in the CommandString, but allows for more extension.

### Leads to readable code
Bit hard to read because of processing of strings involved.

### Hard to misuse
Might be a problem if there is a command that requires more than one action (i.e. returning multiple paths)

### Provides for extension
Different lists in the CommandString object provides for extension because the raw list, type list and native list are all available through the object.

## Use cases:

#### 1. User enters 'fd 50'
CommandString stores 3 lists: one of raw input string, one of the type for each raw input string, and one for each CommandObject.
Builds tree to execute 'fd', then calls ForwardCommand. It asks for one parameter, so we pass in 50. No remainder left, ends.

#### 2. User enters nothing
CommandString lists will be empty. Building tree will give remainder of nothing, ends.

#### 3. User enters a loop for turtle to draw a square
CommandString is built like usual, tree will be built with the first bracket as a parent. Then the tree will branch into 
smaller branches, with each being a parameter of the for. The last branch will be a right bracket, signaling the end of the for.
Afterwards, the forCommand will be executed, with each of the branch elements passed in as an appropriate parameter.

#### 4. User enters a loop for turtle to draw square, alternating pen-up and pen-down
Same as above, but the penUpCommand and penDownCommands will be added in within the trees, and then those command objects
will be called and executed as a parameter in the for.

The more complex methods will need specific implementation styles in their respective classes, which will be more difficult
than the more simple commands, such as fd. 
