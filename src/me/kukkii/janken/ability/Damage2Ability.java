package me.kukkii.janken.ability;

import me.kukkii.janken.GameManager;
import me.kukkii.janken.bot.AbstractBot;
import me.kukkii.janken.JankenFragment;
import me.kukkii.janken.User;

public class Damage2Ability extends AbstractAbility{

  public void applyAfterPon(GameManager gm){
      AbstractBot bot = gm.getBot();
      User user = gm.getUser();
      JankenFragment fragment = gm.getFragment();
      int damage = gm.getDamage();

      if(user.getHitPoint()%2 == 0){
        user.setHitPoint(user.getHitPoint()-(damage*2));
        fragment.showPopup("bot hit " + damage + "!!!", 700);   
      }
  }

}
