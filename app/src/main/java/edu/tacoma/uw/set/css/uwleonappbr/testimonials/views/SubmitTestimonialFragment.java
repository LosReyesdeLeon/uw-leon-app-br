package edu.tacoma.uw.set.css.uwleonappbr.testimonials.views;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import edu.tacoma.uw.set.css.uwleonappbr.R;
import edu.tacoma.uw.set.css.uwleonappbr.testimonials.model.Testimonial;

/**
 * A simple {@link Fragment} subclass.
 */
public class SubmitTestimonialFragment extends Fragment {

    Testimonial testimonial;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_submit_testimonial, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Save the Testimonial passed in as an argument
        SubmitTestimonialFragmentArgs args = SubmitTestimonialFragmentArgs.fromBundle(getArguments());
        testimonial = args.getTestimonial();
    }

    @Override
    public void onStart() {
        super.onStart();
    }
}