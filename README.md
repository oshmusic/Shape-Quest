## This is a copy of an existing project, reposted here with permission. The original README file is preserved below.

### Authors: Oliver Halberg and Caroline Semel, Spring 2023





# CMPU-203 S23 - Team 1E

### Functionality
This program currently supports the user fighting combats,
upgrading their stats after each combat, viewing story
fragments, customizing their character from a list of options,
and viewing a tutorial on how to play the game. Multiple enemy types
have been implemented to provide an engaging challenge to the user.
Note: completing the game is difficult, but not impossible (we tested).
### How to run the program
This program is currently in the form of an Android application,
which can be run either from an emulator within Android
Studio (we used an emulated Google Pixel 5, API 33) or through installation on
a physical Android device.

Upon launch, the application displays a main menu featuring
two buttons: "New Game" and "Load Saved Game". When the user clicks
"Load Saved Game", the application will attempt to load a previously saved game state;
if none is available then a message will be displayed. The game state is saved to local storage
every time the app is closed and the user has not completed the story or died.

When the user clicks "New Game",
they will be taken to a menu screen where they can click buttons to view one of three tutorial pages:
"Stat Tutorial", "Combat Tutorial", and "Upgrade Tutorial". Each screen informs the user about a different
part of how to play the game, and contains a "Continue" button to return to the tutorial menu screen.
Each page can be viewed as many times as the user wants. When the user is done with the tutorial, they
can then click a button labeled "Skip" to move to the next screen.

After the tutorial, the user is  taken to a screen where they can select one
of three character options: a "Balanced Focus" character with
even magic and physical starting stats, a "Magic Focus"
character with higher magic stats but lower physical ones,
or a "Physical Focus" character with higher physical stats
but lower magic ones.

After selecting a character, the user is shown the first part of the story before being sent to a combat screen.

The combat screen
displays the player and enemy stats and features two
buttons, one for each of the user's attack options. Combat
occurs in a turn-based fashion; sometimes the user will move first, while other times their opponent
will, but both moves are always resolved (in order) before the next turn begins, unless the player
or enemy making the second move has died as a result of the first move.
The displayed player and enemy stats update after every move.

If the player loses the fight, they are shown a "Game Over"
message and then sent back to
the beginning of the game.

If the player wins the fight, they will be given
options for stat upgrades. There will always be
an option to upgrade physicalAttack or
physicalDefense, an option to upgrade magicAttack
or magicDefense, and an option to increase
currentHealth or maxHealth. These options are displayed
on three buttons: one for upgrading physical stats, one for
magic stats, and one for health stats. Clicking a
button applies the specified upgrade and moves the player
to the next story screen. There is also a "skip" button
which moves the user to the next screen without applying any
upgrades. The player's current stats are displayed on the
upgrade screen to aid in choosing which stat to upgrade.
All stats carry over between combats during a game.

Play continues, following the pattern of story, then combat,
then upgrade, until the player reaches the end of the story.
At this point, they are shown a "Victory" message and sent back
to the beginning of the game. There are 10 combats in total, including a
"miniboss" fight in the middle and a "boss" fight at the end. The first 4 fights
feature a randomly selected "easy" enemy type, followed by the "miniboss" fight; fights
6 through 9 feature randomly selected "hard" enemy types, and fight 10 is the "boss" fight.
The "easy" and "hard" enemy types follow the same pattern as the player options: one with even magic
and physical stats, one with higher magic stats, and one with higher physical stats.
