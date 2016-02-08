package iohandling;

import java.util.Scanner;

public class IO{
	
	private static IO instance = null;
	private static Scanner scan;
	
	private IO(){
		scan = new Scanner(System.in);
	}
	public static IO getInstance(){
		if(instance == null){
			instance = new IO();
			return instance;
		}
		return instance;
	}
	
	public static String getString(String ask){
		System.out.println(ask);
		return scan.nextLine();
	}
	public static String getString(){
		return scan.nextLine();
	}
	public static int getInt(String ask){
		System.out.println(ask);
		return Integer.parseInt(scan.nextLine());
	}
	public static int getInt(){
		return Integer.parseInt(scan.nextLine());
	}
	public static void display(String text){
		System.out.println(text);
	}
	public static void display(int text){
		System.out.println(text);
	}
}
