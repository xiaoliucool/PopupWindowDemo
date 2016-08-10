package com.xiaoliu.popupwindowdemo;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private PopupWindow mPopupWidow;
    private Button mShowPopupWindowButton;
    private TextView takePhotoTextView;
    private TextView cancelPhotoTextView;

    private Animation mAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mShowPopupWindowButton = (Button) findViewById(R.id.btnShowPopupWindow);
        mShowPopupWindowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initPopupWindow();
                registerListener();
                //loadAnimation(MainActivity.this, R.anim.my_scale);

            }
        });
    }

    private void initPopupWindow() {
        mPopupWidow = new PopupWindow(this);
        View tPopView = LayoutInflater.from(this).inflate(R.layout.pop_one, null);
        takePhotoTextView = (TextView) tPopView.findViewById(R.id.tvPhoto);
        cancelPhotoTextView = (TextView) tPopView.findViewById(R.id.tvCancel);

        mPopupWidow.setContentView(tPopView);
        mPopupWidow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopupWidow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopupWidow.setBackgroundDrawable(new ColorDrawable(0x00000000));
        mPopupWidow.setOutsideTouchable(true);
        mPopupWidow.setFocusable(true);

        mPopupWidow.setAnimationStyle(R.style.PopupAnimation);
        mPopupWidow.showAtLocation(mShowPopupWindowButton, Gravity.CENTER, 0, 0);

        mPopupWidow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                Toast.makeText(MainActivity.this, "dismiss", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadAnimation(Context context, int animResId) {
        mAnimation = AnimationUtils.loadAnimation(context, animResId);
    }

    private void registerListener() {
        takePhotoTextView.setOnClickListener(this);
        cancelPhotoTextView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvPhoto:
                Toast.makeText(this, "拍照中...", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tvCancel:
                Toast.makeText(this, "cancel photoing", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}
