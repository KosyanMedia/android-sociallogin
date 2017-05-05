# SocialLogin RxJava module

This module depends on [SocialLogin core library][sociallogin-core]

```Groovy
dependencies {
    compile 'com.github.KosyanMedia.android-sociallogin:sociallogin:1.0.0'
}
```

## Install

```Groovy
dependencies {
    compile 'com.github.KosyanMedia.android-sociallogin:sociallogin-rx:1.0.0'
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
  RxSocialLogin.instance.onActivityResult(requestCode, resultCode, data)
  super.onActivityResult(requestCode, resultCode, data)
}
```


[sociallogin-core]: https://github.com/KosyanMedia/android-sociallogin