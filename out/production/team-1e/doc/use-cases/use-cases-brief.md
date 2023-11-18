# Brief Use Cases

### Show tutorial:
- Priority:
  - Medium
- Level: 
  - User Goal
- Primary Actor:
  - Player
- Postconditions:
  - All sections of the tutorial have been sequentially shown to the player
- Workflow:
  - ```plantuml
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
    

### Present story:
- Priority:
  - High
- Level: 
  - Subfunction
- Primary Actor:
  - System
- Postconditions:
  - The next phase of the story has been displayed and the story progress tracker has been updated
- Workflow:
  - ```plantuml
    @startuml
    |#LightSkyBlue|Player|
    |#PaleGreen|System|
    ' Sets up colors for the activities and symbols
    skinparam activity{
      BackgroundColor WhiteSmoke
      EndColor Black
    }
    title Story interactions
    |System|
    start
    :Check story counter;
    :Display correct story interaction;
    if (Does the player want to quit?) then (no)
    |Player|
    :Player presses continue;
    else (yes)
    |System|
    :Exit program;
    end
    endif
    |System|
    :Update story counter;
    end
    @enduml
    
    

### Customize character:
- Priority:
  - Low
- Level:
  - User Goal
- Primary Actor:
  - Player
- Postconditions:
  - The player's starting stats are correct according to the chosen character class
- Workflow:
  - ```plantuml
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
    

### Start app
- Priority:
  - Medium
- Level:
  - User Goal
- Primary Actor:
  - Player
- Postconditions:
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
  |System|
  start
  :Display menu options;
  switch (Player chooses menu option)
  case ( New Game)
  :Execute __Begin Game__;
  case ( Continue Game)
  :Load previous game data;
  endswitch
  end
  
  start
  ' In the code, displaying menu options should be impacted by whether or not there is a saved game to load
  :Display menu options;
  if (Player chose "Continue Game") then (yes)
  if (Is there saved data to load?) then (yes)
  :Load previous game data;
  else (no)
  :Execute __Begin Game__;
  endif
  else (Player chose "New Game")
  :Execute __Begin Game__;
  endif
  end
  @enduml
  ```

### Play round
- Priority
  - High
- Level
  - Subfunction
- Primary Actor
  - System
- Postconditions
  - A fight has occurred. If the player is alive after the fight, they have the chance to upgrade their stats and see a story fragment. If the player died during the fight, they are returned to the beginning.
- Workflow:
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

