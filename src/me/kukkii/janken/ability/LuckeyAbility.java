package me.kukkii.janken.ability;

import me.kukkii.janken.GameManager;
import me.kukkii.janken.bot.AbstractBot;
import me.kukkii.janken.JankenFragment;
import me.kukkii.janken.User;

public class LuckeyAbility extends AbstractAbility{

  public void applyEnd(GameManager gm){
      User user = gm.getUser();
      JankenFragment fragment = gm.getFragment();

      user.setHitPoint(user.getHitPoint()+10);
  }

}
