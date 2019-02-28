package com.lling.photopicker;

import android.content.Context;

/**
 * Description :
 * Author : lauren
 * Email  : lauren.liuling@gmail.com
 * Blog   : http://www.liuling123.com
 * Date   : 2016/3/4
 */
public class PhotoPickerApplication {

    private static Context context;

	public static void setUp(Context context) {
		PhotoPickerApplication.context = context;
	}

	public static Context getContext() {
		return context;
	}



}
