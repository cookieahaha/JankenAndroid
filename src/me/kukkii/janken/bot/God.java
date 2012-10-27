// $Id$

package me.kukkii.janken.bot;
import me.kukkii.janken.R;

import android.graphics.drawable.Drawable;

public class God extends AbstractBot {

  public God(){
    super();
  }

  public God(long id, String name){
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

  public int getSong(){
    return R.raw.boss;
  }
  
  public int getJan(){
    return R.raw.janken2;
  }

  public int getKen(){
    return R.raw.janken2;

  }

  public int getPon(){
    return R.raw.janken2;
  }
  
}
