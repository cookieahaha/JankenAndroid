package me.kukkii.janken;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.View;

import com.google.ads.AdRequest;
import com.google.ads.AdView;

public class AdFragment extends Fragment implements View.OnClickListener {

  public void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    Activity activity = getActivity();
    AdView adView = (AdView)activity.findViewById(R.id.adView1);
    adView.loadAd(new AdRequest());
  }

}