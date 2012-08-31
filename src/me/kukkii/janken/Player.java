// $Id$

package me.kukkii.janken;

//import java.awt.Image;
import java.io.Serializable;

public interface Player extends Serializable {

  public long getId();

  public String getName();

//  public Image getImage();

  public int hand();

  public Hand hand2();

  public int hand(int other);

  public Hand hand2(Hand other);

  public void setHitPoint(int hp);
  
  public int getHitPoint();
}
