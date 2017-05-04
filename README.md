# Social Login
### Library for authorizing in popular social networks

## Install

Include jcenter to repositories inside project build.gradle file

```Groovy
repositories {
    jcenter()
}
```

Add following string to your module build.gradle file

```Groovy
dependencies {
    compile 'com.jetradarmobile:andorid-sociallogin:1.0.0'
}
```

## Usage

All samples will be in Kotlin, [sample project is here][kotlin-sample]

If you use Java see [Java Sample][java-sample]

To login into some network you should call SocialLogin singleton

```Kotlin
SocialLogin.instance.loginTo(this, /* Activity */ FacebookNetwork(), object: SocialLoginCallback {
  
  override fun onLoginSuccess(socialNetwork: SocialNetwork, token: SocialToken){
    // your code  
  }
  
  override fun onLoginError(socialNetwork: SocialNetwork, errorMessage: Strig) {
    // your code
  }
})
```
Also you should redirect you onActivityResult() callback to SocialLogin

```Kotlin
override fun onActivtyResult(requestCode: Int, resultCode: Int, data: Intent?) { 
  SocialLogin.instance.onActivityResult(requestCode, resultCode, data)
  super.onActivityResult(requestCode, resultCode, data)
}
```

## Rx Java

First of all you should include sociallogin-rx module to your project

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


[java-sample]: https://github.com/KosyanMedia/android-sociallogin/tree/master/sample-java
[kotlin-sample]: https://github.com/KosyanMedia/android-sociallogin/tree/master/sample-kotlin
