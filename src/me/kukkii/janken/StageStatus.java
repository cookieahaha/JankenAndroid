// $Id$

package me.kukkii.janken;

import java.io.Serializable;

public enum StageStatus implements Serializable {

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

  public String toString() {
    switch (value) {
    case 0 :
      return "Locked";
    case 1 :
      return "Current";
    case 2 :
      return "Cleared";
    case 3 :
      return "Perfect";
    default :
      return "Locked";
    }
  }

}
