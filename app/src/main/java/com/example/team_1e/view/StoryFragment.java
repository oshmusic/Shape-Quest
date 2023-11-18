package com.example.team_1e.view;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.team_1e.R;
import com.example.team_1e.databinding.FragmentStoryBinding;
import com.example.team_1e.model.GameState;

import java.util.Locale;


public class StoryFragment extends Fragment implements IStoryView{

    /**
     * Reference to graphical elements from xml layout for Story fragments
     */
    private @NonNull FragmentStoryBinding binding;

    /**
     * Observer to be notified of events for Story fragments
     */
    private IStoryView.Listener listener;

    /**
     * Array that holds the text for story screens
     */
    private String[] story;

    /**
     * Integer that represents what page of the story the player should see
     */
    private int storyProgress;

    /**
     * Constructor method for StoryFragments.
     * @param listener An observer to be notified of events
     */
    public StoryFragment(IStoryView.Listener listener) {
        this.listener = listener;
        this.storyProgress = this.listener.getGameState().getStoryProgress();
    }

    /**
     * Overrides method of same name from the superclass. Inflates the xml layout
     * associated with the fragment.
     * @param inflater The LayoutInflater object that can be used to inflate
     * any views in the fragment.
     * @param container If non-null, this is the parent view that the fragment's
     * UI should be attached to.  The fragment should not add the view itself,
     * but this can be used to generate the LayoutParams of the view.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     * @return The root of the layout that has just been inflated.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.binding = FragmentStoryBinding.inflate(inflater);
        story = getContext().getResources().getStringArray(R.array.storyStringsArray);
        this.updateStoryDisplay();
        return this.binding.getRoot();
    }

    /**
     * Overrides the method of the same name from the superclass. Called by the Android platform
     * after the layout has been inflated but before the view transitions to the created state.
     * @param view The View returned by {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     */
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        this.binding.storyContinueButton.setOnClickListener(new View.OnClickListener() {
            /**
             * Listener method to be called when the Story Continue button is clicked.
             * @param view The view that was clicked on.
             */
            @Override
            public void onClick(View view) {
                StoryFragment.this.listener.onContinueStory();
            }
        });
    }

    /**
     * Has the view update the Story display to reflect the Story Progress of the Player.
     */
    @Override
    public void updateStoryDisplay() {
        this.binding.story.setText(story[storyProgress]);
    }
}