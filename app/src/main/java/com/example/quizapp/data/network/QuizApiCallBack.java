package com.example.quizapp.data.network;

import com.example.quizapp.core.IBaseCallBack;
import com.example.quizapp.models.QuizResponse;
import com.example.quizapp.models.TriviaCategories;

public interface QuizApiCallBack {
   interface Questions extends IBaseCallBack<QuizResponse> {
       void onSuccess(QuizResponse quizResponse);

       void onFailure(Throwable throwable);
   }

   interface Categories {
       void onSuccess(TriviaCategories categories);

       void onFailure(Throwable throwable);
   }
}
