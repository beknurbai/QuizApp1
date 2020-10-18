package com.example.quizapp.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizapp.R;
import com.example.quizapp.databinding.ItemHistoryBinding;
import com.example.quizapp.models.HistoryResModel;

import java.util.ArrayList;
import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {
    private List<HistoryResModel> resultList = new ArrayList<>();

    public void addData(List<HistoryResModel> resultList) {
        this.resultList = resultList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemHistoryBinding binding = ItemHistoryBinding.bind(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history, parent, false));
        return new HistoryViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
        holder.binding(resultList.get(position));
    }

    @Override
    public int getItemCount() {
        return resultList.size();
    }

    class HistoryViewHolder extends RecyclerView.ViewHolder {
        ItemHistoryBinding item;

        public HistoryViewHolder(@NonNull ItemHistoryBinding itemView) {
            super(itemView.getRoot());
            item = itemView;
        }

        public void binding(HistoryResModel resultQuiz) {
            item.textViewCategory.setText(resultQuiz.getCategory());
            item.textViewCorrectAns.setText(resultQuiz.getCorrectAns());
            item.textViewDifficulty.setText(resultQuiz.getDifficulty());
            item.textViewData.setText(resultQuiz.getData());
        }
    }
}
