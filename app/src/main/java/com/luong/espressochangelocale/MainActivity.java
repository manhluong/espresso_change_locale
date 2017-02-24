package com.luong.espressochangelocale;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements MainContract.MainView {

  private MainContract.Presenter presenter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    presenter = new MainPresenter();
    presenter.setView(this);
    this.setPresenter(presenter);

    Button changeButton = (Button) findViewById(R.id.button_change);
    changeButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        presenter.changeLocale(v.getContext());
      }
    });
  }

  @Override
  public void onDestroy() {
    presenter.release();
    super.onDestroy();
  }

  @Override
  public void setPresenter(MainContract.Presenter presenter) {
    this.presenter = presenter;
  }

  @Override
  public void restart() {
    Intent intent = getIntent();
    finish();
    startActivity(intent);
  }
}
