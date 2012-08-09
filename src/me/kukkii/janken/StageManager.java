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
    initStatus();
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

  private void initStatus() {
    // for test data
    // TODO: gets from SQLite.
    for (int i = 0; i < stages.length; i++) {
      if (i < 5) {
        if (i % 3 == 0) {
          stages[i].setStatus(StageStatus.PERFECT);
        }
        else {
          stages[i].setStatus(StageStatus.CLEARED);
        }
      }
      else if (i == 5) {
        stages[i].setStatus(StageStatus.CURRENT);
      }
      else {
        stages[i].setStatus(StageStatus.LOCKED);
      }
    }
  }

  public Stage getStage(int id) {
    return stages[id];
  }

  public int getNumberOfStages() {
    return stages.length;
  }

}
