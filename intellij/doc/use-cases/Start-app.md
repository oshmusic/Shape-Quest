# Start App

### Priority
- Medium

### Level
- User goal

### Primary Actor
- Player

### Stakeholders and Interests
- Player: wants to start the game, either by loading from a previously saved state
or by starting from the beginning

### Preconditions
- The player has not yet started playing

### Postconditions
- Player sees first story fragment and gameplay starts

```plantuml
  @startuml 
  |#LightSkyBlue|Player|
  |#PaleGreen|System|
  skinparam activity{
    BackgroundColor WhiteSmoke
    EndColor Black
  }
  title Start app
  ' Older diagram; commented out but included for now for reference
  '|System|
  'start
  ':Display menu options;
  'switch (Player chooses menu option)
  'case ( New Game)
  ':Execute __Begin Game__;
  'case ( Continue Game)
  ':Load previous game data;
  'endswitch
  'end
  
  start
  ' In the code, displaying menu options should be impacted by whether or not there is a saved game to load
  :Display menu options;
  switch (Player choses menu option)
  case (New Game)
  :Execute __Begin Game__;
  case (Continue Game)
  if (Is there saved data to load?) then (yes)
  :Load previous game data;
  else (no)
  :Execute __Begin Game__;
  endif
  endswitch
  end
  @enduml
  ```