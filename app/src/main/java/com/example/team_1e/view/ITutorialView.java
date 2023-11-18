package com.example.team_1e.view;

import com.example.team_1e.model.GameState;

public interface ITutorialView {
    /**
     * Interface that specifies methods needed by other classes that
     * are interested in being notified of events happening in this view.
     */
    interface Listener{
        /**
         * Called when the user wants to continue to the next page of the tutorial
         */
        void onTutorialContinue();

        /**
         * Gets MainActivity's current GameState. Allows TutorialFragments to access the
         * GameState's fields.
         * @return MainActivity's current GameState
         */
        GameState getGameState();
    }
    void updateTutorialImage();
}
