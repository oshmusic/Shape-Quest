package com.example.team_1e.view;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.content.res.AppCompatResources;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.team_1e.R;
import com.example.team_1e.databinding.FragmentCharacterCustomizationBinding;
import com.google.android.material.snackbar.Snackbar;

public class CharacterCustomizationFragment extends Fragment implements ICharacterCustomizationView{
    /**
     * Reference to graphical elements from xml layout for Character Customization fragments
     */
    private FragmentCharacterCustomizationBinding binding;
    /**
     * Observer to be notified of events for Character Customization fragments
     */
    Listener listener;


    /**
     * Constructor method for characterCustomizationFragments.
     * @param listener An observer to be notified of events
     */
    public CharacterCustomizationFragment(Listener listener){
        this.listener = listener;
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
       this.binding = FragmentCharacterCustomizationBinding.inflate(inflater);
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
        //balanced character selection button handler
        this.binding.defaultButton.setOnClickListener(new View.OnClickListener() {
            /**
             * Listener method to be called when the Balanced Focus button is clicked.
             * @param view The view that was clicked on.
             */
            @Override
            public void onClick(View view) {
                CharacterCustomizationFragment.this.listener.onBalancedSelection();
            }
        });


        //magic character selection button handler
        this.binding.magButton.setOnClickListener(new View.OnClickListener() {
            /**
             * Listener method to be called when the Magic Focus button is clicked.
             * @param view The view that was clicked on.
             */
            @Override
            public void onClick(View view) {
                CharacterCustomizationFragment.this.listener.onMagicSelection();
            }
        });

        //physical character selection button handler
        this.binding.physButton.setOnClickListener(new View.OnClickListener() {
            /**
             * Listener method to be called when the Physical Focus button is clicked.
             * @param view The view that was clicked on.
             */
            @Override
            public void onClick(View view) {
                CharacterCustomizationFragment.this.listener.onPhysicalSelection();
            }
        });
    }
}