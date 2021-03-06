/*
 * Copyright (c) 2015 Zhang Hai <Dreaming.in.Code.ZH@Gmail.com>
 * All Rights Reserved.
 */

package me.zhanghai.android.douya.settings.ui;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.zhanghai.android.douya.R;
import me.zhanghai.android.douya.scalpel.ScalpelHelperFragment;
import me.zhanghai.android.douya.ui.KonamiCodeDetector;
import me.zhanghai.android.douya.util.AppUtils;
import me.zhanghai.android.douya.util.ToastUtils;

public class AboutActivity extends AppCompatActivity
        implements ConfirmEnableScalpelDialogFragment.Listener {

    @BindView(R.id.container)
    LinearLayout mContainerLayout;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.version)
    TextView mVersionText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.about_activity);
        ButterKnife.bind(this);

        ScalpelHelperFragment.attachTo(this);

        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("");

        // Seems that ScrollView intercepts touch event, so we have to set the onTouchListener on a
        // view inside it.
        mContainerLayout.setOnTouchListener(new KonamiCodeDetector(this) {
            @Override
            public void onDetected() {
                onEnableScalpel();
            }
        });

        mVersionText.setText(getString(R.string.about_version_format,
                AppUtils.getPackageInfo(this).versionName));
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

    private void onEnableScalpel() {
        ConfirmEnableScalpelDialogFragment.show(this);
    }

    @Override
    public void enableScalpel() {
        ScalpelHelperFragment.setEnabled(true);
    }
}
