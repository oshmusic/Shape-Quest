package com.example.team_1e.view;

import com.example.team_1e.model.GameState;

public interface IStoryView {
    void updateStoryDisplay();
    /**
     * Interface that specifies methods needed by other classes that
     * are interested in being notified of events happening in this view.
     */
    interface Listener{
        /**
         * Called when the user wants to continue after seeing the story
         */
        void onContinueStory();

        /**
         * Gets MainActivity's current GameState. Allows TutorialFragments to access the
         * GameState's fields.
         * @return MainActivity's current GameState
         */
        GameState getGameState();
    }
}
