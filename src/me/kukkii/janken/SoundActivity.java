// $Id$
package me.kukkii.janken;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.ads.AdRequest;
import com.google.ads.AdView;

public class SoundActivity extends Activity {

  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.sound);

		   /*
		    AdView adView = (AdView)this.findViewById(R.id.adView0);
		    adView.loadAd(new AdRequest());
		   */
  }

  public void onResume(){
    super.onResume();
    if(SoundManager.getSoundManager().getChangeActivity() == false){   
      SoundManager.getSoundManager().startBgm();
    }
    SoundManager.getSoundManager().setChangeActivity(false);
  }

  public void onPause(){
    super.onPause();
    
    if(SoundManager.getSoundManager().getChangeActivity() == false){
      SoundManager.getSoundManager().stopBgm();
    }
  }  
  
   public void menu(View view) {
     Intent intent = new Intent(this, MenuActivity.class);
     startActivity(intent);
     SoundManager.getSoundManager().setChangeActivity(true);
   }
}