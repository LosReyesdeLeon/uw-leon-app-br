package edu.tacoma.uw.set.css.uwleonappbr.news.views;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.tacoma.uw.set.css.uwleonappbr.R;
import edu.tacoma.uw.set.css.uwleonappbr.databinding.FragmentViewNewsBinding;
import edu.tacoma.uw.set.css.uwleonappbr.news.model.ArticleViewModel;
import edu.tacoma.uw.set.css.uwleonappbr.news.model.ArticlesRecyclerViewAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class ViewNewsFragment extends Fragment {

    private ArticleViewModel mModel;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mModel = new ViewModelProvider(getActivity()).get(ArticleViewModel.class);
        mModel.getNews();
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_view_news, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        @NonNull FragmentViewNewsBinding binding = FragmentViewNewsBinding.bind(view);
        mModel.addNewsArticleListObserver(getViewLifecycleOwner(), articleView -> {
            if (!articleView.isEmpty()) {
                // Set the adapter for the RecyclerView
                binding.layoutRoot.setAdapter(new ArticlesRecyclerViewAdapter(articleView));
            }
        });
    }
}