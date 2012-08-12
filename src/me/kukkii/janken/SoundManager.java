package me.kukkii.janken;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.media.SoundPool.OnLoadCompleteListener;
import android.util.Log;
import static android.content.Context.AUDIO_SERVICE;

public class SoundManager {

  //singleton
  private static SoundManager manager = null;
  private static Context _context;
 
  public static void setContext(Context context) {
     _context = context;
	 manager = new SoundManager(context);
  }

  public static SoundManager getSoundManager(){
	  return manager;
  }
  //
  
  private MediaPlayer bgm;
  private boolean changeActivity;
  
  private SoundPool soundPool;
  private int soundID;
  boolean loaded = false;
  
  private boolean bgmIsOn;
  
  public SoundManager(Context context) {
	    bgm = MediaPlayer.create(context, R.raw.bgm1);
	    
	    soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
	    soundPool.setOnLoadCompleteListener(new OnLoadCompleteListener() {
	      // @Override
	      public void onLoadComplete(SoundPool soundPool, int sampleId,
	          int status) {
	        loaded = true;
	      }
	    });
	    soundID = soundPool.load(context, R.raw.janken, 1);
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
  
  public void jan(){
    AudioManager audioManager = (AudioManager) _context.getSystemService(AUDIO_SERVICE);
    float actualVolume = (float) audioManager
        .getStreamVolume(AudioManager.STREAM_MUSIC);
    float maxVolume = (float) audioManager
        .getStreamMaxVolume(AudioManager.STREAM_MUSIC);
    float volume = actualVolume / maxVolume;
    // Is the sound loaded already?
    if (loaded) {
      soundPool.play(soundID, volume, volume, 1, 0, 1f);
      Log.e("Test", "Played sound");
    }
  }

  public void ken(){
    AudioManager audioManager = (AudioManager) _context.getSystemService(AUDIO_SERVICE);
    float actualVolume = (float) audioManager
        .getStreamVolume(AudioManager.STREAM_MUSIC);
    float maxVolume = (float) audioManager
        .getStreamMaxVolume(AudioManager.STREAM_MUSIC);
    float volume = actualVolume / maxVolume;
    // Is the sound loaded already?
    if (loaded) {
      soundPool.play(soundID, volume, volume, 1, 0, 1f);
      Log.e("Test", "Played sound");
    }
  }
  
  public void pon(){
    AudioManager audioManager = (AudioManager) _context.getSystemService(AUDIO_SERVICE);
    float actualVolume = (float) audioManager
        .getStreamVolume(AudioManager.STREAM_MUSIC);
    float maxVolume = (float) audioManager
        .getStreamMaxVolume(AudioManager.STREAM_MUSIC);
    float volume = actualVolume / maxVolume;
    // Is the sound loaded already?
    if (loaded) {
      soundPool.play(soundID, volume, volume, 1, 0, 1f);
      Log.e("Test", "Played sound");
    }
  }

  public void setBgmIsOn(boolean bgmIsOn){
    this.bgmIsOn = bgmIsOn;
  }
  
  public boolean getBgmIsOn(){
    return bgmIsOn;
  }
  
}
