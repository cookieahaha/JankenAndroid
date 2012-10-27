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
  
  private PopupWindow popupWindow;
  
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
    showMessage("Jan");
    SoundManager.getSoundManager().jan();
  }

  public void showKen(){
    showMessage("Ken");
    SoundManager.getSoundManager().ken();
  }
    
  public void showPon(){
    showMessage("Pon");
    SoundManager.getSoundManager().pon();
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
  
  public void showPopup(String string){
    final String string2 = string;

    activity.runOnUiThread(new Runnable() {
      public void run() {
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.popup_view, (ViewGroup) activity.findViewById(R.id.popupWindow));
        popupWindow = new PopupWindow(popupView, 300, 400, true);
        popupWindow.showAtLocation(activity.findViewById(R.id.view_BOT), Gravity.CENTER, 0, 0);

        TextView textView = (TextView) popupView.findViewById(R.id.popupText);
        textView.setText(string2);
      }
    });
    try{
      Thread.sleep(3000);
    }
    catch (InterruptedException e){
    }
    activity.runOnUiThread(new Runnable() {
      public void run() {
        popupWindow.dismiss();
      }
    });
  }


}
