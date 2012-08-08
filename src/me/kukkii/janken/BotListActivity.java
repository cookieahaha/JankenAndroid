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
    int viewId = view.getId();
    int stageId = getStageId(viewId);
    intent.putExtra("stage", StageManager.getManager().getStage(stageId));
    startActivity(intent);
  }

  private int getStageId(int viewId) {
    if (viewId == R.id.button_stage_0) {
      return 0;
    } else if (viewId == R.id.button_stage_1) {
      return 1;
    } else if (viewId == R.id.button_stage_2) {
      return 2;
    } else if (viewId == R.id.button_stage_3) {
      return 3;
    } else if (viewId == R.id.button_stage_4) {
      return 4;
    } else if (viewId == R.id.button_stage_5) {
      return 5;
    } else if (viewId == R.id.button_stage_6) {
      return 6;
    } else if (viewId == R.id.button_stage_7) {
      return 7;
    } else if (viewId == R.id.button_stage_8) {
      return 8;
    } else if (viewId == R.id.button_stage_9) {
      return 9;
    } else if (viewId == R.id.button_stage_10) {
      return 10;
    } else if (viewId == R.id.button_stage_11) {
      return 11;
    } else if (viewId == R.id.button_stage_12) {
      return 12;
    } else if (viewId == R.id.button_stage_13) {
      return 13;
    } else if (viewId == R.id.button_stage_14) {
      return 14;
    } else if (viewId == R.id.button_stage_15) {
      return 15;
    } else if (viewId == R.id.button_stage_16) {
      return 16;
    } else if (viewId == R.id.button_stage_17) {
      return 17;
    } else if (viewId == R.id.button_stage_18) {
      return 18;
    } else if (viewId == R.id.button_stage_19) {
      return 19;
    } else {
      // return -1;
      return 0;
    }
  }
}
