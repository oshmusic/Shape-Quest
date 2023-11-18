
## Domain Model
```plantuml
@startuml
hide circle
hide empty methods
' Classes
class Game{
    player
    storyCount
}
class Player{
    health
    magicAttack
    magicDefense
    physicalAttack
    physicalDefense
}
class Enemy{
    health
    magicAttack
    magicDefense
    physicalAttack
    physicalDefense
}
class Move{
    magicAttack
    magicDefense
    physicalAttack
    physicalDefense
}

' Associaltions
Game "1" - "1" Player : \tacts in\t
Game "1" -- "1..*" Enemy : acts in
Player "1" -- "1..*" Move : selects
Enemy "1" - "1..*" Move : \tselects
@enduml
```

## Sequence Diagram for the Upgrading process
```plantuml
@startuml

actor Player as user
participant " : UI" as ui
participant " : Game" as game
participant ": Player" as player
ui -> user : displayUpgradeOptions()
alt playerHasNotSkippedOrQuit
user -> ui : select upgrade option
ui -> game : getPlayerUpgradeInput()
game -> player : upgradeStats(src.Player p)
else !playerHasNotSkippedOrQuit
alt playerHasQuit
user -> ui : select "quit" option
ui -> game: getPlayerUpgradeInput()
game -> game: quit
else !player
user -> ui : select "skip" option
ui -> game : getPlayerUpgradeInput()
game -> game: skip
end
end
@enduml
```

## Sequence Diagram for Fight use case
```plantuml
@startuml
actor Player as user
participant " : UI" as ui
participant " : Game" as game
participant " : Player" as player
participant " : Enemy" as enemy
loop while both player and enemy are alive
    ui -> user : display move options
    alt playerHasNotQuit
    user -> ui : select move
    ui -> game : getPlayerMoveInput()
    game -> player: pickMove(String userText)
    player -->> "PlayerMove : Move" ** : userMove = pickMove(String userText)
    game -> enemy : resolveMove(Move m, APlayer a)
    game -> ui : displayStatus()
    ui -> user : displayStatus()
    game -> enemy : pickMove()
    enemy -->> "EnemyMove : Move" ** : enemyMove = pickMove()
    game -> player : resolveMove(Move m, APlayer a)
    game -> ui : displayStatus()
    ui -> user : displayStatus()
    else !playerHasNotQuit
    user -> ui : select "quit"
    ui -> game: getPlayerMoveInput()
    game -> game: quit
    end
end
@enduml
```

## Sequence Diagram for Play round use case
```plantuml
@startuml
actor Player as user
participant " : UI" as ui
participant " : Game" as game
participant " : Move" as move
participant " : Player" as player
participant " : Enemy" as enemy
loop while Player has not reached end
    [o-> game : Fight
    alt playerIsDead
        game -> game : beginGame()
    else !playerIsDead
        ui -> user : display upgrade options
        alt playerChoosesQuit
            user -> ui : select "quit"
            ui -> game : quit
        else !playerChoosesQuit
            user -> ui : select upgrade
            ui -> game : upgradeStats(String s)
            game -> player : upgradeStats(String s)
            game -> ui : Present Story
        end
    end
end
@enduml
```
## Sequence Diagram for the Present Story use case
```plantuml
@startuml
actor Player as user
participant " : UI" as ui
participant " : Game" as game
[o-> game : Present Story
game -> ui : currentStory = presentStory(int currentStoryNumber)
ui -> user: displayStory(String currentStory)
alt playerContinues
user -> ui: select "continue"
else !playerContinues
user ->ui: select "quit"
ui -> game : displayStory(String currentStory)
game -> game : quit
end
@enduml
```

## Sequence Diagram for the Start App use case
```plantuml
@startuml
actor Player as user
participant " : UI" as ui
participant " : Game" as game
[o-> game : App launches
game -> ui : display welcome message and prompt user
alt !userQuits
user -> ui : select "start"
ui -> game : beginGame()
else userQuits
user -> ui : select "quit"
ui -> game : quit
end
@enduml
```

## Sequence Diagram for Begin Game use case
```plantuml
@startuml
participant " : Game" as game
participant " : Player" as player
alt Player died during fight
game -> player : reset stats
game -> game : start game
else new game
game -> game : start game
end
@enduml
```

## Class Diagram
```plantuml
@startuml

abstract class APlayer{
- currentHealth: int
- maxHealth: int
- magicAttack: int
- magicDefense: int
- physicalAttack: int
- physicalDefense: int
--
}

class Player{
--
pickMove() Move
}

class Enemy{
--
pickMove() Move \n{At this stage of the implementation, Moves are selected at random}
}

class Game{
- storyCounter: int
- currentStoryNumber: int
- upgradeValue: int
- story: String[]
--
resolvePlayerMove() : boolean
resolveEnemyMove() : boolean
upgradeStats(Player p) String
toString() String 
fight(Player p, Enemy e) String
beginGame()
presentStory(int storyNumber)
round(Player p) String
}
Player -> "1\t \n player\t" Game
Enemy --> "\t\t1* \n \t\tenemies" Game 
'associations because Player and Enemy are data types defined in this project
class Move{
- magicAttack: int
- physicalAttack: int
--
getMagicAttack() int
getPhysicalAttack int
}
Player -up-.> Move
Enemy -up-.> Move
class TextUI{
- scan : Scanner
- upgradeList: ArrayList<String>
--
main(String[]args)
getPlayerMoveInput() String
getPlayerUpgradeInput() String
displayUpgradeOptions()
displayStatus()
displayStory(String story) : String
displayMessage(String message)
}
src.TextUI -> "1 \n game\t" Game: \t\t
' association because Game is a data type defined in this project

APlayer <|-- Player
APlayer <|-- Enemy

@enduml
```
