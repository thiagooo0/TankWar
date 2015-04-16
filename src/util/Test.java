package util;

import java.util.ArrayList;

public class Test extends Thread
{
	public void run()
	{
//		Date date = new Date();
//		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss:SS");
//		System.out.println(format.format(date));
//		
//		try
//		{
//			sleep(1);
//		} catch (InterruptedException e)
//		{
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		Calendar time = Calendar.getInstance();
//		Date date2 = time.getTime();
//		System.out.println(format.format(date2));
//		
//		try
//		{
//			sleep(1);
//		} catch (InterruptedException e)
//		{
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		time.set(Calendar.YEAR, 1994);
//		time.set(Calendar.MONTH, 0);
//		time.set(Calendar.DAY_OF_MONTH, 3);
//		time.add(Calendar.YEAR, -1);
//		date2 = time.getTime();
//		System.out.println(format.format(date2));
//		System.out.println("-------------------------");
//		
//		
//		HashMap<Calendar,Integer> map = new HashMap<Calendar,Integer>();
//		
//		Calendar cal1 = Calendar.getInstance();
//		Calendar cal2 = Calendar.getInstance();
//		Calendar cal3 = Calendar.getInstance();
//		Calendar cal4 = Calendar.getInstance();
//		
//		cal1.set(Calendar.YEAR, 1994);
//		cal2.set(Calendar.YEAR, 1991);
//		cal3.set(Calendar.YEAR, 2004);
//		cal4.set(Calendar.YEAR, 2004);
//		cal4.set(Calendar.HOUR_OF_DAY,3);
//		
//		map.put(cal1, 1);		
//		map.put(cal2, 1);		
//		map.put(cal3, 1);
//		map.put(cal4, 2);
//
//
//		HashMap<String, Integer> map2 = new HashMap<String, Integer>();
//		
//		Iterator<Entry<Calendar, Integer>> ite = map.entrySet().iterator();
//		
//		while(ite.hasNext())
//		{
//			Entry<Calendar, Integer> entry = ite.next();
//			
//			System.out.println(format.format(entry.getKey().getTime()) + ", " + entry.getValue());
//			
//			map2.put(format.format(entry.getKey().getTime()), entry.getValue());
//			
//		}
//		
//		System.out.println("----------------");
//		
//		Iterator<Entry<String, Integer>> ite2 = map2.entrySet().iterator();
//		
//		while(ite2.hasNext())
//		{
//			Entry<String, Integer> entry2  = ite2.next();
//			System.out.println(entry2.getKey() + ", " + entry2.getValue());
//		}
		
		
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(2);
		list.add(1);
		
		if(list.contains(1))
		{
			int i = list.indexOf(1);
			System.out.println(i + ": " + list.get(i));
		}
		
	}

	public static void main(String[] args)
	{

		Test test = new Test();
		test.start();
		
	}
}
