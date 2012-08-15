// $Id$
package me.kukkii.janken;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class MenuFragment extends Fragment implements OnClickListener{

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    Activity activity = getActivity();
    MySQLiteOpenHelper.setContext(activity.getApplicationContext());
    return inflater.inflate(R.layout.menu_fragment, container, false);
  }

  public void onStart(){
    super.onStart();
    Button button = (Button)getActivity().findViewById(R.id.button_back);
    button.setOnClickListener(this);
    Button button2 = (Button)getActivity().findViewById(R.id.button_botlist);
    button2.setOnClickListener(this);
  }
  
  public void onResume(){
	super.onResume();
  }
  
  public void onPause(){
    super.onPause();
  }  
  
  public void startMain(View view) {
    JankenFragment fragment = new JankenFragment();

    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
    transaction.replace(R.id.main_fragment, fragment);
    transaction.addToBackStack(null);

    // Commit the transaction
    transaction.commit();
  }
  
  public void startBotList(View view) {
    BotListFragment fragment = new BotListFragment();
    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
    transaction.replace(R.id.main_fragment, fragment);
    transaction.addToBackStack(null);

    // Commit the transaction
    transaction.commit();
  }
  
  public void onClick(View view){
    int id = view.getId();
    if(id == R.id.button_back){
      startMain(view);
    }
    if(id == R.id.button_botlist){
      startBotList(view);
    }
  }
}
