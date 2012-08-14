package me.kukkii.janken;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

public class MainActivity extends FragmentActivity{

  public void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);

    Log.i("cointoss", "view for main_fragment=" + findViewById(R.id.main_fragment));
    if (findViewById(R.id.main_fragment) != null) {
      if (savedInstanceState != null) {
        return;
      }

      MenuFragment fragment = new MenuFragment();
            
      getSupportFragmentManager().beginTransaction().add(R.id.main_fragment, fragment).commit();
    }
  }

}
