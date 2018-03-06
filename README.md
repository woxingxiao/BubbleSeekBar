[![API](https://img.shields.io/badge/API-16%2B-blue.svg?style=flat)](https://android-arsenal.com/api?level=16)
[![License](http://img.shields.io/badge/License-Apache%202.0-brightgreen.svg?style=flat)](https://opensource.org/licenses/Apache-2.0)

[**中文说明**](https://github.com/woxingxiao/BubbleSeekBar/blob/master/README_zh.md)

![logo](https://github.com/woxingxiao/BubbleSeekBar/blob/master/app/src/main/res/mipmap-xxhdpi/ic_launcher.png)

**A beautiful Android custom seek bar, which has a bubble view with progress appearing upon when seeking. Highly customizable, mostly demands has been considered. `star` or `pull request` will be welcomed**
****
## Screenshot
![demo1](https://github.com/woxingxiao/BubbleSeekBar/blob/master/screenshot/demo1.gif)
![demo2](https://github.com/woxingxiao/BubbleSeekBar/blob/master/screenshot/demo2.gif)
******
![demo3](https://github.com/woxingxiao/BubbleSeekBar/blob/master/screenshot/demo3.gif)
![demo4](https://github.com/woxingxiao/BubbleSeekBar/blob/master/screenshot/demo4.gif)

## Download
The **LATEST_VERSION**: [![Download](https://api.bintray.com/packages/woxingxiao/maven/bubbleseekbar/images/download.svg)](https://bintray.com/woxingxiao/maven/bubbleseekbar/_latestVersion)
```groovy
  dependencies {
     // lite version (recommended)
     // e.g. compile 'com.xw.repo:bubbleseekbar:3.16-lite'
        compile 'com.xw.repo:bubbleseekbar:${LATEST_VERSION}-lite'

     // enhanced version
     // e.g. compile 'com.xw.repo:bubbleseekbar:3.16'
     // compile 'com.xw.repo:bubbleseekbar:${LATEST_VERSION}'
  }
```

## Usage  
### Init in xml
```xml
<com.xw.repo.BubbleSeekBar
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    app:bsb_bubble_color="@color/color_red_light"
    app:bsb_bubble_text_color="@color/colorPrimaryDark"
    app:bsb_max="50.0"
    app:bsb_min="-50"
    app:bsb_progress="0"
    app:bsb_second_track_color="@color/color_red"
    app:bsb_section_count="5"
    app:bsb_section_text_position="bottom_sides"
    app:bsb_show_progress_in_float="true"
    app:bsb_show_section_mark="true"
    app:bsb_show_section_text="true"
    app:bsb_show_thumb_text="true"
    app:bsb_track_color="@color/color_red_light"/>
```
```xml
<com.xw.repo.BubbleSeekBar
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    app:bsb_auto_adjust_section_mark="true"
    app:bsb_second_track_color="@color/color_blue"
    app:bsb_section_count="5"
    app:bsb_section_text_position="below_section_mark"
    app:bsb_show_section_mark="true"
    app:bsb_show_section_text="true"
    app:bsb_show_thumb_text="true"
    app:bsb_thumb_text_size="18sp"
    app:bsb_touch_to_seek="true"/>
```
### Init in java (not for **_lite_** version)
```java
mBbubbleSeekBar.getConfigBuilder()
               .min(0.0)
               .max(50)
               .progress(20)
               .sectionCount(5)
               .trackColor(ContextCompat.getColor(getContext(), R.color.color_gray))
               .secondTrackColor(ContextCompat.getColor(getContext(), R.color.color_blue))
               .thumbColor(ContextCompat.getColor(getContext(), R.color.color_blue))
               .showSectionText()
               .sectionTextColor(ContextCompat.getColor(getContext(), R.color.colorPrimary))
               .sectionTextSize(18)
               .showThumbText()
               .thumbTextColor(ContextCompat.getColor(getContext(), R.color.color_red))
               .thumbTextSize(18)
               .bubbleColor(ContextCompat.getColor(getContext(), R.color.color_green))
               .bubbleTextSize(18)
               .showSectionMark()
               .seekBySection()
               .autoAdjustSectionMark()
               .sectionTextPosition(BubbleSeekBar.TextPosition.BELOW_SECTION_MARK)
               .build();
```
Check out the demo for more details. Or download the apk: [**sample.apk**](https://github.com/woxingxiao/BubbleSeekBar/raw/master/apk/sample.apk)
## Attentions  
- There are two versions of this library.The differences as follow:  

  version | init | getter/setter
  -------- | ---|---
  lite|xml|min, max, progress
  enhanced|xml, java|all attrs

  **_lite_** version is recommended.
- You must correct the offsets by setting `ScrollListener` when `BubbleSeekBar`'s parent view is scrollable
(such as `ScrollView`, except `ViewPager`), otherwise, the appearing position of the bubble may be wrong. For example:
```java
   mContainer.setOnYourContainerScrollListener(new OnYourContainerScrollListener() {
       @Override
       public void onScroll() {
           // call this method to correct offsets
           mBubbleSeekBar.correctOffsetWhenContainerOnScrolling();
       }
   });
```
- When customize the section texts, you should make sure that the attr `bsb_section_text_position`
has been set to `below_section_mark` at first, then follow the example below in your java code:
```java
   mBubbleSeekBar.setCustomSectionTextArray(new BubbleSeekBar.CustomSectionTextArray() {
       @NonNull
       @Override
       public SparseArray<String> onCustomize(int sectionCount, @NonNull SparseArray<String> array) {
           array.clear();
           array.put(1, "bad");
           array.put(4, "ok");
           array.put(7, "good");
           array.put(9, "great");

           return array;
       }
   });
```
BTW, the attr `bsb_show_thumb_text` will be set to `false` automatically for avoiding the text coverage display problems.
- The attr `bsb_always_show_bubble` is not supported in the `RecyclerView`, `ListView` and `GridView`.

## Attributes
[attr.xml](https://github.com/woxingxiao/BubbleSeekBar/blob/master/bubbleseekbar/src/main/res/values/attr.xml)
## How to submit a valid issue
- **Make sure you compiled the latest version.** If it still doesn't work out, don't hesitate to open a new issue.
- Describe the scenarios or operates when crash happened as much as possible(pictures would be better).
- Tell me your device type and Android OS version is very helpful.
- Paste your xml or java code.
- Paste the crash log.
- Be polite.

## License
```
   Copyright 2017 woxingxiao

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
