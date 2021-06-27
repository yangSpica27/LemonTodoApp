package spica.lemon.plan.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

/**
 * @ClassName FABBehavior
 * @Description TODO
 * @Author Spica2 7
 * @Date 2021/6/27 16:41
 */
public class FABBehavior extends FloatingActionButton.Behavior {
  private boolean visible = true;//是否可见

  public FABBehavior(Context context, AttributeSet attrs) {
    super();
  }

  @Override
  public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
    return axes == ViewCompat.SCROLL_AXIS_VERTICAL || super.onStartNestedScroll(coordinatorLayout, child, directTargetChild, target, axes, type);
  }

  @Override
  public void onNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull FloatingActionButton child, @NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type) {
    super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, type);
    if (dyConsumed > 0 && visible) {
      visible = false;
      animateOut(child);
    } else if (dyConsumed < 0 && !visible) {
      visible = true;
      animateIn(child);
    }
  }

  // FAB隐藏动画
  private void animateOut(FloatingActionButton fab) {
    CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) fab.getLayoutParams();
    fab.animate().translationY(fab.getHeight() + layoutParams.bottomMargin).setInterpolator(new AccelerateInterpolator(3));
    ViewCompat.animate(fab).scaleX(0f).scaleY(0f).start();
  }

  // FAB显示动画
  private void animateIn(FloatingActionButton fab) {
    fab.animate().translationY(0).setInterpolator(new DecelerateInterpolator(3));
    ViewCompat.animate(fab).scaleX(1f).scaleY(1f).start();
  }

}
