package edu.tacoma.uw.set.css.uwleonappbr.testimonials.model;

import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.AndroidViewModel;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import edu.tacoma.uw.set.css.uwleonappbr.R;

class TestimonialView extends AppCompatActivity {
    ArrayList<Testimonial> testimonialsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_view_testimonials);

        RecyclerView recyclerView =findViewById(R.id.testimonialView);

        setTestimonialsList();

        //attracted above list to adapter
        TestimonialsRecyclerViewAdapter adapter = new TestimonialsRecyclerViewAdapter(this,testimonialsList);

        //put adapter on recyclerView
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


    }

    /**
     * Set up data for each Testimonial
     */
    private void setTestimonialsList(){

        //grab infor from strings.xml file, put into the String[]
        int[] studentIds = getResources().getIntArray(R.array.student_ids);
        String[] studentNames = getResources().getStringArray(R.array.student_names);
        String[] uwQuarters = getResources().getStringArray(R.array.uw_quarters);
        int[]   uwYear = getResources().getIntArray(R.array.uw_years);
        String[] uwMajor = getResources().getStringArray(R.array.uw_majors);
        String[] uwCampus = getResources().getStringArray(R.array.uw_campuses);
        String[] titleTestimonial = getResources().getStringArray(R.array.testimonial_titles);
        String[] contentTestimonial = getResources().getStringArray(R.array.testimonial_contents);


        // loop through all the name and put Testimonial Object inside the testimonialList
        for(int i = 0; i < studentNames.length; i++){
            testimonialsList.add(new Testimonial(
                    studentIds[i],studentNames[i],
                    uwCampus[i],uwMajor[i],
                    uwQuarters[i],uwYear[i],R.drawable.person_icon));
        }


    }

}
