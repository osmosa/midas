package org.meruvian.midas.activity;

import static org.meruvian.midas.Constant.OSMOSA_TILE;

import org.meruvian.midas.R;
import org.meruvian.midas.activity.fragment.ListViewFragment;
import org.meruvian.midas.activity.fragment.OsmosaMapFragment;
import org.meruvian.midas.view.lib.ActionBarActivity;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;

import roboguice.inject.InjectFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.inject.internal.Nullable;

public class ListViewActivity extends ActionBarActivity implements
        ListViewFragment.OnListSelectedListener {
    
    @InjectFragment(R.id.osmosa_fragment)
    @Nullable
    private OsmosaMapFragment viewer;
    
    private boolean isOsmosa = true;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_fragment);
    }
    
    @Override
    public void onListSelected(double lat, double lon) {
       
        if (viewer == null || !viewer.isInLayout()) {
            Intent showContent = new Intent(getApplicationContext(),
                    OsmosaMapActivity.class);
            showContent.putExtra("lat", lat);
            showContent.putExtra("lon", lon);
            startActivity(showContent);
        } else {
            viewer.goToLocation(lat, lon);
        }
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case R.id.menu_toggle_myloc:
            viewer.toggleMyLocation();
            return true;
        case R.id.osmosa_server:
            if (!isOsmosa)
                viewer.changeServer(OSMOSA_TILE);
            isOsmosa = true;
            return true;
        case R.id.openstreetmap_server:
            if (isOsmosa)
                viewer.changeServer(TileSourceFactory.MAPNIK);
            isOsmosa = false;
            return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }
}