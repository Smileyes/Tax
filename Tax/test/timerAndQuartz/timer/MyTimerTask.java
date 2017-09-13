package timerAndQuartz.timer;

import java.util.Date;
import java.util.TimerTask;

/*
 * 定时输出时间
 */
public class MyTimerTask extends TimerTask {

	public void run() {
		System.out.println(new Date().toString());
	}

}
