package me.samthompson.bubbleactions_sample;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import me.samthompson.bubbleactions.BubbleActions;
import me.samthompson.bubbleactions.MenuCallback;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        findViewById(R.id.text_view).setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(final View v) {
                BubbleActions.on(v)
                        .fromMenu(R.menu.menu_actions, new MenuCallback() {
                            @Override
                            public void doAction(int itemId) {
                                switch (itemId) {
                                    case R.id.action_star:
                                        Toast.makeText(v.getContext(), "Star pressed!", Toast.LENGTH_SHORT).show();
                                        break;
                                    case R.id.action_share:
                                        Toast.makeText(v.getContext(), "Share pressed!", Toast.LENGTH_SHORT).show();
                                        break;
                                    case R.id.action_hide:
                                        Toast.makeText(v.getContext(), "Hide pressed!", Toast.LENGTH_SHORT).show();
                                        break;
                                }
                            }
                        })
                        .withCustomDrawable(new CustomDrawable(4,v),150)
                        .attachToView(true)
                        .show();
                return true;
            }
        });
    }
}

class CustomDrawable extends Drawable {

    private Paint mPaint;
    private int mColor;
    private int mBorderRadius;

    private RectF mRect;
    private Path mPath;

    private View view;

    CustomDrawable(int borderRadius, View v) {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);

        mPath = new Path();
        mPath.setFillType(Path.FillType.EVEN_ODD);

        mRect = new RectF();

        mColor = Color.RED;
        mBorderRadius = borderRadius;
        this.view = v;
        mPaint.setColor(Color.RED);
        mPaint.setAlpha(0);
    }

    @Override
    protected void onBoundsChange(Rect bounds) {
        mPath.reset();
        int location[] = new int[2];
        view.getLocationOnScreen(location);
        mPath.addRect(bounds.left, bounds.top, bounds.right, bounds.bottom, Path.Direction.CW);
        mRect.set(location[0], location[1], location[0]+view.getWidth(), location[1]+view.getHeight());
        mPath.addRoundRect(mRect, 10, 10, Path.Direction.CW);
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        int alpha = mPaint.getAlpha();
        Log.d("draw()","alfa - "+alpha);
        mPaint.setColor(Color.RED);
        mPaint.setAlpha(alpha);
        Log.d("draw()","alfa - "+alpha);
        canvas.drawPath(mPath, mPaint);
    }

    @Override
    public void setAlpha(int i) {
        Log.d("alpha()","alfa - "+i);
        mPaint.setAlpha(i);
        invalidateSelf();
    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        mPaint.setColorFilter(colorFilter);
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }
}
