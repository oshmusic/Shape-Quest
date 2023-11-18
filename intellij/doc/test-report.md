## Test Log:
### 2/24/23
Initial tests discovered that the Enemy class constructor was 
never called, resulting in a ``NullPointerException``. Code was changed to fix this.

Further testing revealed that the combat loop would not exit even when the 
enemy was dead:
> Game starts...\
Your move (physical attack or magic attack):\
Physical attack\
Player health: 20/20, Enemy health: 2/10\
Player health: 18/20, Enemy health: 2/10\
Your move (physical attack or magic attack):\
magic attack\
Player health: 18/20, Enemy health: -6/10\
Player health: 16/20, Enemy health: -6/10\
Your move (physical attack or magic attack):\
test
Invalid Move. Type physical attack or magic attack\
physical attack\
Player health: 16/20, Enemy health: -14/10\
Player health: 14/20, Enemy health: -14/10\
Your move (physical attack or magic attack):\
...

Luckily, this was easily fixed by changing two tests from "==" to "<=".

Next, further testing found that the entire "upgrade stats" usecase had some
bugs in its current implementation. Even after resolving an error in choosing
which upgrades were presented to the user, upgrades were not being displayed.
Luckily, this was due to an accidentally omitted method call and was easily fixed.

More testing revealed that when completed, the game would not recognize user
input to play again:
>Story fragment 3\
Congratulations! You reached the end!\
Play again? y/n\
y\
Play again? y/n\
Y\
Play again? y/n\
n\
Process finished with exit code 0

This turned out to be due to an ommitted call to the `Game.returnToBeginning()` method
and a missing ``break`` statement. After those were added, the code ran smoothly,
although some small quality of life errors remained and were later fixed.

Here is a transcript of a complete session at this point in the testing:
> Game starts...\
An enemy approaches!\
Your move (physical attack or magic attack):\
physical attack\
Player health: 20/20, Enemy health: 2/10\
The enemy attacks!\
Player health: 18/20, Enemy health: 2/10\
Your move (physical attack or magic attack):\
magic attack\
Victory!\
UPGRADE OPTIONS:\
Physical Attack (A)\
Magic Defense (D)\
Maximum Health (F)\
Select upgrade ("skip" to skip):\
a\
Story fragment 1\
An enemy approaches!\
Your move (physical attack or magic attack):\
physical attack\
Victory!\
UPGRADE OPTIONS:\
Physical Defense (B)\
Magic Attack (C)\
Current Health (E)\
Select upgrade ("skip" to skip):\
E\
Story fragment 2\
An enemy approaches!\
Your move (physical attack or magic attack):\
magic attack\
Player health: 20/20, Enemy health: 2/10\
The enemy attacks!\
Player health: 18/20, Enemy health: 2/10\
Your move (physical attack or magic attack):\
physical attack\
Victory!\
UPGRADE OPTIONS:\
Physical Defense (B)\
Magic Attack (C)\
Current Health (E)\
Select upgrade ("skip" to skip):\
B\
Story fragment 3\
Congratulations! You reached the end!\
Play again? y/n\
y\
Your next adventure begins...\
An enemy approaches!\
Your move (physical attack or magic attack):\
physical attack\
Player health: 20/20, Enemy health: 2/10\
The enemy attacks!\
Player health: 18/20, Enemy health: 2/10\
Your move (physical attack or magic attack):\
magic attack\
Victory!\
UPGRADE OPTIONS:\
Physical Defense (B)\
Magic Attack (C)\
Maximum Health (F)\
Select upgrade ("skip" to skip):\
C\
Story fragment 1\
An enemy approaches!\
Your move (physical attack or magic attack):\
magic attack\
Victory!\
UPGRADE OPTIONS:\
Physical Defense (B)\
Magic Defense (D)\
Maximum Health (F)\
Select upgrade ("skip" to skip):\
D\
Story fragment 2\
An enemy approaches!\
Your move (physical attack or magic attack):\
magic attack\
Victory!\
UPGRADE OPTIONS:\
Physical Attack (A)\
Magic Defense (D)\
Current Health (E)\
Select upgrade ("skip" to skip):\
no\
Sorry, try again.\
skip\
Story fragment 3\
Congratulations! You reached the end!\
Play again? y/n\
n\
Process finished with exit code 0

### 2/27/23
We tried to do a full run of the program again:
>Game starts... \
An enemy approaches! \
Your move (physical attack or magic attack): \
physical attack \
Player health: 20/20, Enemy health: 2/10 \
The enemy attacks! \
Player health: 18/20, Enemy health: 2/10 \
Your move (physical attack or magic attack): \
magic attack \
Victory! \
UPGRADE OPTIONS: \
Physical Defense (B) \
Magic Defense (D) \
Current Health (E) \
Select upgrade ("skip" to skip): \
b \
Story fragment 1 \
An enemy approaches! \
Your move (physical attack or magic attack): \
magic attack \
Player health: 18/20, Enemy health: 2/10 \
The enemy attacks! \
Player health: 19/20, Enemy health: 2/10 \
Your move (physical attack or magic attack): \
physical attack \
Victory! \
UPGRADE OPTIONS: \
Physical Attack (A) \
Magic Defense (D) \
Maximum Health (F) \
Select upgrade ("skip" to skip): \
a \
Story fragment 2 \
An enemy approaches! \
Your move (physical attack or magic attack): \
magic attack \
Player health: 19/20, Enemy health: 2/10 \
The enemy attacks! \
Player health: 17/20, Enemy health: 2/10 \
Your move (physical attack or magic attack): \
physical attack \
Victory! \
UPGRADE OPTIONS: \
Physical Attack (A) \
Magic Attack (C) \
Maximum Health (F) \
Select upgrade ("skip" to skip): \
skip \
Story fragment 3 \
Congratulations! You reached the end! \
Play again? y/n \
n \
Process finished with exit code 0
>
This revealed that enemy attacks can occasionally 
heal the player.

Test after fixing bug where enemy attacks heal player:
>An enemy approaches! \
Your move (physical attack or magic attack): \
physical attack \
Player health: 20/20, Enemy health: 2/10 \
The enemy attacks! \
Player health: 18/20, Enemy health: 2/10 \
Your move (physical attack or magic attack): \
physical attack \
Victory! \
UPGRADE OPTIONS: \
Physical Defense (B) \
Magic Attack (C) \
Maximum Health (F) \
Select upgrade ("skip" to skip): \
b \
Story fragment 1 \
An enemy approaches! \
Your move (physical attack or magic attack): \
magic attack \
Player health: 18/20, Enemy health: 2/10 \
The enemy attacks! \
Player health: 17/20, Enemy health: 2/10 \
Your move (physical attack or magic attack): 

Testing whether the game handles player death correctly (Enemy stats were adjusted to be unbeatable):
>Game starts... \
An enemy approaches!\
Your move (physical attack or magic attack):\
physical attack\
Player health: 20/20, Enemy health: 19/20\
The enemy attacks!\
Player health: 5/20, Enemy health: 19/20\
Your move (physical attack or magic attack):\
magic attack\
Player health: 5/20, Enemy health: 18/20\
The enemy attacks!\
Defeat\
Returning to the beginning of the game...\
Game starts...\
An enemy approaches!\
Your move (physical attack or magic attack):
> 

We moved the first story fragment to before the first
fight instead of after. This test makes sure things
still work after this change:
>Game starts... \
Story fragment 1 \
An enemy approaches! \
Your move (physical attack or magic attack): \
physical attack \
Player health: 20/20, Enemy health: 2/10 \
The enemy attacks! \
Player health: 18/20, Enemy health: 2/10 \
Your move (physical attack or magic attack): \
magic attack \
Victory! \
UPGRADE OPTIONS: \
Physical Defense (B) \
Magic Defense (D) \
Maximum Health (F) \
Select upgrade ("skip" to skip): \
f \
Story fragment 2 \
An enemy approaches! \
Your move (physical attack or magic attack): \
physical attack \
Player health: 18/23, Enemy health: 2/10 \
The enemy attacks! \
Player health: 16/23, Enemy health: 2/10 \
Your move (physical attack or magic attack): \
magic attack \
Victory! \
UPGRADE OPTIONS: \
Physical Attack (A) \
Magic Defense (D) \
Maximum Health (F) \
Select upgrade ("skip" to skip): \
f \
Story fragment 3 \
An enemy approaches! \
Your move (physical attack or magic attack): \
physical attack \
Player health: 16/26, Enemy health: 2/10 \
The enemy attacks! \
Player health: 14/26, Enemy health: 2/10 \
Your move (physical attack or magic attack): \
magic attack \
Victory! \
UPGRADE OPTIONS: \
Physical Defense (B) \
Magic Attack (C) \
Current Health (E) \
Select upgrade ("skip" to skip): \
e \
Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException: Index 3 out of bounds for length 3 \
at Game.presentStory(Game.java:115) \
at Game.round(Game.java:128) \
at TextUI.main(TextUI.java:106)
>
This change resulted in an ``ArrayIndexOutOfBoundsException``

Test after fixing the ``ArrayIndexOutOfBoundsException``:
>Game starts... \
Story fragment 1 \
An enemy approaches! \
Your move (physical attack or magic attack): \
physical attack \
Player health: 20/20, Enemy health: 2/10 \
The enemy attacks! \
Player health: 18/20, Enemy health: 2/10 \
Your move (physical attack or magic attack): \
magic attack \
Victory! \
UPGRADE OPTIONS: \
Physical Attack (A) \
Magic Defense (D) \
Maximum Health (F) \
Select upgrade ("skip" to skip): \
a \
Story fragment 2 \
An enemy approaches! \
Your move (physical attack or magic attack): \
physical attack \
Victory! \
UPGRADE OPTIONS: \
Physical Defense (B) \
Magic Attack (C) \
Maximum Health (F) \
Select upgrade ("skip" to skip): \
skip \
Story fragment 3 \
Congratulations! You reached the end! \
Play again? y/n \
n \
Process finished with exit code 0
> 

First test after trying to allow player to quit:
> Game starts... \
Story fragment 1 \
An enemy approaches! \
Your move (physical attack or magic attack): \
physical attack \
Player health: 20/20, Enemy health: 2/10 \
The enemy attacks! \
Player health: 18/20, Enemy health: 2/10 \
Your move (physical attack or magic attack): \
physical attack \
Victory! \
UPGRADE OPTIONS: \
Physical Defense (B) \
Magic Defense (D) \
Maximum Health (F) \
Select upgrade ("skip" to skip): \
b \
Story fragment 2 \
An enemy approaches! \
Your move (physical attack or magic attack): \
quit \
Exception in thread "main" java.lang.NullPointerException \
at Game.resolvePlayerMove(Game.java:23) \
at Game.fight(Game.java:93) \
at Game.round(Game.java:128) \
at TextUI.main(TextUI.java:111)
> 

Test after trying to fix ``NullPointerException``:
>Game starts... \
Story fragment 1 \
An enemy approaches! \
Your move (physical attack or magic attack): \
quit \
An enemy approaches! \
Your move (physical attack or magic attack): \
quit \
Congratulations! You reached the end! \
> 
Now results in fights being skipped when the player tries
to quit, instead of actually quiting

Test after trying to fix bug where player wins fights
automatically by entering quit:
>Game starts... \
Story fragment 1 \
An enemy approaches! \
Your move (physical attack or magic attack): \
quit \
Congratulations! You reached the end! \
Process finished with exit code 0
 
Program now properly quits the fight, but the ending
message still displays.

Test after removing the congratulations message if
player quits:
>Game starts... \
Story fragment 1 \
An enemy approaches! \
Your move (physical attack or magic attack): \
quit \
Process finished with exit code 0 
> 
Quiting now works properly during fights.

Test to make sure quit also works during upgrades:
>Game starts... \
Story fragment 1 \
An enemy approaches! \
Your move (physical attack or magic attack): \
physical attack \
Player health: 20/20, Enemy health: 2/10 \
The enemy attacks! \
Player health: 18/20, Enemy health: 2/10 \
Your move (physical attack or magic attack): \
magic attack \
Victory! \
UPGRADE OPTIONS: \
Physical Defense (B) \
Magic Defense (D) \
Maximum Health (F) \
Select upgrade ("skip" to skip): \
quit \
Story fragment 2 \
An enemy approaches! \
Your move (physical attack or magic attack):
>
This test revealed that player cannot quit while
upgrading stats.

Test after trying to fix quit not working while
upgrading stats:
>Game starts... \
Story fragment 1 \
An enemy approaches! \
Your move (physical attack or magic attack): \
physical attack \
Player health: 20/20, Enemy health: 2/10 \
The enemy attacks! \
Player health: 18/20, Enemy health: 2/10 \
Your move (physical attack or magic attack): \
magic attack \
Victory! \
UPGRADE OPTIONS: \
Physical Defense (B) \
Magic Attack (C) \
Maximum Health (F) \
Select upgrade ("skip" to skip): \
f \
Story fragment 2 \
An enemy approaches! \
Your move (physical attack or magic attack): \
magic attack \
Player health: 18/23, Enemy health: 2/10 \
The enemy attacks! \
Player health: 16/23, Enemy health: 2/10 \
Your move (physical attack or magic attack): \
physical attack \
Victory! \
UPGRADE OPTIONS: \
Physical Attack (A) \
Magic Attack (C) \
Maximum Health (F) \
Select upgrade ("skip" to skip): \
quit \
Process finished with exit code 0
> 
Issue has been fixed.

We then made the player need to type continue to
progress after the story. This also added another
opportunity for the player to quit. This test
sees if continue and quit work during presentStory:
>Game starts... \
Story fragment 1 \
continue \
An enemy approaches! \
Your move (physical attack or magic attack): \
physical attack \
Player health: 20/20, Enemy health: 2/10 \
The enemy attacks! \
Player health: 18/20, Enemy health: 2/10 \
Your move (physical attack or magic attack): \
physical attack \
Victory! \
UPGRADE OPTIONS: \
Physical Defense (B) \
Magic Defense (D) \
Maximum Health (F) \
Select upgrade ("skip" to skip): \
f \
Story fragment 2 \
quit \
An enemy approaches! \
Your move (physical attack or magic attack):
> 
This test revealed that continue works but quit does not.

To help figure out why quit is not working, this
test sees if input other than quit or continue
is invalid:
>Game starts... \
Story fragment 1 \
c \
Please enter continue to continue or quit to quit \
continue \
An enemy approaches! \
Your move (physical attack or magic attack): \
physical attack \
Player health: 20/20, Enemy health: 2/10 \
The enemy attacks! \
Player health: 18/20, Enemy health: 2/10 \
Your move (physical attack or magic attack): \
magic attack \
Victory!
> 
Invalid inputs are not accepted.

Test to double-check that invalid inputs are not
accepted in other parts of the program:
>Game starts... \
Story fragment 1 \
continue \
An enemy approaches! \
Your move (physical attack or magic attack): \
s \
Invalid Move. Type "physical attack" or "magic attack" \
physical attack \
Player health: 20/20, Enemy health: 2/10 \
The enemy attacks! \
Player health: 18/20, Enemy health: 2/10 \
Your move (physical attack or magic attack): \
magic attack \
Victory! \
UPGRADE OPTIONS: \
Physical Defense (B) \
Magic Attack (C) \
Maximum Health (F) \
Select upgrade ("skip" to skip): \
s \
Sorry, try again. \
> 
Invalid inputs are never accepted.

We realized that we had only tested quit during the
first story fragment. This test checks if quit works
during later story fragments:
>Game starts... \
Story fragment 1 \
quit \
An enemy approaches! \
Your move (physical attack or magic attack): \
physical attack \
Player health: 20/20, Enemy health: 2/10 \
The enemy attacks! \
Player health: 18/20, Enemy health: 2/10 \
Your move (physical attack or magic attack): \
physical attack \
Victory! \
UPGRADE OPTIONS: \
Physical Defense (B) \
Magic Defense (D) \
Maximum Health (F) \
Select upgrade ("skip" to skip): \
f \
Story fragment 2 \
quit \
Process finished with exit code 0
>
Quit works later in the program, so the problem is
with the first time we present the story.

This revealed why player could not quit. This test
checks if it works now:
>Game starts... \
Story fragment 1 \
quit \
Process finished with exit code 0

Testing main menu:
>Welcome!\
Type "start" to launch the game or "quit" to quit:\
test\
Type "start" to launch the game or "quit" to quit:\
quit\
> \
Process finished with exit code 0

>Welcome!\
Type "start" to launch the game or "quit" to quit:\
start\
Game starts...\
Story fragment 1\
continue\
An enemy approaches!\
Your move (physical attack or magic attack):\
physical attack\
Player health: 20/20, Enemy health: 2/10