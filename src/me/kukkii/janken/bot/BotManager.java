// $Id$

package me.kukkii.janken.bot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.kukkii.janken.Player;
import me.kukkii.janken.ability.HealAbility;
import me.kukkii.janken.ability.DamageAbility;
import me.kukkii.janken.ability.Damage2Ability;
import me.kukkii.janken.ability.TransferAbility;
import me.kukkii.janken.ability.LuckeyAbility;


public class BotManager {

  // singleton
  private static BotManager manager = new BotManager();

  public static BotManager getManager() {
    return manager;
  }

  //
  private List<Player> bots;
  private Map<String, Player> map;

  private BotManager(){
    bots = new ArrayList<Player>();
    map = new HashMap<String, Player>();
  }

  private void add(Player bot) {
    bots.add(bot);
    map.put(bot.getName(), bot);
  }

  private void addLvl1(){
    add(new TimingBot());
    add(new Rock100Bot());
    add(new Scissor100Bot());
    add(new Paper100Bot());
    add(new RotationBot());
  }

  private void addLvl2(){
    add(new RotationBot2());
    add(new TimingBot2());
    add(new God());
    add(new RandomBot());
  }

  private void addLvl3(){
    add(new God());
  }

  public Player next(int winAmount) {
    if(winAmount == 0){
      bots = new ArrayList<Player>();
      addLvl1();
    }
    else if(winAmount == 5){
      bots = new ArrayList<Player>(); 
      addLvl2();
    }
    else if(winAmount == 10){
      bots = new ArrayList<Player>(); 
      addLvl3();

    }
    else if(winAmount == 30){

    }
    else if(winAmount == 40){

    }
    else if(winAmount == 50){

    }
    AbstractBot bot = (AbstractBot) bots.get( (int)(Math.random() * bots.size()) );
    DamageAbility h = new DamageAbility();
    bot.addAbility(h);
    return bot;
  }

  public Player getBot(String name) {
    return map.get(name);
  }
}
