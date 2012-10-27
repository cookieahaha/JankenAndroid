// $Id$

package me.kukkii.janken.bot;

//import java.awt.Image;

import me.kukkii.janken.AbstractPlayer;
import me.kukkii.janken.Hand;
import me.kukkii.janken.Player;
import me.kukkii.janken.R;
//import me.kukkii.janken.gui.ImageManager;
import android.graphics.drawable.Drawable;

abstract public class AbstractBot extends AbstractPlayer {
  
  protected int hp = 10;

  public AbstractBot() {
    super();
    id *= (-1);
  }

  public AbstractBot(long id, String name) {
    super(id, name);
    id *= (-1);
  }

//  public Image getImage() {
//    return ImageManager.getManager().getPlayerImage(this);
//  }

  abstract public int hand() ;

  public int hand(int other) {
    return hand();
  }

  abstract public int getImage();
  
  public int getTiming(){
	  return 0;
  }

  abstract public int getSong();
  
  abstract public int getJan();

  abstract public int getKen();

  abstract public int getPon();


}
