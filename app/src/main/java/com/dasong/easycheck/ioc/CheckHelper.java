package com.dasong.easycheck.ioc;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.lang.ref.SoftReference;
import java.lang.reflect.Field;

import androidx.collection.ArrayMap;

public class CheckHelper {

    private static ArrayMap<String, ArrayMap<SoftReference<View>,IChecker>> cache = new ArrayMap<>();

    public static void bind(Object target){
        String key = String.valueOf(target.hashCode());
        ArrayMap<SoftReference<View>,IChecker> toastMap = cache.get(key);
        if(toastMap == null){
            toastMap = new ArrayMap<>();
            try {
                getToasts(target,toastMap);
                cache.put(key,toastMap);
            } catch (CheckerException e) {
                e.printStackTrace();
            }

        }
    }

    private static void getToasts(Object target, ArrayMap<SoftReference<View>,IChecker> toastMap) throws CheckerException{
        Class clz = target.getClass();
        Field[] fields = clz.getDeclaredFields();
        for(Field field : fields){
            if(field.isAnnotationPresent(Check.class)){
                field.setAccessible(true);
                try {
                    Object o = field.get(target);
                    if(o instanceof View){
                        Check annotation = field.getAnnotation(Check.class);
                        String toast = annotation.toast();
                        Class checker = annotation.checker();
                        IChecker iChecker = (IChecker) checker.newInstance();
                        Log.e("check",iChecker.getClass().getSimpleName());
                        iChecker.setToast(toast);
                        SoftReference<View> srView = new SoftReference<>((View)o);
                        toastMap.put(srView,iChecker);
                    }else{
                        throw new CheckerException("Annotation @Check Can only be used for View");
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static boolean check(Object target) throws CheckerException {
        if(!(target instanceof Context)){
            throw new CheckerException("target is not Context,use check(Context context,Object target)");
        }else{
            return check((Context)target,target);
        }
    }

    public static boolean check(Object target,Class filter) throws CheckerException {
        if(!(target instanceof Context)){
            throw new CheckerException("target is not Context,use check(Context context,Object target)");
        }else{
            return check((Context)target,target,filter);
        }
    }

    public static boolean check(Context context,Object target) throws CheckerException {
        return check(context,target,null);
    }

    public static boolean check(Context context,Object target,Class filter) throws CheckerException {
        String key = String.valueOf(target.hashCode());
        ArrayMap<SoftReference<View>,IChecker> toastMap = cache.get(key);
        if(toastMap == null){
            throw new CheckerException("Checker not bind");
        } else{
            for(ArrayMap.Entry<SoftReference<View>,IChecker> entry : toastMap.entrySet()){
                View view = entry.getKey().get();
                IChecker checker = entry.getValue();
                if(filter == null){
                    if(view != null && checker != null){
                        if(checker.check(view)){
                            Toast.makeText(context,checker.getToast(),Toast.LENGTH_SHORT).show();
                            return false;
                        }
                    }
                }else{
                    if(!checker.getClass().equals(filter)) continue;
                    if(filter != null && checker.getClass().equals(filter)){
                        if(view != null && checker != null){
                            if(checker.check(view)){
                                Toast.makeText(context,checker.getToast(),Toast.LENGTH_SHORT).show();
                                return false;
                            }
                        }
                    }
                }
            }
            return true;
        }
    }

    public static void checkForCallback(Object target,Callback callback) throws CheckerException {
        checkForCallback(target,null,callback);
    }

    public static void checkForCallback(Object target,Class filter,Callback callback) throws CheckerException {
        String key = String.valueOf(target.hashCode());
        ArrayMap<SoftReference<View>,IChecker> toastMap = cache.get(key);
        if(toastMap == null) throw new CheckerException("Checker not bind");
        else{
            for(ArrayMap.Entry<SoftReference<View>,IChecker> entry : toastMap.entrySet()){
                View view = entry.getKey().get();
                IChecker checker = entry.getValue();
                if(filter == null){
                    if(view != null && checker != null){
                        if(callback != null){
                            callback.result(view,checker.check(view),checker.getToast());
                        }
                    }
                }else{
                    if(!checker.getClass().equals(filter)) continue;
                    if(view != null && checker != null){
                        if(callback != null){
                            callback.result(view,checker.check(view),checker.getToast());
                        }
                    }
                }

            }
        }
    }

    public static void unbind(Object target){
        String key = String.valueOf(target.hashCode());
        cache.remove(key);
    }

    public interface Callback{
        void result(View view, boolean checkResult, String toast);
    }
}
