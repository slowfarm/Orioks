package ru.eva.oriokslive.adapters;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chauthai.swipereveallayout.SwipeRevealLayout;
import com.chauthai.swipereveallayout.ViewBinderHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.test_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SecurityFragmentAdapter.ViewHolder holder, int position) {
        binderHelper.bind(holder.swipeLayout, tokenList.get(position).getToken());
        String inputDate = tokenList.get(position).getLastUsed();
        String pattern = "yyyy-MM-dd'T'HH:mm";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern, Locale.getDefault());
        Date date = new Date();
        try {
            date = sdf.parse(inputDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        holder.date.setText(date.toString());
        holder.device.setText(tokenList.get(position).getUserAgent());
    }

    @Override
    public int getItemCount() {
        return tokenList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private SwipeRevealLayout swipeLayout;
        private View deleteLayout;
        private TextView date, device;

        ViewHolder(View itemView) {
            super(itemView);
            swipeLayout = itemView.findViewById(R.id.swipe_layout);
            deleteLayout = itemView.findViewById(R.id.delete_layout);
            date = itemView.findViewById(R.id.date);
            device = itemView.findViewById(R.id.device);
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