package edu.tacoma.uw.set.css.uwleonappbr.news.model;

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

public class ArticleViewModel extends AndroidViewModel {
    private MutableLiveData<JSONObject> mResponse;

    private MutableLiveData<List<Article>> mNewsArticleView;

    public ArticleViewModel(@NonNull Application application) {
        super(application);
        mResponse = new MutableLiveData<>();
        mResponse.setValue(new JSONObject());
        mNewsArticleView = new MutableLiveData<>();
        mNewsArticleView.setValue(new ArrayList<>());
    }

    public void addResponseObserver(@NonNull LifecycleOwner owner,
                                    @NonNull Observer<? super JSONObject> observer) {
        mResponse.observe(owner, observer);
    }

    private void handleError(final VolleyError error) {
        if (Objects.isNull(error.networkResponse)) {
            try {
                mResponse.setValue(new JSONObject("{" +
                        "error:\"" + error.getMessage() +
                        "\"}"));
            } catch (JSONException e) {
                Log.e("JSON PARSE", "JSON Parse Error in handleError");
            }
        } else {
            String data = new String(error.networkResponse.data, Charset.defaultCharset())
                    .replace('\"', '\'');
            try {
                mResponse.setValue(new JSONObject("{" +
                        "code:" + error.networkResponse.statusCode +
                        ", data:\"" + data +
                        "\"}"));
            } catch (JSONException e) {
                Log.e("JSON PARSE", "JSON Parse Error in handleError");
            }
        }
    }

    public void addNewsArticleListObserver(@NonNull LifecycleOwner owner,
                                           @NonNull Observer<? super List<Article>> observer) {
        mNewsArticleView.observe(owner, observer);
    }

    public void getNews() {
        String url =
                "https://students.washington.edu/ianlist/get_newsArticle.php";
        Request<JSONObject> request = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null, //no body for this get request
                this::handleResult,
                this::handleError);
        request.setRetryPolicy(new DefaultRetryPolicy(
                10_000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        //Instantiate the RequestQueue and add the request to the queue
        Volley.newRequestQueue(getApplication().getApplicationContext())
                .add(request);
    }

    private void handleResult(final JSONObject result) {
        try {
            String data = result.getString("articles");
            JSONArray arr = new JSONArray(data);
            for (int i = 0; i < arr.length(); i++) {
                JSONObject obj = arr.getJSONObject(i);
                Article article = new Article(
                        obj.getString(Article.NAME),
                        obj.getString(Article.IMAGE),
                        obj.getString(Article.DATE),
                        obj.getString(Article.LINK));
                Objects.requireNonNull(mNewsArticleView.getValue()).add(article);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("ERROR!", Objects.requireNonNull(e.getMessage()));
        }
        mNewsArticleView.setValue(mNewsArticleView.getValue());
    }
}

