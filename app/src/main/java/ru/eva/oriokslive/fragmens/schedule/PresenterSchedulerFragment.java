package ru.eva.oriokslive.fragmens.schedule;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.graphics.drawable.Icon;
import android.os.Build;

import java.util.List;

import ru.eva.oriokslive.R;
import ru.eva.oriokslive.activities.schedule.SchedulerActivity;

class PresenterSchedulerFragment implements ContractSchedulerFragment.Presenter {
    private ContractSchedulerFragment.View mView;
    private ContractSchedulerFragment.Repository mRepository;


    PresenterSchedulerFragment(ContractSchedulerFragment.View mView) {
        this.mView = mView;
        mRepository = new RepositorySchedulerFragment();
    }

    @Override
    public void getRecyclerView() {
        List<String> groupList = mRepository.getLocalGroups();
        mView.setRecyclerView(groupList);
    }

    @Override
    public void addLocalGroup(String group) {
        mRepository.addLocalGroup(group);
        mView.notifyDataSetChanged(mRepository.getLocalGroups());
    }

    @Override
    public void removeGroup(String group) {
        mRepository.removeGroup(group);
    }

    @Override
    public void addPinnedShortcuts(Context context, String group) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            ShortcutManager mShortcutManager = context.getSystemService(ShortcutManager.class);

            if (mShortcutManager.isRequestPinShortcutSupported()) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setClass(context, SchedulerActivity.class);
                intent.putExtra("group", group);

                ShortcutInfo pinShortcutInfo = new ShortcutInfo.Builder(context, group)
                        .setShortLabel(group.split("\\s")[0])
                        .setLongLabel(group)
                        .setIcon(Icon.createWithResource(context, R.drawable.ic_schedule))
                        .setIntent(intent)
                        .build();

                Intent pinnedShortcutCallbackIntent = mShortcutManager.createShortcutResultIntent(pinShortcutInfo);

                PendingIntent successCallback = PendingIntent.getBroadcast(context, 0, pinnedShortcutCallbackIntent, 0);

                mShortcutManager.requestPinShortcut(pinShortcutInfo, successCallback.getIntentSender());
            }
        }
    }
}
