[![Download](https://api.bintray.com/packages/woxingxiao/maven/bubbleseekbar/images/download.svg?version=3.1)](https://bintray.com/woxingxiao/maven/bubbleseekbar/3.0/link)
[![Download](https://api.bintray.com/packages/woxingxiao/maven/bubbleseekbar/images/download.svg?version=3.1-lite)](https://bintray.com/woxingxiao/maven/bubbleseekbar/3.0-lite/link)
[![License](http://img.shields.io/badge/License-Apache%202.0-brightgreen.svg?style=flat)](https://opensource.org/licenses/Apache-2.0)
  
**自定义`SeekBar`，进度变化由可视化气泡样式呈现，定制化程度较高，适合大部分需求。欢迎`star` or `pull request`**  
****
## Screenshot
![demo1](https://github.com/woxingxiao/BubbleSeekBar/blob/master/screenshot/demo1.gif)
![demo2](https://github.com/woxingxiao/BubbleSeekBar/blob/master/screenshot/demo2.gif)
*******
![demo3](https://github.com/woxingxiao/BubbleSeekBar/blob/master/screenshot/demo3.gif)
![demo4](https://github.com/woxingxiao/BubbleSeekBar/blob/master/screenshot/demo4.gif)
## Download
The **LATEST_VERSION**：[![Download](https://api.bintray.com/packages/woxingxiao/maven/bubbleseekbar/images/download.svg)](https://bintray.com/woxingxiao/maven/bubbleseekbar/_latestVersion)
```groovy
  dependencies {
     // lite version 轻量版
     // 例如：compile 'com.xw.repo:bubbleseekbar:3.1-lite'
        compile 'com.xw.repo:bubbleseekbar:${LATEST_VERSION}-lite'
     
     // enhanced version 增强版
     // 例如：compile 'com.xw.repo:bubbleseekbar:3.0'
     // compile 'com.xw.repo:bubbleseekbar:${LATEST_VERSION}'
  }
```
## Usage  
### xml  
```xml
<com.xw.repo.BubbleSeekBar
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:layout_marginTop="8dp"
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
### java (not for **_lite_** version)
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
查看demo获知更多使用细节。  
或者下载安装apk：[**sample.apk**](https://github.com/woxingxiao/BubbleSeekBar/raw/master/apk/sample.apk)

## Attentions
- 下列是两个版本的差异对比：  

  version | init | getter/setter
  -------- | ---|---
  lite|xml|min, max, progress
  enhanced|xml, java|all attrs
  
  推荐使用 **_lite_** 版本。

- 如果`BubbleSeekBar`的外部容器是可滑动的控件，需要设置滑动监听来修正气泡的偏移，否则滑动后气泡出现位置可能错乱。方法如下：
```java
   mContainer.setOnYourContainerScrollListener(new OnYourContainerScrollListener() {
       @Override
       public void onScroll() {
           // 调用修正偏移方法
           mBubbleSeekBar.correctOffsetWhenContainerOnScrolling();
       }
   });
```
- 当设置`bsb_touch_to_seek`属性为`true`时， 最好不要点击**太快**去seek进度，否则动画可能没有足够时间播放。
- 本库依赖`support:appcompat-v7`采用的 **`provided`** 方式，所以不必担心冗余的依赖引入。

## Attributes  
```xml
<attr name="bsb_min" format="float|reference"/> <!--min < max, default: 0.0f-->
<attr name="bsb_max" format="float|reference"/> <!--min < max, default: 100.0f-->
<attr name="bsb_progress" format="float|reference"/> <!--real time progress value, default: min-->
<attr name="bsb_is_float_type" format="boolean"/> <!--support for float type-->
<attr name="bsb_track_size" format="dimension|reference"/> <!--height of right-track(on the right of thumb), default: 2dp-->
<!--height of left-track(on the left of thumb), default: 2dp higher than right-track's height-->
<attr name="bsb_second_track_size" format="dimension|reference"/>
<attr name="bsb_thumb_radius" format="dimension|reference"/> <!--radius of thumb, default: 2dp higher than left-track's height-->nce"/>
<!--radius of thumb when be dragging, default: 2 times of left-track's height-->
<attr name="bsb_thumb_radius_on_dragging" format="dimension|reference"/>
<attr name="bsb_track_color" format="color|reference"/> <!--color of right-track, default: R.color.colorPrimary-->
<attr name="bsb_second_track_color" format="color|reference"/> <!--color of left-track, default: R.color.colorAccent-->
<attr name="bsb_thumb_color" format="color|reference"/> <!--color of thumb, default: same as left-track's color-->
<attr name="bsb_section_count" format="integer|reference"/> <!--shares of whole progress(max - min), default: 10-->
<attr name="bsb_show_section_mark" format="boolean"/> <!--show demarcation points or not, default: false-->
<attr name="bsb_auto_adjust_section_mark" format="boolean"/> <!--auto scroll to the nearest section_mark or not, default: false-->
<attr name="bsb_show_section_text" format="boolean"/> <!--show section-text or not, default: false-->
<attr name="bsb_section_text_size" format="dimension|reference"/> <!--text size of section-text, default: 14sp-->
<attr name="bsb_section_text_color" format="color|reference"/> <!--text color of section-text, default: same as right-track's color-->
<!--text position of section-text relative to track, sides, bottom_sides, below_section_mark, default: sides-->
<attr name="bsb_section_text_position">
    <enum name="sides" value="0"/>
    <enum name="bottom_sides" value="1"/>
    <enum name="below_section_mark" value="2"/>
</attr>
<attr name="bsb_section_text_interval" format="integer"/> <!--the interval of two section-text, default: 1-->
<attr name="bsb_show_thumb_text" format="boolean"/> <!--show real time progress-text under thumb or not, default: false-->
<attr name="bsb_thumb_text_size" format="dimension|reference"/> <!--text size of progress-text, default: 14sp-->
<attr name="bsb_thumb_text_color" format="color|reference"/> <!--text color of progress-text, default: same as left-track's color-->
<attr name="bsb_show_progress_in_float" format="boolean"/> <!--show bubble-progress in float or not, default: false-->
<attr name="bsb_touch_to_seek" format="boolean"/> <!--touch anywhere on track to quickly seek, default: false-->
<attr name="bsb_seek_by_section" format="boolean"/> <!--seek by section, the progress may not be linear, default: false-->
<attr name="bsb_bubble_color" format="color|reference"/> <!--color of bubble, default: same as left-track's color-->
```
--------
> **人生苦短，请选择科学上网。推荐一下本人正在使用的，稳定高速，便宜好用。[推介链接](https://portal.shadowsocks.com.hk/aff.php?aff=8881)**  

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
