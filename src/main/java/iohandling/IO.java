package iohandling;

import java.util.Scanner;

public class IO {
	private Scanner scan;
	
	private IO(){
		scan = new Scanner(System.in);
	}
	
	private static class scanHolder{
		private static final IO INSTANCE = new IO();
	}
	
	public static String getString(String ask){
		System.out.println(ask);
		return scanHolder.INSTANCE.scan.nextLine();
	}
	public static String getString(){
		return scanHolder.INSTANCE.scan.nextLine();
	}
	
	public static int getInt(String ask){
		System.out.println(ask);
		return Integer.parseInt(scanHolder.INSTANCE.scan.nextLine());
	}
	
	public static void display(String text){
		System.out.println(text);
	}
}
