package com.example.team_1e.model;

import java.io.Serializable;

public class GameState implements Serializable {
    /**
     * An integer representing the user's progress through fights.
     */
    private int progress;

    /**
     * An integer representing the user's progress through the story.
     */
    private int storyProgress;

    /**
     * An integer representing the user's progress through the tutorial.
     */
    private int tutorialProgress;

    /**
     * A String representing the current fragment displayed to the user.
     */
    private String curFrag;

    /**
     * A String representing if the Enemy or Player should move first. "player" if the Player moves
     * first. "enemy" if the Enemy moves first.
     */
    private String firstMover;

    /**
     * Empty constructor for GameState objects
     */
    public GameState(){ }

    /**
     * Constructor for GameState objects.
     * @param progress The user's progress through fights
     * @param storyProgress The user's progress through the story
     * @param tutorialProgress The user's progress through the tutorial
     * @param curFrag The current Fragment displayed to the user
     * @param firstMover The APlayer that should move first during a fight
     */
    public GameState(int progress, int storyProgress, int tutorialProgress, String curFrag, String firstMover) {
        this.progress = progress;
        this.storyProgress = storyProgress;
        this.tutorialProgress = tutorialProgress;
        this.curFrag = curFrag;
        this.firstMover = firstMover;
    }

    /**
     * Returns the GameState's progress field
     * @return The user's current progress through fights
     */
    public int getProgress() { return this.progress; }

    /**
     * Returns the GameState's storyProgress field
     * @return The user's current progress through the story
     */
    public int getStoryProgress() { return this.storyProgress; }

    /**
     * Returns the GameState's tutorialProgress field
     * @return The user's current progress through the tutorial
     */
    public int getTutorialProgress() { return tutorialProgress; }

    /**
     * Returns the GameState's curFrag field
     * @return A string representing the Fragment currently displayed to the user
     */
    public String getCurFrag() { return curFrag; }

    /**
     * Returns the GameState's firstMover field
     * @return A string representing which APlayer should move first during a fight
     */
    public String getFirstMover() {return firstMover; }

    /**
     * Sets the GameState's progress field
     * @param progress An integer representing what the new progress should be
     */
    public void setProgress(int progress) { this.progress = progress; }

    /**
     * Sets the GameState's storyProgress field
     * @param storyProgress An integer representing what the new storyProgress should be
     */
    public void setStoryProgress(int storyProgress) { this.storyProgress = storyProgress; }

    /**
     * Sets the GameState's tutorialProgress field
     * @param tutorialProgress An integer representing what the new tutorialProgress should be
     */
    public void setTutorialProgress(int tutorialProgress) { this.tutorialProgress = tutorialProgress; }

    /**
     * Sets the GameState's curFrag field
     * @param curFrag A String representing what the new curFrag should be
     */
    public void setCurFrag(String curFrag) { this.curFrag = curFrag; }

    /**
     * Sets the GameState's firstMover field
     * @param firstMover A String representing what the new firstMover should be
     */
    public void setFirstMover(String firstMover) { this.firstMover = firstMover; }

    /**
     * Adds one to the GameState's progress field
     */
    public int addOneProgress() {
        this.progress++;
        return this.progress;
    }

    /**
     * Adds one to the GameState's storyProgress field
     */
    public int addOneStoryProgress() {
        this.storyProgress++;
        return this.storyProgress;
    }
}
