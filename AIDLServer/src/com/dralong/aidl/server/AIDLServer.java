package com.dralong.aidl.server;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

public class AIDLServer extends Service {
	List<String> list = new ArrayList<String>();
	String[] str = new String[] { "Jerry", "Xiong", "Long", "Yue", "Dralong",
			"赵", "钱", "孙", "李", "周", "吴", "郑", "王", "冯", "陈", "楮", "卫", "蒋",
			"沈", "韩", "杨", "朱", "秦", "尤", "许", "何", "吕", "施", "张", "孔", "曹",
			"严", "华", "金", "魏", "陶", "姜" };

	@Override
	public IBinder onBind(Intent intent) {
		return mIBinder;
	}

	private final AIDLInterface.Stub mIBinder = new AIDLInterface.Stub() {

		@Override
		public String getName(int i) throws RemoteException {
			list = Arrays.asList(str);
			return list.get(i);
		}
	};
}
