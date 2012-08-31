// $Id$

package me.kukkii.janken.bot;

import me.kukkii.janken.Hand;
import android.graphics.drawable.Drawable;
import me.kukkii.janken.R;

public class Paper100Bot extends AbstractBot {
  
  public Paper100Bot(){
    super();
    hp = 3;
  }

  public Paper100Bot(long id, String name){
    super(id, name);
  }

  public int hand(){
    return Hand.PAPER.value();
  }

  public int getImage(){
    return R.drawable.girl_orange;
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
  
  public void setHitPoint(int hp){
    this.hp = hp;
  }
  
  public int getHitPoint(){
    return hp;
  }
}

