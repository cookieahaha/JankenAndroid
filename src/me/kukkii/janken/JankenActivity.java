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

import android.media.MediaPlayer;

import com.google.ads.*;


public class JankenActivity extends Activity {

  private static final String tag = "janken";

  private MySQLiteOpenHelper dataManager;
  private GameManager gameManager;
  private boolean resumed;

  public void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);

    AdView adView = (AdView)this.findViewById(R.id.adView1);
    adView.loadAd(new AdRequest());
    
    MediaPlayer player = new MediaPlayer();
    try {
      player.setDataSource("R.raw.bgm1.mp3");
      player.setLooping(true);
      player.prepare();
      player.start();
    } 
    catch (IllegalArgumentException e) {
      e.printStackTrace();
    } 
    catch (IllegalStateException e) {
      e.printStackTrace();
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void onStart(){
    super.onStart();

    dataManager = new MySQLiteOpenHelper(getApplicationContext());
    dataManager.readSQL();
  }

  protected void onStop() {
    super.onStop();
    dataManager.close();
    Log.i(tag, "dataManager was closed");
  }

  public void onResume(){
    super.onResume();
    resumed = true;

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
  }

  public void showKen(){
    showMessage("Ken");
  }
    
  public void showPon(){
    showMessage("Pon");
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
