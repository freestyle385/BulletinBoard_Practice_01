package project;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Date_Exam {

	public static void main(String[] args) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		Date time = new Date();
		String time1 = sdf.format(time);
		
		System.out.println(time1);
	}

}
