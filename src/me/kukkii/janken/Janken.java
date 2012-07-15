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

  public void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
  }

  public void onResume(){
    super.onResume();
    newGame();
  }

  public void hand(View view){
    Hand userHand = null;
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
    Hand botHand = bot.hand2();
    Judge judge = new Judge();
    Result result = judge.judge(userHand, botHand);
    String text = bot.getName() + "\n" + userHand.toString() + "\n" + botHand.toString() + "\n" + result.toString();

    Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();
    newGame();
 }

    public void newGame(){
      bot =(AbstractBot) BotManager.getManager().next();
      Resources res = getResources();
      int drawableId = bot.getImage();
 //     Drawable drawable = res.getDrawable(drawableId);
      ImageView view = (ImageView) findViewById(R.id.view_BOT);
      view.setImageResource(drawableId); 
    }
}
