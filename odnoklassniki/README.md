[![](https://jitpack.io/v/KosyanMedia/android-sociallogin.svg)](https://jitpack.io/#KosyanMedia/android-sociallogin)

# SocialLogin Odniklassniki module

This module depends on [SocialLogin core library][sociallogin-core]

```Groovy
dependencies {
    compile 'com.github.KosyanMedia.android-sociallogin:sociallogin:x.y.z'
}
```

## Install

```Groovy
dependencies {
    compile 'com.github.KosyanMedia.android-sociallogin:odnoklassniki:x.y.z'
}
```

## Setup

Include Odnoklasniki Activity in your AndroidManifest.xml

```xml
<activity
    android:name="ru.ok.android.sdk.OkAuthActivity">
    <intent-filter>
        <action android:name="android.intent.action.VIEW"/>

        <category android:name="android.intent.category.DEFAULT"/>
        <category android:name="android.intent.category.BROWSABLE"/>

        <data
            android:host="ok{YOUR_APP_ID}"
            android:scheme="okauth"/>
    </intent-filter>
</activity>
```

Then replace {YOUR_APP_ID} with your app id from odnokassniki. Should looks like:

```xml
<data
    android:host="ok1234567890"
    android:scheme="okauth"/>
```


[sociallogin-core]: https://github.com/KosyanMedia/android-sociallogin