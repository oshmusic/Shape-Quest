# CMPU-203 S23 - Team 1E

### Functionality
Currently, this program supports the user fighting,
upgrading their stats, and viewing story fragments. 
The fights are simplified because there is only one
enemy type at this time. Additionally, the enemy
does not scale with the player and chooses moves at
random, so it should be relatively simple for the user
to win fights. Later iterations will have more complex
enemy types and behaviors, making combat much more
difficult. Upgrading stats is also simplified to
always upgrade stats by the same value. Later
iterations will likely have this value be random.
The story also has not been written yet, so the
program currently shows "Story fragment" with a
number.

Our program also has not yet implemented character
customization or a tutorial, so the beginning of the
game has been simplified and assumes the user has
read this document.

### How to run the program
The main() method is in the TextUI class. The program can be run from an IDE
such as IntelliJ or from the command line by running the TextUI class with no
input arguments.

The program starts with a simplified main menu with two options 
for the player to enter: "start", which launches the game, 
and "quit", which quits the program.

During fights, the user should enter "physical attack" to
make an attack using their physicalAttack stat 
against the enemy's physicalDefense stat or "magic
attack" to make an attack using their magicAttack
stat against the enemy's magicDefense stat. Case 
does not matter. Any other input will result in an 
invalid input message. Health status for the player
and the enemy will display after each attack.

If the player loses the fight, they are sent back to
the beginning of the game.

If the player wins the fight, they will be given
options for stat upgrades. There will always be
an option to upgrade physicalAttack or 
physicalDefense, an option to upgrade magicAttack
or magicDefense, and an option to increase
currentHealth or maxHealth. Each of these options will
have a letter next to them. The player should enter
the letter next to the stat they wish to upgrade.
Case does not matter. Any other input will prompt
the user to try again. Stats unrelated to health
are currently never shown to the player.

Story fragments are displayed, and user must type
continue in order to progress. 

Player can also type quit at any point to quit. 
If the player quits, the next time they run the 
program they will need to start at the beginning again.
