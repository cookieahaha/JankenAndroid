// $Id$

package me.kukkii.janken;

import java.io.Serializable;

public enum Achievement implements Serializable {

  UNKNOWN (-1),
  WIN1 (1),
  WIN10 (2),
  WIN100 (3),
  WIN1000 (4),
  WIN10000 (5),
  WIN100000 (6),
  PLAY10 (12),
  PLAY100 (13),
  PLAY1000 (14),
  PLAY10000 (15),
  PLAY100000 (16),
  SEQWIN2 (102),
  SEQWIN3 (103),
  SEQWIN4 (104),
  SEQWIN5 (105),
  SEQWIN6 (106),
  SEQWIN7 (107),
  SEQWIN8 (108),
  SEQWIN9 (109),
  SEQWIN10 (110);

  private int value;

  private Achievement(int value) {
    this.value = value;
  }

  public int value() {
    return value;
  }

  public static Achievement get(int value) {
    switch (value) {
    case 1 :
      return WIN1;
    case 2 :
      return WIN10;
    case 3 :
      return WIN100;
    case 4 :
      return WIN1000;
    case 5 :
      return WIN10000;
    case 6 :
      return WIN100000;
    case 12 :
      return PLAY10;
    case 13 :
      return PLAY100;
    case 14 :
      return PLAY1000;
    case 15 :
      return PLAY10000;
    case 16 :
      return PLAY100000;
    case 102 :
      return SEQWIN2;
    case 103 :
      return SEQWIN3;
    case 104 :
      return SEQWIN4;
    case 105 :
      return SEQWIN5;
    case 106 :
      return SEQWIN6;
    case 107 :
      return SEQWIN7;
    case 108 :
      return SEQWIN8;
    case 109 :
      return SEQWIN9;
    case 110 :
      return SEQWIN10;
    default :
      return UNKNOWN;
    }
  }
}
