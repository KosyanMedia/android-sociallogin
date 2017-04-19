# Social Login
### Library for authorizing in popular social networks

## Import

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
      .doOnNext { token -> /* do somthing with token */ }
      .doOnError { error -> /* handle error */ }
      .subscribe()
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
