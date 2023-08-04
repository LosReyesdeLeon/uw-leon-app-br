package edu.tacoma.uw.set.css.uwleonappbr.info;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import edu.tacoma.uw.set.css.uwleonappbr.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PreFlightInfoFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pre_flight_info, container, false);
    }
}