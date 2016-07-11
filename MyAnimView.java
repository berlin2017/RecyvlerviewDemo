package com.berlin.recyvlerviewdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by berlin on 2016/7/6 0006.
 */
public class MyAnimView extends ImageView{

    private int maxHeight = 500;
    private int height = 150;
    private int width;//屏幕宽度
    private int bg_color = Color.GREEN;//背景颜色
    private int color2 = Color.RED;//弧形颜色
    private Paint paint1;
    private Paint paint2;
    private Path path;
    private int textwidth;

    public MyAnimView(Context context) {
        this(context,null);
    }


    public MyAnimView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyAnimView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        width = context.getResources().getDisplayMetrics().widthPixels;
        paint1 = new Paint();
        paint1.setColor(color2);

        paint2 = new Paint();
        paint2.setColor(bg_color);
        paint2.setTextSize(40);
        path = new Path();
        textwidth = (int) paint2.measureText("这是测试");
    }

    @Override
    public void requestLayout() {
        super.requestLayout();
        invalidate();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(bg_color);
        canvas.drawRect(0,0,width,height,paint1);
        if (getLayoutParams().height>150){
            path.reset();
            path.moveTo(0, 150);
            path.cubicTo(width/2,getLayoutParams().height,width/2,getLayoutParams().height,width,150);
            canvas.drawPath(path,paint1);
        }
        // 文本
        if (getLayoutParams().height >= maxHeight) {
            canvas.drawText("哈哈哈哈", (width - textwidth) / 2, 30, paint2);
            canvas.drawText("哈哈哈哈", (width - textwidth) / 2, 30, paint2);
        } else {
            canvas.drawText("这是测试", (width - textwidth) / 2, 30, paint2);
            canvas.drawText("这是测试", (width - textwidth) / 2, 30, paint2);
        }
    }
}
