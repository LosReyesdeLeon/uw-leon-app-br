package edu.tacoma.uw.set.css.uwleonappbr.testimonials.views;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.time.Year;
import java.util.Calendar;

import edu.tacoma.uw.set.css.uwleonappbr.R;
import edu.tacoma.uw.set.css.uwleonappbr.databinding.FragmentSubmitStudentInfoBinding;
import edu.tacoma.uw.set.css.uwleonappbr.testimonials.model.Testimonial;

/**
 * A simple {@link Fragment} subclass.
 */
public class SubmitStudentInfoFragment extends Fragment {

    private FragmentSubmitStudentInfoBinding mBinding;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentSubmitStudentInfoBinding.inflate(inflater, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mBinding.submitStudentInfoButton.setOnClickListener(button -> processStudentInfo());
    }

    private void processStudentInfo() {
        String name = mBinding.editNameField.getText().toString();
        int studentID = Integer.parseInt(mBinding.editStudentIDField.getText().toString());
        String campus = "";
        String season = "";
        if (getSelectedStringFromRadioButtonGroup(mBinding.campusButtonGroup) != null
                && getSelectedStringFromRadioButtonGroup(mBinding.seasonButtonGroup) != null) {
            campus = getSelectedStringFromRadioButtonGroup(mBinding.campusButtonGroup);
            season = getSelectedStringFromRadioButtonGroup(mBinding.seasonButtonGroup);
        } else {
            // Toasts will already be shown for these two. Just cancel so we don't submit an
            // error.
            return;
        }
        int year = Integer.parseInt(mBinding.editYearField.getText().toString());
        String major = mBinding.editMajorField.getText().toString();
        if (!hasErrors(name, studentID, year, major)) {
            // TODO: First I have to set up the navigation, and then I will program this to move on
            // to the next fragment.
            Navigation.findNavController(getView()).navigate(R.id.action_submitStudentInfoFragment_to_submitTestimonialFragment);
        }
        // If it has errors, the hasErrors method will print the toast. We just want to stop so we
        // don't process invalid information.
    }

    private boolean hasErrors(String name, int studentID, int year, String major) {
        if (name.isEmpty()) {
            Toast.makeText(getContext(),
                    "Name field may not be left blank.", Toast.LENGTH_SHORT).show();
            return true;
        }
        if (studentID < 999999) {
            Toast.makeText(getContext(),
                    "Student ID number must be 7 digits long.", Toast.LENGTH_SHORT).show();
            return true;
        }
        if (year < 2010) {
            Toast.makeText(getContext(),
                    "The UW León center was not open at that time.", Toast.LENGTH_SHORT).show();
            return true;
        }
        if (year > Calendar.getInstance().get(Calendar.YEAR)) {
            Toast.makeText(getContext(),
                    "You cannot submit a testimonial from the future.", Toast.LENGTH_SHORT).show();
            return true;
        }
        if (major.isEmpty()) {
            Toast.makeText(getContext(),
                    "Major field may not be left blank.", Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }

    private String getSelectedStringFromRadioButtonGroup(RadioGroup radioGroup) {
        int selectedId = radioGroup.getCheckedRadioButtonId();
        // If there is not a selected id.
        if (selectedId == -1) {
            return null;
        }
        RadioButton selectedButton = getActivity().findViewById(selectedId);
        return selectedButton.getText().toString();
    }

}