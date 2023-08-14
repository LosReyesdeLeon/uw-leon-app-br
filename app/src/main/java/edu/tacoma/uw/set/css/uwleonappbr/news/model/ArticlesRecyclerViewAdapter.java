package edu.tacoma.uw.set.css.uwleonappbr.news.model;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;


import edu.tacoma.uw.set.css.uwleonappbr.R;
import edu.tacoma.uw.set.css.uwleonappbr.databinding.FragmentArticleDetailBinding;


public class ArticlesRecyclerViewAdapter extends RecyclerView.Adapter<ArticlesRecyclerViewAdapter.ViewHolder> {


    private final List<Article> mValues;
    public ArticlesRecyclerViewAdapter(List<Article> newsArticleList) {mValues = newsArticleList;}

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.fragment_article_detail, parent, false));
    }
<<<<<<< Updated upstream
=======
    @SuppressLint("QueryPermissionsNeeded")
    public void setItem(final Article item) {
        mItem = item;
        Glide.with(binding.image)
                .load(item.getImage())
                .override(550,400)
                .centerCrop()
                .into(binding.image);
>>>>>>> Stashed changes

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.setItem(mValues.get(position));
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public @NonNull FragmentArticleDetailBinding binding;
        public Article mItem;
        public ViewHolder(View view) {
            super(view);
            mView = view;
            binding = FragmentArticleDetailBinding.bind(view);
        }
        @SuppressLint("QueryPermissionsNeeded")
        public void setItem(final Article item) {
            mItem = item;
            Glide.with(binding.image)
                    .load(item.getImage())
                    .fitCenter() // scale to fit entire image within ImageView
                    .into(binding.image);


            Spanned result;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                result = Html.fromHtml(item.getName(), Html.FROM_HTML_MODE_LEGACY);
            } else {
                result = Html.fromHtml(item.getName());
            }

            binding.name.setText(result);
            binding.date.setText(item.getDate().toString());
            binding.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String internetLink = item.getLink();
                    if (internetLink != null && !internetLink.isEmpty()) {
                        Uri webpage = Uri.parse(internetLink);
                        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
                        view.getContext().startActivity(intent);
                    }
                }



            });
        }


    }
}