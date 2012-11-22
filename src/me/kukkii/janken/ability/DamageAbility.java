package me.kukkii.janken.ability;

import me.kukkii.janken.GameManager;
import me.kukkii.janken.bot.AbstractBot;
import me.kukkii.janken.JankenFragment;
import me.kukkii.janken.User;

public class DamageAbility extends AbstractAbility{

  public void applyAfterPon(GameManager gm){
      AbstractBot bot = gm.getBot();
      User user = gm.getUser();
      JankenFragment fragment = gm.getFragment();

      int damage = (int)(Math.random()*3)+1;
      user.setHitPoint(user.getHitPoint() - damage);
      fragment.showPopup("Bot hit " + damage  + "!!!", 300);
    }

}
