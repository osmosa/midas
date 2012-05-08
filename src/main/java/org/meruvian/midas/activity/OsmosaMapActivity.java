package org.meruvian.midas.activity;

import org.meruvian.midas.R;
import org.meruvian.midas.activity.fragment.OsmosaMapFragment;
import org.meruvian.midas.view.lib.ActionBarActivity;

import roboguice.inject.InjectFragment;
import android.content.res.Configuration;
import android.os.Bundle;

public class OsmosaMapActivity extends ActionBarActivity {
    
    @InjectFragment(R.id.osmosa_fragment)
    private OsmosaMapFragment mapFragment;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            finish();
            return;
        }
        setContentView(R.layout.osmosa_fragment);
        
        Bundle contentData = getIntent().getExtras();
        mapFragment.goToLocation(contentData.getDouble("lat"), contentData.getDouble("lon"));
    }
}
