// $Id$

package me.kukkii.janken.bot;

import me.kukkii.janken.Hand;
import android.graphics.drawable.Drawable;
import me.kukkii.janken.R;

public class Paper100Bot extends AbstractBot {

  public Paper100Bot(){
    super();
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

}

