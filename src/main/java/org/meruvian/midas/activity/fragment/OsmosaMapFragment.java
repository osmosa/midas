package org.meruvian.midas.activity.fragment;

import static org.meruvian.midas.Constant.LOCATION_POINT_LIST;
import static org.meruvian.midas.Constant.LOCATION_NAME_LIST;
import static org.meruvian.midas.Constant.OSMOSA_TILE;

import java.util.ArrayList;

import org.meruvian.midas.R;
import org.meruvian.midas.ResourceProxyImpl;
import org.meruvian.midas.overlay.PointItemizedOverlay;
import org.osmdroid.tileprovider.MapTileProviderBasic;
import org.osmdroid.tileprovider.tilesource.OnlineTileSourceBase;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.MyLocationOverlay;
import org.osmdroid.views.overlay.OverlayItem;
import org.osmdroid.views.overlay.TilesOverlay;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import roboguice.fragment.RoboFragment;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class OsmosaMapFragment extends RoboFragment {

    private Logger log = LoggerFactory.getLogger(OsmosaMapFragment.class.getName());
   
    private MapView osmosaMap = null;
    private MyLocationOverlay myLocationOverlay = null;
    private Context applicationContext = null;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
        applicationContext = getActivity().getApplicationContext();
        osmosaMap = (MapView) inflater.inflate(R.layout.osmosa_mapview, container,
                false);       
        osmosaMap.setBuiltInZoomControls(true);
        osmosaMap.setMultiTouchControls(true);
        osmosaMap.getController().setZoom(4);
        osmosaMap.getController().setCenter(
            new GeoPoint((int) (-2.56 * 1E6), (int) (116.97 * 1E6)));
        changeServer(OSMOSA_TILE);
        initItemizedOverlay();
        return osmosaMap;
    }
   
    private void initItemizedOverlay(){
        ArrayList<OverlayItem> items = new ArrayList<OverlayItem>();
        for(int i = 0; i < LOCATION_NAME_LIST.length; i++) { 
            items.add(new OverlayItem(LOCATION_NAME_LIST[i], "", new GeoPoint((int) (LOCATION_POINT_LIST[i][0] * 1E6), 
                (int) (LOCATION_POINT_LIST[i][1] * 1E6))));    
        }
        PointItemizedOverlay<OverlayItem> mLocationOverlay = new PointItemizedOverlay<OverlayItem>(items, 
                        applicationContext.getResources().getDrawable(R.drawable.marker_default), null, applicationContext);
        osmosaMap.getOverlays().add(mLocationOverlay);
    }

    public void toggleMyLocation() {
        log.debug("toggleMyLocation");
        if (osmosaMap != null) {
            myLocationOverlay = new MyLocationOverlay(applicationContext, osmosaMap, new ResourceProxyImpl(applicationContext));
            myLocationOverlay.enableMyLocation();
            myLocationOverlay.runOnFirstFix(new Runnable() {
                public void run() {
                    try{                        
                        osmosaMap.getController().animateTo(myLocationOverlay.getMyLocation());
                    }catch (Exception e) {
                        log.warn("getting my location failed");
                    }
                }
            });
            osmosaMap.getController().setZoom(13);
            osmosaMap.getOverlays().add(myLocationOverlay);
        }
    }
    
    public void goToLocation(double latitude, double longitude) {
        log.debug("go to location {"+latitude+"}, {"+longitude+"}");
        if (osmosaMap != null) {
            GeoPoint targetLocation = new GeoPoint((int) (latitude * 1E6), (int) (longitude * 1E6));
            osmosaMap.getController().setZoom(13);
            osmosaMap.getController().animateTo(targetLocation);
        }
    }
    
    public void changeServer(OnlineTileSourceBase tileSource) {
        MapTileProviderBasic tileProvider = new MapTileProviderBasic(applicationContext);
        tileProvider.setTileSource(tileSource);

        TilesOverlay tilesOverlay = new TilesOverlay(tileProvider, applicationContext);
        tilesOverlay.setLoadingBackgroundColor(Color.TRANSPARENT);

        if (osmosaMap.getOverlays().isEmpty()) {
            osmosaMap.getOverlays().add(tilesOverlay);
        } else {
            osmosaMap.getOverlays().set(0, tilesOverlay);
        }
        osmosaMap.invalidate();
    }
    
    
}
