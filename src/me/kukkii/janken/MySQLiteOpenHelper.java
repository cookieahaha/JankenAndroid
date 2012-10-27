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

  public String getResultAsString(int userHp, int botHp, AbstractBot bot, Hand userHand, Hand botHand, Result result){
    String text = "userHp: " + userHp + "   botHP: " + botHp + "\n" + bot.getName() + ":" + userHand.toString() + ":" + botHand.toString() + ":" + result.toString() + "\n" + history(result) + "\n";
    return text;
  }

}
