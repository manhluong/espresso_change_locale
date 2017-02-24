package com.luong.espressochangelocale;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import java.util.Locale;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class EspressoChangeLocaleTest {

  @Rule
  public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule(MainActivity.class);

  private MainPresenter mainPresenter;

  @Before
  public void setUp() {
    mainPresenter = new MainPresenter();
    mainPresenter.setView(mActivityRule.getActivity());
  }

  @Test
  public void checkITLocale() {
    mainPresenter.changeLocale(InstrumentationRegistry.getTargetContext());

    onView(withText("Ciao Mondo!")).check(matches(isDisplayed()));
  }

  @Test
  public void checkUSLocale() {
    mainPresenter.changeLocale(InstrumentationRegistry.getTargetContext());
    mainPresenter.changeLocale(InstrumentationRegistry.getTargetContext());

    onView(withText("Hello World!")).check(matches(isDisplayed()));
  }

  @After
  public void tearDown() {
    resetLocaleToUS();
  }

  /**
   * This is only for a quick test. Changing Locale considering all the deprecations is more
   * complex.
   * See here: http://stackoverflow.com/a/40704077
   */
  private void resetLocaleToUS() {
    Context context = InstrumentationRegistry.getTargetContext();
    Locale locale = Locale.US;
    Locale.setDefault(locale);
    Resources resources = context.getResources();
    Configuration configuration = resources.getConfiguration();
    if (android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.JELLY_BEAN) {
      configuration.setLocale(locale);
    } else {
      configuration.locale = locale;
    }
    resources.updateConfiguration(configuration, resources.getDisplayMetrics());
  }
}
