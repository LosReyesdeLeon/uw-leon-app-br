package edu.tacoma.uw.set.css.uwleonappbr.testimonials.model;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TestimonialViewModel extends AndroidViewModel {

    private MutableLiveData<JSONObject> mResponse;

    private MutableLiveData<List<Testimonial>> mTestimonials;

    public TestimonialViewModel(@NonNull Application application) {
        super(application);
        mResponse = new MutableLiveData<>();
        mResponse.setValue(new JSONObject());
        mTestimonials = new MutableLiveData<>();
        mTestimonials.setValue(new ArrayList<>());
    }

    public void addResponseObserver(@NonNull LifecycleOwner owner,
                                    @NonNull Observer<? super JSONObject> observer) {
        mResponse.observe(owner, observer);
    }

    public void addTestimonialListObserver(@NonNull LifecycleOwner owner,
                                           @NonNull Observer<? super List<Testimonial>> observer) {
        mTestimonials.observe(owner, observer);
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

    public void getTestimonialsFromDatabase() {
        String url = "https://students.washington.edu/dpeevy/get_testimonials.php";

        Request request = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                this::handleResult,
                this::handleError);

        request.setRetryPolicy(new DefaultRetryPolicy(
                10_000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        Volley.newRequestQueue(getApplication().getApplicationContext()).add(request);
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

    /**
     * This is called by the GET request.
     * @param result The return from get_testimonials.php.
     */
    private void handleResult(final JSONObject result) {
        try {
            String data = result.getString("testimonials");
            JSONArray arr = new JSONArray(data);
            for (int i = 0; i < arr.length(); i++) {
                JSONObject object = arr.getJSONObject(i);

                int id = Integer.parseInt(object.getString("student_id"));
                String name = object.getString("student_name");
                String campus = object.getString("student_campus");
                String major = object.getString("student_major");
                String quarter = object.getString("program_quarter");
                String title = object.getString("testimonial_title");
                String content = object.getString("testimonial_content");

                Testimonial testimonial = new Testimonial(id, name, campus, major, quarter, title, content);

                mTestimonials.getValue().add(testimonial);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("ERROR!", "handleResult: JSONException from getTestimonialsFromDatabase. Possible JSON Parse Error.");
        }
        mTestimonials.setValue(mTestimonials.getValue());
    }

}
