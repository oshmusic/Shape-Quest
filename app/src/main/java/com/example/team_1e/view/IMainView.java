package com.example.team_1e.view;

import android.view.View;

import androidx.fragment.app.Fragment;

/**
 * An interface for the application screen template.
 */
public interface IMainView {

    /**
     * Gets the graphical widget (Android view) at the root of the screen hierarchy.
     * @return The screen's root widget (Android view)
     */
    View getRootView();

    /**
     * Replaces the contents of the screen's fragment container with the fragment passed in
     * as an argument.
     * @param fragment The fragment to be displayed
     * @param reversible True if the fragment should be reversible, false otherwise
     * @param name A name to be attached to this transaction
     */

    void displayFragment(Fragment fragment, boolean reversible, String name);

    /**
     * Updates the progress tracker displayed at the top of the screen.
     * @param progress An integer representing the player's progress through the story.
     */
    void updateProgressText(int progress);

    /**
     * Makes the progress bar invisible
     */
    void setProgressTextInvisible();

    /**
     * Makes the progress bar visible
     */
    void setProgressTextVisible();

    /**
     * A method to display messages using a Snackbar
     * @param msg The message to display
     */
    void displayMessage(String msg);
}
