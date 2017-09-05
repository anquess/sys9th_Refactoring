package debug;

public class DebugPrint {
	public static final boolean WRITE = false;

	public static void debugPrint(String str){
		if(WRITE){
			System.out.println(str);
		}
	}
	public static void debugPrint(double dbl){
		if(WRITE){
			System.out.println(dbl);
		}
	}
	public static void debugPrint(int integer){
		if(WRITE){
			System.out.println(integer);
		}
	}
}
