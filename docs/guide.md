#CocosPlay

##Overview

###Requirements
Android 2.3 (API 10) and above

###Package contents
**sdk**: CocosPlay SDK integration files
**demo**: A Demo project demostrates how to integrate CocosPlay SDK
**docs**: Documentations

##Integration
Integrate CocosPlay SDK is quick and easy, just a few steps.

###1.Android Project Integration
Copy everything under `sdk` folder to the `libs` folder of your project

###2.Edit AndroidManifest.xml
####Add Permissions
```xml
<uses-permission android:name="android.permission.BLUETOOTH" />
<uses-permission android:name="android.permission.INTERNET"/>
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
<uses-permission android:name="android.permission.ACCESS_WIFI_STATE"/>
<uses-permission android:name="android.permission.READ_PHONE_STATE" />
<uses-permission android:name="android.permission.WAKE_LOCK" />
<uses-permission android:name="android.permission.GET_TASKS" />
<uses-permission android:name="android.permission.READ_SETTINGS" />

<!-- If you want to supports billable cocosplay app, you'll also need  -->
<uses-permission android:name="com.android.vending.BILLING" />
```

####Register services and activities
```xml
<!-- CocosPlay Activities -->

<activity
    android:name="com.chukong.cocosplay.GameActivity"
    android:configChanges="orientation"
    android:launchMode="singleTop"
    android:process="your.package.name.gameview"
    android:label="@string/app_name"
    android:screenOrientation="behind"
    android:theme="@android:style/Theme.NoTitleBar.Fullscreen">
    <intent-filter>
        <action android:name="com.chukong.cocosplay.VIEW" />
        <category android:name="android.intent.category.DEFAULT" />
    </intent-filter>
</activity>

<activity
    android:name="com.chukong.cocosplay.CocosProxyActivity"
    android:configChanges="orientation"
    android:process="your.package.name.gameview"
    android:label="@string/app_name"
    android:screenOrientation="behind"
    android:theme="@android:style/Theme.NoTitleBar.Fullscreen">
    <intent-filter>
        <action android:name="com.chukong.cocosplay.VIEW" />
        <category android:name="android.intent.category.DEFAULT" />
    </intent-filter>
</activity>
<activity
    android:name="com.chukong.cocosplay.CocosProxyFragmentActivity"
    android:configChanges="orientation"
    android:process="your.package.name.gameview"
    android:label="@string/app_name"
    android:screenOrientation="behind"
    android:theme="@android:style/Theme.NoTitleBar.Fullscreen">
    <intent-filter>
        <action android:name="com.chukong.cocosplay.fragment.VIEW" />
        <category android:name="android.intent.category.DEFAULT" />
    </intent-filter>
</activity>
<activity
    android:name="com.chukong.cocosplay.CocosProxyActivityGroup"
    android:configChanges="orientation"
    android:process="your.package.name.gameview"
    android:label="@string/app_name"
    android:screenOrientation="behind"
    android:theme="@android:style/Theme.NoTitleBar.Fullscreen">
    <intent-filter>
        <action android:name="com.chukong.cocosplay.activitygroup.VIEW" />
        <category android:name="android.intent.category.DEFAULT" />
    </intent-filter>
</activity>

<activity
    android:name="com.chukong.cocosplay.host.CocosPlayHostActivity"
    android:theme="@android:style/Theme.NoTitleBar.Fullscreen"
    android:configChanges="keyboardHidden|navigation|orientation"
    android:process="your.package.name.gameview"/> <!-- Replace Package Name Here -->
<activity
    android:name="com.chukong.cocosplay.host.CocosPlayHostActivityGroup"
    android:theme="@android:style/Theme.NoTitleBar.Fullscreen"
    android:process="your.package.name.gameview"/> <!-- Replace Package Name Here -->
<activity
    android:name="com.chukong.cocosplay.host.CocosPlayHostListActivity"
    android:theme="@android:style/Theme.NoTitleBar.Fullscreen"
    android:process="your.package.name.gameview"/> <!-- Replace Package Name Here -->
<activity
    android:name="com.chukong.cocosplay.host.CocosPlayHostTabActivity"
    android:theme="@android:style/Theme.NoTitleBar.Fullscreen"
    android:process="your.package.name.gameview"/> <!-- Replace Package Name Here -->
<activity
    android:name="com.chukong.cocosplay.host.CocosPlayHosteActivityGroup"
    android:theme="@android:style/Theme.NoTitleBar.Fullscreen"
    android:process="your.package.name.gameview"/> <!-- Replace Package Name Here -->
<service
    android:name="com.chukong.cocosplay.host.CocosPlayHostService"
    android:process="your.package.name.gameview"/> <!-- Replace Package Name Here -->
<service
    android:name="com.chukong.cocosplay.host.CocosPlayHostRemoteService"
    android:process=":cocosplay_remote"/>
<provider android:name="com.chukong.cocosplay.host.CocosPlayHostProvider"
    android:authorities="your.package.name.provider"
    android:process="your.package.name.gameview"/> <!-- Replace Package Name Here -->
<provider android:name="com.chukong.cocosplay.host.CocosPlayHostRemoteProvider"
    android:authorities="your.package.name.provider_remote"
    android:process=":your.package.name.cocosplay_remote"/> <!-- Replace Package Name Here -->
<!-- End of CocosPlay Activities -->
```
####Update package name
Search and replace `your.package.name` with the package name of your app
>Note: most of them are in the `android:process`

####Modify MainActivity
Add the following line to your main activity class
```
android:configChanges="orientation|keyboard|screenSize|mcc|mnc"
```
If your build target is lower than android 4.0, add the following instead
```
android:configChanges="orientation|keyboard|mcc|mnc"
```

###3.Code Integration
Add the following line to your `Activity` class
```java
import com.chukong.cocosplay.CocosPlay;
import com.chukong.cocosplay.CocosPlay.DemoGameEndedListener;

protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    //Initialize
    //Switch 0000001 with your app id
    CocosPlay.init(this, "000001");
}

@Override
protected void onDestroy() {
  super.onDestroy();

  CocosPlay.destroy();
}

@Override
protected void onPause() {
  super.onPause();

  CocosPlay.pause();
}

@Override
protected void onResume() {
  super.onResume();

  CocosPlay.resume();
}
```

Use the following code to start a game
```java
CocosPlay.runDemoGame(MainActivity.this, "com.chukong.cosmiccrash2", loadingBackground, new DemoGameEndedListener(){

  @Override
  public String getDialogBtnText() {
    return "";
  }

  @Override
  public String getDialogContent() {
    return "";
  }

  @Override
  public void onGameExit() {
  }

  @Override
  public void onNextStep() {
  }

});
```

###4.Proguard
If your project use proguard, please add the following to your proguard config
```
-keep class com.chukong.cocosplay.**{ *;}
-keep class com.coco.**{ *;}
-keep class android.support.v4.**{ *;}
```
