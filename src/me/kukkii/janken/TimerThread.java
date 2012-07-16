package me.kukkii.janken;

public class TimerThread implements Runnable{

  private Janken janken;

  public TimerThread(Janken janken){
    this.janken = janken;
  }

  public void run(){
    try{
      Thread.sleep(3000);
    }
    catch (InterruptedException e){
    }   
    janken.jan();
    try{
      Thread.sleep(500);
    }
    catch (InterruptedException e){
    }   
    janken.ken();
    try{
      Thread.sleep(500);
    }
    catch (InterruptedException e){
    }   
    janken.pon();
    try{
      Thread.sleep(50);
    }
    catch (InterruptedException e){
    }   
    janken.afterPon();
  }

}
