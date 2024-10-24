package com.nfricke.coursecrafter_selfmade;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Set;

import io.noties.markwon.image.ImageItem;
import io.noties.markwon.image.SchemeHandler;

public class AssetSchemeHandler extends SchemeHandler {

    private final Context context;
    public AssetSchemeHandler(Context context) {
        this.context = context;
    }
    @NonNull
    @Override
    public Set<String> supportedSchemes() {
        return Collections.singleton("asset");
    }

    @Nullable
    @Override
    public ImageItem handle(@NonNull String raw, @NonNull Uri uri) {
        if ("asset".equals(uri.getScheme())) {
            String path = uri.getPath();
            if (path != null && path.startsWith("/")) {
                path = path.substring(1);
            }
            try (InputStream inputStream = context.getAssets().open(path)) {
                Drawable drawable = Drawable.createFromStream(inputStream, null);
                if (drawable != null) {
                    // Bildschirmbreite ermitteln
                    int screenWidth = context.getResources().getDisplayMetrics().widthPixels;

                    // Bestimmen Sie einen bestimmten Anteil der Bildschirmbreite für das Bild
                    int desiredWidth = (int) (screenWidth); // z.B. 100% der Bildschirmbreite
                    // Berechnen Sie die Höhe, um das Verhältnis beizubehalten
                    int intrinsicWidth = drawable.getIntrinsicWidth();
                    int intrinsicHeight = drawable.getIntrinsicHeight();
                    int desiredHeight = (int) ((double)desiredWidth / intrinsicWidth * intrinsicHeight);

                    drawable.setBounds(0, 0, desiredWidth, desiredHeight);
                }
                return ImageItem.withResult(drawable);
            } catch (IOException e) {
                String code = context.getString(R.string.image_load_error);
                Log.e("AssetSchemeHandler", code + uri, e);
            }
        }
        return null;
    }
    }
