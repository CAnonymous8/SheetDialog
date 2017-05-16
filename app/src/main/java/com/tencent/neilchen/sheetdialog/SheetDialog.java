package com.tencent.neilchen.sheetdialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by neil.chen on 2017/5/2.
 */

public class SheetDialog extends Dialog {

  private SheetDialog.sheetDialogListener sheetDialogListener;

  public SheetDialog(@NonNull Context context) {
    super(context, R.style.sheetDialogStyle);

    View root = getLayoutInflater().inflate(R.layout.layout_sheetdialog, null);
    setContentView(root);

    initLinstener(root);
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setCanceledOnTouchOutside(false);
    getWindow().setWindowAnimations(R.style.sheetDialogStyle);
    getWindow().setGravity(Gravity.BOTTOM);
    WindowManager m = getWindow().getWindowManager();
    Display d = m.getDefaultDisplay();
    WindowManager.LayoutParams p = getWindow().getAttributes();
    p.width = d.getWidth(); //设置dialog的宽度为当前手机屏幕的宽度
    p.height = (int) (d.getHeight() * 0.4);
    getWindow().setAttributes(p);


  }

  private void initLinstener(View root) {
    root.findViewById(R.id.tvConfirm).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        if (sheetDialogListener != null) sheetDialogListener.onConfirm();
      }
    });
    root.findViewById(R.id.tvCancel).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        if (sheetDialogListener != null) sheetDialogListener.onCancel();
      }
    });
  }

  public void setOnSheetDialogListener(sheetDialogListener sheetDialogListener) {

    this.sheetDialogListener = sheetDialogListener;
  }

  public interface sheetDialogListener {
    void onConfirm();

    void onCancel();
  }
}
