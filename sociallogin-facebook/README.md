# SocialLogin Facebook module

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