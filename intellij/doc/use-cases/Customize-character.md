# Customize character

### Priority:
- Low
### Level:
- User Goal
### Primary Actor:
- Player 
### Stakeholders and interests:
- Player: wants to have clear options to pick from to create their character
### Preconditions:
- Player has started a game
### Postconditions:
- The player's starting stats are correct according to the chosen character class
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
  title Character customization
  |System|
  start
  :Display starting customization options;
  |Player|
  :Selects desired customization;
  |System|
  :Applies correct customization;
  end
  @enduml
```
### Non-functional requirements
- #### Usability
  - Ui is readable, options are clear
- #### Performance
  - Character is created correctly based on player's choices, creation is fast
- #### Legal
  - No copyright infringements in images