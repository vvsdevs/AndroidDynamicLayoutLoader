[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
[![](https://jitpack.io/v/vvsdevs/AndroidDynamicLayoutLoader.svg)](https://jitpack.io/#vvsdevs/AndroidDynamicLayoutLoader)
[![API](https://img.shields.io/badge/API-19%2B-orange.svg?style=flat)](https://android-arsenal.com/api?level=19)
 
# DynamicLayoutLoader

Introducing the AndroidDynamicLayoutLoader library, a powerful solution for seamlessly inflating dynamic layouts at runtime using JSON configurations obtained from a server. This library proves invaluable in scenarios where layouts require modification without the need for updating the entire application.

By leveraging the DynamicLayoutLoader library, developers can effortlessly adapt their app's UI in real-time, ensuring a dynamic user experience. With the ability to fetch JSON configurations from a server, it becomes a breeze to incorporate layout changes on-the-fly without necessitating a full app update.

Stay ahead of the curve with this groundbreaking Android library, enabling you to effortlessly handle ever-changing layout requirements. Experience the ease and flexibility of runtime layout inflation, powered by the DynamicLayoutLoader library.

## Advanced features

* Targeting specific devices by brand, model and Android API version
* Layout changes based on configuration fields

Some of Designs

Results | Login Screen
--- | ---
![LOGIN SCREEN](https://raw.githubusercontent.com/vvsdevs/AndroidDynamicLayoutLoader/master/DemoLayout/src/main/assets/results.jpg) | ![LOGIN SCREEN](https://raw.githubusercontent.com/vvsdevs/AndroidDynamicLayoutLoader/master/DemoLayout/src/main/assets/login_screen.jpg)

## Installing

1. Add repository in root ```build.gradle```

```gradle
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}
```

2. Add the dependency

```gradle
    implementation 'com.github.vvsdevs:AndroidDynamicLayoutLoader:v1.0.0'
```

3. Add below 2 permissions
````
    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
````


## Usage

1. Create JSON layout and upload it somewhere

```json
{
  "views":[  
    {  
      "class":"android.widget.ImageView",
      "attributes":{  
        "layout_width":"wrap_content",
        "layout_height":"wrap_content",
        "src":"https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png",
        "cache":true
      }
    },
    {  
      "class":"android.widget.TextView",
      "attributes":{  
        "text":"Yo!",
        "textColor":"#FF69B4"
      }
    }
  ]
}
```

2. Create XML wrapper layout that will contain loaded views

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout
	xmlns:android="http://schemas.android.com/apk/res/android"
	android:layout_width="match_parent"
	android:layout_height="match_parent"
	android:orientation="vertical"
	android:id="@+id/mainLayout">
		
	<!-- Dynamic content will be added here -->
</LinearLayout>
```

3. Initialize DynamicLayoutLoader by passing it:
* URL = Link to JSON layout **directory**
* name = Name of JSON layout **file**
* layout = Wrapper layout that will contain loaded views

```java
   try {
        new LayoutUpdater("https://raw.githubusercontent.com/vvsdevs/AndroidDynamicLayoutLoader/master/DemoLayout/src/main/assets/",
        "login_screen",
        findViewById(R.id.mainLayout))
        .initialize();
        } 
        catch (DynamicException e) {
        e.printStackTrace();
      }
```

## Manual

For advanced usage, take a look at this awesome [manual](https://github.com/vvsdevs/AndroidDynamicLayoutLoader/blob/master/MANUAL.md).

## Additional features

* Event listener

```java
setListener(new LayoutStateListener() {
	@Override
	public void onSuccess(String message) {
		// everything is okay
	}
	
	@Override
	public void onError(String message) {
		// notify user
	}
})
```

* Loading from cache (skip layout fetching from server)

```java
setOptions(CACHE_ONLY)
```

* Non-stop layout fetching

```java
setOptions(NON_STOP) // use with setAsyncPause(long millis)
```

## Documentation

###### `public DynamicLayoutLoader(String url, String name, ViewGroup layout) throws DynamicLayoutLoaderException`

One and only constructor

 * **Parameters:**
   * `url` — URL of directory/folder where JSON layout file is located (for example, "https://raw.githubusercontent.com/vvsdevs/AndroidDynamicLayoutLoader/master/DemoLayout/src/main/assets/")
   * `name` — JSON layout file name with or without extension (for example, "login_screen") (* Don't add example. "login_screen.json")
   * `layout` — wrapper layout that will contain inflated layout from JSON file (for example, findViewById(R.id.mainLayout))
 * **Exceptions:** `DynamicLayoutLoaderException` — if any of passed parameters is null

###### `public DynamicLayoutLoader setListener(DynamicLayoutLoaderListener listener)`

Attaches event listener to DynamicLayoutLoader object

 * **Parameters:** `listener` — listener for success and error events caused by network, storage, etc.
 * **Returns:** DynamicLayoutLoader object ready for initialization

###### `public DynamicLayoutLoader setOptions(DynamicLayoutLoaderOptions.Option ... options)`

Attaches options to DynamicLayoutLoader object

 * **Parameters:** `options` — options for DynamicLayoutLoader (for example, ONLY_CACHE)
 * **Returns:** DynamicLayoutLoader object ready for initialization

###### `public void initialize()`

Starts layout fetching from cache/server depending on provided options

## TODO

* Support vector drawables
* Support more views
