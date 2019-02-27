package mx.unam.enallt.eica1;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AletsRdz on 23/03/18.
 */

public class InicioAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragments = null;

    public InicioAdapter(FragmentManager fm) {
        super(fm);
        fragments = new ArrayList<>();
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    public void addFragment(Fragment fragment){
        fragments.add(fragment);
    }
}
