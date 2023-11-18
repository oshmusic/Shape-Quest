package com.example.team_1e.persistence;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.team_1e.model.Enemy;
import com.example.team_1e.model.GameState;
import com.example.team_1e.model.Player;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
    Class that implements the persistence facade interface by writing data to the device's file system.
 */
public class LocalStorageFacade implements IPersistenceFacade{

    //The directory where the files will be written to
    private final File directory;

    //Constants for file names
    private static final String PLAYERFILE = "player";
    private static final String ENEMYFILE = "enemy";
    private static final String SAVEFILE = "savefile";

    /**
     * Constructor for the LocalStorageFacade class.
     * @param directory The location where files are to be written to and read from.
     */
    public LocalStorageFacade(@NonNull File directory){
        this.directory = directory;
    }


    /**
     * Saves the Player object passed as a parameter to a file in local memory.
     * @param p The Player object to be saved.
     */
    @Override
    public void savePlayer(@NonNull Player p) {
        File outFile = new File(this.directory, PLAYERFILE);

        try{
            FileOutputStream fileOutputStream = new FileOutputStream(outFile);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(p);
        }
        catch (FileNotFoundException e){
            final String emsg = String.format("Unable to find file %s", outFile.getAbsolutePath());
            Log.e("Team-1e", emsg);
            e.printStackTrace();
        }
        catch (IOException e){
            final String emsg = String.format("I/O error occurred while attempting to write to %s", outFile);
            Log.e("Team-1e", emsg);
            e.printStackTrace();
        }
    }

    /**
     * Saves the Enemy object passed as a parameter to a file in local memory.
     * @param enemy The Enemy object to be saved.
     */
    @Override
    public void saveEnemy(@NonNull Enemy enemy) {
        File outFile = new File(this.directory, ENEMYFILE);

        try{
            FileOutputStream fileOutputStream = new FileOutputStream(outFile);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(enemy);
        }
        catch (FileNotFoundException e){
            final String emsg = String.format("Unable to find file %s", outFile.getAbsolutePath());
            Log.e("Team-1e", emsg);
            e.printStackTrace();
        }
        catch (IOException e){
            final String emsg = String.format("I/O error occurred while attempting to write to %s", outFile);
            Log.e("Team-1e", emsg);
            e.printStackTrace();
        }
    }

    /**
     * Saves the GameState object passed in as a parameter to a file in local memory.
     * @param gameState The GameState object to be saved.
     */
    @Override
    public void saveGameState(GameState gameState) {
        File outFile = new File(this.directory, SAVEFILE);

        try{
            FileOutputStream fileOutputStream = new FileOutputStream(outFile);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(gameState);
        }
        catch (FileNotFoundException e){
            final String emsg = String.format("Unable to find file %s", outFile.getAbsolutePath());
            Log.e("Team-1e", emsg);
            e.printStackTrace();
        }
        catch (IOException e){
            final String emsg = String.format("I/O error occurred while attempting to write to %s", outFile);
            Log.e("Team-1e", emsg);
            e.printStackTrace();
        }

    }

    /**
     * Retrieves a Player object from the relevant file in local memory.
     * @return A Player object that had been previously saved to memory.
     */
    @Override
    public Player retrievePlayer() {
        Player player = null;

        File inFile = new File(this.directory, PLAYERFILE);

        if(inFile.isFile()){
            try{
                FileInputStream fileInputStream = new FileInputStream(inFile);
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                player = (Player) objectInputStream.readObject();
            }
            catch (IOException e){
                final String emsg = String.format("I/O error occurred while reading from %s", inFile);
                Log.e("Team-1e", emsg);
                e.printStackTrace();
            }
            catch (ClassNotFoundException e){
                final String emsg = String.format("Can't find class of object from %s", inFile);
                Log.e("Team-1e", emsg);
                e.printStackTrace();
            }
        }

        return player;
    }

    /**
     * Retrieves an Enemy object from the relevant file in local memory.
     * @return An Enemy object that had been previously saved to memory.
     */
    @Override
    public Enemy retrieveEnemy() {
        Enemy enemy = null;

        File inFile = new File(this.directory, ENEMYFILE);

        if(inFile.isFile()){
            try{
                FileInputStream fileInputStream = new FileInputStream(inFile);
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                enemy = (Enemy) objectInputStream.readObject();
            }
            catch (IOException e){
                final String emsg = String.format("I/O error occurred while reading from %s", inFile);
                Log.e("Team-1e", emsg);
                e.printStackTrace();
            }
            catch (ClassNotFoundException e){
                final String emsg = String.format("Can't find class of object from %s", inFile);
                Log.e("Team-1e", emsg);
                e.printStackTrace();
            }
        }

        return enemy;
    }


    /**
     * Retrieves a GameState object from the relevant file in local memory.
     * @return A GameState object that had been previously saved to memory.
     */
    @Override
    public GameState retrieveGameState() {
        GameState gameState = null;

        File inFile = new File(this.directory, SAVEFILE);

        if(inFile.isFile()){
            try{
                FileInputStream fileInputStream = new FileInputStream(inFile);
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                gameState = (GameState) objectInputStream.readObject();
            }
            catch (IOException e){
                final String emsg = String.format("I/O error occurred while reading from %s", inFile);
                Log.e("Team-1e", emsg);
                e.printStackTrace();
            }
            catch (ClassNotFoundException e){
                final String emsg = String.format("Can't find class of object from %s", inFile);
                Log.e("Team-1e", emsg);
                e.printStackTrace();
            }
        }
        return gameState;
    }
}
