package com.example.quizapp.ui.activities.question;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.SnapHelper;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;

import com.example.quizapp.App;
import com.example.quizapp.R;
import com.example.quizapp.adapters.QuizAdapter;
import com.example.quizapp.databinding.ActivityQuestionBinding;
import com.example.quizapp.interfaces.OnResultAnswerClickListener;
import com.example.quizapp.ui.activities.result.ResultActivity;
import com.example.quizapp.utilits.CustomGridLayoutManager;


import static com.example.quizapp.utilits.Config.CORRECT_ANSWER;
import static com.example.quizapp.utilits.Config.CORRECT_ANSWER_AND_FINAL_ANSWER;
import static com.example.quizapp.utilits.Config.RESULT_CATEGORY_KEY;
import static com.example.quizapp.utilits.Config.RESULT_DIFFICULTY_KEY;
import static com.example.quizapp.utilits.Config.RESULT_QUESTIONS_AMOUNT_KEY;
import static com.example.quizapp.utilits.Config.RESULT_QUIZ_KEY;
import static com.example.quizapp.utilits.Config.RESULT_TITLE_KEY;
import static com.example.quizapp.utilits.Config.WRONG_ANSWER_AND_FINAL_ANSWER;

public class QuestionActivity extends AppCompatActivity implements OnResultAnswerClickListener {
    private int questionsAmount;
    private int category;
    private String title, difficulty = "Any type";
    private ActivityQuestionBinding activityQuestionsBinding;
    private QuizAdapter quizAdapter;
    private QuestionViewModel mViewModel;
    private CustomGridLayoutManager customGridLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(App.getInstance().getPreferences().getTheme());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        init();
        setArg();
        observeForever();
        setListener();
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.setTheme(App.getInstance().getPreferences().getTheme());
    }

    private void setListener() {
        activityQuestionsBinding.path.setOnClickListener(v -> onBackPressed());
        activityQuestionsBinding.skip.setOnClickListener(v -> scroll());
    }

    private void setArg() {
        mViewModel.setAmountQuestions(questionsAmount, category, difficulty);
        activityQuestionsBinding.recyclerview.setAdapter(quizAdapter);
        activityQuestionsBinding.recyclerview.setLayoutManager(customGridLayoutManager);
        customGridLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        activityQuestionsBinding.progressBarQuestionActivity.setMax(questionsAmount);
        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(activityQuestionsBinding.recyclerview);
        quizAdapter.setAnswerClick(this);
        activityQuestionsBinding.progressBarQuestionActivity.setProgress(0);
        activityQuestionsBinding.categoryTitle.setText(title);
    }

    private void init() {
        mViewModel = ViewModelProviders.of(this).get(QuestionViewModel.class);
        activityQuestionsBinding = DataBindingUtil.setContentView(this, R.layout.activity_question);
        quizAdapter = new QuizAdapter();
        if (getIntent() != null) {
            questionsAmount = getIntent().getIntExtra(RESULT_QUESTIONS_AMOUNT_KEY, 10);
            category = getIntent().getIntExtra(RESULT_CATEGORY_KEY, 99);
            difficulty = getIntent().getStringExtra(RESULT_DIFFICULTY_KEY);
            title = getIntent().getStringExtra(RESULT_TITLE_KEY);
        }
        customGridLayoutManager = new CustomGridLayoutManager(this);
    }

    private void observeForever() {
        mViewModel.listQuestions.observeForever(quizModels -> quizAdapter.setQuestions(quizModels));
        mViewModel.result.observeForever(resultQuiz -> {
            Log.e("ololo", "observeForever: " + resultQuiz.getDifficulty());
            Intent intent = new Intent(QuestionActivity.this, ResultActivity.class);
            intent.putExtra(RESULT_QUIZ_KEY, resultQuiz);
            startActivity(intent);
            finish();
        });

    }

    @Override
    public void onClick(int result) {
        if (result == CORRECT_ANSWER_AND_FINAL_ANSWER)
            mViewModel.onLastAnswerClick(true, title, difficulty);
        else if (result == WRONG_ANSWER_AND_FINAL_ANSWER)
            mViewModel.onLastAnswerClick(false, title, difficulty);
        else if (result == CORRECT_ANSWER)
            mViewModel.onAnswerClick(true);
        else mViewModel.onAnswerClick(false);

        new CountDownTimer(500, 500) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                scroll();
            }

        }.start();
    }

    void scroll() {
        activityQuestionsBinding.progressBarQuestionActivity.setProgress(activityQuestionsBinding.progressBarQuestionActivity.getProgress() + 1);
        activityQuestionsBinding.recyclerview.scrollToPosition(activityQuestionsBinding.progressBarQuestionActivity.getProgress());
    }

    @Override
    public void onBackPressed() {
        Log.e("ololo", "onBackPressed: " + activityQuestionsBinding.progressBarQuestionActivity.getProgress());
        if (activityQuestionsBinding.progressBarQuestionActivity.getProgress() > 0) {
            activityQuestionsBinding.progressBarQuestionActivity.setProgress(activityQuestionsBinding.progressBarQuestionActivity.getProgress() - 1);
            activityQuestionsBinding.recyclerview.scrollToPosition(activityQuestionsBinding.progressBarQuestionActivity.getProgress());
        } else {
            super.onBackPressed();
        }
    }

}