package me.kukkii.janken;

import me.kukkii.janken.bot.AbstractBot;
import me.kukkii.janken.bot.BotManager;

public class GameManager{

  private Janken activity;
  private MySQLiteOpenHelper dataManager;

  private Judge judge;
  private AbstractBot bot;
  private Hand userHand;
  private Hand botHand;
  private Result result;

  private static final int timeJan = 2500;
  private static final int timeKen = 1000;
  private static final int timePon = 1000;
  private static final int timeAfterPon = 50;
  private static final int timeTilNewGame = 2500;
  private static final int timeTilPon = timeJan + timeKen + timePon;

  public GameManager(Janken activity, MySQLiteOpenHelper dataManager) {
    this.activity = activity;
    this.dataManager = dataManager;
    startGame();
  }

  private void startGame(){
    userHand = Hand.UNKNOWN;
    bot =(AbstractBot) BotManager.getManager().next();
    activity.showBot(bot);

    new Thread(new Runnable() {
      public void run() {
        game();
      }
    }).start();

    new Thread(new Runnable() {
      public void run() {
        sleep(timeTilPon);
        setBotHand( bot.hand2() );
      }
    }).start();
  }

  public void sleep(int msec) {
    try{
      Thread.sleep(msec);
    }
    catch (InterruptedException e){
    }
  }

  public void jan(){
    activity.showJan();
  }

  public void ken(){
    activity.showKen();
  }
   
  public void pon(){
    activity.showPon();
  }

  public void afterPon(){
    if(! activity.isResumed()){
      return;
    }
    result = judge.judge(userHand, botHand);
    dataManager.writeResultToSQL(result);
    activity.showResult(dataManager.getResultAsString(bot,userHand,botHand,result));
  }

  public void game() {
    sleep(timeJan);
    jan();
    sleep(timeKen);
    ken();
    sleep(timePon);
    pon();
    sleep(timeAfterPon);
    afterPon();
    if(! activity.isResumed()){
      return;
    }
    sleep(timeTilNewGame);
    if(! activity.isResumed()){
      return;
    }
    startGame();
  }

  public void setUserHand(Hand hand){
    this.userHand = hand;
  }

  public void setBotHand(Hand hand){
    botHand = hand;
    activity.showBotHand(hand);
  }

}
