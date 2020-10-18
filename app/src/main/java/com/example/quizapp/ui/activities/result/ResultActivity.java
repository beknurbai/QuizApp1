package com.example.quizapp.ui.activities.result;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.example.quizapp.App;
import com.example.quizapp.R;
import com.example.quizapp.databinding.ActivityResultBinding;
import com.example.quizapp.models.ResultQuiz;

import static com.example.quizapp.utilits.Config.RESULT_QUIZ_KEY;

public class ResultActivity extends AppCompatActivity {
    private ResultQuiz resultQuiz;
    private ActivityResultBinding binding;
    private ResultViewModel viewModel;
    TextView dif, correct, result,category;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_AppCompat_Light_NoActionBar);
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_result);
        setContentView(R.layout.activity_result);
        Log.e("ololo", "ResultActivity: onCreate: ");
        init();
        if (getIntent() != null) getArg(getIntent());
    }

    private void getArg(Intent data) {
        resultQuiz = (ResultQuiz) data.getSerializableExtra(RESULT_QUIZ_KEY);
        binding.setModel(resultQuiz);
        binding.correctAns.setText(resultQuiz.getCategory());
        dif.setText(resultQuiz.getDifficulty());
        correct.setText(resultQuiz.getCorrectAns());
        result.setText(resultQuiz.getResultPercentage());
        category.setText(resultQuiz.getCategory());
        viewModel.getResult(resultQuiz);
        Log.e("jjjj", "getArg: " + resultQuiz.getResultPercentage());
    }

    public void onBtnClickFinish(View view) {
        finish();
    }

    public void init() {
        viewModel = ViewModelProviders.of(this).get(ResultViewModel.class);
        dif = findViewById(R.id.result_text_view_difficulty);
        correct = findViewById(R.id.result_text_view_correct_ans);
        result = findViewById(R.id.result_text_view_result);
        category = findViewById(R.id.result_text_view_category);
    }
}