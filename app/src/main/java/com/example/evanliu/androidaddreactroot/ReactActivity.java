package com.example.evanliu.androidaddreactroot;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;

import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactRootView;
import com.facebook.react.common.LifecycleState;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;
import com.facebook.react.shell.MainReactPackage;

/**
 * Created by xiaoconglau on 16/06/2017.
 */

public class ReactActivity extends Activity implements DefaultHardwareBackBtnHandler {
    private ReactRootView mReactRootView;
    private ReactInstanceManager mReactInstanceManager;

    public static void startActivity(Context context){
        Intent intent = new Intent(context, ReactActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.react_activity);
        ViewGroup wrap = (ViewGroup) findViewById(R.id.react_wrap);

        mReactRootView = new ReactRootView(this);
        mReactInstanceManager = ReactInstanceManager.builder()
                .setApplication(getApplication())
                .setBundleAssetName("index.android.bundle")
                .setJSMainModuleName("index.android")
                .addPackage(new MainReactPackage())
                .setUseDeveloperSupport(true)
                .setInitialLifecycleState(LifecycleState.RESUMED)
                .build();
        mReactRootView.startReactApplication(mReactInstanceManager, "rntest", null);

        wrap.addView(mReactRootView);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if(mReactInstanceManager != null){
            mReactInstanceManager.onHostResume(this, this);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        if(mReactInstanceManager != null){
            mReactInstanceManager.onHostPause();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(mReactInstanceManager != null){
            mReactInstanceManager.onHostDestroy();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        if(mReactInstanceManager != null){
            mReactInstanceManager.onBackPressed();
        }else{
            super.onBackPressed();
        }
    }

    @Override
    public void invokeDefaultOnBackPressed() {
        super.onBackPressed();
    }
}
