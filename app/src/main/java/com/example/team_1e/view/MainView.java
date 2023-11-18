package com.example.team_1e.view;

import static android.provider.Settings.System.getString;

import android.app.Activity;
import android.content.res.Resources;
import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.team_1e.R;
import com.example.team_1e.databinding.MainBinding;
import com.google.android.material.snackbar.Snackbar;

import android.content.Context;

/**
 * An implementation of the application's main screen template.
 */
public class MainView implements IMainView{
    /**
     * Object to perform fragment transactions
     */
    FragmentManager fmanager;

    /**
     * Gives access to graphical elements in the xml layout for the main screen
     */
    MainBinding binding;

    /**
     * Constructor method.
     * @param activity The Android activity the screen is associated with.
     */
    public MainView(FragmentActivity activity){
        this.fmanager = activity.getSupportFragmentManager();
        this.binding = MainBinding.inflate(activity.getLayoutInflater());
    }


    /**
     * Retrieves the graphical widget (Android view) at the root of the layout hierarchy.
     * @return The screen's root view/widget.
     */
    @Override
    public View getRootView() {
        return this.binding.getRoot();
    }

    /**
     * Replaces the contents of the screen's fragment container with the fragment passed in
     * as an argument.
     * @param fragment The fragment to be displayed
     * @param reversible True if the fragment should be reversible, false otherwise
     * @param name A name to be attached to this transaction
     */
    @Override
    public void displayFragment(Fragment fragment, boolean reversible, String name) {
        FragmentTransaction ft = fmanager.beginTransaction()
                .replace(this.binding.fragmentContainerView.getId(), fragment);
        if (reversible){
            ft.addToBackStack(name);
        }
       ft.commit();
    }

    /**
     * Updates the progress tracker displayed at the top of the screen.
     * @param progress An integer representing the player's progress through the story.
     */
    public void updateProgressText(int progress){
        String text = "Progress: " + progress + "/10"; //done this way to use less hard-coding of text
        this.binding.progressText.setText(text);
    }

    /**
     * Makes the progress bar invisible
     */
    public void setProgressTextInvisible(){
        this.binding.progressText.setVisibility(View.GONE);
    }

    /**
     * Makes the progress bar visible
     */
    public void setProgressTextVisible() {
        this.binding.progressText.setVisibility(View.VISIBLE);
    }

    /**
     * A method to display messages to the user using a Snackbar
     * @param msg The message to display
     */
    public void displayMessage(String msg){
        Snackbar sb = Snackbar.make(this.binding.getRoot(), msg, Snackbar.LENGTH_LONG);
        sb.show();
    }
}
