package me.kukkii.janken;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.media.SoundPool.OnLoadCompleteListener;
import android.util.Log;
import static android.content.Context.AUDIO_SERVICE;
import me.kukkii.janken.bot.*;

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
  private SoundPool soundPool;
  
  private int soundJan;
  private int soundKen;
  private int soundPon;
  private int soundWin;
  private int soundLose;

  
  boolean loaded = false;
  
  private boolean bgmIsOn = true;
  private boolean soundpoolIsOn = true;
  
  private int currentSong = R.raw.bgm1;
  
  private Context context;
  
  public SoundManager(Context context) {
        this.context = context;
        
	    bgm = MediaPlayer.create(context, R.raw.bgm1);
	    
	    soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
	    soundPool.setOnLoadCompleteListener(new OnLoadCompleteListener() {
	      // @Override
	      public void onLoadComplete(SoundPool soundPool, int sampleId,
	          int status) {
	        loaded = true;
	      }
	    });
	    soundJan = soundPool.load(context, R.raw.janken, 1);
	    soundKen = soundPool.load(context, R.raw.janken, 1);
	    soundPon = soundPool.load(context, R.raw.janken, 1);
        soundWin = soundPool.load(context, R.raw.janken, 1);
        soundLose = soundPool.load(context, R.raw.janken, 1);

  }

  public void startBgm(){
    bgm.setLooping(true);
    bgm.start();
    bgm.setVolume(0.3F, 0.3F);
  }

  public void changeBgm(AbstractBot bot){
    if(bot.getSong() != currentSong){
      bgm.stop();
      bgm = MediaPlayer.create(context, bot.getSong());
      startBgm();
      
      currentSong = bot.getSong();
    }
  }
  
  public void changeSoundpool(AbstractBot bot){
    if(bot.getJan() != soundJan){
      soundJan = soundPool.load(context, bot.getJan(), 1);
    }
    if(bot.getKen() != soundKen){
      soundKen = soundPool.load(context, bot.getKen(), 1);
    }
    if(bot.getPon() != soundPon){
      soundPon = soundPool.load(context, bot.getPon(), 1);
    }
  }
  
  public void stopBgm(){
    bgm.pause();
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
      if(soundpoolIsOn){
        soundPool.play(soundJan, volume, volume, 1, 0, 1f);
      Log.e("Test", "Played sound");
      }
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
      if(soundpoolIsOn){
        soundPool.play(soundKen, volume, volume, 1, 0, 1f);
     // Log.e("Test", "Played sound");
      }
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
      if(soundpoolIsOn){
        soundPool.play(soundPon, volume, volume, 1, 0, 1f);
 //     Log.e("Test", "Played sound");
      }
    }
  }
  
  public void win(){
    AudioManager audioManager = (AudioManager) _context.getSystemService(AUDIO_SERVICE);
    float actualVolume = (float) audioManager
        .getStreamVolume(AudioManager.STREAM_MUSIC);
    float maxVolume = (float) audioManager
        .getStreamMaxVolume(AudioManager.STREAM_MUSIC);
    float volume = actualVolume / maxVolume;
    // Is the sound loaded already?
    if (loaded) {
      if(soundpoolIsOn){
        soundPool.play(soundWin, volume, volume, 1, 0, 1f);
 //     Log.e("Test", "Played sound");
      }
    }
  }
  
  public void lose(){
    AudioManager audioManager = (AudioManager) _context.getSystemService(AUDIO_SERVICE);
    float actualVolume = (float) audioManager
        .getStreamVolume(AudioManager.STREAM_MUSIC);
    float maxVolume = (float) audioManager
        .getStreamMaxVolume(AudioManager.STREAM_MUSIC);
    float volume = actualVolume / maxVolume;
    // Is the sound loaded already?
    if (loaded) {
      if(soundpoolIsOn){
        soundPool.play(soundLose, volume, volume, 1, 0, 1f);
 //     Log.e("Test", "Played sound");
      }
    }
  }
  
  public void setBgmIsOn(boolean bgmIsOn){
    this.bgmIsOn = bgmIsOn;
  }
  
  public boolean getBgmIsOn(){
    return bgmIsOn;
  }
  
  public void setSoundpoolIsOn(boolean soundpoolIsOn){
    this.soundpoolIsOn = soundpoolIsOn;
  }
  
  public boolean getSoundpoolIsOn(){
    return soundpoolIsOn;
  }
}
