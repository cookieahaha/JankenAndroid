// $Id$
package me.kukkii.janken;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import me.kukkii.janken.bot.AbstractBot;

public class MySQLiteOpenHelper extends SQLiteOpenHelper {

  // singleton
  private static Context _context = null;
  private static MySQLiteOpenHelper helper = null;

  public static void setContext(Context context) {
    _context = context;
    helper = new MySQLiteOpenHelper(context);
  }

  public static MySQLiteOpenHelper getHelper() {
    return helper;
  }

  private static final String DB_NAME = "sqlite_sample.db";  
  private static final int DB_VERSION = 1;         

  private static final String CREATE_LOGTABLE = "create table logtable(result INT);";
  // private static final String DROP_LOGTABLE = "drop table logtable;";
  private static final String CREATE_STAGESTATUS_TABLE = "create table stagestatustable(id INT, status INT);";
  // private static final String DROP_STAGESTATUS_TABLE = "drop table stagestatustable;";

  private static final int NUM_STAGES = 20;

  private int numberOfWin = 0;
  private int numberOfDraw = 0;
  private int numberOfLose = 0;
  private SQLiteDatabase mydb;

  public MySQLiteOpenHelper(Context mContext){
    super(mContext,DB_NAME,null,DB_VERSION);
  }

  public MySQLiteOpenHelper(Context context, String name,
    CursorFactory factory, int version) {
    super(context, name, factory, version);
  }
 
  public void onCreate(SQLiteDatabase db) {
    db.execSQL(CREATE_LOGTABLE);
    db.execSQL(CREATE_STAGESTATUS_TABLE);
  }

  public void initStatus() {
    insertStatus(1, StageStatus.CURRENT);
    for (int id = 2; id <= NUM_STAGES; id++) {
      insertStatus(id, StageStatus.LOCKED);
    }
  }

  // for testing
  public void initStatusForTest() {
    for (int id = 1; id <= NUM_STAGES; id++) {
      if (id < 7) {
        if (id % 3 == 1) {
          insertStatus(id, StageStatus.PERFECT);
        }
        else {
          insertStatus(id, StageStatus.CLEARED);
        }
      }
      else if (id == 7) {
        insertStatus(id, StageStatus.CURRENT);
      }
      else {
        insertStatus(id, StageStatus.LOCKED);
      }
    }
  }
 
  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
  }

  public void readSQL(){
    SQLiteDatabase radb = this.getReadableDatabase();
    Cursor cursor = radb.query("logtable", new String[] {"result"}, null, null, null, null, null, null);
    while(cursor.moveToNext()){
      int n = cursor.getInt(0);
      if(n == Result.WIN.value()){
        numberOfWin += 1;
      }
      if(n == Result.LOSE.value()){
        numberOfLose += 1;
      }
      if(n == Result.DRAW.value()){
        numberOfDraw += 1;
      }
    }
  }

  public void writeResultToSQL(Result result){
    ContentValues values = new ContentValues();
    values.put("result", result.value());
    mydb = this.getWritableDatabase();
    mydb.insert("logtable", null, values);
  }

  public String history(Result result){
    if(result == Result.WIN){
      numberOfWin += 1;
    }
    if(result == Result.LOSE){
      numberOfLose += 1;
    }
    if(result == Result.DRAW){
      numberOfDraw += 1;
    }
    String history = numberOfWin + " win, " + numberOfLose + " lose, " + numberOfDraw + " draw";
    return history;
  }

  public String getResultAsString(AbstractBot bot, Hand userHand, Hand botHand, Result result){
    String text = bot.getName() + ":" + userHand.toString() + ":" + botHand.toString() + ":" + result.toString() + "\n" + history(result);
    return text;
  }

  public StageStatus[] getStageStatus() {
    if (getStageStatus(1) == null) {
      // initStatus();
      initStatusForTest();
    }
    StageStatus[] status = new StageStatus[NUM_STAGES];
    SQLiteDatabase radb = this.getReadableDatabase();
    Cursor cursor = radb.query("stagestatustable", new String[] {"id", "status"}, null, null, null, null, null, null);
    if (cursor == null) {
      return status;
    }
    while(cursor.moveToNext()){
      int id = cursor.getInt(0);
      int st = cursor.getInt(1);
      status[id - 1] = StageStatus.get(st);
    }
    return status;
  }

  public StageStatus getStageStatus(int id) {
    SQLiteDatabase radb = this.getReadableDatabase();
    int st = 0;
    try {
      Cursor cursor = radb.query("stagestatustable", new String[] {"id", "status"}, "id=?", new String[] { String.valueOf(id) }, null, null, null, null);

      if (cursor == null) {
        return null;
      }
      cursor.moveToFirst();
      // int id = cursor.getInt(0);
      st = cursor.getInt(1);
    }
    catch (Exception e) {
      return null;
    }
    StageStatus stat  = StageStatus.get(st);
    return stat;
  }

  public void updateStatus(int id, StageStatus stat) {
    ContentValues values = new ContentValues();
    // values.put("id", id);
    values.put("status", stat.value());
    mydb = this.getWritableDatabase();
    mydb.update("stagestatustable", values, "id=?", new String[] { String.valueOf(id) });
  }

  public void insertStatus(int id, StageStatus stat) {
    ContentValues values = new ContentValues();
    values.put("id", id);
    values.put("status", stat.value());
    mydb = this.getWritableDatabase();
    mydb.insert("stagestatustable", null, values);
  }

  public void setStatus(int id, StageStatus stat) {
    updateStatus(id, stat);
   /*
    if (getStatus(id) == null) {
      insertStatus(id, stat);
    }
    else {
      updateStatus(id, stat);
    }
   */
  }

}
