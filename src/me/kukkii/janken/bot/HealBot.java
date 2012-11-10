
package me.kukkii.janken.bot;

import me.kukkii.janken.R;

import android.graphics.drawable.Drawable;

public class HealBot extends AbstractBot {

  public HealBot(){
    super();
  }

  public HealBot(long id, String name){
    super(id, name);
  }

  public int hand(){
    int bot = (int)(Math.random()*3);
    return bot;
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


}

