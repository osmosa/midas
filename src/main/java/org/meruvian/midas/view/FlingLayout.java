/*
 * Copyright 2012 Meruvian
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.meruvian.midas.view;

import java.util.List;

import org.meruvian.midas.view.adapter.SwipeLayoutAdapter;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author "Dias Nurul Arifin"
 * 
 */
public class FlingLayout extends ViewPager {

	private Context context;

	public FlingLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
	}

	public void addViewList(List<View> views) {
		setAdapter(new SwipeLayoutAdapter(context, views));
	}
}
