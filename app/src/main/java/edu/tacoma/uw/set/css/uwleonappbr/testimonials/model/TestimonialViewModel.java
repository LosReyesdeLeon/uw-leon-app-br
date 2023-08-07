package edu.tacoma.uw.set.css.uwleonappbr.testimonials.model;

import android.app.Application;
import android.os.Bundle;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.AndroidViewModel;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.Charset;
import java.util.Objects;

import java.util.ArrayList;

import edu.tacoma.uw.set.css.uwleonappbr.R;

class TestimonialViewModel extends AppCompatActivity {
  
    private ArrayList<Testimonial> testimonialsList = new ArrayList<>();
  
    private MutableLiveData<JSONObject> mResponse;
  
    public TestimonialViewModel(@NonNull Application application) {
        super(application);
        mResponse = new MutableLiveData<>();
        mResponse.setValue(new JSONObject());
    }

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

    public void addResponseObserver(@NonNull LifecycleOwner owner,
                                    @NonNull Observer<? super JSONObject> observer) {
        mResponse.observe(owner, observer);
    }

    public void addTestimonialToDatabase(Testimonial testimonial) {
        String url = "https://students.washington.edu/dpeevy/add_testimonial.php";
        JSONObject body = new JSONObject();
        try {
            body.put("student_id", testimonial.getStudentID());
            body.put("student_name", testimonial.getStudentName());
            body.put("student_campus", testimonial.getStudentCampus());
            body.put("student_major", testimonial.getStudentMajor());
            body.put("program_quarter", testimonial.getProgramQuarter());
            body.put("testimonial_title", testimonial.getTestimonialTitle());
            body.put("testimonial_content", testimonial.getTestimonialContent());
            Log.i(getClass().toString(), "addTestimonialToDatabase: body made without error.");
        } catch (JSONException e) {
            Log.e("JSONException",
                    "TestimonialViewModel.addTestimonialToDatabase(): Error putting element to testimonial.");
        }

        Request request = new JsonObjectRequest(
                Request.Method.POST,
                url,
                body,
                mResponse::setValue,
                this::handleError);
        Log.i(getClass().toString(), "addTestimonialToDatabase: request constructed.");

        request.setRetryPolicy(new DefaultRetryPolicy(10_000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        Volley.newRequestQueue(getApplication().getApplicationContext()).add(request);
        Log.i(getClass().toString(), "addTestimonialToDatabase: request made with volley.");
    }

    private void handleError (final VolleyError error) {
        if (Objects.isNull(error.networkResponse)) {
            try {
                mResponse.setValue(new JSONObject("{" +
                        "error:\"" + error.getMessage() + "\"}"));
            } catch (JSONException e) {
                Log.e("JSON PARSE", "JSON Parse Error in handleError");
            }
        } else {
            String data = new String(error.networkResponse.data, Charset.defaultCharset())
                    .replace('\"','\'');
            try {
                mResponse.setValue(new JSONObject("{" + "code:" + error.networkResponse.statusCode + ",data:\"" + data + "\"}"));
            } catch (JSONException e) {
                Log.e("JSON PARSE", "JSON Parse Exception in handleError");
            }
        }
    }

}
