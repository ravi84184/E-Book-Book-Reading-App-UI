package com.nikdemo.e_bookbookreadingapp.utils;


import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.nikdemo.e_bookbookreadingapp.R;

public class GKProgrssDialog extends ProgressDialog {
    @SuppressLint("StaticFieldLeak")
    private static Context context;
    private SimpleDraweeView progress_iv;

    public GKProgrssDialog(Context context) {
        super(context);
        GKProgrssDialog.context = context;
    }

    public GKProgrssDialog(Context context, int theme) {
        super(context, theme);
        GKProgrssDialog.context = context;
    }

    public static GKProgrssDialog gkProgrssDialog(Context mContext) {
        context = mContext;
        GKProgrssDialog dialog = new GKProgrssDialog(mContext);
        dialog.setIndeterminate(true);
        dialog.setCancelable(false);
        return dialog;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_progress_dialog);

        try {
            progress_iv = findViewById(R.id.progress_iv);
            ImageRequest imageRequest = ImageRequestBuilder.newBuilderWithResourceId(
                    R.raw.a).build();
            DraweeController controller = Fresco.newDraweeControllerBuilder()
                    .setUri(imageRequest.getSourceUri())
                    .setAutoPlayAnimations(true)
                    .build();
            progress_iv.setController(controller);

            /*Glide.with(context).load(R.raw.lodder)
                    .diskCacheStrategy(DiskCacheStrategy.ALL).dontAnimate()
                    .into(progress_iv);*/
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void show() {
        super.show();
    }

    @Override
    public void dismiss() {
        super.dismiss();
    }
}