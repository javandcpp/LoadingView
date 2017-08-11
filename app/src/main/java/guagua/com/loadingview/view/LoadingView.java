package guagua.com.loadingview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.nineoldandroids.animation.ValueAnimator;

import static android.R.attr.factor;
import static android.R.attr.radius;
import static android.R.attr.readPermission;

/**
 * Created by android on 8/11/17.
 */

public class LoadingView extends View {

    private final Context mContext;
    private int actualWidth;
    private int actualHeight;
    private Paint mPaint;
    private ValueAnimator value;
    private float dotPositionX;
    private float dotPositionY;
    private float radius;
    private ValueAnimator valueAnimate1;
    private ValueAnimator valueAnimate2;
    private float dotPositionX1;
    private float dotPositionY1;
    private float dotPositionX2;
    private float dotPositionY2;
    private ValueAnimator valueAnimate3;
    private float dotPositionX3;
    private float dotPositionY3;
    private float radius1;
    private float mIntervalue1;
    private float mIntervalue2;
    private float mIntervalue3;
    private float radius2;
    private float radius3;
    private int factor1;
    private int factor2;
    private int factor3;

    public LoadingView(Context context) {
        this(context, null);
    }

    public LoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();
        initValueAnimatior();
    }

    private void initValueAnimatior() {
        valueAnimate1 = ValueAnimator.ofFloat(360, 0);
        valueAnimate1.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimate1.setRepeatMode(ValueAnimator.RESTART);
        valueAnimate1.setDuration(2000);
        valueAnimate1.setInterpolator(new LinearInterpolator());
        valueAnimate1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float degree = (float) valueAnimator.getAnimatedValue();
                dotPositionX1 = (float) Math.sin(degree * Math.PI / 180) * radius1;
                dotPositionY1 = (float) Math.cos(degree * Math.PI / 180) * radius1;
                invalidate();
            }
        });
        valueAnimate2 = ValueAnimator.ofFloat(360, 0);
        valueAnimate2.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimate2.setRepeatMode(ValueAnimator.RESTART);
        valueAnimate2.setDuration(1800);
        valueAnimate2.setInterpolator(new LinearInterpolator());
        valueAnimate2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float degree = (float) valueAnimator.getAnimatedValue();
                dotPositionX2 = (float) Math.sin(degree * Math.PI / 180) * radius2;
                dotPositionY2 = (float) Math.cos(degree * Math.PI / 180) * radius2;
                invalidate();
            }
        });
        valueAnimate3 = ValueAnimator.ofFloat(360, 0);
        valueAnimate3.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimate3.setRepeatMode(ValueAnimator.RESTART);
        valueAnimate3.setDuration(1600);
        valueAnimate3.setInterpolator(new LinearInterpolator());
        valueAnimate3.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float degree = (float) valueAnimator.getAnimatedValue();
                dotPositionX3 = (float) Math.sin(degree * Math.PI / 180) * radius3;
                dotPositionY3 = (float) Math.cos(degree * Math.PI / 180) * radius3;
                invalidate();
            }
        });
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        valueAnimate1.start();
        valueAnimate2.start();
        valueAnimate3.start();
    }

    private void initView() {
        mPaint = new Paint(Paint.DITHER_FLAG | Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.RED);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(5);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int x = getWidth() / 2;
        int y = getHeight() / 2;

        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(x, y, radius1, mPaint);
        canvas.drawCircle(x, y, radius2, mPaint);
        canvas.drawCircle(x, y, radius3, mPaint);

        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle((x + dotPositionX1), (y + dotPositionY1), 10, mPaint);
        canvas.drawCircle((x + dotPositionX2), (y + dotPositionY2), 10, mPaint);
        canvas.drawCircle((x + dotPositionX3), (y + dotPositionY3), 10, mPaint);

        canvas.drawCircle(x,y,30,mPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);

        factor1 = width / 8;
        factor2 = width / 4;
        factor3 = width / 3;

        radius1 = width / 2 - factor1;
        radius2 = width / 2 - factor2;
        radius3 = width / 2 - factor3;
        setMeasuredDimension(width, width);
    }
}

