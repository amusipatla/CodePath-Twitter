package fragments;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by amusipatla on 7/3/17.
 */

public class TweetsPagerAdapter extends FragmentPagerAdapter {

    private HomeTimelineFragment homeTimelineFragment;
    private MentionsTimelineFragment mentionsTimelineFragment;

    private String tabTitles[] = new String[] {"Home", "Mentions"};
    Context context;

    public TweetsPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    //return total number of fragments


    @Override
    public int getCount() {
        return 2;
    }

    //return fragment to use depending on position

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            homeTimelineFragment = getHomeTimelineFragment();
            return homeTimelineFragment;
        } else if (position == 1) {
            mentionsTimelineFragment = getMentionsTimelineFragment();
            return mentionsTimelineFragment;
        } else {
            return null;
        }
    }


    private HomeTimelineFragment getHomeTimelineFragment(){
        if (homeTimelineFragment == null) {
            homeTimelineFragment = new HomeTimelineFragment();
        }
        return homeTimelineFragment;
    }

    private MentionsTimelineFragment getMentionsTimelineFragment(){
        if (mentionsTimelineFragment == null) {
            mentionsTimelineFragment = new MentionsTimelineFragment();
        }
        return mentionsTimelineFragment;
    }

    //return title

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
