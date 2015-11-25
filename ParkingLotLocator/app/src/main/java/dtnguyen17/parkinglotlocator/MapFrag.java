package dtnguyen17.parkinglotlocator;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class MapFrag extends Fragment implements OnMapReadyCallback{

    private double lng;
    private double lat;
    String address;
    LatLng latlng;

    public MapFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        address = this.getArguments().getString("addr");
        lng = this.getArguments().getDouble("lng");
        lat = this.getArguments().getDouble("lat");
        latlng = new LatLng(lat,lng);

        View view = inflater.inflate(R.layout.mapfrag, container, false);
        MapFragment mapFragment = (MapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        googleMap.addMarker(new MarkerOptions().position(latlng).title(address));
        // Move the camera instantly to location with a zoom of 15.
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latlng, 15));
        // Zoom in, animating the camera.
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(14), 2000, null);
    }
}
