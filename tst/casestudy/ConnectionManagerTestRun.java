package casestudy;

public class ConnectionManagerTestRun {

	public static void main(String[] args) throws InterruptedException {
		RunMethod testRun = new RunMethod();
		testRun.setLoop(true);

		testRun.start();

		Thread.sleep(100000);

		System.out.println("止めるよ");

		testRun.setLoop(false);

		System.out.println("終了");


	}

}
