package edu.tacoma.uw.set.css.uwleonappbr.main;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import org.jetbrains.annotations.Nullable;

import java.util.Objects;

import edu.tacoma.uw.set.css.uwleonappbr.R;
import edu.tacoma.uw.set.css.uwleonappbr.databinding.FragmentHomeScreenBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeScreenFragment extends Fragment {

    private FragmentHomeScreenBinding myBinding;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        myBinding = FragmentHomeScreenBinding.inflate(inflater, container, false);
        return myBinding.getRoot();
    }
    @Override
    public void onViewCreated (@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        myBinding.homepageTestimonialButton.setOnClickListener(homepage_testimonial_button -> gotoTestimonials());
        myBinding.homepageNewsButton.setOnClickListener(homepage_testimonial_button -> gotoNews());
        myBinding.homepagePreflightButton.setOnClickListener(homepage_preflight_button -> gotoPreflight());
        myBinding.homepageScholarshipsButton.setOnClickListener(homepage_scholarships_button -> gotoScholarships());
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        myBinding = null;
    }

    private void gotoTestimonials() {
        //Log.d("HOME SCREEN FRAGMENT", "Testimonial button pressed");
        Navigation.findNavController(requireView())
                .navigate(R.id.action_homeScreenFragment_to_viewTestimonialsFragment);
    }
    private void gotoNews(){
        Navigation.findNavController(requireView())
                .navigate(R.id.action_homeScreenFragment_to_viewNewsFragment);
    }
    private void gotoScholarships(){
        Navigation.findNavController(requireView())
                .navigate(R.id.action_homeScreenFragment_to_scholarshipInfoFragment);
    }
    private void gotoPreflight(){
        Navigation.findNavController(requireView())
                .navigate(R.id.action_homeScreenFragment_to_preFlightInfoFragment);
    }


    }