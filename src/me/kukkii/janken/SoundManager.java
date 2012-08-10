package me.kukkii.janken;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.media.SoundPool.OnLoadCompleteListener;

public class SoundManager {

  //singleton
  private static SoundManager manager = null;
 
  public static void setContext(Context context) {
	 manager = new SoundManager(context);
  }

  public static SoundManager getSoundManager(){
	  return manager;
  }
  //
  
  private MediaPlayer bgm;
  private boolean changeActivity;
  
  public SoundManager(Context context) {
	    bgm = MediaPlayer.create(context, R.raw.bgm1);
  }

  public void startBgm(){
    bgm.setLooping(true);
    bgm.start();
    bgm.setVolume(0.3F, 0.3F);
  }

  public void stopBgm(){
    bgm.stop();
  }

  public void setChangeActivity(Boolean changeActivity){
	  this.changeActivity = changeActivity;
  }

  public boolean getChangeActivity(){
	  return changeActivity;
  }
  
}
