package e.user.gadsleaderboard;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import e.user.gadsleaderboard.LearningLeadersFragment;
import e.user.gadsleaderboard.SkillIqFragment;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    public ViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if (position == 0) {
            fragment = new LearningLeadersFragment();
        }else if (position == 1){
            fragment = new SkillIqFragment();
        }
        return fragment;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = null;
        if (position == 0){
            title = "Learning Leaders";
        }else if (position == 1) {
            title = "Skill IQ Leaders";
        }
        return title;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
