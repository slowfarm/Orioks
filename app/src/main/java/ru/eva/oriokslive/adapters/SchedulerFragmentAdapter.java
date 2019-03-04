package ru.eva.oriokslive.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chauthai.swipereveallayout.SwipeRevealLayout;
import com.chauthai.swipereveallayout.ViewBinderHelper;

import java.util.List;

import ru.eva.oriokslive.R;
import ru.eva.oriokslive.interfaces.OnGroupDeleteButtonClickListener;
import ru.eva.oriokslive.interfaces.OnGroupItemClickListener;

public class SchedulerFragmentAdapter extends RecyclerView.Adapter<SchedulerFragmentAdapter.ViewHolder> {

    private List<String> groupList;
    private OnGroupItemClickListener onGroupItemClickListener;
    private OnGroupDeleteButtonClickListener onGroupDeleteButtonClickListener;
    private final ViewBinderHelper binderHelper = new ViewBinderHelper();


    public SchedulerFragmentAdapter(List<String> groupList) {
        this.groupList = groupList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.group_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String group = groupList.get(position);
        holder.group.setText(group);
        binderHelper.bind(holder.swipeLayout, group);
    }

    @Override
    public int getItemCount() {
        return groupList.size();
    }

    public void addItems(List<String> groupList) {
        this.groupList = groupList;
        notifyDataSetChanged();
    }

    public void removeItem(int position) {
        groupList.remove(position);
        notifyItemRemoved(position);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView group;
        private final SwipeRevealLayout swipeLayout;
        private final LinearLayout frontLayout;
        private final View deleteLayout;

        ViewHolder(View itemView) {
            super(itemView);
            swipeLayout = itemView.findViewById(R.id.swipe_layout);
            deleteLayout = itemView.findViewById(R.id.delete_layout);
            group = itemView.findViewById(R.id.group);
            frontLayout = itemView.findViewById(R.id.front_layout);
            frontLayout.setOnClickListener(v -> onGroupItemClickListener.onClick(groupList.get(getAdapterPosition())));
            deleteLayout.setOnClickListener(v ->  {
                onGroupDeleteButtonClickListener.onClick(groupList.get(getAdapterPosition()), getAdapterPosition());
                swipeLayout.close(true);
            });
        }
    }

    public void setOnGroupItemClickListener(OnGroupItemClickListener onGroupItemClickListener) {
        this.onGroupItemClickListener = onGroupItemClickListener;
    }

    public void setOnGroupDeleteButtonClickListener(OnGroupDeleteButtonClickListener onGroupDeleteButtonClickListener) {
        this.onGroupDeleteButtonClickListener = onGroupDeleteButtonClickListener;
    }
}