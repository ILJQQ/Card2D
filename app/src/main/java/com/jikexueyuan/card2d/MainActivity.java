package com.jikexueyuan.card2d;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView ivFront,ivBack;

//    淡出动画
    private ScaleAnimation saFadeOut = new ScaleAnimation(1,0,1,1, Animation.RELATIVE_TO_PARENT,
            0.5f,Animation.RELATIVE_TO_PARENT,0.5f);

//    淡入动画
    private ScaleAnimation saFadeIn = new ScaleAnimation(0,1,1,1, Animation.RELATIVE_TO_PARENT,
            0.5f,Animation.RELATIVE_TO_PARENT,0.5f);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        初始化视窗
        init();
//      主容器点击事件
        findViewById(R.id.activity_main).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                判断当前显示画面
                if (ivFront.getVisibility() == View.VISIBLE){
                    ivFront.startAnimation(saFadeOut);
                }else{
                    ivBack.startAnimation(saFadeOut);
                }
            }
        });
    }

//    正面显示
    private void showFront(){
        ivFront.setVisibility(View.VISIBLE);
        ivBack.setVisibility(View.INVISIBLE);
    }

//    背面显示
    private void showBack(){
        ivFront.setVisibility(View.INVISIBLE);
        ivBack.setVisibility(View.VISIBLE);
    }

//    初始化
    private void init(){
        ivFront = (ImageView) findViewById(R.id.ivFront);
        ivBack = (ImageView) findViewById(R.id.ivBack);
        ivFront.setVisibility(View.VISIBLE);
        ivBack.setVisibility(View.INVISIBLE);
        showFront();
        saFadeIn.setDuration(500);
        saFadeOut.setDuration(500);

        saFadeOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

//            当动画结束时进行判断
            @Override
            public void onAnimationEnd(Animation animation) {
                if (ivFront.getVisibility() == View.VISIBLE){
                    ivFront.setAnimation(null);
                    showBack();
                    ivBack.startAnimation(saFadeIn);
                }else{
                    ivBack.setAnimation(null);
                    showFront();
                    ivFront.startAnimation(saFadeIn);
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
