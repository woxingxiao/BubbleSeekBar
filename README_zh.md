[![](https://jitpack.io/v/woxingxiao/BubbleSeekBar.svg)](https://jitpack.io/#woxingxiao/BubbleSeekBar)
[![License](http://img.shields.io/badge/license-MIT-green.svg?style=flat)]()
  
**自定义`SeekBar`，进度变化由可视化气泡样式呈现，定制化程度较高，适合大部分需求。欢迎`star` or `pull request`**  
****
##Screenshot
![demo](https://github.com/woxingxiao/BubbleSeekBar/blob/master/screenshot/demo.gif)  
![demo](https://github.com/woxingxiao/BubbleSeekBar/blob/master/screenshot/demo2.gif)
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
     compile 'com.github.woxingxiao:BubbleSeekBar:$LATEST_VERSION'
  }
```
##Usage
查看demo获知更多使用细节: [**`MainActivity.java`**](https://github.com/woxingxiao/BubbleSeekBar/blob/master/app/src/main/java/com/xw/samlpe/bubbleseekbar/MainActivity.java)
[**`content_main.xml`**](https://github.com/woxingxiao/BubbleSeekBar/blob/master/app/src/main/res/layout/content_main.xml)
[**sample.apk**](https://github.com/woxingxiao/BubbleSeekBar/raw/master/apk/sample.apk)
##Attentions
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
- 本库依赖`support:appcompat-v7`采用的**`provided`**方式，所以不必担心冗余的依赖引入。  

##Attributes  
attr | format | description
-------- | ---|---
bsb_min|int|最小值，或起始值，或首值，整数（可正可负），默认0
bsb_max|int|最大值，或结束值，或尾值，整数，默认100
bsb_progress|int|进度值，或实时值，介于min和max之间的整数
bsb_track_size|dimension|进度值右边track（右track）的厚度，默认2dp
bsb_second_track_size|dimension|进度值左边track（左track）的厚度，默认比右track厚2dp
bsb_thumb_radius|dimension|thumb半径，默认比左track厚2dp
bsb_thumb_radius_on_dragging|dimension|当拖动时thumb半径，默认是左track的2倍
bsb_section_count|int|整个track均分份数，默认10
bsb_show_section_mark|boolean|是否显示track均分标识，默认false
bsb_auto_adjust_section_mark|boolean|是否拖动放手后自动滑向均分点，默认false
bsb_track_color|int|右track颜色，默认R.color.colorPrimary
bsb_second_track_color|int|左track颜色，默认R.color.colorAccent
bsb_thumb_color|int|thumb颜色，默认与左track颜色相同
bsb_show_text|boolean|是否显示起始值和结束值，默认false
bsb_text_size|dimension|显示起始值和结束值的文字大小，默认14sp
bsb_text_color|int|显示起始值和结束值的文字颜色，默认与右track相同
bsb_text_position|enum|显示首尾值与track的位置关系，在两端还是在底部，默认在两端
bsb_show_thumb_text|boolean|是否在thumb下面显示进度值，默认false
bsb_thumb_text_size|dimension|thumb下进度文字大小，默认14sp
bsb_thumb_text_color|int|thumb下进度文字颜色，默认与左track相同
bsb_show_progress_in_float|boolean|进度是否显示浮点数，默认false
bsb_bubble_color|int|气泡的颜色，默认与左track相同
bsb_bubble_text_size|dimension|气泡中进度文字大小，默认14sp
bsb_bubble_text_color|int|气泡中进度文字颜色，默认白色
bsb_anim_duration|int|动画执行时间， 默认: 200ms
bsb_touch_to_seek|boolean|是否点击track任意地方来快速设置进度， 默认: false  
--------
>**人生苦短，请选择科学上网。推荐一下本人正在使用的，稳定高速，便宜好用。[推介链接](https://portal.shadowsocks.com.hk/aff.php?aff=8881) **  

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
