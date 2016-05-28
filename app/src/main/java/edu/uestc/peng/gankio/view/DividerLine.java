package edu.uestc.peng.gankio.view;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.provider.SyncStateContract;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import edu.uestc.peng.gankio.Constants;

/**
 * Created by Peng on 2016/5/7.
 * divider line
 */
public class DividerLine extends RecyclerView.ItemDecoration {

    private int color = Constants.DIVIDER_LINE_COLOR;


    private int size = Constants.DIVIDER_LINE_SIZE;

    private Paint paint;

    public DividerLine() {
        super();
        this.paint = new Paint();
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);

        super.onDrawOver(c, parent, state);

        final int left = parent.getPaddingLeft();
        final int right = parent.getWidth() - parent.getPaddingRight();

        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int top = child.getBottom() + params.bottomMargin;
            final int bottom = top + size;

            c.drawRect(left, top, right, bottom, paint);
        }
    }
}
