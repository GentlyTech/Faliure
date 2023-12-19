package com.yepdevelopment.failure.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.yepdevelopment.failure.R;
import com.yepdevelopment.failure.ViewModels.MainViewModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddCourseFragment extends Fragment {
    MainViewModel mainViewModel;
    NavController navController;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        navController = NavHostFragment.findNavController(this);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_course, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Button buttonCancelAddCourse = view.findViewById(R.id.buttonCancelAddCourse);
        Button buttonConfirmAddCourse = view.findViewById(R.id.buttonConfirmAddCourse);
        TextInputEditText editTextCourseStartDate = view.findViewById(R.id.editTextCourseStartDate);
        TextInputEditText editTextCourseEndDate = view.findViewById(R.id.editTextCourseEndDate);
        TextInputLayout editTextLayoutCourseStartDate = view.findViewById(R.id.editTextLayoutCourseStartDate);
        TextInputLayout editTextLayoutCourseEndDate = view.findViewById(R.id.editTextLayoutCourseEndDate);

        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy/MM/dd", Locale.CANADA); // TODO maybe make locale dynamic

        editTextLayoutCourseStartDate.setEndIconOnClickListener(v -> new DatePickerFragment((v2, year, month, day) -> {
            Calendar calendar = Calendar.getInstance();
            calendar.set(year, month, day);

            editTextCourseStartDate.setText(dateFormatter.format(calendar.getTime()));
            return null;
        }).show(getParentFragmentManager(), null));

        editTextLayoutCourseEndDate.setEndIconOnClickListener(v -> new DatePickerFragment((v2, year, month, day) -> {
            Calendar calendar = Calendar.getInstance();
            calendar.set(year, month, day);

            editTextCourseEndDate.setText(dateFormatter.format(calendar.getTime()));
            return null;
        }).show(getParentFragmentManager(), null));

        buttonCancelAddCourse.setOnClickListener(button -> navController.popBackStack());
    }
}