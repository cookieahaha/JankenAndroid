// $Id$

package me.kukkii.janken.bot;
import me.kukkii.janken.Hand;
import me.kukkii.janken.R;

import android.graphics.drawable.Drawable;

public class TimingBot extends AbstractBot {

  public TimingBot(){
    super();
  }

  public TimingBot(long id, String name){
    super(id, name);
  }

  public int hand(){
    int bot = (int)(Math.random()*3);
    return bot;
  }

  public int hand(int other) {
    if (other < 0) {
      return hand();
    }
    return (other + 2) % 3;
  }

  public int getImage(){
    return R.drawable.girl_yellow;
  }
  
  public int getTiming(){
	return -1000;  
  }
  
  public int getSong(){
    return R.raw.bgm1;
  }
  
  public int getJan(){
    return R.raw.janken;
  }

  public int getKen(){
    return R.raw.janken;

  }

  public int getPon(){
    return R.raw.janken;
  }
  
}

