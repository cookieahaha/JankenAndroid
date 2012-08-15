// $Id$
package me.kukkii.janken;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;


public class SoundFragment extends Fragment implements OnClickListener {
  
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    return inflater.inflate(R.layout.sound_fragment, container, false);
  }

 private boolean bgmOn;
  
 public void onStart(){
   super.onStart();
   Button button = (Button)getActivity().findViewById(R.id.button_sound);
   button.setOnClickListener(this);
   
   bgmOn = true;
   SoundManager.getSoundManager().startBgm();
 }
 
 public void onClick(View view){
   if(bgmOn){
     bgmOn = false;
     SoundManager.getSoundManager().stopBgm();
   }
   if(!bgmOn){
     bgmOn = true;
     SoundManager.getSoundManager().startBgm();
   }
 }
 
  public void onResume(){
    super.onResume();
  }

  public void onPause(){
    super.onPause();
  }  
  
}