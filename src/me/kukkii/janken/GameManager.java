package me.kukkii.janken;


public class GameManager{

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

  public void newGame(){
    userHand = Hand.UNKNOWN;
    bot =(AbstractBot) BotManager.getManager().next();
    Resources res = getResources();
    final int drawableId = bot.getImage();
    final ImageView view = (ImageView) findViewById(R.id.view_BOT);
    runOnUiThread(new Runnable() {
      public void run() {
        view.setImageResource(drawableId);
      }
    });

    Runnable tt = new Runnable() {
      public void run() {
        game();
      }
    };
    Thread t = new Thread(tt);
    t.start();

     Runnable botRun = new Runnable() {
      public void run() {
        sleep(timeTilPon);
        botHand(bot.hand2());
      }
    };
    Thread botThread = new Thread(botRun);
    botThread.start();
  }

  public void sleep(int msec) {
    try{
      Thread.sleep(msec);
    }
    catch (InterruptedException e){
    }
  }

  public void jan(Activity a){
    a.showResultOnUiThread("Jan");
  }

  public void ken(Activity a){
    a.showResultOnUiThread("Ken");
  }
   
  public void pon(Activity a){
    a.showResultOnUiThread("Pon");
  }

  public void afterPon(Activity ai,){
    if(resumed == false){
      return;
    }
    result = judge.judge(userHand, botHand);
    hlpr.writeResultToSQL(result);
    showResultOnUiThread(hlpr.getResultAsString(bot,userHand,botHand,result));
    sleep(timeTilNewGame);
    newGame();
  }

  public void game(Activity a) {
    sleep(timeJan);
    jan(a);
    sleep(timeKen);
    ken(a);
    sleep(timePon);
    pon(a);
    sleep(timeAfterPon);
    afterPon(a);
  }

  public void hand(int id){
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

  public void botHand(Hand hand){
    botHand = hand;
    int botHandImage = 0;
    if(botHand == hand.ROCK){
      botHandImage = R.drawable.hand_rock;
    }
    if(botHand == hand.SCISSOR){
      botHandImage = R.drawable.hand_scissor;
    }
    if(botHand == hand.PAPER){
      botHandImage = R.drawable.hand_paper;
    }
    Resources res = getResources();
    final int drawableId = botHandImage;
    final ImageView view = (ImageView) findViewById(R.id.view_BOT);
    runOnUiThread(new Runnable() {
      public void run() {
        view.setImageResource(drawableId);
      }
    });
  }


}
