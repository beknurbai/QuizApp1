package com.example.quizapp.ui.fragments.settings;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.quizapp.R;
import com.example.quizapp.adapters.AdapterTheme;
import com.example.quizapp.databinding.SettingsFragmentBinding;
import com.example.quizapp.interfaces.OnRadioBtnClick;

public class SettingsFragment extends Fragment {
    private SettingsViewModel mViewModel;
    private SettingsFragmentBinding binding;
    private AdapterTheme adapterTheme = new AdapterTheme();

    public static SettingsFragment newInstance() {
        return new SettingsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.settings_fragment, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(SettingsViewModel.class);
        binding.recView.setAdapter(adapterTheme);
        mViewModel.mutableLiveData.observeForever(themeModels -> adapterTheme.updateList(themeModels));
        mViewModel.show.observe(requireActivity(), integer -> requireActivity().setTheme(integer));
        adapterTheme.setRadioBtnClick(new OnRadioBtnClick() {
            @Override
            public void onClick(int pos) {
                mViewModel.onChangeTheme(pos);
                Intent intent = requireActivity().getIntent();
                requireActivity().finish();
                startActivity(intent);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        binding.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("ololo", "onClick: ");
                mViewModel.deleteAllHistory();
            }
        });
    }
}