package edu.tacoma.uw.set.css.uwleonappbr.info;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.tacoma.uw.set.css.uwleonappbr.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ScholarshipInfoFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_scholarship_info, container, false);
    }
}