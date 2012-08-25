// $Id$

package me.kukkii.janken.bot;

import me.kukkii.janken.Hand;
import android.graphics.drawable.Drawable;
import me.kukkii.janken.R;

public class Scissor100Bot extends AbstractBot {

  public Scissor100Bot(){
    super();
  }

  public Scissor100Bot(long id, String name){
    super(id, name);
  }

  public int hand(){
    return Hand.SCISSOR.value();
  }

  public int getImage(){
    return R.drawable.girl_safari;
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
