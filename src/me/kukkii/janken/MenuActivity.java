package me.kukkii.janken;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MenuActivity extends Activity {



  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
  }

  public void back(View view) {
    Intent intent = new Intent(this, Janken.class);
    startActivity(intent);
  }

}
