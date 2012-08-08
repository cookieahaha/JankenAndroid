// $Id$

package me.kukkii.janken;

import me.kukkii.janken.bot.AbstractBot;
import me.kukkii.janken.bot.BotManager;

public class StageManager {

  // singleton
  private static StageManager manager = new StageManager();

  public static StageManager getManager() {
    return manager;
  }

  //

  private Stage[] stages;

  public StageManager() {
    this(20);
  }

  public StageManager(int nStages) {
    stages = new Stage[nStages];
    init();
  }

  private void init() {
    for (int i = 0; i < stages.length; i++) {
      String name = String.format("Stage %d", i);
      Stage stage = new Stage(i, name);
      stages[i] = stage;
      // TODO: needs to update BotManager.
      stage.setBot( (AbstractBot) BotManager.getManager().next() );
      stage.setPoint(i+1);
    }
  }

  public Stage getStage(int id) {
    return stages[id];
  }

  public int getNumberOfStages() {
    return stages.length;
  }

}
