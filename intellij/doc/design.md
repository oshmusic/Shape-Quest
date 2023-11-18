
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
participant " : MainActivity" as controller
participant ": Player" as player
ui -> user : updatePlayerStatsUpgradeDisplay()
ui -> user : updatePhysUpgradeDisplay()
ui -> user : updateMagUpgradeDisplay()
ui -> user : updateHealthUpgradeDisplay()
alt physicalUpgrade
user -> ui : select physical upgrade option
ui -> controller : onPhysicalUpgrade()
alt attackUpgrade
controller -> player : updatePhysicalAttack(int upgradeValue) 
else defenseUpgrade
controller -> player : updatePhysicalDefense(int upgradeValue)
end
else magicUpgrade
user -> ui : select magic upgrade option
ui -> controller : onMagicUpgrade()
alt attackUpgrade
controller -> player : updateMagicAttack(int upgradeValue)
else defenseUpgrade
controller -> player : updateMagicDefense(int upgradeValue)
end
else healthUpgrade
user -> ui : select health upgrade option
ui -> controller : onHealthUpgrade()
alt currentHealthUpgrade
controller -> player : updateCurrentHeath(int upgradeValue)
else maxHealthUpgrade
controller -> player : updateMaxHealth(int upgradeValue)
end
else skip
user -> ui : select "skip" option
ui -> controller : onUpgradeSkip()
controller -> controller: skip
end
@enduml
```

## Sequence Diagram for Fight use case
```plantuml
@startuml
actor Player as user
participant " : UI" as ui
participant " : MainActivity" as controller
participant " : Player" as player
participant " : Enemy" as enemy

ui -> controller : updatePlayerStatsDisplay(IFightView view)
ui -> controller : updateEnemyStatsDisplay(IFightView view)
controller -> ui : updatePlayerStatsDisplay(Player p)
controller -> ui : updateEnemyStatsDisplay(Enemy e)
ui -> user : display player and enemy stats and move options

loop while Player and Enemy are both alive
    user -> ui : select move
    alt physicalAttackSelection
        ui -> controller : onPhysicalAttack()
        player -->> "PlayerMove : Move" ** : m = pickMove("PHYSICAL ATTACK")
        player -> enemy : resolveMove(Move m, Enemy e)
    alt enemyIsDead
        ui -> user : move on to Upgrade screen
        else enemyIsNotDead
        ui -> controller : updateEnemyStatsDisplay(IFightView view)
        controller -> ui : updateEnemyStatsDisplay(Enemy e)
        ui -> user : display updated enemy stats
        enemy -->> "EnemyMove : Move" ** : m = pickMove()
        enemy -> player : resolveMove(Move m, Player p)
        end
    alt playerIsDead
        ui -> user : move to Game Over screen
        end
        ui -> controller : updatePlayerStatsDisplay(IFightView view)
        controller -> ui : updatePlayerStatsDisplay(Player p)
        ui -> user : display updated player stats
        end
    else magicAttackSelection
        ui -> controller : onMagicAttack()
        player -->> "PlayerMove : Move" ** : m = pickMove("MAGIC ATTACK")
        player -> enemy : resolveMove(Move m, Enemy e)
        alt enemyIsDead
            ui -> user : move on to Upgrade screen
        else enemyIsNotDead
            ui -> controller : updateEnemyStatsDisplay(IFightView view)
            controller -> ui : updateEnemyStatsDisplay(Enemy e)
            ui -> user : display updated enemy stats
            enemy -->> "EnemyMove : Move" ** : m = pickMove()
            enemy -> player : resolveMove(Move m, Player p)
        alt playerIsDead
            ui -> user : move to Game Over screen
        end
    end
    ui -> controller : updatePlayerStatsDisplay(IFightView view)
    controller -> ui : updatePlayerStatsDisplay(Player p)
    ui -> user : display updated player stats
end
@enduml
```

## Sequence Diagram for Play round use case
```plantuml
@startuml
actor Player as user
participant " : UI" as ui
participant " : MainActivity" as controller
loop while Player has not reached end
    [o-> controller : Fight
    alt playerIsDead
        controller -> controller : Begin Game
    else !playerIsDead
        ui -> user : display upgrade options
      
            user -> ui : select upgrade or skip
            ui -> controller : Upgrade
            controller -> ui : Present Story
    end
end
@enduml
```
## Sequence Diagram for the Present Story use case
```plantuml
@startuml
actor Player as user
participant " : UI" as ui
participant " : MainActivity" as controller
[o-> controller : Present Story
ui -> user: updateStoryDisplay
alt playerContinues
user -> ui: select "continue"
ui -> controller : onContinueStory()
alt userHasReachedEnd
ui -> user : Display victory screen
else userHasNotReachedEnd
controller -> ui : Fight
end
@enduml
```

## Sequence Diagram for the Start App use case
```plantuml
@startuml
actor Player as user
participant " : UI" as ui
participant " : MainActivity" as controller
[o-> controller : App launches
ui -> user: display Main Menu screen
user -> ui : select start option
alt userStartsNewGame
ui -> game : beginGame()
else userLoadsSavedGame
controller -> controller : load saved game
ui -> game : quit
end
@enduml
```

## Sequence Diagram for Begin Game use case
```plantuml
@startuml
participant " : MainActivity" as controller
participant " : Player" as player
alt Player died during fight
controller -> controller : resetForNewGame()
controller -> controller : start game
else new game
controller -> controller : start game
end
@enduml
```

## Sequence Diagram for Show Tutorial use case
```plantuml
@startuml
actor Player as user
participant " : UI" as ui
participant " : MainActivity" as controller
ui -> user : prompt user to view or skip tutorial
user -> ui : selects view or skip
alt player skips tutorial
ui -> controller : onNoButton() 
controller -> ui : Play Round
end
alt player did not skip
ui -> controller : onYesButton()
loop while there are still tutorial pages to show
ui -> user : updateTutorialDisplay()
user -> ui : clicks continue button
ui -> controller : onTutorialContinue()
end
controller -> ui : Play Round
end
@enduml
```

## Sequence Diagram for Customize Character use case
```plantuml
@startuml
actor Player as user
participant " : UI" as ui
participant " : MainActivity" as controller
ui -> user : display character customization options
user -> ui : selects character customization
alt balancedCharacterSelection
ui -> controller : onBalancedSelection()
controller -->> "Player : Player" ** : p = new Player()
else physicalCharacterSelection
ui -> controller : onPhysicalSelection()
controller -->> "Player : Player" ** : p = new Player(int currHealth, int maxHealth, int magicAttack, int magicDefense, int physicalAttack, int physicalDefense)
else magicCharacterSelection
ui -> controller : onMagicSelection()
controller -->> "Player : Player" ** : p = new Player(int currHealth, int maxHealth, int magicAttack, int magicDefense, int physicalAttack, int physicalDefense)
end
controller -> ui : Show Tutorial
@enduml
```

## Class Diagram
```plantuml
@startuml
class com.example.team_1e.controller.MainActivity{
- mainView : IMainView
- upgradeView : UpgradeFragment 
- persistenceFacade : IPersistenceFacade
- p : Player
- e : Enemy
- gameState : GameState
- upgradeValue : int
- gameState : GameState
+ testMode : int
{static} - IN_PROGRESS : String
{static} - PLAYER : String
{static} - ENEMY : String
{static} - PROGRESS : String
{static} - STORY_PROGRESS : String
{static} - TUTORIAL_PROGRESS : String
{static} - CURRENT_FRAGMENT : String
{static} - GAME_STATE : String
--
# void onCreate(Bundle savedInstanceState)
+ void onSaveInstanceState(Bundle outState)
+ void resetForNewGame()
+ getGameState() : GameState
+ void onNewGameStart()
+ void onLoadSavedGame()
+ void onBalancedSelection()
+ void onMagicSelection()
+ void onPhysicalSelection()
+ void onStatTutorialButton()
+ void onFightTutorialButton()
+ void onSkipTutorial()
+ void onTutorialContinue()
+ void onContinueStory()
+ void updatePlayerStatsDisplay(IFightView view)
+ void updateEnemyStatsDisplay(IFightView view)
+ void onMagicAttack()
+ void onPhysicalAttack()
- void playerFirstFightHelper(Move m)
- void enemyFirstFightHelper(Move m)
- void firstMoveDecider()
- void fightHelper(Move m)
+ void setEnemy()
+ void updateImages(IFightView view)
+ void onPhysicalUpgrade()
+ void onMagicUpgrade()
+ void onHealthUpgrade()
+ void onUpgradeSkip()
+ void updatePlayerStatsUpgradeDisplay(IUpgradeView view)
+ void onMainMenuReturn()
}
package com.example.team_1e.persistence{
    interface IPersistenceFacade{
        savePlayer(Player p)
        saveEnemy(Enemy e)
        saveGameState(GameState gameState)
        retrievePlayer() : Player
        retrieveEnemy() : Enemy
        retrieveGameState() : GameState
    }
    class LocalStorageFacade{
        - directory : File
        {static} - PLAYERFILE : String
        {static} - ENEMYFILE : String
        {static} - SAVEFILE : String
        ---
        savePlayer(Player p)
        saveEnemy(Enemy enemy)
        saveGameState(GameState gameState)
        retrievePlayer() : Player
        retrieveEnemy() : Enemy
        retrieveGameState() : GameState
    }
    IPersistenceFacade <|.. LocalStorageFacade
} 


com.example.team_1e.view.UI -> "1 \n controller\t" com.example.team_1e.controller.MainActivity: \t\t

com.example.team_1e.model.Player -> "1\t \n player\t \n\n\n\n" com.example.team_1e.controller.MainActivity
com.example.team_1e.model.Enemy --> "1\n    enemy\n\n" com.example.team_1e.controller.MainActivity

LocalStorageFacade -> " 1 \nIPersistenceFacade \t\n\n" com.example.team_1e.controller.MainActivity: \t\t
com.example.team_1e.model.GameState -> " 1 \n    GameState\n\n\n\n" com.example.team_1e.controller.MainActivity: \t\t

@enduml
```
```plantuml
package com.example.team_1e.model{
abstract class APlayer{
- currentHealth: int
- maxHealth: int
- magicAttack: int
- magicDefense: int
- physicalAttack: int
- physicalDefense: int
- image: int
--
+ resolveMove(Move m, APlayer a) : int
+ toString() : String
+ getCurrentHealth() : int
+ upgradePhysicalAttack(int upgradeValue) : int
+ upgradePhysicalDefense(int upgradeValue) : int
+ upgradeMagicAttack(int upgradeValue) : int
+ upgradeMagicDefense(int upgradeValue) : int
+ upgradeCurrentHealth(int upgradeValue) : int
+ upgradeMaxHealth(int upgradeValue) : int
+ getImageTracker() : int
+ getPhysicalAttack() : int
+ getPhysicalDefense() : int
+ getMagicAttack() : int
+ getMagicDefense() : int
+ getMaxHealth() : int
}

class Player{
--
+ pickMove(String s) : Move
}

class Enemy{
--
+ pickMove() : Move
}

class Boss{
}

class EasyBalEnemy{
}

class EasyMagicEnemy{
+pickMove(String s) : Move
}

class EasyPhysicalEnemy{
+pickMove(String s) : Move
}

class HardBalEnemy{
}

class HardMagicEnemy{
+pickMove(String s) : Move
}

class HardPhysEnemy{
+pickMove(String s) : Move
}

class MiniBoss{
}

Enemy <|-- Boss
Enemy <|-- EasyBalEnemy
Enemy <|-- EasyMagicEnemy
Enemy <|-- EasyPhysicalEnemy
Enemy <|-- HardBalEnemy
Enemy <|-- HardMagicEnemy
Enemy <|-- HardPhysicalEnemy
Enemy <|-- MiniBoss

Boss -up-.> Move
EasyBalEnemy -up-.> Move
EasyMagicEnemy -up-.> Move
EasyPhysicalEnemy -up-.> Move
HardBalEnemy -up-.> Move
HardMagicEnemy -up-.> Move
HardPhysicalEnemy -up-.> Move
MiniBoss -up-.> Move

Player -> "1\t\t                \nplayer\t\t\t\t \n\n" com.example.team_1e.controller.MainActivity
Enemy --> "1 \n\t\tenemy\t\t\t" com.example.team_1e.controller.MainActivity 
'associations because Player and Enemy are data types defined in this project

com.example.team_1e.controller.MainActivity -> "\n\n1 \nGameState" GameState: \t\t

class Move{
- magicAttack: int
- physicalAttack: int
--
+ getMagicAttack() : int
+ getPhysicalAttack : int
+ equals(Object o) : boolean
}

class GameState{
- progress : int
- storyProgress : int
- tutorialProgress : int
- curFrag : String
- firstMover : String
---
+ getProgress() : int
+ getStoryProgress() : int
+ getTutorialProgress() : int
+ getCurFrag() : String
+ getFirstMover() : String
+ setProgress(int progress)
+ setStoryProgress(int storyProgress)
+ setTutorialProgress(int tutorialProgress)
+ setCurFrag(String curFrag)
+ setFirstMover(String firstMover)
+ addOneProgress()
+ addOneStoryProgress()
}
APlayer <|-- Player
APlayer <|-- Enemy
Player -up-.> Move
Enemy -up-.> Move

}
```
```plantuml
package com.example.team_1e.view{
    interface ICharacterCustomizationView{
        --
        Listener:
            onBalancedSelection()
            onPhysicalSelection()
            onMagicSelection() 
    }
    
    interface IFightView{
        updatePlayerStatsDisplay(Player p)
        updateEnemyStatsDisplay(Enemy e)
        updateImages(Player p, Enemy e)
        --
        Listener:
            updatePlayerStatsDisplay(IFightView view)
            updateEnemyStatsDisplay(IFightView view)
            onMagicAttack()
            onPhysicalAttack()
            setEnemy()
            updateImages(IFightView view)
    }
    
    interface IGameOverView{
        --
        Listener:
        onMainMenuReturn()
    }
    
    interface IMainMenuView{
        --
        Listener:
        onNewGameStart()
        onLoadSavedGame()
    }
    
    interface IMainView{
        getRootView() : View
        displayFragment(Fragment fragment, boolean reversible, String name)
        updateProgressText(int progress)
        setProgressTextInvisible();
        setProgressTextVisible();
        displayMessage(String msg)
    }
    
    interface ISeeTutorialView{
        --
        Listener:
        onStatTutorialButton()
        onFightTutorialButton()
        onUpgradeTutorialButton()
        onSkipTutorial()
    }
    
    interface IStoryView{
        updateStoryDisplay()
        --
        Listener:
        onContinueStory()
        getGameState : GameState
    }
    
    interface ITutorialView{
        updateTutorialImage()
        --
        Listener:
        onTutorialContinue()
        getGameState(): GameState
    }
    
    interface IUpgradeView{
        updatePhysUpgradeDisplay()
        updateMagUpgradeDisplay()
        updateHealthUpgradeDisplay()
        getPhysUpgrade() : double
        getMagUpgrade() : double
        getHealthUpgrade() : double
        updatePlayerStatsUpgradeDisplay(Player p)
        --
        Listener:
        onPhysicalUpgrade()
        onMagicUpgrade()
        onHealthUpgrade()
        onUpgradeSkip()
        updatePlayerStatsUpgradeDisplay(IUpgradeView view)
    }
    
    interface IVictoryView{
        --
        Listener:
        onMainMenuReturn()
    }
}
```
