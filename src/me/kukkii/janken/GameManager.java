package me.kukkii.janken;

import android.app.Activity;
import android.support.v4.app.FragmentTransaction;
import me.kukkii.janken.bot.AbstractBot;
import me.kukkii.janken.bot.BotManager;

public class GameManager implements Runnable{

  private JankenFragment fragment;
  private MySQLiteOpenHelper dataManager;

  private Judge judge;
  private User user;
  private AbstractBot bot;
  private Hand userHand;
  private Hand botHand;
  private Result result;

  private static final int timeJan = 2000;
  private static final int timeKen = 1000;
  private static final int timePon = 1000;
  private static final int timeAfterPon = 50;
  private static final int timeTilNewGame = 1000;
  private static final int timeTilPon = timeJan + timeKen + timePon;

  private Thread gameThread;
  
  private boolean gameIsRunning;
  
  public GameManager(JankenFragment activity, MySQLiteOpenHelper dataManager) {
    this.fragment = activity;
    this.dataManager = dataManager;
  }
  
  public void run(){
    user = new User();
    judge = new Judge();
    
        
    gameIsRunning = true;
  
    while(user.getHitPoint() > 0){
      gamePerBot();
    } 
    MenuFragment fragment2 = new MenuFragment();
    FragmentTransaction transaction = fragment.getActivity().getSupportFragmentManager().beginTransaction();
    transaction.replace(R.id.main_fragment, fragment2);
    transaction.addToBackStack(null);
    //Commit the transaction
    transaction.commit();
  }

  public void sleep(int msec) {
    try{
      Thread.sleep(msec);
    }
    catch (InterruptedException e){
    }
  }

  public void jan(){
    fragment.showJan();
  }

  public void ken(){
    fragment.showKen();
  }
   
  public void pon(){
    fragment.showPon();
  }

  public void afterPon(){
    sleep(300);
    if(!gameIsRunning){
      return;
    }
    result = judge.judge(userHand, botHand);
    dataManager.writeResultToSQL(result);
    
    int damage = damage();

    bot.applyAbilitiesAfterPon(this);

//damagebot
    if(bot.getName().equals("DamageBot")){
      int addDamage = (int)(Math.random()*3+1);
      user.setHitPoint(user.getHitPoint() - addDamage);
      fragment.showPopup("Bot hit " + damage + "!!!", 300);
    }   

    if(result == Result.WIN){
      bot.setHitPoint(bot.getHitPoint()-damage);
      fragment.showPopup("you hit " + damage + "!!!", 700);
    }
    if(result == Result.LOSE){

//damagebot2
      if(bot.getName().equals("DamageBot2")){
        if(user.getHitPoint()%2 == 0){
          user.setHitPoint(user.getHitPoint()-(damage*2));
          fragment.showPopup("bot hit " + damage + "!!!", 700);
        }
      }
      else{
        user.setHitPoint(user.getHitPoint()-damage);
        fragment.showPopup("bot hit " + damage + "!!!", 700);
      }
    }    
    
//transferBot
    if(bot.getName().equals("TransferBot")){
      int temp = user.getHitPoint();
      user.setHitPoint(bot.getHitPoint());
      bot.setHitPoint(temp);
      fragment.showPopup("HP swapped!!!", 300);
    }

    fragment.setHP(user.getHitPoint(), bot.getHitPoint());
    // fragment.showUserHealthText(user.getHitPoint());
    // fragment.showBotHealthText(bot.getHitPoint());
    fragment.showResult(dataManager.getResultAsString(user.getHitPoint(), bot.getHitPoint(),bot,userHand,botHand,result));
  }

  public void game() {
    while(true){   // loop during draw    
      userHand = Hand.UNKNOWN;
      new Thread(new Runnable() {
        public void run() {
          sleep(timeTilPon + bot.getTiming());
          if(gameIsRunning){
            setBotHand( bot.hand2(userHand) );
          }
        }
      }).start();
    
      sleep(timeJan);
      if(!gameIsRunning){
        return;
      }
      jan();
      sleep(timeKen);
      if(!gameIsRunning){
        return;
      }
      ken();
      sleep(timePon);
      if(!gameIsRunning){
        return;
      }
      pon();
      sleep(timeAfterPon);
      if(!gameIsRunning){
        return;
      }
      afterPon();
      if(!gameIsRunning){
        return;
      }      
      sleep(timeTilNewGame);
      if(!gameIsRunning){
        return;
      }
      if(result != Result.DRAW){
        break;
      }
//DrawHealBot
      if(bot.getName().equals("DrawHealBot")){
        bot.setHitPoint(10);
        fragment.showPopup("Bot Healed full HP!!!", 300);
        fragment.showBotHealth(bot.getHitPoint());
      }

    }
  }

  public void gamePerBot(){
    bot =(AbstractBot) BotManager.getManager().next();
    bot.setHitPoint(10);
 
    fragment.showBot(bot);
    fragment.showPopup(bot.getName(), 1000);
    fragment.setMaxHP(user.getHitPoint(), bot.getHitPoint());
    fragment.setHP(user.getHitPoint(), bot.getHitPoint());
    // fragment.showUserHealthText(user.getHitPoint());
    // fragment.showBotHealthText(bot.getHitPoint());

    SoundManager.getSoundManager().changeBgm(bot);
    SoundManager.getSoundManager().changeSoundpool(bot);
 
    while((user.getHitPoint() > 0) && (bot.getHitPoint() > 0)){
      game();
    }
    if(bot.getHitPoint() <= 0){
      //fragment.showResult("YOU WIN!!!");
      fragment.showPopup("you win!!!", 700);
      SoundManager.getSoundManager().win();
      user.setHitPoint(user.getHitPoint()+10);
//luckeyBot
      if(bot.getName().equals("LuckeyBot")){
        user.setHitPoint(user.getHitPoint()+10);
      }
      //sleep(1000);
    }
    if(user.getHitPoint() <= 0){
      //fragment.showResult("YOU LOSE!!!");
      fragment.showPopup("you lose!!!", 700);
      SoundManager.getSoundManager().lose();
      //sleep(1000);
    }
  }

  public int damage(){
    return (int)(Math.random()*4)+2;
  }

  public void setUserHand(Hand hand){
    this.userHand = hand;
  }

  public void setBotHand(Hand hand){
    botHand = hand;
    fragment.showBotHand(hand);
  }
 
  public void killGameThread(){
    //gameThread.stop();
    gameIsRunning = false;
  }
    
  public Judge getJudge(){
    return judge;
  }

  public User getUser(){
    return user;
  }

  public AbstractBot getBot(){
    return bot;
  }

  public Hand getUserHand(){
    return userHand;
  }

  public Hand getBotHand(){
    return botHand;
  }

  public Result getResult(){
    return result;
  }

  public JankenFragment getFragment(){
    return fragment;
  }
}
