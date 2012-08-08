// $Id$
package me.kukkii.janken;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.ads.AdRequest;
import com.google.ads.AdView;

public class BotListActivity extends Activity {

  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.botlist);

    AdView adView = (AdView)this.findViewById(R.id.adViewBotList);
    adView.loadAd(new AdRequest());
  }
  
  public void menu(View view) {
    Intent intent = new Intent(this, MenuActivity.class);
    startActivity(intent);
  }

  public void gotoStage(View view) {
    Intent intent = new Intent(this, JankenActivity.class);
    startActivity(intent);
  }
}
