// $Id$
package me.kukkii.janken;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.ads.AdRequest;
import com.google.ads.AdView;

public class MenuActivity extends Activity {

  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.menu);

   /*
    AdView adView = (AdView)this.findViewById(R.id.adView0);
    adView.loadAd(new AdRequest());
   */
  }

  public void startMain(View view) {
    Intent intent = new Intent(this, JankenActivity.class);
    startActivity(intent);
  }

}