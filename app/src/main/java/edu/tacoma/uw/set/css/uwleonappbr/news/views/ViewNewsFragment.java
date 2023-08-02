package edu.tacoma.uw.set.css.uwleonappbr.news.views;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.tacoma.uw.set.css.uwleonappbr.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ViewNewsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_news, container, false);
    }
}