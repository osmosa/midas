package org.meruvian.midas;

import org.osmdroid.tileprovider.tilesource.OnlineTileSourceBase;
import org.osmdroid.tileprovider.tilesource.XYTileSource;

public class Constant {
    
    public static final OnlineTileSourceBase OSMOSA_TILE = new XYTileSource("OSMOSA-ID", null, 3, 18, 256, ".png",
                    "http://www.osmosa.net/osm_tiles3/");
    
    public static final String[] LOCATION_NAME_LIST = { "Meruvian", "Malang", "Blitar", "Banyuwangi"};
    
    public static final double[][] LOCATION_POINT_LIST = { {-6.17, 106.8} , {-7.97, 112.63} , {-8.10, 112.16} , {-8.05, 114.24} };
    
    public static final String PREFS_NAME = "org.meruvian.midas.prefs";
    
    public static final String PREFS_SHOW_LOCATION = "showLocation";
}
