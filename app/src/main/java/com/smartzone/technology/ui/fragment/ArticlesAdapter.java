package com.smartzone.technology.ui.fragment;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.smartzone.technology.R;
import com.smartzone.technology.databinding.CustomItemBinding;
import com.smartzone.technology.model.Result;

import java.util.ArrayList;

public class ArticlesAdapter extends RecyclerView.Adapter<ArticlesAdapter.ArticlesViewHolder> {
    ArrayList<Result> resultArrayList;
    Context context;


    public ArticlesAdapter(ArrayList<Result> resultArrayList, Context context) {
        this.resultArrayList = resultArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ArticlesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CustomItemBinding customItemBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.custom_item, parent, false);
        return new ArticlesViewHolder(customItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticlesViewHolder holder, final int position) {
        Result result = resultArrayList.get(position);
        holder.binding.textTitel.setText(result.getTitle());
        holder.binding.textCity.setText(result.getSection());
        holder.binding.textDate.setText(result.getPublishedDate());
        holder.binding.textWriter.setText(result.getByline());
        if (result.getMedia()!=null&&result.getMedia().size()>0&&result.getMedia().get(0).getMediaMetadata()!=null&&result.getMedia().get(0).getMediaMetadata().size()>0){
            Glide.with(context)
                    .load(result.getMedia().get(0).getMediaMetadata().get(0).getUrl())
                    .into(holder.binding.imagePost);
        }

    }

    @Override
    public int getItemCount() {
        Log.e("tag", resultArrayList.size() + "");
        return resultArrayList.size();
    }

    class ArticlesViewHolder extends RecyclerView.ViewHolder {
        CustomItemBinding binding;

        public ArticlesViewHolder(CustomItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

    }

    public interface OnItemClickListner {
        void onClickItem(int position);
    }


}
