// $Id$
package me.kukkii.janken;

import java.io.IOException;

import me.kukkii.janken.bot.AbstractBot;
import me.kukkii.janken.bot.BotManager;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.content.res.Resources;
import android.content.Context;
import android.content.Intent;
import android.content.ContentValues;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Toast;
import android.widget.TextView;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.media.SoundPool.OnLoadCompleteListener;

import com.google.ads.*;


public class JankenFragment extends Fragment implements OnClickListener {

  private static final String tag = "janken";

  private MySQLiteOpenHelper dataManager;
  private GameManager gameManager;
  
  private Activity activity;;  
  
  private ImageButton button_rock;
  private ImageButton button_scissor;
  private ImageButton button_paper;
  
  private PopupWindow[] popupWindows = new PopupWindow[16];

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
      // Inflate the layout for this fragment
    return inflater.inflate(R.layout.main_fragment, container, false);
  }

  public void onStart(){
    super.onStart();
    activity = getActivity();
    MySQLiteOpenHelper.setContext(activity.getApplicationContext());
    dataManager = MySQLiteOpenHelper.getHelper();
    dataManager.readSQL();
    activity.setVolumeControlStream(AudioManager.STREAM_MUSIC);
    
    button_rock = (ImageButton)getActivity().findViewById(R.id.button_ROCK);
    button_rock.setOnClickListener(this);
    button_scissor = (ImageButton)getActivity().findViewById(R.id.button_SCISSOR);
    button_scissor.setOnClickListener(this);
    button_paper = (ImageButton)getActivity().findViewById(R.id.button_PAPER);
    button_paper.setOnClickListener(this);
  }

  public void onStop() {
    super.onStop();
    // dataManager.close();
    // Log.i(tag, "dataManager was closed");

  }

  public void onResume(){
    super.onResume();
    Bundle args = getArguments();
    gameManager = new GameManager(this, dataManager);
    
  }

  public void onPause(){
    super.onPause();
    gameManager.killGameThread();
  }

  public void showResult(String text) {
    showMessage(text);
  }

  public void showMessage(String text) {
    final String text0 = text;
    final TextView view = (TextView) activity.findViewById(R.id.text);
    activity.runOnUiThread(new Runnable() {
      public void run() {
        view.setText(text0);
      }
    });
  }

  public void showUserHealthText(int userhealth){
    final int userHealth = userhealth;
    final String emptyString = "";
    final TextView view = (TextView) activity.findViewById(R.id.userHealthText);
    activity.runOnUiThread(new Runnable() {
      public void run() {
        view.setText(emptyString + userHealth);
      }
    });
  }

  public void showBotHealthText(int bothealth){
    final int botHealth = bothealth;
    final String emptyString = "";
    final TextView view = (TextView) activity.findViewById(R.id.botHealthText);
    activity.runOnUiThread(new Runnable() {
      public void run() {
        view.setText(emptyString + botHealth);
      }
    });
  }

  public void showBot(AbstractBot bot){
    final int drawableId = bot.getImage();
    final ImageView view = (ImageView) activity.findViewById(R.id.view_BOT);
    activity.runOnUiThread(new Runnable() {
      public void run() {
        view.setImageResource(drawableId); 
      }
    });
  }

  public void showJan(){
    SoundManager.getSoundManager().jan();
    showPopupInThread("Jan", 500);
  }

  public void showKen(){
    SoundManager.getSoundManager().ken();
    showPopupInThread("Ken", 500);
  }
    
  public void showPon(){
    SoundManager.getSoundManager().pon();
    showPopupInThread("Pon", 300);
  }

  public void onClick(View view){
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
    final ImageView view = (ImageView) activity.findViewById(R.id.view_BOT);
    activity.runOnUiThread(new Runnable() {
      public void run() {
        view.setImageResource(drawableId); 
      }
    });
  }
  
  public void showPopupInThread(String string, int time){
    final String string2 = string;
    final int time2 = time;
    Thread th = new Thread(new Runnable(){
      public void run() {
        showPopup(string2, time2);
      }
    });
    th.start(); 
  }

  public void showPopup(String string, int time){
    final String string2 = string;
    int n = -1;
    while (n < 0) {
      for (int i = 0; i < 16; i++) {
        if (popupWindows[i] == null) {
          n = i;
          break;
        }
      }
      if (n >= 0) {
        break;
      }
      try {
        Thread.sleep(100);
      }
      catch (InterruptedException e) {
      }
      continue;
    }
    final int index = n;
    activity.runOnUiThread(new Runnable() {
      public void run() {
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.popup_view, (ViewGroup) activity.findViewById(R.id.popupWindow));
        popupWindows[index] = new PopupWindow(popupView, 300, 400, true);
        popupWindows[index].showAtLocation(activity.findViewById(R.id.view_BOT), Gravity.CENTER, 0, 0);
        TextView textView = (TextView) popupView.findViewById(R.id.popupText);
        textView.setText(string2);
      }
    });
    try{
      Thread.sleep(time);
    }
    catch (InterruptedException e){
    }
    activity.runOnUiThread(new Runnable() {
      public void run(){
        popupWindows[index].dismiss();
        popupWindows[index] = null;
      }
    });
  }
}
