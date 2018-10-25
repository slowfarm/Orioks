package ru.eva.oriokslive.adapters;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import ru.eva.oriokslive.fragmens.Scheduler.ViewPagerFragments.FragmentFirstDenominator;
import ru.eva.oriokslive.fragmens.Scheduler.ViewPagerFragments.FragmentFirstNumerator;
import ru.eva.oriokslive.fragmens.Scheduler.ViewPagerFragments.FragmentSecondDenominator;
import ru.eva.oriokslive.fragmens.Scheduler.ViewPagerFragments.FragmentSecondNumerator;
import ru.eva.oriokslive.fragmens.Scheduler.ViewPagerFragments.FragmentToday;
import ru.eva.oriokslive.fragmens.Scheduler.ViewPagerFragments.FragmentTomorrow;

public class SchedulerFragmentPagerAdapter extends FragmentStatePagerAdapter {

    public SchedulerFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }

    @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new FragmentToday();
                case 1:
                    return new FragmentTomorrow();
                case 2:
                    return new FragmentFirstNumerator();
                case 3:
                    return new FragmentFirstDenominator();
                case 4:
                    return new FragmentSecondNumerator();
                case 5:
                    return new FragmentSecondDenominator();
                    default:
                        return new FragmentToday();
            }
        }

        @Override
        public int getCount() {
            return 6;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            // Generate title based on item position
            switch (position) {
                case 0:
                    return "Сегодня";
                case 1:
                    return "Завтра";
                case 2:
                    return "1 Числитель";
                case 3:
                    return "1 Знаменатель";
                case 4:
                    return "2 Числитель";
                case 5:
                    return "2 Знаменатель";
                default:
                    return null;
            }
        }

    }