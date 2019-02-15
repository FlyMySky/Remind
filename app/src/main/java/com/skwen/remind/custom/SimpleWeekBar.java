/*
 * Copyright (C) 2016 huanghaibin_dev <huanghaibin_dev@163.com>
 * WebSite https://github.com/MiracleTimes-Dev
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.skwen.remind.custom;

import android.content.Context;
import android.view.LayoutInflater;
import com.haibin.calendarview.WeekBar;
import com.skwen.remind.R;

/**
 * 星期栏，如果你要使用星期栏自定义，切记XML使用 merge，不要使用LinearLayout
 * Created by huanghaibin on 2017/11/30.
 */
public class SimpleWeekBar extends WeekBar {

    public SimpleWeekBar(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.cv_week_bar, this, true);
        this.setBackgroundColor(getResources().getColor(R.color.background_color));
    }


    @Override
    protected void onWeekStartChange(int weekStart) {
        super.onWeekStartChange(weekStart);
    }
}
