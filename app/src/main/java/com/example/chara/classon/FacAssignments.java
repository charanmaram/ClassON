package com.example.chara.classon;

/**
 * Created by charan on 4/19/2017.
 * <p>
 * Class for viewing the assignments of the students in Faculty Interface
 */

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;


public class FacAssignments extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Returns the view of the fragment FacAssignments
        View view = inflater.inflate(R.layout.fac_assignments, container, false);

        // Url of the link to be opened
        String url = "http://charanmaram.com/action.php?action=show_assignment";
        // Assignment of the Webview
        WebView vq = (WebView) view.findViewById(R.id.wv);
        vq.getSettings().setJavaScriptEnabled(true);
        vq.loadUrl(url);
        return view;
    }
}
