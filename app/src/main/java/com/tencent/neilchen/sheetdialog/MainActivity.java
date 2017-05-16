package com.tencent.neilchen.sheetdialog;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import android.widget.Toast;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements
    SheetDialogAdapter.RecyclerviewItemOnclickListener{
    private  ArrayList<sheetBean> datas;
    private SheetDialog sheetDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sheetDialog = new SheetDialog(this);
        sheetDialog.setOnSheetDialogListener(new SheetDialog.sheetDialogListener() {
            @Override public void onConfirm() {
                sheetDialog.dismiss();

            }

            @Override public void onCancel() {
                sheetDialog.dismiss();

            }
        });
        datas = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            datas.add(new sheetBean(false,String.valueOf(i * i + 5)));
        }
        final SheetDialogAdapter sheetDialogAdapter = new SheetDialogAdapter(getApplicationContext(), datas);
        sheetDialogAdapter.setOnRecyclerviewItemOnclickListener(this);
        findViewById(R.id.tv_helloworld).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecyclerView recyclerView = (RecyclerView) sheetDialog.findViewById(R.id.rv);
                recyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(getApplicationContext()).colorResId(
                    R.color.colorAccent).size(1).build());
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                recyclerView.setAdapter(sheetDialogAdapter);
                sheetDialog.show();

            }
        });

    }

    @Override public void onItemClick(View view, int position) {

        String data = datas.get(position).getData();
        Toast.makeText(getApplicationContext(),data,Toast.LENGTH_SHORT).show();
    }
}
