package e.user.gadsleaderboard;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.recyclerview.*;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import e.user.gadsleaderboard.SkillIqModel;

public class SkillAdapter extends RecyclerView.Adapter<SkillAdapter.SkillViewHolder>{

    Context context;
    List<SkillIqModel> skillList;

    public SkillAdapter(Context context, List<SkillIqModel> skillList) {
        this.context = context;
        this.skillList = skillList;
    }

    @NonNull
    @Override
    public SkillViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View skillView = LayoutInflater.from(context)
                .inflate(R.layout.leaders_list_items, parent, false);
        return new SkillViewHolder(skillView);
    }

    @Override
    public void onBindViewHolder(@NonNull SkillViewHolder holder, int position) {
        holder.tvName.setText(skillList.get(position).getName());
        holder.tvLearningHoursAndCountry
                .setText(skillList.get(position)
                        .getScore() + "Skill IQ Score, " + skillList.get(position).getCountry());
        Glide.with(context).load(skillList.get(position).getBadgeUrl())
                .centerCrop().into(holder.budge);
    }

    @Override
    public int getItemCount() {
        return skillList.size();
    }

    public class SkillViewHolder extends RecyclerView.ViewHolder{
        ImageView budge;
        TextView tvName;
        TextView tvLearningHoursAndCountry;

        public SkillViewHolder(@NonNull View itemView) {
            super(itemView);
            budge = itemView.findViewById(R.id.buldge);
            tvName = itemView.findViewById(R.id.name);
            tvLearningHoursAndCountry = itemView.findViewById(R.id.country);

        }
    }
}
