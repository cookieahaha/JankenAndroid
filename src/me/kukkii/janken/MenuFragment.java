// $Id$
package me.kukkii.janken;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.ads.AdRequest;
import com.google.ads.AdView;

public class MenuFragment extends Fragment {

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    Activity activity = getActivity();
    MySQLiteOpenHelper.setContext(activity.getApplicationContext());
    return inflater.inflate(R.layout.main_fragment, container, false);
  }

  public void onResume(){
	super.onResume();
  }
  
  public void onPause(){
    super.onPause();
  }  
  
  public void startMain(View view) {
    JankenFragment fragment = new JankenFragment();
    Bundle args = new Bundle();
    args.putSerializable("type", type);
    fragment.setArguments(args);

    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
    transaction.replace(R.id.main_fragment, fragment);
    transaction.addToBackStack(null);

    // Commit the transaction
    transaction.commit();
  }
  
  public void startBotList(View view) {
    BotListFragment fragment = new BotListFragment();
    Bundle args = new Bundle();
    args.putSerializable("type", type);
    fragment.setArguments(args);

    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
    transaction.replace(R.id.botlist_fragment, fragment);
    transaction.addToBackStack(null);

    // Commit the transaction
    transaction.commit();
  }
  
}
