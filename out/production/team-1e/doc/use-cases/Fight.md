# Fight

### Priority: 

- High

### Level: 

- User Goal

### Primary Actor: 

- Player

### Stakeholders and Interests:

- Player: Wants fast, smooth, easy to operate, and enjoyable gameplay without having to wait too long between rounds.


### Preconditions:

- Player is alive.

### Postconditions:

- Player is either victorious or dead.

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

title Fight

|System|
start
:Determine who goes first;
while (Are both Player and Enemy alive?) is (yes)
    if (Is it the Player's turn?) then (yes)
    |Player|
        :Player selects Move;
        if (Player chose "Quit") then (yes)
        end
        else (no)
        endif
    |System|
    else (no)
        :Enemy selects Move;
    endif
    :Read in Move and adjust relevant stats;
    :Display Player and Enemy health;
    :Determine next turn;
endwhile (no)
end
@enduml
```
### Non-Functional Requirements:
- #### Usability:
  - Intuitive UI that is easily understandable by the Player.
- ### Performance:
  - System picks Enemy move within a few seconds at most.
- ### Legal:
  - No copyright infringements in images used.