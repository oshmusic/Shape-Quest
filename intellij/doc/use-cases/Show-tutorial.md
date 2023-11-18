# Show Tutorial

### Priority:
- Medium
### Level:
- User Goal
### Primary Actor:
- Player
### Stakeholders and interests:
- Player: wants to understand how to play the game
### Preconditions:
- Player has started a game
### Postconditions:
- All sections of the tutorial have been sequentially shown to the player or been skipped
### Workflow:
  ```plantuml
    @startuml
    |#LightSkyBlue|Player|
    |#PaleGreen|System|
    ' Sets up colors for the activities and symbols
    skinparam activity{
      BackgroundColor WhiteSmoke
      EndColor Black
    } 
    title Tutorial
    |System|
    :Show skip tutorial prompt;
    |Player|
    if (Player skips) then (skip)
    |System|
    else (show tutorial)
    while (Is there more tutorial to show?) is (yes)
    |Player|
    :Player requests next page;
    |System|
    :Display next tutorial element;
    endwhile (no)
    endif
    :Execute __Play Round__;
    end
    @enduml
  ```

### Non-functional requirements
- #### Usability
  - Instructions are clear and understandable
- #### Performance
  - Tutorial only moves on when the player decides to, full tutorial is shown, next tutorial component is shown quickly
- #### Legal
  - No copyright infringements in images