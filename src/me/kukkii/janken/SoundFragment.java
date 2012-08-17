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
  
  private SoundManager soundManager = null;
     
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance){
    soundManager = soundManager.getSoundManager();
    if (soundManager == null) {
      SoundManager.setContext( getActivity() );
      soundManager = SoundManager.getSoundManager();
    }
    return inflater.inflate(R.layout.sound_fragment, container, false);
  }
 
  public void onStart(){
    super.onStart();
    Button button = (Button)getActivity().findViewById(R.id.button_bgm);
    button.setOnClickListener(this);
    Button button2 = (Button)getActivity().findViewById(R.id.button_sound);
    button2.setOnClickListener(this);
   
    soundManager.setBgmIsOn(true);
    soundManager.startBgm();
  }
 
  public void onClick(View view){
    int id = view.getId();
    if(id == R.id.button_bgm){
      if(soundManager.getBgmIsOn()){
        soundManager.setBgmIsOn(false);
        soundManager.stopBgm();
      }
      else{
        soundManager.setBgmIsOn(true);
        soundManager.startBgm();
      }
    }
    if(id == R.id.button_sound){
      if(soundManager.getSoundpoolIsOn()){
        soundManager.setSoundpoolIsOn(false);
        return;
      }
      else{
        soundManager.setSoundpoolIsOn(true);
      }
    }
  }
 
  public void onResume(){
    super.onResume();
  }

  public void onPause(){
    super.onPause();
  }  
  
}