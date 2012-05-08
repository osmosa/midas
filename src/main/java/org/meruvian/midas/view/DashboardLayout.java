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

import org.meruvian.midas.view.adapter.ActionInfo;
import org.meruvian.midas.view.adapter.ActionInfoAdapter;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * @author "Dias Nurul Arifin"
 * @author Dian Aditya
 * 
 */
public class DashboardLayout extends GridView {

	private Context context;
	private ActionInfoAdapter actionInfoAdapter;

	/**
	 * @param context
	 * @param attrs
	 */
	public DashboardLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		this.actionInfoAdapter = new ActionInfoAdapter(context);

		setAdapter(actionInfoAdapter);
	}

	public void addActionsList(List<ActionInfo> actionInfos) {
		setAdapter(new ActionInfoAdapter(context, actionInfos));
	}

	public void addActionInfo(ActionInfo actionInfo) {
		actionInfoAdapter.addActionInfo(actionInfo);
	}

	public ActionInfo getInfo(int position) {
		try {
			return actionInfoAdapter.getActionInfos().get(position);
		} catch (IndexOutOfBoundsException e) {
			throw new IndexOutOfBoundsException(
					"Error getting action info at index: " + position
							+ ", found " + actionInfoAdapter.getCount());
		}
	}

}
