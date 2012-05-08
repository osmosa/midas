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

import android.widget.ImageView;
import android.widget.TextView;

/**
 * @author "Dias Nurul Arifin"
 * 
 */
public class ViewHolder {
	ImageView ivIcon;
	TextView tvName;

	public ViewHolder(ImageView imageView, TextView textView) {
		this.ivIcon = imageView;
		this.tvName = textView;
	}

	public ImageView getIvIcon() {
		return ivIcon;
	}

	public void setIvIcon(ImageView ivIcon) {
		this.ivIcon = ivIcon;
	}

	public TextView getTvName() {
		return tvName;
	}

	public void setTvName(TextView tvName) {
		this.tvName = tvName;
	}

}
