package com.example.quizapp.ui.activities.result;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.quizapp.App;
import com.example.quizapp.models.HistoryResModel;
import com.example.quizapp.models.ResultQuiz;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ResultViewModel extends ViewModel {



    public void getResult(ResultQuiz resultQuiz) {
        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
        String formattedDate = df.format(c);
        App.getInstance().dataBase.resultDao().insert(new HistoryResModel(
                resultQuiz.getCorrectAns(),
                resultQuiz.getCategory(),
                resultQuiz.getDifficulty(),
                resultQuiz.getAnswers()
                ,
                formattedDate

        ));

    }
}
