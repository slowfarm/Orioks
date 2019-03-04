package ru.eva.oriokslive.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import ru.eva.oriokslive.R;
import ru.eva.oriokslive.interfaces.OnGroupItemClickListener;

public class GroupAdapter extends RecyclerView.Adapter<GroupAdapter.ViewHolder> {

    private List<String> groupList;
    private OnGroupItemClickListener onGroupItemClickListener;
    private Disposable single;
    private List<String> filteredData;

    public GroupAdapter(List<String> groupList) {
        this.groupList = groupList;
        this.filteredData = groupList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.group_picker_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.group.setText(filteredData.get(position));
    }

    @Override
    public int getItemCount() {
        return filteredData.size();
    }

    public void addItems(List<String> groupList) {
        this.groupList = groupList;
        this.filteredData = groupList;
        notifyDataSetChanged();
    }

    public void filterData(String constraint) {
        single = Single.fromCallable(() -> mapData(constraint, groupList))
                .delay(500, TimeUnit.MILLISECONDS)
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(()-> single.dispose())
                .subscribe(list -> {
                    filteredData = list;
                    notifyDataSetChanged();
                });

    }

    private List<String> mapData(String constraint,  List<String> list) {
        List<String> nlist = new ArrayList<>();
        for (String item : list)
            if (item.toLowerCase().contains(constraint.toLowerCase()))
                nlist.add(item);
        return nlist;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView group;

        ViewHolder(View itemView) {
            super(itemView);
            group = itemView.findViewById(R.id.group);
            itemView.setOnClickListener(v->onGroupItemClickListener.onClick(filteredData.get(getAdapterPosition())));
        }
    }

    public void setOnGroupItemClickListener(OnGroupItemClickListener onGroupItemClickListener) {
        this.onGroupItemClickListener = onGroupItemClickListener;
    }
}