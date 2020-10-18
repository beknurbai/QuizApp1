package com.example.quizapp.ui.fragments.main;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.quizapp.R;
import com.example.quizapp.databinding.MainFragmentBinding;
import com.example.quizapp.models.TriviaCategory;
import com.example.quizapp.ui.activities.question.QuestionActivity;

import java.util.ArrayList;
import java.util.List;

import static com.example.quizapp.utilits.Config.RESULT_CATEGORY_KEY;
import static com.example.quizapp.utilits.Config.RESULT_DIFFICULTY_KEY;
import static com.example.quizapp.utilits.Config.RESULT_QUESTIONS_AMOUNT_KEY;
import static com.example.quizapp.utilits.Config.RESULT_TITLE_KEY;

public class MainFragment extends Fragment {

    private MainViewModel mViewModel;
    private int category = 99;
    private String stringCategory = "Any category";
    private String difficulty = "Any type";
    private MainFragmentBinding binding;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.main_fragment, container, false);
        return binding.getRoot();
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        observeForever();
        seekBarInitialisation();
        setListener();
        mViewModel.updateCategory();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void setListener() {
        binding.buttonMainStart.setOnClickListener(v -> {
            Intent intent = new Intent(requireActivity(), QuestionActivity.class);
            intent.putExtra(RESULT_QUESTIONS_AMOUNT_KEY, mViewModel.progressBarSuccess.getValue());
            intent.putExtra(RESULT_CATEGORY_KEY, category);
            intent.putExtra(RESULT_DIFFICULTY_KEY, difficulty);
            intent.putExtra(RESULT_TITLE_KEY, stringCategory);
            startActivity(intent);
        });
        binding.amountSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                mViewModel.progressBarSuccess.setValue(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        binding.spinnerCategoryDifficulty.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                difficulty = getResources().getStringArray(R.array.spinner_category_example)[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void seekBarInitialisation() {
        binding.amountSeekBar.setMax(20);
        binding.amountSeekBar.setProgress(0);
    }

    private void observeForever() {
        mViewModel.progressBarSuccess.observeForever(integer -> {
            binding.amount.setText(String.valueOf(integer));
            binding.amountSeekBar.setProgress(integer);
        });
        mViewModel.triviaCategories.observeForever(triviaCategories -> {
            List<TriviaCategory> categoryList = triviaCategories.getTriviaCategories();
            TriviaCategory defCategory = new TriviaCategory();
            defCategory.setName(getString(R.string.def_value_for_cat_spinner));
            defCategory.setId(99);
            categoryList.add(10, defCategory);
            List<String> name_category = new ArrayList<>();
            for (TriviaCategory triviaCategory : categoryList)
                name_category.add(triviaCategory.getName());

            ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), R.layout.support_simple_spinner_dropdown_item, name_category);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            binding.spinnerMainCategory.setAdapter(adapter);
            binding.spinnerMainCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    category = triviaCategories.getTriviaCategories().get(position).getId();
                    stringCategory = triviaCategories.getTriviaCategories().get(position).getName();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        });
    }
}