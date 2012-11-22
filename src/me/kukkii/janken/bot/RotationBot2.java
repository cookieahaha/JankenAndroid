// $Id$

package me.kukkii.janken.bot;
import android.graphics.drawable.Drawable;
import me.kukkii.janken.R;

public class RotationBot2 extends AbstractBot {

  private int prevHand = -1;

  public RotationBot2(){
    super();
  }

  public RotationBot2(long id, String name){
    super(id, name);
  }

  public int hand(){
    if (prevHand < 0) {
      prevHand = (int)(Math.random()*3);
    }
    else {
      prevHand = (prevHand + 1) % 3;
    }
    return prevHand;
  }
 
  public int getImage(){
    return R.drawable.girl_yellow2;
  }

  public int getSong(){
    return R.raw.dangeon;
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
