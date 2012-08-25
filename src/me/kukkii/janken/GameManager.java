package me.kukkii.janken;

import android.app.Activity;
import android.support.v4.app.FragmentTransaction;
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
  
  private int win;
  private int lose;
  
  public GameManager(JankenFragment activity, MySQLiteOpenHelper dataManager, Stage stage) {
    this.fragment = activity;
    this.dataManager = dataManager;
    this.stage = stage;
    judge = new Judge();
    
    win = 0;
    lose = 0;
    
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
    SoundManager.getSoundManager().changeBgm(bot);
    SoundManager.getSoundManager().changeSoundpool(bot);
    
    if(win == 0 && lose == 0){
      fragment.showResult(bot.getName());
    }
    
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
    fragment.showResult(dataManager.getResultAsString(bot,userHand,botHand,result,stage.getId()));
    
    if(result == Result.WIN){
      win +=1;
    }
    if(result == Result.LOSE){
      lose +=1;
    }    
    if(win == 2){
    //  fragment.showResult("YOU WIN!!!");
      fragment.showPopup("you win!!!");
      SoundManager.getSoundManager().win();

      win = 0;
      lose = 0;      
      sleep(1000);
      
      stage = StageManager.getManager().getStage(stage.getId()+1);      
    }
    if(lose == 2){
     // fragment.showResult("YOU LOSE!!!");
      fragment.showPopup("you lose!!!");
      SoundManager.getSoundManager().lose();

      
      win = 0;
      lose = 0;      
      sleep(1000);
      
      BotListFragment fragment2 = new BotListFragment();
      FragmentTransaction transaction = fragment.getActivity().getSupportFragmentManager().beginTransaction();
      transaction.replace(R.id.main_fragment, fragment2);
      transaction.addToBackStack(null);
      // Commit the transaction
      transaction.commit();
    }
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
