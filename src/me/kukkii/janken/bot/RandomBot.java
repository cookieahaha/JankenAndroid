// $Id$

package me.kukkii.janken.bot;

import me.kukkii.janken.R;

import android.graphics.drawable.Drawable;

public class RandomBot extends AbstractBot {

  public RandomBot(){
    super();
  }

  public RandomBot(long id, String name){
    super(id, name);
  }

  public int hand(){
    int bot = (int)(Math.random()*3);
    return bot;
  }

  public int getImage(){
    return R.drawable.girl_orange;
  }

}
