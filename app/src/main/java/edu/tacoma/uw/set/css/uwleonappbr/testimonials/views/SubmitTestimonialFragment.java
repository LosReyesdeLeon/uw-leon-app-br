package edu.tacoma.uw.set.css.uwleonappbr.testimonials.views;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import edu.tacoma.uw.set.css.uwleonappbr.databinding.FragmentSubmitTestimonialBinding;
import edu.tacoma.uw.set.css.uwleonappbr.testimonials.model.Testimonial;
import edu.tacoma.uw.set.css.uwleonappbr.testimonials.views.SubmitTestimonialFragmentDirections;

/**
 * A simple {@link Fragment} subclass.
 */
public class SubmitTestimonialFragment extends Fragment {

    FragmentSubmitTestimonialBinding mBinding;

    Testimonial mTestimonial;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentSubmitTestimonialBinding.inflate(inflater, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Save the Testimonial passed in as an argument
        SubmitTestimonialFragmentArgs args = SubmitTestimonialFragmentArgs.fromBundle(getArguments());
        mTestimonial = args.getTestimonial();
        mBinding.previewTestimonialButton.setOnClickListener(button -> processTestimonial());
    }

    private void processTestimonial() {
        hasErrors();
        String title = mBinding.editTitleField.getText().toString();
        String content = mBinding.editContentField.getText().toString();
        mTestimonial.addTestimonial(title, content);
        SubmitTestimonialFragmentDirections.ActionSubmitTestimonialFragmentToTestimonialPreviewFragment directions =
                SubmitTestimonialFragmentDirections.actionSubmitTestimonialFragmentToTestimonialPreviewFragment(mTestimonial);
        Navigation.findNavController(getView()).navigate(directions);
    }

    public boolean hasErrors() {
        if (mBinding.editTitleField.getText().toString().isEmpty()) {
            Toast.makeText(getContext(),
                    "You must include a title for your testimonial.", Toast.LENGTH_SHORT).show();
            return true;
        }
        if (mBinding.editContentField.getText().toString().isEmpty()) {
            Toast.makeText(getContext(),
                    "You must include content for your testimonial.", Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }
}