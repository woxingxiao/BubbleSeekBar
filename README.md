[![](https://jitpack.io/v/woxingxiao/BubbleSeekBar.svg)](https://jitpack.io/#woxingxiao/BubbleSeekBar)
[![License](http://img.shields.io/badge/license-MIT-green.svg?style=flat)]()

[**中文说明**](https://github.com/woxingxiao/BubbleSeekBar/blob/master/README_zh.md)

**A beautiful Android custom seekbar, which has a bubble view with progress appearing upon when seeking. Highly customizable, mostly demands has been considered. `star` or `pull request` will be welcomed**
****
##Screenshot
![demo1](https://github.com/woxingxiao/BubbleSeekBar/blob/master/screenshot/demo1.gif)
![demo2](https://github.com/woxingxiao/BubbleSeekBar/blob/master/screenshot/demo2.gif)
![demo3](https://github.com/woxingxiao/BubbleSeekBar/blob/master/screenshot/demo3.gif)

##Download
root project:`build.gradle`
```groovy
  allprojects {
	 repositories {
		...
		maven { url "https://jitpack.io" }
	 }
  }
```
app:`build.gradle`
```groovy
  dependencies {
     // enhanced version
     // e.g. compile 'com.github.woxingxiao:BubbleSeekBar:3.0' 
     compile 'com.github.woxingxiao:BubbleSeekBar:${LATEST_VERSION}'
     
     // lite version
     // e.g. compile 'com.github.woxingxiao:BubbleSeekBar:3.0-lite'
     // compile 'com.github.woxingxiao:BubbleSeekBar:${LATEST_VERSION}-lite' 
  }
```

##Usage  
###xml  
```xml
<com.xw.repo.BubbleSeekBar
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:layout_marginTop="8dp"
    app:bsb_bubble_color="@color/color_red_light"
    app:bsb_bubble_text_color="@color/colorPrimaryDark"
    app:bsb_max="50"
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
    android:layout_marginTop="8dp"
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
###java (not for **lite version**)  
```java
mBbubbleSeekBar.getConfigBuilder()
               .min(0)
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
Check out the demo for more details.  
Or download the apk:
[**sample.apk**](https://github.com/woxingxiao/BubbleSeekBar/raw/master/apk/sample.apk)
##Attentions  
- There are two versions of this library.The differences as follow:  
version | init | getter/setter
-------- | ---|---
lite|xml|min, max, progress
enhanced|xml, java|all attrs

- You must correct the offsets by setting `ScrollListener` when `BubbleSeekBar`'s parent view is scrollable, otherwise the position of bubble appears maybe be wrong. For example:
```java
   mContainer.setOnYourContainerScrollListener(new OnYourContainerScrollListener() {
       @Override
       public void onScroll() {
           // call this method to correct offsets
           mBubbleSeekBar.correctOffsetWhenContainerOnScrolling();
       }
   });
```
- When set `bsb_touch_to_seek` attribute to be `true` , you better not to click **too fast** because the animation may not have enough time to play.
- This library depends `support:appcompat-v7` is **`provided`**, so you don't need to worry about redundant `dependencies`.

##Attributes  
```xml
<attr name="bsb_min" format="integer|reference"/>
<attr name="bsb_max" format="integer|reference"/>
<attr name="bsb_progress" format="integer|reference"/>
<attr name="bsb_is_float_type" format="boolean"/>
<attr name="bsb_track_size" format="dimension|reference"/>
<attr name="bsb_second_track_size" format="dimension|reference"/>
<attr name="bsb_thumb_radius" format="dimension|reference"/>
<attr name="bsb_thumb_radius_on_dragging" format="dimension|reference"/>
<attr name="bsb_section_count" format="integer|reference"/>
<attr name="bsb_thumb_color" format="color|reference"/>
<attr name="bsb_track_color" format="color|reference"/>
<attr name="bsb_second_track_color" format="color|reference"/>
<attr name="bsb_show_section_text" format="boolean"/>
<attr name="bsb_section_text_size" format="dimension|reference"/>
<attr name="bsb_section_text_color" format="color|reference"/>
<attr name="bsb_section_text_position">
    <enum name="sides" value="0"/>
    <enum name="bottom_sides" value="1"/>
    <enum name="below_section_mark" value="2"/>
</attr>
<attr name="bsb_section_text_interval" format="integer"/>
<attr name="bsb_show_thumb_text" format="boolean"/>
<attr name="bsb_thumb_text_size" format="dimension|reference"/>
<attr name="bsb_thumb_text_color" format="color|reference"/>
<attr name="bsb_bubble_color" format="color|reference"/>
<attr name="bsb_bubble_text_size" format="dimension|reference"/>
<attr name="bsb_bubble_text_color" format="color|reference"/>
<attr name="bsb_show_section_mark" format="boolean"/>
<attr name="bsb_auto_adjust_section_mark" format="boolean"/>
<attr name="bsb_show_progress_in_float" format="boolean"/>
<attr name="bsb_anim_duration" format="integer"/>
<attr name="bsb_touch_to_seek" format="boolean"/>
<attr name="bsb_seek_by_section" format="boolean"/>
```
##License
```
The MIT License (MIT)

Copyright (c) 2017 woxingxiao

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
