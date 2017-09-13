package timerAndQuartz.timer;

import java.util.Timer;

/**
 * 
 * @ClassName: TestTimer
 * @Description: 测试定时工具
 * @author Smileyes
 * @date 2017年9月12日 下午12:50:30
 *
 */
public class TestTimer {
	public static void main(String[] args) {
		Timer timer = new Timer();
		// 两秒延迟，每秒执行一次
		timer.schedule(new MyTimerTask(), 2000, 1000);

	}
}
