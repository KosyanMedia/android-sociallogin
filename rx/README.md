[![](https://jitpack.io/v/KosyanMedia/android-sociallogin.svg)](https://jitpack.io/#KosyanMedia/android-sociallogin)

# SocialLogin RxJava module

This module depends on [SocialLogin core library][sociallogin-core]

```Groovy
dependencies {
    compile 'com.github.KosyanMedia.android-sociallogin:sociallogin:x.y.z'
}
```

## Install

```Groovy
dependencies {
    compile 'com.github.KosyanMedia.android-sociallogin:rx:x.y.z'
}
```

## Setup

Social login supports RxJava 2. For use social login in rx maner Just replace **SocialLogin** with **RxSocialLogin**

```Kotlin
RxSocialLogin.instance.loginTo(this, /* Activity */ FacebookNetwork())
      .subscribe(
        { token -> /* do somthing with token */ },
        { error -> /* handle error */ }
      )
```

and in onActivityResult too


```Kotlin
override fun onActivtyResult(requestCode: Int, resultCode: Int, data: Intent?) {
  super.onActivityResult(requestCode, resultCode, data)
  RxSocialLogin.instance.onActivityResult(requestCode, resultCode, data)
}
```


[sociallogin-core]: https://github.com/KosyanMedia/android-sociallogin