package qupingfang.mykj.com.statusdemo;

import android.app.Activity;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import java.lang.reflect.Field;

/**
 * Created by zhou12314 on 2016/4/13 0013.
 */
public class SetContentViewUtils {

    public  static  void setView(Activity activity,int id){
        LayoutInflater layoutInflater = LayoutInflater.from(activity);
        setTranslucentStatus(activity);
        ViewGroup decorView = (ViewGroup) activity.getWindow().getDecorView().findViewById(android.R.id.content);
        int height = getStatusBarHeight(activity);
        ViewGroup linearLayout = (ViewGroup) layoutInflater.from(activity).inflate(id,null);
        linearLayout.setPadding(0,height,0,0);
        decorView.removeAllViews();
       decorView.addView(linearLayout);

    }
    private static  int getStatusBarHeight(Activity activity) {
        Class<?> c = null;
        Object obj = null;
        Field field = null;
        int x = 0, sbar = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            sbar = activity.getResources().getDimensionPixelSize(x);
            return  sbar;
        } catch (Exception e1) {
            //       loge("get status bar height fail");
            e1.printStackTrace();
            return 0;
        }
    }

    /**
     * 设置状态栏背景状态
     */
    private static  void setTranslucentStatus(Activity context)
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
        {
            Window win = context.getWindow();
            WindowManager.LayoutParams winParams = win.getAttributes();
            final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
            winParams.flags |= bits;
            win.setAttributes(winParams);
        }

    }
}
