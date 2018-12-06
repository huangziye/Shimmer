
[![](https://jitpack.io/v/huangziye/Shimmer.svg)](https://jitpack.io/#huangziye/Shimmer)

# Add ` Shimmer ` to project

- Step 1：Add the JitPack repository to your build file

Add it in your root build.gradle at the end of repositories:

```android
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

- Step 2：Add the dependency

The latest version shall prevail.

```android
dependencies {
        implementation 'com.github.huangziye:Shimmer:${latest_version}'
}
```

# Features

- `Simple and efficient` : One line of code to complete all control state switching, recovery.
- `High compatibility` : Support all system controls, custom controls.
- `customizability` : Customize rendering rules and configure as needed.


# Effect picture


![微光效果图](https://github.com/huangziye/Shimmer/blob/master/screenshot/shimmer.gif)


# Usage

```Kotlin
//ViewGroup

var ll = findViewById<LinearLayout>(R.id.ll)
// set shimmer effect
ShimmerUtil.with(ll).renderChilds()

// hide shimmer effect
Handler().postDelayed({ShimmerUtil.with(ll).removeChilds()},5000)

//View

var textView = findViewById<TextView>(R.id.tv)
ShimmerUtil.with(textView).render()

ShimmerUtil.with(textView).remove()


//Optional operation
ShimmerUtil.with(textView)
// set draw rect color
.color(color)
// set draw rect radius
.radius(radius)
//set alpha
.alpha(alpha)
// draw rect
.drawRect(drawRect)
.drawRect(width, height)
//set filter: default filter DefaultOnShimmerFilter
.filter(filter)
```







# About me


- [简书](https://user-gold-cdn.xitu.io/2018/7/26/164d5709442f7342)

- [掘金](https://juejin.im/user/5ad93382518825671547306b)

- [Github](https://github.com/huangziye)


# License

```
Copyright 2018, huangziye

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
```



