
package com.edu.shu.wy;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

import java.util.HashMap;

public class MyMusicUtils {
	int Music[] = {R.raw.white1,R.raw.white2,R.raw.white3,R.raw.white4,R.raw.white5,R.raw.white6,R.raw.white7,
			R.raw.white8,R.raw.white9,R.raw.white10,R.raw.white11,R.raw.white12,R.raw.white13,R.raw.white14,R.raw.white15,
	};
//	R.raw.black1,R.raw.black2,R.raw.black3,R.raw.black4,R.raw.black5,R.raw.black6,R.raw.black7,R.raw.black8,R.raw.black9,
	SoundPool soundPool;
	HashMap<Integer, Integer> soundPoolMap;

	/**
	 * 
	 * @param context
	 *            ����soundpool.load
	 * @param no
	 *            ���������ı��
	 */
	public MyMusicUtils(Context context) {
		soundPool = new SoundPool(2, AudioManager.STREAM_MUSIC, 100);
		soundPoolMap = new HashMap<Integer, Integer>();
		for (int i = 0; i < Music.length; i++) {
			soundPoolMap.put(i, soundPool.load(context, Music[i], 1));
		}
	}

	public int soundPlay(int no) {
		return soundPool.play(soundPoolMap.get(no), 100, 100, 1, 0, 1.0f);
	}

	public int soundOver() {
		return soundPool.play(soundPoolMap.get(1), 100, 100, 1, 0, 1.0f);
	}

	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		soundPool.release();
		super.finalize();
	}
}
