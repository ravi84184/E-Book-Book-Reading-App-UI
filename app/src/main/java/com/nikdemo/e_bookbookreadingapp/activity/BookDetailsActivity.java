package com.nikdemo.e_bookbookreadingapp.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.folioreader.Config;
import com.folioreader.Constants;
import com.folioreader.FolioReader;
import com.folioreader.model.locators.ReadLocator;
import com.folioreader.util.ReadLocatorListener;
import com.nikdemo.e_bookbookreadingapp.R;
import com.nikdemo.e_bookbookreadingapp.model.CommentModel;
import com.nikdemo.e_bookbookreadingapp.utils.BaseActivity;
import com.nikdemo.e_bookbookreadingapp.utils.Utilities;
import com.nikdemo.e_bookbookreadingapp.utils.customui.CustomButton;

import java.io.File;
import java.util.ArrayList;

public class BookDetailsActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "BookDetailsActivity";
    private static final int READ_STORAGE_PERMISSION_REQUEST_CODE = 1;
    ReadLocator readLocators;
    private Toolbar toolbar;
    private RecyclerView rl_comment;
    private CustomButton btn_read_now;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);

        intiUI();

    }

    private void intiUI() {
        toolbar = findViewById(R.id.actionbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Utilities.setTitle(this, "Book Details", null);

        btn_read_now = findViewById(R.id.btn_read_now);
        btn_read_now.setOnClickListener(this);
        rl_comment = findViewById(R.id.rl_comment);
        rl_comment.setLayoutManager(new LinearLayoutManager(this, LinearLayout.VERTICAL, false));
        rl_comment.setAdapter(new CommentAdapter(getApplicationContext(), new ArrayList<CommentModel>()));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_read_now:
                if (!checkPermissionForReadExtertalStorage()) {
                    try {
                        requestPermissionForReadExtertalStorage();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    FolioReader folioReader = FolioReader.get();
                    String cacheDir = String.valueOf(getCacheDir());
                    File imageFile = new File(cacheDir, "image1.jpg");

                    File file = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS + "/epubBook.epub");
                    Config config = new Config()
                            .setAllowedDirection(Config.AllowedDirection.ONLY_VERTICAL)
                            .setDirection(Config.Direction.VERTICAL)
                            .setFont(Constants.FONT_LORA)
                            .setThemeColorRes(R.color.colorAccent)
                            .setShowTts(true);
                    folioReader.setConfig(config, true);
                    folioReader.setReadLocator(readLocators);
                    folioReader.openBook(file.getPath(), "2");
                    folioReader.setReadLocatorListener(new ReadLocatorListener() {
                        @Override
                        public void saveReadLocator(ReadLocator readLocator) {
                            readLocators = readLocator;
                        }
                    });

                }
                break;
        }
    }

    public boolean checkPermissionForReadExtertalStorage() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int result = checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            return result == PackageManager.PERMISSION_GRANTED;
        }
        return false;
    }

    public void requestPermissionForReadExtertalStorage() throws Exception {
        try {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    READ_STORAGE_PERMISSION_REQUEST_CODE);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
