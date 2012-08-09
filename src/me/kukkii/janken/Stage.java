// $Id$

package me.kukkii.janken;

import java.io.Serializable;

import me.kukkii.janken.bot.AbstractBot;

public class Stage implements Serializable {

  private int id;
  private String name;
  private AbstractBot bot;
  private int point;
  private StageStatus status;

  public Stage(int id, String name) {
    this.id = id;
    this.name = name;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public AbstractBot getBot() {
    return bot;
  }

  public void setBot(AbstractBot  bot) {
    this.bot = bot;
  }

  public int getPoint() {
    return point;
  }

  public void setPoint(int point) {
    this.point = point;
  }

  public StageStatus getStatus() {
    return status;
  }

  public void setStatus(StageStatus status) {
    this.status = status;
  }

}
