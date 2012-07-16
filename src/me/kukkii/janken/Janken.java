package me.kukkii.janken;

import me.kukkii.janken.bot.BotManager;
import me.kukkii.janken.bot.AbstractBot;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import android.graphics.drawable.Drawable;
import android.content.res.Resources;

public class Janken extends Activity{

  private AbstractBot bot;
//  private ImageView view = (ImageView) findViewById(R.id.view_BOT);

  private Hand userHand;
  private Hand botHand;
  private Result result;
  private Judge judge = new Judge();

  public void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
  }

  public void onResume(){
    super.onResume();
    newGame();
  }

  public void afterPon(){
    botHand = bot.hand2();
    result = judge.judge(userHand, botHand);
    String text = bot.getName() + "\n" + userHand.toString() + "\n" + botHand.toString() + "\n" + result.toString();

    showToastOnUiThread(text, Toast.LENGTH_LONG);
    newGame();
  }

  public void showToastOnUiThread(String text, int length) {
    final String text0 = text;
    final int length0 = length;
    runOnUiThread(new Runnable() {
      public void run() {
        Toast.makeText(getApplicationContext(), text0, length0).show();
      }
    });
  }

  public void newGame(){
    bot =(AbstractBot) BotManager.getManager().next();
    Resources res = getResources();
    final int drawableId = bot.getImage();
 // Drawable drawable = res.getDrawable(drawableId);
    final ImageView view = (ImageView) findViewById(R.id.view_BOT);
    runOnUiThread(new Runnable() {
      public void run() {
        view.setImageResource(drawableId); 
      }
    });

 // TimeThread tt = new TimeThread(this);
    Runnable tt = new Runnable() {
      public void run() {
        game();
      }
    };
    Thread t = new Thread(tt);
    t.start();
  }

  public void game() {
    sleep(3000);
    jan();
    sleep(500);
    ken();
    sleep(500);
    pon();
    sleep(50);
    afterPon();
  }

  public void sleep(int msec) {
    try{
      Thread.sleep(msec);
    }
    catch (InterruptedException e){
    }
  }

  public void jan(){
    showToastOnUiThread("Jan", Toast.LENGTH_SHORT);
  }

  public void ken(){
    showToastOnUiThread("Ken", Toast.LENGTH_SHORT);
  }
    
  public void pon(){
    showToastOnUiThread("Pon", Toast.LENGTH_SHORT);
  }

  public void hand(View view){
    userHand = null;
    int id = view.getId();
    if(id==R.id.button_ROCK){
      userHand = Hand.ROCK;
    }
    if(id==R.id.button_SCISSOR){
      userHand = Hand.SCISSOR;
    }
    if(id==R.id.button_PAPER){
      userHand = Hand.PAPER;
    }
    Toast.makeText(getApplicationContext(), userHand.toString(), Toast.LENGTH_SHORT).show();
  }

}
