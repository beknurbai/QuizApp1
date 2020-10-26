package com.example.quizapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizapp.R;
import com.example.quizapp.databinding.ItemColorThemeBinding;
import com.example.quizapp.interfaces.OnRadioBtnClick;
import com.example.quizapp.models.ThemeModel;

import java.util.ArrayList;
import java.util.List;

public class AdapterTheme extends RecyclerView.Adapter<AdapterTheme.ThemeHolder> {
    private List<ThemeModel> list = new ArrayList<>();
    private OnRadioBtnClick click;

    public void setRadioBtnClick(OnRadioBtnClick click) {
        this.click = click;
    }

    public void updateList(List<ThemeModel> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ThemeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemColorThemeBinding binding = ItemColorThemeBinding.bind(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_color_theme, parent, false));
        return new ThemeHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ThemeHolder holder, int position) {
        holder.binding(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ThemeHolder extends RecyclerView.ViewHolder {
        ItemColorThemeBinding item;

        public ThemeHolder(@NonNull ItemColorThemeBinding itemView) {
            super(itemView.getRoot());
            item = itemView;
            item.getRoot().setOnClickListener(view -> {
                    item.radioBtn.setChecked(!item.radioBtn.isChecked());
                    if (item.radioBtn.isChecked()) {
                        click.onClick(getAdapterPosition());
                    }
                    item.radioBtn.setOnCheckedChangeListener((compoundButton, isChecked) -> {
                        if (isChecked) click.onClick(getAdapterPosition());
                    });
            });
        }

        public void binding(ThemeModel themeModel) {
            item.imageTheme.setImageDrawable(ContextCompat.getDrawable(item.getRoot().getContext(), themeModel.getDefImage()));
            item.radioBtn.setChecked(themeModel.isCheckChange());
        }
    }
}
