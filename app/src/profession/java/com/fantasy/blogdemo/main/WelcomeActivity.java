package com.fantasy.blogdemo.main;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.fantasy.blogdemo.BuildConfig;
import com.fantasy.blogdemo.R;
import com.fantasy.blogdemo.base.BaseActivity;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;

/**
 * 主界面（专业版）
 * <pre>
 *     author  : Fantasy
 *     version : 1.0, 2020-05-30
 *     since   : 1.0, 2020-05-30
 * </pre>
 */
public class WelcomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 解决Android应用Launcher重复启动的问题
        if (!isTaskRoot() && getIntent() != null) {
            String action = getIntent().getAction();
            if (getIntent().hasCategory(Intent.CATEGORY_LAUNCHER) && Intent.ACTION_MAIN.equals(action)) {
                finish();
                return;
            }
        }
        setContentView(R.layout.activity_welcome);

        // 全屏，透明状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        new QMUIDialog.MessageDialogBuilder(this)
                .setMessage("服务器地址为：" + BuildConfig.SERVER_URL)
                .addAction(R.string.btn_confirm, new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        dialog.dismiss();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                MainActivity.actionStart(WelcomeActivity.this);
                                finish();
                            }
                        }, 1500);
                    }
                })
                .show();
    }
}
