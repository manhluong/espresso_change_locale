package com.luong.espressochangelocale;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import java.util.Locale;

public class MainPresenter implements MainContract.Presenter {

  private MainContract.MainView view;

  @Override
  public void setView(MainContract.MainView view) {
    this.view = view;
  }

  /**
   * This is only for a quick test. Changing Locale considering all the deprecations is more
   * complex.
   * See here: http://stackoverflow.com/a/40704077
   */
  @Override
  public void changeLocale(Context context) {
    Locale locale = Locale.ITALY;
    if (Locale.getDefault().equals(Locale.ITALY)) {
      locale = Locale.US;
    }
    Locale.setDefault(locale);
    Resources resources = context.getResources();
    Configuration configuration = resources.getConfiguration();
    if (android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.JELLY_BEAN) {
      configuration.setLocale(locale);
    } else {
      configuration.locale = locale;
    }
    resources.updateConfiguration(configuration, resources.getDisplayMetrics());
    view.restart();
  }

  @Override
  public void release() {
    this.view = null;
  }
}
