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
import ru.eva.oriokslive.interfaces.OnDeleteButtonClickListener;
import ru.eva.oriokslive.models.orioks.Security;

public class SecurityFragmentAdapter extends RecyclerView.Adapter<SecurityFragmentAdapter.ViewHolder> {
    private List<Security> tokenList;
    private final ViewBinderHelper binderHelper = new ViewBinderHelper();
    private OnDeleteButtonClickListener onDeleteButtonClickListener;


    public SecurityFragmentAdapter(List<Security> tokenList) {
        this.tokenList = tokenList;
    }

    public void addItems(List<Security> tokenList) {
            this.tokenList = tokenList;
            notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.security_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SecurityFragmentAdapter.ViewHolder holder, int position) {
        Security security = tokenList.get(position);
        binderHelper.bind(holder.swipeLayout, security.getToken());
        holder.application.setText(security.getApplication());
        holder.device.setText(security.getDevice());
        holder.deviceLayout.setVisibility(security.isContainDevice() ? View.VISIBLE : View.GONE);
        holder.date.setText(security.getLastUsedValue());
    }

    @Override
    public int getItemCount() {
        return tokenList.size();
    }

    public void removeItem(int position) {
        tokenList.remove(position);
        notifyItemRemoved(position);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private SwipeRevealLayout swipeLayout;
        private View deleteLayout;
        private TextView date, device, application;
        private LinearLayout deviceLayout;

        ViewHolder(View itemView) {
            super(itemView);
            swipeLayout = itemView.findViewById(R.id.swipe_layout);
            deleteLayout = itemView.findViewById(R.id.delete_layout);
            date = itemView.findViewById(R.id.date);
            device = itemView.findViewById(R.id.device);
            application = itemView.findViewById(R.id.application);
            deviceLayout = itemView.findViewById(R.id.device_layout);
            deleteLayout.setOnClickListener(v ->  {
                onDeleteButtonClickListener.onClick(tokenList.get(getAdapterPosition()), getAdapterPosition());
                swipeLayout.close(true);
            });
        }
    }

    public void setOnDeleteButtonClickListener(OnDeleteButtonClickListener onDeleteButtonClickListener) {
        this.onDeleteButtonClickListener = onDeleteButtonClickListener;
    }
}