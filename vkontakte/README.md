# SocialLogin Vkontakte module

This module depends on [SocialLogin core library][sociallogin-core]

```Groovy
dependencies {
    compile 'com.github.KosyanMedia.android-sociallogin:sociallogin:1.1.0'
}
```

## Install

```Groovy
dependencies {
    compile 'com.jetradarmobile:sociallogin-vkontakte:1.0.0'
}
```

## Setup

Add your app id in resources. You should use exact same resource name as in sample below

```xml
<integer name="com_vk_sdk_AppId">YOUR_APP_ID</integer>
```

Then initialize VKSDK inside onCreate() method of your Application class

```Kotlin
class App: Application() {
 override fun onCreate() {
     super.onCreate()
     VKSdk.initialize(applicationContext)
 }
}
```

[sociallogin-core]: https://github.com/KosyanMedia/android-sociallogin