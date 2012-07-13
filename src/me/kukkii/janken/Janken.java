package me.kukkii.janken;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;

public class Janken extends Activity
{
  public void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
  }

  public void hand(View view){
    Button button = (Button) findViewById(R.id.button_BOT);
    button.setText(((Button)view).getText().toString());
  }
}
