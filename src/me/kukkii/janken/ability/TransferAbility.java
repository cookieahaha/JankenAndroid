package me.kukkii.janken.ability;

import me.kukkii.janken.GameManager;
import me.kukkii.janken.bot.AbstractBot;
import me.kukkii.janken.JankenFragment;
import me.kukkii.janken.User;

public class TransferAbility extends AbstractAbility{

  public void applyAfterPon(GameManager gm){
      AbstractBot bot = gm.getBot();
      User user = gm.getUser();
      JankenFragment fragment = gm.getFragment();
     
      int temp = user.getHitPoint();
      user.setHitPoint(bot.getHitPoint());
      bot.setHitPoint(temp);
      fragment.showPopup("HP swapped!!!", 300);
  }

}
