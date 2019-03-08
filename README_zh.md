[![API](https://img.shields.io/badge/API-16%2B-blue.svg?style=flat)](https://android-arsenal.com/api?level=16)
[![License](http://img.shields.io/badge/License-Apache%202.0-brightgreen.svg?style=flat)](https://opensource.org/licenses/Apache-2.0)

![logo](https://github.com/woxingxiao/BubbleSeekBar/blob/master/app/src/main/res/mipmap-xxhdpi/ic_launcher.png)

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
     // lite version 轻量版（推荐）
     // 例如：implementation 'com.xw.repo:bubbleseekbar:3.20-lite'
        implementation 'com.xw.repo:bubbleseekbar:${LATEST_VERSION}-lite'
     
     // enhanced version 增强版
     // 例如：implementation 'com.xw.repo:bubbleseekbar:3.20'
     // implementation 'com.xw.repo:bubbleseekbar:${LATEST_VERSION}'
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
查看demo获知更多使用细节。或者下载安装apk：[**sample.apk**](https://github.com/woxingxiao/BubbleSeekBar/raw/master/apk/sample.apk)

## Attentions
- 下列是两个版本的差异对比：  

  version | init | getter/setter
  -------- | ---|---
  lite|xml|min, max, progress
  enhanced|xml, java|all attrs
  
  推荐使用 **_lite_** 版本。

- 如果`BubbleSeekBar`的外部容器是可滑动的控件（如：`ScrollView`，但`ViewPager`除外），需要设置滑动监听来修正气泡的偏移，
否则滑动后气泡出现位置可能错乱。方法如下：
```java
   mContainer.setOnYourContainerScrollListener(new OnYourContainerScrollListener() {
       @Override
       public void onScroll() {
           // 调用修正偏移方法
           mBubbleSeekBar.correctOffsetWhenContainerOnScrolling();
       }
   });
```
- 当自定义section texts的时候，你首先需要确保属性 `bsb_section_text_position` 已经被设置为 `below_section_mark`，
然后在java代码中参照以下代码实现你自己的需求：
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
顺便，为了防止文字覆盖显示问题，属性`bsb_show_thumb_text`将被自动置`false`。
- 属性`bsb_always_show_bubble`在`RecyclerView`,`ListView`和`GridView`中不被支持。

## Attributes
[attr.xml](https://github.com/woxingxiao/BubbleSeekBar/blob/master/bubbleseekbar/src/main/res/values/attr.xml)
## 怎样提出有效的issue
- **确保你使用的是最新版本。** 如果仍然有问题，请开新issue；
- 尽可能详细的描述crash发生时的使用场景或者操作（有图片说明更好）；
- 告知手机型号和系统版本；
- 贴出你的xml或者java代码；
- 贴出你的奔溃日志；
- 礼貌。

--------
> **人生苦短，请选择科学上网。无限流量，节点多速度快。[电梯直达](https://my.holytech.tech/aff.php?aff=1016)**  

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
