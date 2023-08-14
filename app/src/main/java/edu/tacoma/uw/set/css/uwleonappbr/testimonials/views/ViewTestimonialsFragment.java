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

import edu.tacoma.uw.set.css.uwleonappbr.R;
import edu.tacoma.uw.set.css.uwleonappbr.databinding.FragmentViewTestimonialsBinding;
import edu.tacoma.uw.set.css.uwleonappbr.testimonials.model.TestimonialViewModel;
import edu.tacoma.uw.set.css.uwleonappbr.testimonials.model.TestimonialsRecyclerViewAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class ViewTestimonialsFragment extends Fragment {

        private TestimonialViewModel testimonialViewModel;

        private FragmentViewTestimonialsBinding mBinding;

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
        mBinding = FragmentViewTestimonialsBinding.inflate(inflater, container, false);

        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        @NonNull FragmentViewTestimonialsBinding binding = FragmentViewTestimonialsBinding.bind(getView());
        mBinding.addTestimonialButton.setOnClickListener(button -> goToSubmitStudentInfo());
        testimonialViewModel.addTestimonialListObserver(getViewLifecycleOwner(), testimonials -> {
            if (!testimonials.isEmpty()) {
                binding.layoutRoot.setAdapter(new TestimonialsRecyclerViewAdapter(testimonials));
            }
        });
    }

    private void goToSubmitStudentInfo() {
        Navigation.findNavController(requireView())
                .navigate(R.id.action_viewTestimonialsFragment_to_submitStudentInfoFragment);
    }
}