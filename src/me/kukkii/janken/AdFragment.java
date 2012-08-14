package me.kukkii.janken;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.os.Bundle;

import com.google.ads.AdRequest;
import com.google.ads.AdView;

public class AdFragment extends Fragment{

  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {    super.onCreate(savedInstanceState);
    Activity activity = getActivity();
    AdView adView = (AdView)activity.findViewById(R.id.adView);
    adView.loadAd(new AdRequest());
    return inflater.inflate(R.layout.ad_fragment, container, false);

  }

}