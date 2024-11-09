package com.nfricke.coursecrafter_selfmade.Listener_Handler;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.nfricke.coursecrafter_selfmade.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Set;

import io.noties.markwon.image.ImageItem;
import io.noties.markwon.image.SchemeHandler;

public class FAQDetailAssetSchemeHandler extends SchemeHandler {

    private final Context context;

    // Constructor takes a context to access app assets and resources.
    public FAQDetailAssetSchemeHandler(Context context) {
        this.context = context;
    }

    // Specifies that the handler supports URIs with the "asset" scheme.
    @NonNull
    @Override
    public Set<String> supportedSchemes() {
        return Collections.singleton("asset");
    }

    @Nullable
    @Override
    public ImageItem handle(@NonNull String raw, @NonNull Uri uri) {
        // Checks the URI scheme and processes asset paths.
        if ("asset".equals(uri.getScheme())) {
            String path = uri.getPath();
            if (path != null && path.startsWith("/")) {
                path = path.substring(1);
            }
            try (InputStream inputStream = context.getAssets().open(path)) {
                Drawable drawable = Drawable.createFromStream(inputStream, null);
                if (drawable != null) {
                    // Scales the drawable to the full width of the screen.
                    int screenWidth = context.getResources().getDisplayMetrics().widthPixels;
                    int intrinsicWidth = drawable.getIntrinsicWidth();
                    int intrinsicHeight = drawable.getIntrinsicHeight();
                    int desiredHeight = (int) ((double) screenWidth / intrinsicWidth * intrinsicHeight);
                    drawable.setBounds(0, 0, screenWidth, desiredHeight);
                }
                return ImageItem.withResult(drawable);
            } catch (IOException e) {
                // Logs an error if asset loading fails.
                String code = context.getString(R.string.image_load_error);
                Log.e("AssetSchemeHandler", code + uri, e);
            }
        }
        return null;
    }
}