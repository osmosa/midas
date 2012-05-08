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

package org.meruvian.midas.view.adapter;

import java.util.ArrayList;
import java.util.List;

import org.meruvian.midas.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @author "Dias Nurul Arifin"
 * @author Dian Aditya
 * 
 */
public class ActionInfoAdapter extends BaseAdapter {
	private Context mContext;
	private List<ActionInfo> mListActionInfo;

	public ActionInfoAdapter(Context context, List<ActionInfo> list) {
		mContext = context;
		mListActionInfo = list;
	}

	public ActionInfoAdapter(Context context) {
		this.mContext = context;
		this.mListActionInfo = new ArrayList<ActionInfo>();
	}

	public void addActionInfo(ActionInfo actionInfo) {
		mListActionInfo.add(actionInfo);
	}

	public List<ActionInfo> getActionInfos() {
		return mListActionInfo;
	}

	@Override
	public int getCount() {
		return mListActionInfo.size();
	}

	@Override
	public Object getItem(int position) {
		return mListActionInfo.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ActionInfo entry = mListActionInfo.get(position);
		ViewHolder holder = null;
		if (convertView == null) {
			LayoutInflater inflater = LayoutInflater.from(mContext);
			convertView = inflater.inflate(R.layout.item_grid, null);
			holder = new ViewHolder(
					(ImageView) convertView.findViewById(R.id.ivIcon),
					(TextView) convertView.findViewById(R.id.tvName));
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.ivIcon.setImageBitmap(entry.getIcon());
		holder.tvName.setText(entry.getName());

		return convertView;
	}

}
