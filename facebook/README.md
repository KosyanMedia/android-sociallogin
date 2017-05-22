[![](https://jitpack.io/v/KosyanMedia/android-sociallogin.svg)](https://jitpack.io/#KosyanMedia/android-sociallogin)

# SocialLogin Facebook module

This module depends on [SocialLogin core library][sociallogin-core]

```Groovy
dependencies {
    compile 'com.github.KosyanMedia.android-sociallogin:sociallogin:x.y.z'
}
```

## Install

```Groovy
dependencies {
    compile 'com.github.KosyanMedia.android-sociallogin:facebook:x.y.z'
}
```

## Setup

To use, add to res/values/strings.xml your Facebook App keys

```xml
<string name="facebook_app_id">YOUR_APP_ID</string>
```

Then add this strings to your AndroidManifest.xml inside <application> section

```xml
<application>

    <meta-data
        android:name="com.facebook.sdk.ApplicationId"
        android:value="@string/facebook_app_id"/>

    <!-- Other tags ... -->

</application>
```


[sociallogin-core]: https://github.com/KosyanMedia/android-sociallogin