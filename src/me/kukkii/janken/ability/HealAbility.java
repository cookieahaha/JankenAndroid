package me.kukkii.janken.ability;

import me.kukkii.janken.GameManager;
import me.kukkii.janken.bot.AbstractBot;
import me.kukkii.janken.JankenFragment;

public class HealAbility extends AbstractAbility{

  public void applyAfterPon(GameManager gm){
      AbstractBot bot = gm.getBot();
      JankenFragment fragment = gm.getFragment();

      bot.setHitPoint(bot.getHitPoint() + 1);
      fragment.showPopup("Bot healed " + 1 + "HP!!!", 300);
    }

}
