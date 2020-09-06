package e.user.gadsleaderboard;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import e.user.gadsleaderboard.LearningModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class LearningLeadersFragment extends Fragment {
    RecyclerView recyclerView;
    LearningAdapter learningAdapter;
    ProgressBar progressBar;
    TextView textView;
    Button retryBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_learning_leaders, container, false);

        recyclerView = view.findViewById(R.id.rvLearningLeaders);
        progressBar = view.findViewById(R.id.progressBar);
        textView = view.findViewById(R.id.tvError);
        retryBtn = view.findViewById(R.id.retryBtn);
        getLearningLeaders();

        retryBtn.setOnClickListener(view1 -> {
            getLearningLeaders();
            progressBar.setVisibility(View.VISIBLE);
            textView.setVisibility(View.GONE);
        });
        return view;
    }

    private void getLearningLeaders() {
        ApiClient.getClient()
                .getLearningLeaders().enqueue(new Callback<List<LearningModel>>() {
            @Override
            public void onResponse(Call<List<LearningModel>> call, Response<List<LearningModel>> response) {
                if (response.isSuccessful()) {
                    List<LearningModel> learners = response.body();
                    learningAdapter = new LearningAdapter(getContext(), learners);
                    recyclerView.setAdapter(learningAdapter);
                    recyclerView.setLayoutManager(new
                            LinearLayoutManager(getContext()));
                    progressBar.setVisibility(View.GONE);
                    retryBtn.setVisibility(View.GONE);
                    Log.d("Tag", "onResponse:Learning" + response.body().size());
                }
            }

            @Override
            public void onFailure(Call<List<LearningModel>> call, Throwable t) {
                textView.setText(t.getMessage());
                textView.setVisibility(View.VISIBLE);
                retryBtn.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
                Log.d("Tag", "onFailure: " + t.getMessage());
            }
        });

    }
}