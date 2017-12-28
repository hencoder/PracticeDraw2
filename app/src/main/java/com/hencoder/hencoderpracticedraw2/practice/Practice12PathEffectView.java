package com.hencoder.hencoderpracticedraw2.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ComposePathEffect;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.DiscretePathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.SumPathEffect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice12PathEffectView extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Path path = new Path();
    Path mPath = new Path();    //创建三角形

    public Practice12PathEffectView(Context context) {
        super(context);
    }

    public Practice12PathEffectView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice12PathEffectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        paint.setStyle(Paint.Style.STROKE);

        path.moveTo(50, 100);
        path.rLineTo(50, 100);
        path.rLineTo(80, -150);
        path.rLineTo(100, 100);
        path.rLineTo(70, -120);
        path.rLineTo(150, 80);

        mPath.moveTo(10f, 10f);
        mPath.rLineTo(20f, 20f);
        mPath.rLineTo(-40f, 0f);
        mPath.close();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 使用 Paint.setPathEffect() 来设置不同的 PathEffect
        //使用PathEffect来给图形的轮廓设置效果

        // 第一处：CornerPathEffect,所有拐角变成圆角
        paint.setPathEffect(new CornerPathEffect(20));  //设置拐角的圆角半径为20
        canvas.drawPath(path, paint);

        canvas.save();
        canvas.translate(500, 0);
        // 第二处：DiscretePathEffect，线条进行随机偏离
        paint.setPathEffect(new DiscretePathEffect(20, 5));
        canvas.drawPath(path, paint);
        canvas.restore();

        canvas.save();
        canvas.translate(0, 200);
        // 第三处：DashPathEffect，使用虚线来绘制线条『画20像素、空5像素、画10像素、空5像素』,phase是虚线的偏移量
        paint.setPathEffect(new DashPathEffect(new float[]{20, 10, 5, 10}, 0));
        canvas.drawPath(path, paint);
        canvas.restore();

        canvas.save();
        canvas.translate(500, 200);
        // 第四处：PathDashPathEffect
        paint.setPathEffect(new PathDashPathEffect(mPath, 40, 0,  //TRANSLATE:位移，ROTATE:旋转 MORPH:变体
                PathDashPathEffect.Style.TRANSLATE));
        canvas.drawPath(path, paint);
        canvas.restore();

        canvas.save();
        canvas.translate(0, 400);
        // 第五处：SumPathEffect
        paint.setPathEffect(new SumPathEffect(new DashPathEffect(new float[]{20, 10}, 0), new DiscretePathEffect(20, 5)));
        canvas.drawPath(path, paint);
        canvas.restore();

        canvas.save();
        canvas.translate(500, 400);
        // 第六处：ComposePathEffect,先对目标Path使用一个PathEffect,再对这个改变后的Path使用另一个PathEffect,类似于Xfermode
        paint.setPathEffect(new ComposePathEffect(new DashPathEffect(new float[]{20, 10}, 0), new DiscretePathEffect(20, 5)));
        canvas.drawPath(path, paint);
        canvas.restore();
    }
}
