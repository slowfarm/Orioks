package ru.eva.oriokslive.adapters;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import ru.eva.oriokslive.fragmens.scheduler.pager.firstdenomination.FirstDenominatorFragment;
import ru.eva.oriokslive.fragmens.scheduler.pager.firstnumerator.FirstNumeratorFragment;
import ru.eva.oriokslive.fragmens.scheduler.pager.seconddenominator.SecondDenominatorFragment;
import ru.eva.oriokslive.fragmens.scheduler.pager.secondnumerator.SecondNumeratorFragment;
import ru.eva.oriokslive.fragmens.scheduler.pager.today.TodayFragment;
import ru.eva.oriokslive.fragmens.scheduler.pager.tomorrow.TomorrowFragment;

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
                    return new TodayFragment();
                case 1:
                    return new TomorrowFragment();
                case 2:
                    return new FirstNumeratorFragment();
                case 3:
                    return new FirstDenominatorFragment();
                case 4:
                    return new SecondNumeratorFragment();
                case 5:
                    return new SecondDenominatorFragment();
                    default:
                        return new TodayFragment();
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