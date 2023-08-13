package edu.tacoma.uw.set.css.uwleonappbr.testimonials.views;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import edu.tacoma.uw.set.css.uwleonappbr.R;
import edu.tacoma.uw.set.css.uwleonappbr.databinding.FragmentTestimonialPreviewBinding;
import edu.tacoma.uw.set.css.uwleonappbr.testimonials.model.Testimonial;
import edu.tacoma.uw.set.css.uwleonappbr.testimonials.model.TestimonialViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class TestimonialPreviewFragment extends Fragment {

    private TestimonialViewModel testimonialViewModel;

    Testimonial mTestimonial;

    FragmentTestimonialPreviewBinding mBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        testimonialViewModel = new ViewModelProvider(getActivity()).get(TestimonialViewModel.class);
        mBinding = FragmentTestimonialPreviewBinding.inflate(inflater, container, false);
        Log.i(getClass().toString(), "onCreateView: done.");
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        testimonialViewModel.addResponseObserver(getViewLifecycleOwner(), response -> {
            observeResponse(response);
        });
        TestimonialPreviewFragmentArgs args = TestimonialPreviewFragmentArgs.fromBundle(getArguments());
        mTestimonial = args.getTestimonial();
        mBinding.submitTestimonialButton.setOnClickListener(button -> submitTestimonial());
        Log.i(getClass().toString(), "onViewCreated: done");
    }

    private void observeResponse(final JSONObject response) {
        if (response.length() > 0) {
            if (response.has("error")) {
                try {
                    Toast.makeText(this.getContext(),
                            "Error Adding Testimonial: " +
                                    response.get("error"), Toast.LENGTH_LONG).show();
                } catch (JSONException e) {
                    Log.e("JSON Parse Error", e.getMessage());
                    Toast.makeText(this.getContext(), "Testimonial added.", Toast.LENGTH_LONG).show();
                }
            } else {
                Log.d("JSON Response", "No Error.");
            }
        }
    }

    private void submitTestimonial() {
        Log.i(getClass().toString(), "submitTestimonial: submitTestimonial called.");
        testimonialViewModel.addTestimonialToDatabase(mTestimonial);
        Log.i(getClass().toString(), "submitTestimonial: submitTestimonial finished.");
        Toast.makeText(getContext(), "Testimonial submitted.", Toast.LENGTH_LONG).show();
        Navigation.findNavController(getView())
                .navigate(R.id.action_testimonialPreviewFragment_to_homeScreenFragment);
    }
}