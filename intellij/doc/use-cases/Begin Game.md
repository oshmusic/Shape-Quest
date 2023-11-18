# Begin Game

### Priority:
- High

### Primary Actor:
- System

### Stakeholders and interests:
- Player: wants this to occur behind the scenes without any input

### Preconditions:
- Player is dead, the end of the game has been reached and player wants to play again, or a new game is being started

### Postconditions:
- Stats and story have both been set to beginning states

### Workflow:
 ```plantuml
    @startuml
    'define swimlanes
    |#LightSkyBlue|Player|
    |#PaleGreen|System|
    ' Sets up colors for the activities and symbols
    skinparam activity{
      BackgroundColor WhiteSmoke
      EndColor Black
    }
    title Begin Game
    |System|
    start
    :Set player values to starting values;
    :Set story counter to starting value;
    :Execute __Customize character__;
    :Execute __Show tutorial__;
    end
    @enduml
```

### Nonfunctional requirements:
- ### Usability:
  - None
- ### Performance
  - Player does not need to wait, stats and story have been set properly
- ### Legal
  - None