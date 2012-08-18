


//$Id$
package me.kukkii.janken;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageButton;


public class BackToMenuFragment extends Fragment implements OnClickListener {
  
 @Override
 public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance){
   return inflater.inflate(R.layout.backtomenu_fragment, container, false);
 }

 public void onStart(){
   super.onStart();
   ImageButton button = (ImageButton)getActivity().findViewById(R.id.button_backtomenu);
   button.setOnClickListener(this);
 }

 public void backToMenu(View view) {
   MenuFragment fragment = new MenuFragment();

   FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
   transaction.replace(R.id.main_fragment, fragment);
   transaction.addToBackStack(null);

   // Commit the transaction
   transaction.commit();
 }
 
 public void onClick(View view){
   backToMenu(view);
 }

 public void onResume(){
   super.onResume();
 }

 public void onPause(){
   super.onPause();
 }  
 
}