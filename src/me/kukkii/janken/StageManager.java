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
      int id = i+1;
      String name = String.format("Stage %d", id);
      Stage stage = new Stage(id, name);
      stages[i] = stage;
      // TODO: needs to update BotManager.
      stage.setBot( (AbstractBot) BotManager.getManager().next() );
      stage.setPoint(id);
    }
  }

  private void initStatus() {
    MySQLiteOpenHelper dataManager = MySQLiteOpenHelper.getHelper();
    StageStatus[] status = dataManager.getStageStatus();
    for (int i = 0; i < status.length; i++) {
      stages[i].setStatus(status[i]);
    }

    // for testing
   /*
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
   */
    //
  }

  // ID starts with 1.
  public Stage getStage(int id) {
    return stages[id - 1];
  }

  public int getNumberOfStages() {
    return stages.length;
  }

}
