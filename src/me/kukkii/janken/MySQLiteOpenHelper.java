package me.kukkii.janken;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import me.kukkii.janken.bot.AbstractBot;

public class MySQLiteOpenHelper extends SQLiteOpenHelper {
    static final String DB_NAME = "sqlite_sample.db";  
    static final int DB_VERSION = 1;         

    private int numberOfWin = 0;
    private int numberOfDraw = 0;
    private int numberOfLose = 0;
    private SQLiteDatabase mydb;

    static String CREATE_TABLE = "create table logtable(result INT);";
    static final String DROP_TABLE = "drop table logtable;";
 
    public MySQLiteOpenHelper(Context mContext){
        super(mContext,DB_NAME,null,DB_VERSION);
    }
 
    public MySQLiteOpenHelper(Context context, String name,
        CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
 
    // DB縺悟ｭ伜惠縺励↑縺�憾諷九〒Open縺吶ｋ縺ｨ縲｛nCreate縺後さ繝ｼ繝ｫ縺輔ｌ繧�    // 譁ｰ隕丈ｽ懈�縺輔ｌ縺櫂B縺ｮ繧､繝ｳ繧ｹ繧ｿ繝ｳ繧ｹ縺御ｻ倅ｸ弱＆繧後ｋ縺ｮ縺ｧ縲√ユ繝ｼ繝悶Ν繧剃ｽ懈�縺吶ｋ縲�    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }
 
    // 繧ｳ繝ｳ繧ｹ繝医Λ繧ｯ繧ｿ縺ｧ謖�ｮ壹＠縺溘ヰ繝ｼ繧ｸ繝ｧ繝ｳ縺ｨ縲∝盾辣ｧ蜈医�DB縺ｮ繝舌�繧ｸ繝ｧ繝ｳ縺ｫ蟾ｮ逡ｰ縺後≠繧九→縺阪↓繧ｳ繝ｼ繝ｫ縺輔ｌ繧�    // 莉雁屓繝舌�繧ｸ繝ｧ繝ｳ縺ｯ�大崋螳壹�縺溘ａ縲∝�逅��陦後ｏ縺ｪ縺��
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
