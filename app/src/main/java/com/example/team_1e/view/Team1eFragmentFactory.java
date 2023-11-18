package com.example.team_1e.view;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentFactory;

import com.example.team_1e.controller.MainActivity;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Team1eFragmentFactory extends FragmentFactory {
    //package where the View classes (fragments) are stored
    private static final String VIEW_PACKAGE = Team1eFragmentFactory.class.getPackage().getName();

    //controller instance to pass to fragments
    private final MainActivity controller;


    /**
     * Constructor method.
     * @param controller the activity to pass in to fragments
     */
    public Team1eFragmentFactory(MainActivity controller){
        this.controller = controller;
    }

    /**
     * Method used by the fragment manager to instantiate fragments.
     * @param classLoader object to use for loading fragment classes.
     * @param className class name of the fragment to instantiate.
     * @return instantiated fragment
     */
    @NonNull
    @Override
    public Fragment instantiate(@NonNull ClassLoader classLoader, @NonNull String className){
        //converting from class name to class
        Class<? extends Fragment> fragClass = loadFragmentClass(classLoader, className);

         // if the fragment in question is in the view package (one of our fragments)
         if(fragClass.getPackage().getName().equals(VIEW_PACKAGE)){
            try{
                //get all constructors associated with the fragment
                Constructor<?>[] fcons = fragClass.getConstructors();
                assert fcons.length > 0 : "Fragment class does not have a constructor";
                //use the first constructor
                return (Fragment) fcons[0].newInstance(controller);
            }
            catch (IllegalAccessException | InstantiationException | InvocationTargetException e){
                //logging error message to console
                final String emsg = String.format("Can't instantiate %s: ensure it's concrete and" +
                        " has a public constructor with a ControllerActivity parameter", fragClass);
                Log.e("team-1e", emsg);
                e.printStackTrace();
            }
        }
        //default: delegate to superclass method
        return super.instantiate(classLoader, className);
    }
}
