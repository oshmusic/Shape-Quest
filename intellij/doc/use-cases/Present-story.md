# Present story

### Priority:
- High 
### Level:
- Subfunction
### Primary Actor:
- System
### Stakeholders and interests:
- Player: wants an engaging story that makes sense
### Preconditions:
- Player has started a game and/or won a fight and been given the option to upgrade their stats
### Postconditions:
- The next phase of the story has been displayed and the story progress tracker has been updated or the player has successfully quit
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
  ```

### Non-functional requirements
- #### Usability
  - Story makes sense and is engaging
- #### Performance
  - PLayer sees story in the correct order and in a timely manner
- #### Legal
  - No copyright infringements in images