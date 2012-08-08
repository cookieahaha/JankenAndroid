// $Id$
package me.kukkii.janken;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.ads.AdRequest;
import com.google.ads.AdView;

public class SoundActivity extends Activity {

	  public void menu(View view) {
		    Intent intent = new Intent(this, MenuActivity.class);
		    startActivity(intent);
	  }
}