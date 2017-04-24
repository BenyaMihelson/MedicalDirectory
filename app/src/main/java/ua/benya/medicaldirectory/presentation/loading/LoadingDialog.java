package ua.benya.medicaldirectory.presentation.loading;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.View;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.concurrent.atomic.AtomicBoolean;

import ua.benya.medicaldirectory.R;

/**
 * Created by Shipohvost on 10.11.2016.
 */

public class LoadingDialog extends DialogFragment {

    private String str;

    private static final Handler HANDLER = new Handler(Looper.getMainLooper());


    @NonNull
    public static LoadingView view(FragmentManager fm) {
        return new LoadingDialogView(fm);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_TITLE, getTheme());
        setCancelable(false);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getActivity())
                .setView(View.inflate(getActivity(), R.layout.dialog_loading,null))
                .create();
    }

    private static class LoadingDialogView implements LoadingView{

         private final FragmentManager mFm;
         private final AtomicBoolean mWaitForHide;

        public LoadingDialogView(@NonNull FragmentManager mFm) {
            this.mFm = mFm;
            boolean shown = mFm.findFragmentByTag(LoadingDialog.class.getName()) != null;
            this.mWaitForHide = new AtomicBoolean(shown);

        }

        @Override
        public void showLoadingIndicator() {
            if(mWaitForHide.compareAndSet(false,true)){
                if(mFm.findFragmentByTag(LoadingDialog.class.getName()) == null){
                    DialogFragment dialog = new LoadingDialog();
                    dialog.show(mFm, LoadingDialog.class.getName());
                }
            }

        }

        @Override
        public void hideLoadingIndicator() {
            if(mWaitForHide.compareAndSet(true,false)){
                HANDLER.post(new HideTask(mFm));
            }

        }
    }
    private static class HideTask implements Runnable{

        private final Reference<FragmentManager> mFmRef;
        private int attampt = 10;

        public HideTask(@NonNull FragmentManager fm) {
            mFmRef = new WeakReference<>(fm);
        }

        @Override
        public void run() {
            HANDLER.removeCallbacks(this);
            final FragmentManager fm = mFmRef.get();
            if(fm != null){
                final LoadingDialog dialog  = (LoadingDialog)
                        fm.findFragmentByTag(LoadingDialog.class.getName());
                if(dialog != null){
                    dialog.dismissAllowingStateLoss();
                }else if(--attampt >=0){
                    HANDLER.postDelayed(this,300);
                }
            }

        }
    }
}
