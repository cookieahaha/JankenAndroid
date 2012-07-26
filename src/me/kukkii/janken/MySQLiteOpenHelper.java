package me.kukkii.janken;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;;
import me.kukkii.janken.bot.AbstractBot;

public class MySQLiteOpenHelper extends SQLiteOpenHelper {
    static final String DB_NAME = "sqlite_sample.db";   // DB名
    static final int DB_VERSION = 1;                // DBのVersion

    private int numberOfWin = 0;
    private int numberOfDraw = 0;
    private int numberOfLose = 0;
    private SQLiteDatabase mydb;

    // SQL文をStringに保持しておく
    static String CREATE_TABLE = "create table logtable(result INT);";
    static final String DROP_TABLE = "drop table logtable;";
 
    // コンストラクタ
    // CREATE用のSQLを取得する
    public MySQLiteOpenHelper(Context mContext){
        super(mContext,DB_NAME,null,DB_VERSION);
    }
 
    public MySQLiteOpenHelper(Context context, String name,
        CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
 
    // DBが存在しない状態でOpenすると、onCreateがコールされる
    // 新規作成されたDBのインスタンスが付与されるので、テーブルを作成する。
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }
 
    // コンストラクタで指定したバージョンと、参照先のDBのバージョンに差異があるときにコールされる
    // 今回バージョンは１固定のため、処理は行わない。
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

}
