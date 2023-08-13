package edu.tacoma.uw.set.css.uwleonappbr.testimonials.model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import edu.tacoma.uw.set.css.uwleonappbr.R;
import edu.tacoma.uw.set.css.uwleonappbr.databinding.FragmentTestimonialBinding;

public class TestimonialsRecyclerViewAdapter
        extends RecyclerView.Adapter<TestimonialsRecyclerViewAdapter.ViewHolder> {

    private final List<Testimonial> testimonials;

    public TestimonialsRecyclerViewAdapter(List<Testimonial> testimonials) {
        this.testimonials = testimonials;
    }

    @NonNull
    @Override
    public TestimonialsRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_testimonial, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TestimonialsRecyclerViewAdapter.ViewHolder holder, int position) {
        holder.setItem(testimonials.get(position));
    }

    @Override
    public int getItemCount() {
        return testimonials.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public final View mView;

        public FragmentTestimonialBinding mBinding;

        public Testimonial mItem;

        public ViewHolder(@NonNull View view) {
            super(view);
            mView = view;
            mBinding = FragmentTestimonialBinding.bind(view);
        }

        public void setItem(final Testimonial item) {
            mItem = item;
            mBinding.studentName.setText(item.getStudentName());
            mBinding.programQuarter.setText(item.getProgramQuarter());
            mBinding.studentCampus.setText(item.getStudentCampus());
            mBinding.studentMajor.setText(item.getStudentMajor());
            mBinding.testimonialTitle.setText(item.getTestimonialTitle());
            mBinding.testimonialContent.setText(item.getTestimonialContent());
        }
    }
}
