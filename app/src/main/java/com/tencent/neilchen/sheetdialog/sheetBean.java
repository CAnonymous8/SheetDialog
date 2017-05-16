package com.tencent.neilchen.sheetdialog;

/**
 * Created by neil.chen on 2017/5/16.
 */

public class sheetBean {
  public sheetBean(boolean isSelector, String data) {
    this.isSelector = isSelector;
    this.data = data;
  }

  public boolean isSelector() {
    return isSelector;
  }

  public void setSelector(boolean selector) {
    isSelector = selector;
  }

  private boolean isSelector;

  public String getData() {
    return data;
  }

  public void setData(String data) {
    this.data = data;
  }

  private String data;
}
