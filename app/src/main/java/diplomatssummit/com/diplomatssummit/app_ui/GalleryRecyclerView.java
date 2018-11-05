package diplomatssummit.com.diplomatssummit.app_ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcelable;
import android.support.annotation.FloatRange;
import android.support.annotation.IntRange;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;






import diplomatssummit.com.diplomatssummit.app_ui.AnimManager;
import diplomatssummit.com.diplomatssummit.app_ui.GalleryItemDecoration;
import diplomatssummit.com.diplomatssummit.app_ui.ScrollManager;
import diplomatssummit.com.diplomatssummit.app_ui.util.DLog;

import diplomatssummit.com.diplomatssummit.R;
import diplomatssummit.com.diplomatssummit.app_ui.util.ThreadUtils;


public class GalleryRecyclerView extends RecyclerView implements View.OnTouchListener, GalleryItemDecoration.OnItemSizeMeasuredListener {

    private static final String TAG = "MainActivity_TAG";

    public static final int LINEAR_SNAP_HELPER = 0;
    public static final int PAGER_SNAP_HELPER = 1;

    private int mFlingSpeed = 1000;

    private boolean mAutoPlay = false;

    private int mInterval = 1000;

    private int mInitPos = -1;


    private AnimManager mAnimManager;
    private ScrollManager mScrollManager;
    private GalleryItemDecoration mDecoration;


    private Runnable mAutoPlayTask = new Runnable() {
        @Override
        public void run() {
            if (getAdapter() == null || getAdapter().getItemCount() <= 0) {
                return;
            }

            int position = getScrolledPosition();
            int itemCount = getAdapter().getItemCount();

            int newPosition = (position + 1) % itemCount;
            smoothScrollToPosition(newPosition);

            ThreadUtils.removeCallbacks(this);
            ThreadUtils.runOnUiThread(this);
        }
    };

    public GalleryItemDecoration getDecoration() {
        return mDecoration;
    }

    public AnimManager getAnimManager() {
        return mAnimManager;
    }

    public GalleryRecyclerView(Context context) {
        this(context, null);
    }

    public GalleryRecyclerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GalleryRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.GalleryRecyclerView);
        int helper = ta.getInteger(R.styleable.GalleryRecyclerView_helper, LINEAR_SNAP_HELPER);
        ta.recycle();

        DLog.d(TAG, "GalleryRecyclerView constructor");

        mAnimManager = new AnimManager();
        attachDecoration();
        attachToRecyclerHelper(helper);

        setOnTouchListener(this);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
    }


    private void attachDecoration() {
        DLog.d(TAG, "GalleryRecyclerView attachDecoration");

        mDecoration = new GalleryItemDecoration();
        mDecoration.setOnItemSizeMeasuredListener(this);
        addItemDecoration(mDecoration);
    }


    @Override
    public boolean fling(int velocityX, int velocityY) {
        velocityX = balanceVelocity(velocityX);
        velocityY = balanceVelocity(velocityY);
        return super.fling(velocityX, velocityY);
    }


    private int balanceVelocity(int velocity) {
        if (velocity > 0) {
            return Math.min(velocity, mFlingSpeed);
        } else {
            return Math.max(velocity, -mFlingSpeed);
        }
    }

    private void attachToRecyclerHelper(int helper) {
        DLog.d(TAG, "GalleryRecyclerView attachToRecyclerHelper");

        mScrollManager = new ScrollManager(this);
        mScrollManager.initScrollListener();
        mScrollManager.initSnapHelper(helper);
    }


    public GalleryRecyclerView initPageParams(int pageMargin, int leftPageVisibleWidth) {
        mDecoration.mPageMargin = pageMargin;
        mDecoration.mLeftPageVisibleWidth = leftPageVisibleWidth;
        return this;
    }


    public GalleryRecyclerView initFlingSpeed(@IntRange(from = 0) int speed) {
        this.mFlingSpeed = speed;
        return this;
    }


    public GalleryRecyclerView setAnimFactor(@FloatRange(from = 0f) float factor) {
        mAnimManager.setAnimFactor(factor);
        return this;
    }


    public GalleryRecyclerView setAnimType(int type) {
        mAnimManager.setAnimType(type);
        return this;
    }

    public GalleryRecyclerView setOnItemClickListener(OnItemClickListener mListener) {
        if (mDecoration != null) {
            mDecoration.setOnItemClickListener(mListener);
        }
        return this;
    }


    public GalleryRecyclerView autoPlay(boolean auto) {
        this.mAutoPlay = auto;
        return this;
    }


    private void autoPlayGallery() {
        if (mAutoPlay) {
            ThreadUtils.removeCallbacks(mAutoPlayTask);
            ThreadUtils.runOnUiThread(mAutoPlayTask);
        }
    }


    private void removeAutoPlayTask() {
        if (mAutoPlay) {
            ThreadUtils.removeCallbacks(mAutoPlayTask);
        }
    }


    public GalleryRecyclerView setUp() {
        if (getAdapter().getItemCount() <= 0) {
            return this;
        }

        smoothScrollToPosition(0);
        mScrollManager.updateConsume();

        autoPlayGallery();

        return this;
    }


    public void release() {
        removeAutoPlayTask();
    }


    public int getOrientation() {

        if (getLayoutManager() instanceof LinearLayoutManager) {
            if (getLayoutManager() instanceof GridLayoutManager) {
                throw new RuntimeException("请设置LayoutManager为LinearLayoutManager");
            } else {
                return ((LinearLayoutManager) getLayoutManager()).getOrientation();
            }
        } else {
            throw new RuntimeException("请设置LayoutManager为LinearLayoutManager");
        }
    }

    public int getScrolledPosition() {
        if (mScrollManager == null) {
            return 0;
        } else {
            return mScrollManager.getPosition();
        }
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        DLog.w(TAG, "GalleryRecyclerView onSaveInstanceState()");
        return super.onSaveInstanceState();
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        super.onRestoreInstanceState(state);


        smoothScrollBy(10, 0);
        smoothScrollBy(0, 0);

        autoPlayGallery();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                removeAutoPlayTask();
                break;
            case MotionEvent.ACTION_MOVE:
                removeAutoPlayTask();
                break;
            case MotionEvent.ACTION_UP:
                autoPlayGallery();
                break;
            default:
                break;
        }
        return false;
    }

    /**
     * 播放间隔时间 ms
     *
     * @param interval int
     * @return GalleryRecyclerView
     */
    public GalleryRecyclerView intervalTime(@IntRange(from = 10) int interval) {
        this.mInterval = interval;
        return this;
    }

    /**
     * 开始处于的位置
     *
     * @param i int
     * @return GalleryRecyclerView
     */
    public GalleryRecyclerView initPosition(@IntRange(from = 0) int i) {
        if (i >= getAdapter().getItemCount()) {
            i = getAdapter().getItemCount() - 1;
        } else if (i < 0) {
            i = 0;
        }
        mInitPos = i;
        return this;
    }

    @Override
    public void onItemSizeMeasured(int size) {
        if (mInitPos < 0) {
            return;
        }
        if (mInitPos == 0) {
            scrollToPosition(0);
        } else {
            if (getOrientation() == LinearLayoutManager.HORIZONTAL) {
                smoothScrollBy(mInitPos * size, 0);
            } else {
                smoothScrollBy(0, mInitPos * size);
            }
        }
        mInitPos = -1;
    }

    public interface OnItemClickListener {

        void onItemClick(View view, int position);
    }
}