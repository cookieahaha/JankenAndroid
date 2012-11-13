package me.kukkii.janken.ability;

import me.kukkii.janken.GameManager;

public interface Ability{
  public void applyBegin(GameManager gm);

  public void applyAfterPon(GameManager gm);

  public void applyEnd(GameManager gm);

}
