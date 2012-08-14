package me.kukkii.janken;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.os.Bundle;

import com.google.ads.AdRequest;
import com.google.ads.AdView;

public class AdFragment extends Fragment{

  public void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    Activity activity = getActivity();
    AdView adView = (AdView)activity.findViewById(R.id.adView1);
    adView.loadAd(new AdRequest());
  }

}