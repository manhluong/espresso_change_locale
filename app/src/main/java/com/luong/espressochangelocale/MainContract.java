package com.luong.espressochangelocale;

import android.content.Context;

public interface MainContract {

  interface Presenter {
    void setView(MainView view);

    void changeLocale(Context context);

    void release();
  }

  interface MainView {
    void setPresenter(Presenter presenter);

    void restart();
  }
}
