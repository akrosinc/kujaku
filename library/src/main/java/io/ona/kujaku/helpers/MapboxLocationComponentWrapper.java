package io.ona.kujaku.helpers;

import android.content.Context;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.mapbox.android.core.permissions.PermissionsManager;
import com.mapbox.mapboxsdk.location.LocationComponent;
import com.mapbox.mapboxsdk.location.LocationComponentActivationOptions;
import com.mapbox.mapboxsdk.location.modes.CameraMode;
import com.mapbox.mapboxsdk.location.modes.RenderMode;
import com.mapbox.mapboxsdk.maps.MapboxMap;

import io.ona.kujaku.callbacks.OnLocationComponentInitializedCallback;

/**
 * @author Vincent Karuri
 */
public class MapboxLocationComponentWrapper {

    private LocationComponent locationComponent;

    private OnLocationComponentInitializedCallback onLocationComponentInitializedCallback;

    /**
     * Init Location Component Wrapper
     *
     * @param mapboxMap          {@link MapboxMap}
     * @param context
     * @param locationRenderMode {@link RenderMode}
     */
    public void init(@NonNull MapboxMap mapboxMap, @NonNull Context context, int locationRenderMode) {

        if (PermissionsManager.areLocationPermissionsGranted(context)) {
            locationComponent = mapboxMap.getLocationComponent();

            // Set the LocationComponent activation options
            LocationComponentActivationOptions locationComponentActivationOptions =
                    LocationComponentActivationOptions.builder(context, mapboxMap.getStyle())
                            .useDefaultLocationEngine(false)
                            .build();

            // Activate with the LocationComponentActivationOptions object
            locationComponent.activateLocationComponent(locationComponentActivationOptions);

            locationComponent.setLocationComponentEnabled(true);
            locationComponent.setCameraMode(CameraMode.NONE);

            PackageManager pm = context.getPackageManager();
            if (pm != null && !pm.hasSystemFeature(PackageManager.FEATURE_SENSOR_COMPASS)) {
                // This device does not have a compass, turn off the compass feature
                locationComponent.setRenderMode(RenderMode.NORMAL);
            } else {
                locationComponent.setRenderMode(locationRenderMode);
            }

            if (onLocationComponentInitializedCallback != null) {
                onLocationComponentInitializedCallback.onLocationComponentInitialized();
            }
        }
    }

    @Nullable
    public LocationComponent getLocationComponent() {
        return locationComponent;
    }

    public void setOnLocationComponentInitializedCallback(OnLocationComponentInitializedCallback onLocationComponentInitializedCallback) {
        this.onLocationComponentInitializedCallback = onLocationComponentInitializedCallback;
    }
}
