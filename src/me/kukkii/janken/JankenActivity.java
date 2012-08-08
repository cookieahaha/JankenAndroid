// $Id$
package me.kukkii.janken;

import java.io.IOException;

import me.kukkii.janken.bot.AbstractBot;
import me.kukkii.janken.bot.BotManager;

import android.app.Activity;
import android.content.res.Resources;
import android.content.Intent;
import android.content.ContentValues;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.TextView;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.media.SoundPool.OnLoadCompleteListener;

import com.google.ads.*;


public class JankenActivity extends Activity {

  private static final String tag = "janken";

  private MySQLiteOpenHelper dataManager;
  private GameManager gameManager;
  private boolean resumed;

  private MediaPlayer mp;
  private SoundPool soundPool;
  private int soundID;
  boolean loaded = false;
  
  public void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);

    AdView adView = (AdView)this.findViewById(R.id.adView1);
    adView.loadAd(new AdRequest());

    setVolumeControlStream(AudioManager.STREAM_MUSIC);
    
    soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
    soundPool.setOnLoadCompleteListener(new OnLoadCompleteListener() {
      // @Override
      public void onLoadComplete(SoundPool soundPool, int sampleId,
          int status) {
        loaded = true;
      }
    });
    soundID = soundPool.load(this, R.raw.janken, 1);
    
    mp = MediaPlayer.create(this, R.raw.bgm1);
    mp.setLooping(true);
    mp.start();
    mp.setVolume(0.3F, 0.3F);
  }

  public void onStart(){
    super.onStart();

    dataManager = new MySQLiteOpenHelper(getApplicationContext());
    dataManager.readSQL();
  }

  protected void onStop() {
    super.onStop();
    dataManager.close();
    mp.stop();
    Log.i(tag, "dataManager was closed");
  }

  public void onResume(){
    super.onResume();
    resumed = true;
    
    SoundPool soundPool = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);  
    int soundID = soundPool.load(this, R.raw.bgm1, 1); 
    soundPool.play(soundID, 1.0F, 1.0F, 1, -1, 1.0F); 

    gameManager = new GameManager(this, dataManager);
  }

  public void onPause(){
    super.onPause();
    resumed = false;
  }

  public boolean isResumed0() {
    return resumed;
  }

  public void showResult(String text) {
    showMessage(text);
  }

  public void showMessage(String text) {
    final String text0 = text;
    final TextView view = (TextView) findViewById(R.id.text);
    runOnUiThread(new Runnable() {
      public void run() {
        view.setText(text0);
      }
    });
  }

  public void showBot(AbstractBot bot){
    final int drawableId = bot.getImage();
    final ImageView view = (ImageView) findViewById(R.id.view_BOT);
    runOnUiThread(new Runnable() {
      public void run() {
        view.setImageResource(drawableId); 
      }
    });
  }

  public void showJan(){
    showMessage("Jan");
    
    AudioManager audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
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

  public void showKen(){
    showMessage("Ken");

    AudioManager audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
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
    
  public void showPon(){
    showMessage("Pon");

    AudioManager audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
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

  public void onClickHand(View view){
    int id = view.getId();
    Hand hand = null;
    if (id == R.id.button_ROCK) {
      hand = Hand.ROCK;
    }
    else if (id == R.id.button_SCISSOR) {
      hand = Hand.SCISSOR;
    }
    else if (id == R.id.button_PAPER) {
      hand = Hand.PAPER;
    }
    gameManager.setUserHand(hand);
  }

  public void showBotHand(Hand hand){
    int botHandImage = 0;
    if(hand == hand.ROCK){
      botHandImage = R.drawable.hand_rock;
    }
    else if(hand == hand.SCISSOR){
      botHandImage = R.drawable.hand_scissor;
    }
    else if(hand == hand.PAPER){
      botHandImage = R.drawable.hand_paper;
    }

    final int drawableId = botHandImage;
    final ImageView view = (ImageView) findViewById(R.id.view_BOT);
    runOnUiThread(new Runnable() {
      public void run() {
        view.setImageResource(drawableId); 
      }
    });
  }

  public void menu(View view) {
    Intent intent = new Intent(this, MenuActivity.class);
    startActivity(intent);
  }

}
