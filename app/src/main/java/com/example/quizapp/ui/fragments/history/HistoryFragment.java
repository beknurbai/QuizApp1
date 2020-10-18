package com.example.quizapp.ui.fragments.history;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.quizapp.R;
import com.example.quizapp.adapters.HistoryAdapter;
import com.example.quizapp.databinding.HistoryFragmentBinding;

public class HistoryFragment extends Fragment {

    private HistoryViewModel mViewModel;
    private HistoryFragmentBinding binding;
    private LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
    private HistoryAdapter adapter = new HistoryAdapter();

    public static HistoryFragment newInstance() {
        return new HistoryFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.history_fragment, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(HistoryViewModel.class);
        binding.recyclerview.setLayoutManager(linearLayoutManager);
        binding.recyclerview.setAdapter(adapter);
        mViewModel.mutableLiveData.observeForever(historyResModels -> adapter.addData(historyResModels));
    }

    @Override
    public void onResume() {
        super.onResume();
        mViewModel.upData();
    }

}