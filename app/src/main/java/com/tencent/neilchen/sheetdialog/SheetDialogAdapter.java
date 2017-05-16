package com.tencent.neilchen.sheetdialog;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by neil.chen on 2017/5/2.
 */

public class SheetDialogAdapter extends Adapter<SheetDialogAdapter.SheetDialogViewHolder> {

  private Context context;
  private ArrayList<sheetBean> datas;
  private RecyclerviewItemOnclickListener recyclerviewItemOnclickListener;

  public SheetDialogAdapter(Context context, ArrayList data) {
    this.context = context;
    this.datas = data;
  }

  @Override public SheetDialogViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View itemView = LayoutInflater.from(context).inflate(R.layout.item_recyclerview, parent, false);
    return new SheetDialogViewHolder(itemView);
  }

  @Override public void onBindViewHolder(SheetDialogViewHolder holder, int position) {
    holder.textView.setText(datas.get(position).getData());
    if (datas.get(position).isSelector()) {
      holder.textView.setBackgroundResource(R.drawable.sheetdialog_recyclerview_item_bg);
    }else {
      holder.textView.setBackgroundDrawable(new ColorDrawable(0));
    }

    holder.itemView.setTag(position);
  }

  @Override public int getItemCount() {
    return datas.size();
  }

  public class SheetDialogViewHolder extends RecyclerView.ViewHolder
      implements View.OnClickListener {

    private final TextView textView;

    public SheetDialogViewHolder(View itemView) {
      super(itemView);
      textView = (TextView) itemView.findViewById(R.id.tv);
      itemView.setOnClickListener(this);
    }

    @Override public void onClick(View itemView) {
      for (int i = 0; i < datas.size(); i++) {
        if (i == (int)itemView.getTag()){
          datas.get(i).setSelector(true);
          continue;
        }
        datas.get(i).setSelector(false);
      }

      notifyDataSetChanged();
      recyclerviewItemOnclickListener.onItemClick(itemView, (int) itemView.getTag());
    }
  }

  public void setOnRecyclerviewItemOnclickListener(
      RecyclerviewItemOnclickListener recyclerviewItemOnclickListener) {

    this.recyclerviewItemOnclickListener = recyclerviewItemOnclickListener;
  }

  public interface RecyclerviewItemOnclickListener {
    void onItemClick(View view, int position);
  }
}
