# Play round

### Priority
- High 
### Level
- Subfunction 
### Primary Actor
- System
### Stakeholders and interests
- Player: wants the game to run in the correct order without delays
### Preconditions
- Player has started a game and has not reached the end of the game
### Postconditions
- A fight has occurred. If the player is alive after the fight, they have the chance to upgrade their stats and see a story fragment. If the player died during the fight, they are returned to the beginning.
### Workflow:
```plantuml
  @startuml
  |#LightSkyBlue|Player|
  |#PaleGreen|System|
  skinparam activity{
    BackgroundColor WhiteSmoke
    EndColor Black
  }
  title Round
  |System|
  start
  while (Player has not reached end) is (yes)
  if (First round?) then (yes)
  :Execute __Present Story__;
  else (no)
  endif
  :Execute __Fight__;
  if (Player alive?) then (yes)
  :Execute __Upgrade Stats__;
  :Execute __Present Story__;
  else (no)
  :Execute __Begin Game__;
  endif
  endwhile
  end
  @enduml
```

### Non-functional requirements
- #### Usability
  - Order of the game makes sense
- #### Performance
  - Player does not need to wait for each part of the game to occur
- #### Legal
  - No copyright infringements in images