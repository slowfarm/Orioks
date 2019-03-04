package ru.eva.oriokslive.adapters;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import ru.eva.oriokslive.activities.schedule.pager.firstdenomination.FirstDenominatorFragment;
import ru.eva.oriokslive.activities.schedule.pager.firstnumerator.FirstNumeratorFragment;
import ru.eva.oriokslive.activities.schedule.pager.seconddenominator.SecondDenominatorFragment;
import ru.eva.oriokslive.activities.schedule.pager.secondnumerator.SecondNumeratorFragment;
import ru.eva.oriokslive.activities.schedule.pager.today.TodayFragment;
import ru.eva.oriokslive.activities.schedule.pager.tomorrow.TomorrowFragment;


public class SchedulerFragmentPagerAdapter extends FragmentStatePagerAdapter {

    public SchedulerFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }
        private String group;

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }

    @Override
        public Fragment getItem(int position) {
        Bundle args = new Bundle();
        args.putString("group", group);
        Fragment fragment;
            switch (position) {
                case 0:
                    fragment =  new TodayFragment();
                    break;
                case 1:
                    fragment = new TomorrowFragment();
                    break;
                case 2:
                    fragment = new FirstNumeratorFragment();
                    break;
                case 3:
                    fragment = new FirstDenominatorFragment();
                    break;
                case 4:
                    fragment = new SecondNumeratorFragment();
                    break;
                case 5:
                    fragment = new SecondDenominatorFragment();
                    break;
                    default:
                        fragment = new TodayFragment();
                        break;
            }
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public int getCount() {
            return 6;
        }

        @Override
        public CharSequence getPageTitle(int position) {
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

        public void setGroup(String group){
        this.group = group;
        }
    }