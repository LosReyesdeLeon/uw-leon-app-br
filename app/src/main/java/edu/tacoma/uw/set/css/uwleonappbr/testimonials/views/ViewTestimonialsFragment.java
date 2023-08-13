package edu.tacoma.uw.set.css.uwleonappbr.testimonials.views;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

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
        View rootView = inflater.inflate(R.layout.fragment_view_testimonials, container, false);

        FloatingActionButton fabButton = rootView.findViewById(R.id.fab_button);
        fabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Open the Add Testimonial", Snackbar.LENGTH_SHORT).show();
                Navigation.findNavController(view)
                        .navigate(R.id.action_viewTestimonialsFragment_to_submitStudentInfoFragment);
            }

        });

        return rootView;
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