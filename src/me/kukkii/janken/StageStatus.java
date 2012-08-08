// $Id$

package me.kukkii.janken;

public enum StageStatus {

  LOCKED (0),
  CURRENT (1),
  CLEARED (2),
  PERFECT (3);

  private int value;

  private StageStatus(int value) {
    this.value = value;
  }

  public int value() {
    return value;
  }

  public static StageStatus get(int value) {
    switch (value) {
    case 0 :
      return LOCKED;
    case 1 :
      return CURRENT;
    case 2 :
      return CLEARED;
    case 3 :
      return PERFECT;
    default :
      return LOCKED;
    }
  }
}
