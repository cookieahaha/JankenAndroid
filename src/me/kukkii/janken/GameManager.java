package me.kukkii.janken;

import me.kukkii.janken.bot.AbstractBot;
import me.kukkii.janken.bot.BotManager;

public class GameManager{

  private JankenFragment fragment;
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

  private Thread gameThread;
  
  private boolean gameIsRunning;
  private Stage stage = null;
  
  public GameManager(JankenFragment activity, MySQLiteOpenHelper dataManager, Stage stage) {
    this.fragment = activity;
    this.dataManager = dataManager;
    this.stage = stage;
    judge = new Judge();
    startGame();
  }
  
  private void startGame(){
    if(stage == null){   // random mode
      bot =(AbstractBot) BotManager.getManager().next();
     }
     else{  // stage mode
       bot = stage.getBot();
     }
    
    fragment.showBot(bot);
    fragment.showResult(bot.getName());

    gameThread = new Thread(new Runnable() {
      public void run() {
        gameIsRunning = true;
        game();
      }
    });
    gameThread.start();
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
    if(!gameIsRunning){
      return;
    }
    result = judge.judge(userHand, botHand);
    dataManager.writeResultToSQL(result);
    fragment.showResult(dataManager.getResultAsString(bot,userHand,botHand,result));
  }

  public void game() {
    while(true){
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
    }
    startGame();
  }

  public void setUserHand(Hand hand){
    this.userHand = hand;
  }

  public void setBotHand(Hand hand){
    botHand = hand;
    fragment.showBotHand(hand);
  }
 
  public void killGameThread(){
 //   gameThread.stop();
    gameIsRunning = false;
  }
    
}
