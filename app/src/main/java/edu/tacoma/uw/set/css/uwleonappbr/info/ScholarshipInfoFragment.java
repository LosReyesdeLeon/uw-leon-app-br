package edu.tacoma.uw.set.css.uwleonappbr.info;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import edu.tacoma.uw.set.css.uwleonappbr.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ScholarshipInfoFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_scholarship_info, container, false);

        Button bookAppointmentButton = view.findViewById(R.id.book_appointment_button);

        bookAppointmentButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String url = "https://www.tacoma.uw.edu/oga/sa";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        return view;
    }
}