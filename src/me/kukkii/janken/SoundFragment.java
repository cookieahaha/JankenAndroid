// $Id$
package me.kukkii.janken;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageButton;


public class SoundFragment extends Fragment implements OnClickListener {
  
  private SoundManager soundManager = null;
  private ImageButton button;
  private ImageButton button2;
  
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
    button = (ImageButton)getActivity().findViewById(R.id.button_bgm);
    button.setImageResource(soundManager.getBgmIsOn()?R.drawable.sound_on_120px_vista_kmixdocked:R.drawable.sound_off_120px_vista_kmixdocked_error);
    button.setOnClickListener(this);
    button2 = (ImageButton)getActivity().findViewById(R.id.button_sound);
    button2.setImageResource(soundManager.getSoundpoolIsOn()?R.drawable.sound_on_120px_vista_kmixdocked:R.drawable.sound_off_120px_vista_kmixdocked_error);
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
      button.setImageResource(soundManager.getBgmIsOn()?R.drawable.sound_on_120px_vista_kmixdocked:R.drawable.sound_off_120px_vista_kmixdocked_error);
    }
    if(id == R.id.button_sound){
      if(soundManager.getSoundpoolIsOn()){
        soundManager.setSoundpoolIsOn(false);
      }
      else{
        soundManager.setSoundpoolIsOn(true);
      }
      button2.setImageResource(soundManager.getSoundpoolIsOn()?R.drawable.sound_on_120px_vista_kmixdocked:R.drawable.sound_off_120px_vista_kmixdocked_error);
    }
  }
 
  public void onResume(){
    super.onResume();
  }

  public void onPause(){
    super.onPause();
  }  
  
}