package edu.tacoma.uw.set.css.uwleonappbr.testimonials.views;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.tacoma.uw.set.css.uwleonappbr.R;
import edu.tacoma.uw.set.css.uwleonappbr.databinding.FragmentViewTestimonialsBinding;
import edu.tacoma.uw.set.css.uwleonappbr.testimonials.model.TestimonialViewModel;
import edu.tacoma.uw.set.css.uwleonappbr.testimonials.model.TestimonialsRecyclerViewAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class ViewTestimonialsFragment extends Fragment {

        private TestimonialViewModel testimonialViewModel;

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            testimonialViewModel = new ViewModelProvider(getActivity()).get(TestimonialViewModel.class);
            testimonialViewModel.getTestimonialsFromDatabase();
        }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_testimonials, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        @NonNull FragmentViewTestimonialsBinding binding = FragmentViewTestimonialsBinding.bind(getView());

        testimonialViewModel.addTestimonialListObserver(getViewLifecycleOwner(), testimonials -> {
            if (!testimonials.isEmpty()) {
                binding.layoutRoot.setAdapter(new TestimonialsRecyclerViewAdapter(testimonials));
            }
        });
    }
}