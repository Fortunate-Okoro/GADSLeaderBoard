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

import e.user.gadsleaderboard.R;
import e.user.gadsleaderboard.SkillIqModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class SkillIqFragment extends Fragment {
    RecyclerView recyclerView;
    SkillAdapter skillAdapter;
    ProgressBar progressBar;
    TextView textView;
    Button reloadSkillBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_skill_iq, container, false);

        recyclerView = view.findViewById(R.id.rvSkillIqLeaders);
        progressBar = view.findViewById(R.id.progressBar);
        textView = view.findViewById(R.id.tvError);
        reloadSkillBtn = view.findViewById(R.id.reloadSkillBtn);
        getSkillIqLeaders();

        reloadSkillBtn.setOnClickListener(view1 -> {
            getSkillIqLeaders();
            progressBar.setVisibility(View.VISIBLE);
            textView.setVisibility(View.GONE);
        });
        return view;
    }

    private void getSkillIqLeaders() {
        ApiClient.getClient()
                .getSkillIq().enqueue(new Callback<List<SkillIqModel>>() {
            @Override
            public void onResponse(Call<List<SkillIqModel>> call, Response<List<SkillIqModel>> response) {
                if (response.isSuccessful()) {
                    List<SkillIqModel> learners = response.body();
                    skillAdapter = new SkillAdapter(getContext(), learners);
                    recyclerView.setAdapter(skillAdapter);
                    recyclerView.setLayoutManager(new
                            LinearLayoutManager(getContext()));
                    progressBar.setVisibility(View.GONE);
                    reloadSkillBtn.setVisibility(View.GONE);
                    Log.d("Tag", "onResponse:Skills" + response.body().size());
                }
            }

            @Override
            public void onFailure(Call<List<SkillIqModel>> call, Throwable t) {
                textView.setText(t.getMessage());
                textView.setVisibility(View.VISIBLE);
                reloadSkillBtn.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
                Log.d("Tag", "onFailure: " + t.getMessage());
            }
        });

    }
}