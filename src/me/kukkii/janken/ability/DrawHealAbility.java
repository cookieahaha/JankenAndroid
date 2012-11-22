package me.kukkii.janken.ability;

import me.kukkii.janken.GameManager;
import me.kukkii.janken.bot.AbstractBot;
import me.kukkii.janken.JankenFragment;
import me.kukkii.janken.User;
import me.kukkii.janken.Result;

public class DrawHealAbility extends AbstractAbility{

  public void applyAfterPon(GameManager gm){
    if(gm.getResult() == Result.DRAW){
      AbstractBot bot = gm.getBot();
      JankenFragment fragment = gm.getFragment();

      bot.setHitPoint(10);
      fragment.showPopup("Bot healed full HP!!!", 300);
    }
    else{
    }
  }
}
