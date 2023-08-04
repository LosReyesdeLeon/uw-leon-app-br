package edu.tacoma.uw.set.css.uwleonappbr.testimonials.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import edu.tacoma.uw.set.css.uwleonappbr.R;

public class TestimonialsRecyclerViewAdapter
        extends RecyclerView.Adapter<TestimonialsRecyclerViewAdapter.MyViewHolder> {

    Context context;
    ArrayList<Testimonial> testimonialsList;

    public TestimonialsRecyclerViewAdapter(Context context, ArrayList<Testimonial> testimonialsList) {
        this.context = context;
        this.testimonialsList = testimonialsList;
    }
    @NonNull
    @Override
    public TestimonialsRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //This inflate the layout ( giving a look to our rows)
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.fragment_testimonial_detail,parent, false);

        return new TestimonialsRecyclerViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TestimonialsRecyclerViewAdapter.MyViewHolder holder, int position) {
        //assigning values to the views we created in the layout file
        // based on the position of the recycler view
        holder.studentName.setText(testimonialsList.get(position).getStudentName());
        holder.campus.setText(testimonialsList.get(position).getStudentCampus());
        holder.quarter.setText(testimonialsList.get(position).getProgramQuarter());
        holder.studentmajor.setText(testimonialsList.get(position).getStudentMajor());
        holder.titleT.setText(testimonialsList.get(position).getTestimonialTitle());
        holder.contentT.setText(testimonialsList.get(position).getTestimonialContent());
        holder.studentImageView.setImageResource(testimonialsList.get(position).getImage());



    }

    @Override
    public int getItemCount() {
        // recycler view wants to know number of items in our file
        return testimonialsList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        //similar to on create view method

        ImageView studentImageView;
        TextView studentName, campus, quarter, titleT, contentT,studentmajor;


        public MyViewHolder(@NonNull View itemView) {

            super(itemView);

            studentImageView = itemView.findViewById(R.id.studentImage);
            studentName = itemView.findViewById(R.id.studenName);
            campus = itemView.findViewById(R.id.campus);
            quarter = itemView.findViewById(R.id.quarter);
            studentmajor = itemView.findViewById(R.id.uw_major);
            titleT = itemView.findViewById(R.id.titleT);
            contentT = itemView.findViewById(R.id.contentT);

        }
    }
}
