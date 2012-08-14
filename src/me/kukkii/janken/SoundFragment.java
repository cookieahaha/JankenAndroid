// $Id$
package me.kukkii.janken;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.ads.AdRequest;
import com.google.ads.AdView;

public class SoundFragment extends Fragment {

  
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    return inflater.inflate(R.layout.sound_fragment, container, false);
  }

  public void onResume(){
    super.onResume();
  }

  public void onPause(){
    super.onPause();
  }  
  
}