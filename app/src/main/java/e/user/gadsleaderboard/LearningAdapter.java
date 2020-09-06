package e.user.gadsleaderboard;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class LearningAdapter extends RecyclerView.Adapter<LearningAdapter.LearnersViewHolder>{

    Context context;
    List<LearningModel> learnersList;

    public LearningAdapter(Context context, List<LearningModel> learnersList) {
        this.context = context;
        this.learnersList = learnersList;
    }

    @NonNull
    @Override
    public LearnersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View learnersView = LayoutInflater.from(context)
                .inflate(R.layout.leaders_list_items, parent, false);
        return new LearnersViewHolder(learnersView);
    }

    @Override
    public void onBindViewHolder(@NonNull LearnersViewHolder holder, int position) {
        holder.tvName.setText(learnersList.get(position).getName());
        holder.tvLearningHoursAndCountry.setText(learnersList.get(position).getHours() +
                "Learning hours, " + learnersList.get(position).getCountry());
        Glide.with(context).load(learnersList.get(position).getBadgeUrl())
                .centerCrop().into(holder.budge);
    }

    @Override
    public int getItemCount() {
        return learnersList.size();
    }

    public class LearnersViewHolder extends RecyclerView.ViewHolder{
        ImageView budge;
        TextView tvName;
        TextView tvLearningHoursAndCountry;

        public LearnersViewHolder(@NonNull View itemView) {
            super(itemView);
            budge = itemView.findViewById(R.id.buldge);
            tvName = itemView.findViewById(R.id.name);
            tvLearningHoursAndCountry = itemView.findViewById(R.id.country);

        }
    }
}
