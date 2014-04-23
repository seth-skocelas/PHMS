package com.example.phms;

/**
Useful for creating Time objects to hold calendar time
author: Jesus Parra
*/



import java.util.Calendar;
import java.util.GregorianCalendar;

public class Time implements Time_Constants
{
	private final int hour; 
	private final int min; 
	private final int sec;
	private final int hundth;

/**
	 * Constructor: default; returns today's time
	   Constructor number 1 in requirements
	 */
    public Time()
	{
        GregorianCalendar c = new GregorianCalendar();
        hour = c.get(Calendar.HOUR_OF_DAY);
        min = c.get(Calendar.MINUTE);
        sec = c.get(Calendar.SECOND);
		hundth = c.get(Calendar.MILLISECOND)/MAX_HTH;
	}

	/**
	 * Constructor: Does bounds-checking to ensure object represents a valid time
	 * Contructor number 2 in the requirements
	 * @param h    represents the hour between 1 and 23
	 * @param m    represents the min between 1 and 59
	 * @param s    represents the sec between 1 and 59
	 * @param hth    represents the sec between 1 and 99
	 * @exception RuntimeException
	 * if the time is invalid
	 */
	 
	public Time(int h, int m, int s, int hth)
	{
		if (!isValid(h, m, s, hth))
			throw new RuntimeException("1: Invalid input/output");
		hour = h;
		min = m;
		sec = s;
		hundth = hth;
	}
	 
	/**
	 * Is the given time valid?
	 * 
	 * @param hour, min, sec, hth
	 * @return false if hour exceeds 23 or is less than 0
	 * @return false if minute exceeds 60 or is less than 0
	 * @return false if second exceeds 60 or is less than 0
	 * @return false if the hundreth of a second exceeds 99 or is less than 0
	 */
	private static boolean isValid(int h, int m, int s, int hth)
	{
		if (h < FIRST_HOUR || h > LAST_HOUR )
			return false; //will not handle negative hours!
        if (m < FIRST_MIN || m > LAST_MIN)
			return false;
		if (s < FIRST_SEC || s > LAST_SEC)
			return false;
		if (hth < FIRST_HTH || hth > LAST_HTH)
			return false;
		return true;
	}
	
	// comparison function between two times
	/**
	 * compares two Time objects
	 * 
	 * @param b    Time object
	 * @return     0 if this Time is the same as Time b <br>
	 *             negative integer if this Time is earlier than Time b <br>
	 *             positive integer if this Time is after Time b
	 */
	public int compareTo(Time b)
	{
		if (hour != b.hour)
			return hour - b.hour;
		if (min != b.min)
			return min - b.min;
		if (sec != b.sec)
			return sec - b.sec;
		return hundth - b.hundth;
	}
	
	// is this Time after b?
	/**
	 * compares two Time objects
	 * 
	 * @param b Time object
	 * @return true if this Time is after Time b
	 */
	public boolean isAfter(Time b)
	{
		return compareTo(b) > ZEROI;
	}

	// is this Time a before b?
	/**
	 * compares two time objects
	 * 
	 * @param b Time object
	 * @return true if this Time is before Time b
	 */
	public boolean isBefore(Time b)
	{
		return compareTo(b) < ZEROI;
	}
	
	
	// Increases the number of hours by 1
	/**
	 * advances the time by 1 hour
	 * 
	 * @param ch    represents the hours to change
	 * @return     new Time object (as the original obj is immutable)
	 */
	 
	public Time addHour()
	{
		int ch = hour;
		
		if( ch == LAST_HOUR)
			ch = FIRST_HOUR;
		else
			ch+=ONEI;
		
		return (new Time(ch, min, sec, hundth));
	}
	
	// Increases the number of minutes by 1
	/**
	 * advances the time by 1 minute
	 * 
	 * @param cm    represents the minutes to change
	 * @param ch	represents the hours that will be altered if the minute is 59
	 * @return     new Time object (as the original obj is immutable)
	 */
	 
	 public Time addMinute()
	 {
		int cm = min, ch=hour;
		
		if( cm == LAST_MIN)
		{
			cm = FIRST_MIN;
			if( ch == LAST_HOUR)
				ch = FIRST_HOUR;
			else
				ch+=ONEI;
			return (new Time(ch, cm, sec, hundth));
		}
		else
		{
			cm+=ONEI;
			return (new Time(hour, cm, sec, hundth));
		}
	 }
	 
	// Increases the number of seconds by 1
	/**
	 * advances the time by 1 second
	 * 
	 * @param cs    represents the seconds to change
	 * @param cm    represents the minutes that will be altered if the second is 59
	 * @param ch	represents the hours that will be altered if the minute is 59
	 * @return     new Time object (as the original obj is immutable)
	 */
	 
	public Time addSecond()
	{
		int cs=sec, cm = min, ch=hour;
		
		if( cs == LAST_SEC) 			//if last second equals to 59
		{
			cs = FIRST_SEC; 			//set it equal to 0
			if( cm == LAST_MIN)			// if the min is at 59 at this time
			{
				cm = FIRST_MIN;			//set it equal to 0
				if( ch == LAST_HOUR)	//if the hour equals 23
					ch = FIRST_HOUR;	//set it equal to 0
				else
					ch+=ONEI;
				return (new Time(ch, cm, cs, hundth));
			}
			else
			{
				cm+=ONEI;
				return (new Time(hour, cm, cs, hundth));
			}
		}
		else
		{
			cs+=ONEI;
			return (new Time(hour, min, cs, hundth));
		}
	}
	
	// Increases the number of hundreth of a second by 1
	/**
	 * advances the time by 1 hundreth of a second
	 * @param chth	represents the hundreth of a second that will be changed
	 * @param cs    represents the seconds that will be altered if the hundreth of a second is 99
	 * @param cm    represents the minutes that will be altered if the second is 59
	 * @param ch	represents the hours that will be altered if the minute is 59
	 * @return      new Time object (as the original obj is immutable)
	*/

	public Time addHundredth()
	{
		int chth=hundth, cs=sec, cm=min, ch=hour;
		
		if(chth == LAST_HTH)				//if the hundreth of a second is 99
		{
			chth = FIRST_HTH;				//set it equal to 0
			if( cs == LAST_SEC) 			//if last second equals to 59
			{
				cs = FIRST_SEC; 			//set it equal to 0
				if( cm == LAST_MIN)			// if the min is at 59 at this time
				{
					cm = FIRST_MIN;			//set it equal to 0
					if( ch == LAST_HOUR)	//if the hour equals 23
						ch = FIRST_HOUR;	//set it equal to 0
					else
						ch+=ONEI;
					return (new Time(ch, cm, cs, chth));
				}
				else
				{
					cm+=ONEI;
					return (new Time(hour, cm, cs, chth));
				}
			}
			else
			{
				cs+=ONEI;
				return (new Time(hour, min, cs, chth));
			}
		}
		else
			{
				chth+=ONEI;
				return (new Time(hour, min, sec, chth));
			}
	}
			
	// advance time by number of hours given as a parameter
	/**
	 * advances the time by h hours
	 * 
	 * @param h     represents the hours to advance
	 * @param t		represents the total hours we got when added hour plus h
	 * @return      new Time object (as the original obj is immutable)
	 */
	public Time addHour(int h)
	{
		int nH, t;
		t = hour + h;
		nH = (t % MAX_HOUR);
		return (new Time(nH, min, sec, hundth));		
	}
	
	// advance time by number of minutes given as a parameter
	/**
	 * advances the time by m minutes
	 * 
	 * @param m     represents the minutes to advance
	 * @param t		represents the total minutes we got when added min plus m
	 * @param nH	represents the new number of hours
	 * @param nM	represents the new number of minutes
	 * @return      new Time object (as the original obj is immutable)
	 */
	public Time addMinute(int m)
	{
		int nM;
		int t=min + m;		//the total nuber of minutes
		int nH = (int)t/MAX_MIN;	//New hours = the times the total was divisible by 60 w/o rounding
		nH+=hour;			//New hours now increase by the hours that were already in hour
		if (nH>LAST_HOUR)			//if hour is a illegal time then it is changed with the modulus
			nH%=MAX_HOUR;
		nM=(int)t%MAX_MIN;		//minutes simply = to its remainder of from the total/60
		return (new Time(nH, nM, sec, hundth));
	}
			
	// advance time by number of seconds given as a parameter
	/**
	 * advances the time by s seconds
	 * 
	 * @param s     represents the seconds to advance
	 * @param t		represents the total seconds we got when added sec plus s
	 * @param nH	represents the new number of hours
	 * @param nM	represents the new number of minutes
	 * @param nS	represents the new number of seconds
	 * @return      new Time object (as the original obj is immutable)
	 * fixed a logical error on the 9/10/2012
	 */
	public Time addSecond(int s)
	{	
		int nH;
		int t = sec + s;		//get the total # of seconds
		int nS=(int)t%MAX_SEC;		//set new seconds from the modulus
		int nM=(int)t/MAX_SEC;		//New minutes = the times the total was divisible by 60 w/o rounding
		nM+=min;				//New minutes = the divisible ones + the ones already in min
		if (nM>LAST_MIN)		//if new minute is bigger than 59
		{
			nH=(int)nM/MAX_MIN;
			nM=(int)nM%MAX_MIN;
			nH+=hour;
					//New hours = the times the total was divisible by 60 w/o rounding
			
			if (nH>LAST_HOUR)	//if hour is a illegal time then it is changed with the modulus
				nH%=MAX_HOUR;
			return (new Time(nH, nM, nS, hundth));
		}
		else
			return (new Time(hour, nM, nS, hundth));
	}
	
	// advance time by number of hundredths of a second given as a parameter
	/**
	 * advances the time by hth hundredths of a second
	 * 
	 * @param hth   represents the hundredths of a second to advance
	 * @param t		represents the total hundredths of a second we got when added sec plus s
	 * @param nH	represents the new number of hours
	 * @param nM	represents the new number of minutes
	 * @param nS	represents the new number of seconds
	 * @param nHth	represents the new number of hundreths of a second
	 * @return      new Time object (as the original obj is immutable)
	 * fixed a logical error on the 9/10/2012
	 */
	
	public Time addHundredth(int hth)
	{
		int nH;
		int nM;
		int t = hundth + hth;
		int nHth=(int)t%MAX_HTH;
		int nS = (int)t/MAX_HTH;
		nS+=sec;
		if (nS>LAST_SEC)
		{
			nM=(int)nS/MAX_SEC;
			nS=(int)nS%MAX_SEC;
			nM+=min;
			if (nM>LAST_MIN)		//if new minute is bigger than 59
			{
				nH=(int)nM/MAX_MIN;					//New hours = the times the total was divisible by 60 w/o rounding
				nM=(int)nM%MAX_MIN;
				nH+=hour;
				if (nH>LAST_HOUR)	//if hour is a illegal time then it is changed with the modulus
					nH%=MAX_HOUR;
				return (new Time(nH, nM, nS, nHth));
			}
			else
				return (new Time(hour, nM, nS, nHth));
		}
		else
			return (new Time(hour, min, nS, nHth));
	}

	//subtract time assuming T1 is always bigger than T2
	//An algorithm that is like regular subtraction in numbers going right to left
	/**
	 * @param t2  		represents the second time
	 * @param hundredth represents the name of the final hundredth of a second
	 * @param second	represents the name of the final second value
	 * @param minute	represents the name of the final minute value
	 * @param hour		represents the name of the final hour value
	 * @param eTime		represents t1
	 * @param sTime		represents t2
	 * @param tempHth	represents the value of hundredths of a second that will get modified
	 * @param tempSec	represents the value of seconds that will get modified
	 * @param tempMin	represents the value of minutes that will get modified
	 * @param tempHour	represents the value of hours that will get modified
	 * @return    the difference between the two times if t2>t1
	 */
	public Time subtractTime(Time t2)
	{
		int hundredth, second, minute, hour;
		int tempHth, tempSec, tempMin, tempHour;
		Time eTime=this;					//type Time
		Time sTime=t2;
		tempMin=eTime.min;
		tempHour=eTime.hour;
		
		if (eTime.isBefore(sTime))
		{
			System.out.println("T2 is greater than T1; T2 is returned");
			return sTime;					//Not sure if you can do this!
		}
		
		if (sTime.hundth <= eTime.hundth)	//hundredths
		{
			tempSec = eTime.sec;
			hundredth = eTime.hundth - sTime.hundth;
		}
		else
		{
			tempHth = eTime.hundth + MAX_HTH;			
			tempSec = eTime.sec - ONEI;
			if (tempSec<ZEROI)
			{
				tempMin = eTime.min -ONEI;
				tempSec+=MAX_SEC;
				if (tempMin<ZEROI)
				{
					tempHour = eTime.hour-ONEI;
					tempMin += MAX_MIN;
				}
			}
			hundredth = tempHth - sTime.hundth;
		}
		
		if (sTime.sec <= tempSec)	//seconds
			second = tempSec - sTime.sec;
		else
		{
			tempSec +=MAX_SEC;
			tempMin -= ONEI;			//remember to declare at the beginning
			if (tempMin<ZEROI)
			{
				tempHour -= ONEI;		//and this
				tempMin += MAX_MIN;
			}
			second = tempSec - sTime.sec;
		}
		if (sTime.min <= tempMin)	//minutes
			minute = tempMin - sTime.min;
		else
		{
			tempMin +=MAX_MIN;
			tempHour -= ONEI;
			minute = tempMin - sTime.min;
		}
		hour = tempHour - sTime.hour;	//hours
		return (new Time(hour, minute, second, hundredth));
	}
	
	// add the two times together in time form
	//hope this works!
	/**
	 * add the two times together in time form
	 * @param newTime	represents the new time that will be modified by the aready existing functions!
	 * @param t2		represents the second date that will be added
	 * @return			new Time object (as the originbal obj is immutable)
	 */

	
	public Time addTime(Time t2)
	{
		Time newTime = 
		t2.addHour(this.hour).addMinute(this.min).addSecond(this.sec).addHundredth(this.hundth);
		return(newTime);
	}
	
	
	// return a string representation of this times
	/**
	 * replaces the default toString of Object class
	 */
	public String toString()
	{
		return hour+""+ min +""+ sec+"" +hundth+"";
	}
	
	
	/**
	 * Code for testing the Time class
	 */

	
	public static void main(String[] args)
	{

		System.out.println("Test Default Constructor: ");

		Time current = new Time();
		System.out.println("Current Time is " + current);
		System.out.println("Current hour is " + current.hour);
		System.out.println("Current minute is " + current.min);
		System.out.println("Current second is " + current.sec);
		System.out.println("Current hundredth is " + current.hundth);

		System.out.println("\nTest Constructor: ");

		Time t1 = new Time(0, 20, 31, 12);
		System.out.println("Input t1 Time is " + t1);

		Time t2 = new Time(13, 0, 1, 1);
		System.out.println("Input t2 Time is " + t2);

		Time t3 = new Time(23, 59, 59, 99);
		System.out.println("Input t3 Time is " + t3);

		Time t4 = new Time(0, 0, 0, 0);
		System.out.println("Input t4 Time is " + t4);

		// testing addHour, addMinute, addSecond, and addHundredth
		System.out.println("\nTESTING add hour method");
		
		current = new Time(12, 59, 30, 99);
        Time newTime = current.addHour();
		System.out.println(current + "+ 1 hour is:" + newTime);
		newTime = current.addHour(14);
		System.out.println(current + " + 14 hours is:" + newTime);
		
		System.out.println("\nTESTING add minute method");
		
		current = new Time(12, 59, 30, 99);
        newTime = current.addMinute();
		System.out.println(current + "+ 1 Minute is:" + newTime);
		newTime = current.addMinute(900);
		System.out.println(current + "+ 900 minutes is:" + newTime);
		
		System.out.println("\nTESTING add second");
		current = new Time(12, 59, 59, 99);
        newTime = current.addSecond();
		System.out.println(current + "+ 1 second is:" + newTime);
		newTime = current.addSecond(60);
		System.out.println(current + "+ 60 seconds is:" + newTime);
		newTime = current.addSecond(6000);
		System.out.println(current + "+ 6000 seconds is:" + newTime);
        
        System.out.println("\nTESTING add hundredth");
		current = new Time(12, 59, 59, 99);
        newTime = current.addHundredth();
		System.out.println(current + "+ 1 hundredth is:" + newTime);
		newTime = current.addHundredth(10000);
		System.out.println(current + "+ 10000 hundredths is:" + newTime);

		// testing isAfter
		System.out.println("\nTESTING isAfter");
		t2 = new Time(23, 59, 59, 99);

		boolean isAfter = t2.isAfter(new Time(23, 59, 59, 99));
		System.out.println(t2 + " is after " + new Time(23, 59, 59, 99) + ": " + isAfter);

		t1 = new Time(23, 59, 59, 99);
		System.out.println(t1.addHour() + " is after " + t1 + ": " + t1.addHour().isAfter(t1));

        //you can nest methods to any depth you want
        t1 = new Time(23, 59, 59, 99);
		System.out.println(t1 + " is after " + t1.addHour().addHour().subtractTime(new Time(1, 0, 0, 0)) + ": " 
                            + t1.addHour().addHour().subtractTime(new Time(1, 0, 0, 0)).isAfter(t1));
	
    	t1 = new Time(1, 20, 31, 12);
		t2 = new Time(0, 0, 0, 0);
		isAfter = t1.isAfter(t2);
		System.out.println(t1 + " is after " + t2 + ": " + isAfter);

        t1 = new Time(0, 0, 0, 12);
		t2 = new Time(0, 0, 0, 11);
		isAfter = t2.isAfter(t1);
		System.out.println(t2 + " is after " + t1 + ": " + isAfter);

		// test isBefore
		System.out.println("\nTESTING isBefore");
		t1 = new Time(1, 20, 31, 12);
		t2 = new Time(0, 18, 11, 13);

		boolean isBefore = t2.isBefore(t1);
		System.out.println(t2 + " is before " + t1 + ": " + isBefore);

		t1 = new Time(1, 0, 0, 0);
		t2 = new Time(0, 59, 59, 59);
		System.out.println(t2 + " is before " + t1 + ": " + t2.isBefore(t1));

		t1 = new Time(0, 0, 0, 0);
		t2 = new Time(1, 59, 59, 59);
		isBefore = t2.isBefore(t1);
		System.out.println(t2 + " is before " + t1 + ": " + isBefore);

        t1 = new Time(0, 0, 0, 1);
		t2 = new Time(0, 0, 0, 2);
		System.out.println(t2 + " is before " + t1 + ": " + t2.isBefore(t1));

		// test subtractTime
		System.out.println("\nTESTING subtractTime");
		t1 = new Time(1, 58, 31, 12);
		t2 = new Time(0, 58, 30, 11);
		Time interval = t1.subtractTime(t2);
		System.out.println(t1 + " - " + t2 + " is " + interval);

		t1 = new Time(1, 20, 31, 12);
		t2 = new Time(0, 0, 0, 0);
		interval = t1.subtractTime(t2);
		System.out.println(t1 + " - " + t2 + " is " + interval);

		t1 = new Time(23, 59, 59, 99);
		t2 = new Time(1, 59, 59, 99);
		interval = t1.subtractTime(t2);
		System.out.println(t1 + " - " + t2 + " is " + interval);

		t1 = new Time(1, 0, 0, 0);
		t2 = new Time(0, 59, 59, 59);
		interval = t1.subtractTime(t2);
		System.out.println(t1 + " - " + t2 + " is " + interval);

		t1 = new Time(0, 0, 0, 0);
		t2 = new Time(0, 0, 0, 0);
		interval = t1.subtractTime(t2);
		System.out.println(t1 + " - " + t2 + " is " + interval);

        t1 = new Time(0, 0, 0, 5);
		t2 = new Time(0, 0, 0, 4);
		interval = t1.subtractTime(t2);
		System.out.println(t1 + " - " + t2 + " is " + interval);

        t1 = new Time(0, 0, 0, 4);
		t2 = new Time(0, 0, 0, 5);
		interval = t1.subtractTime(t2);
		System.out.println(t1 + " - " + t2 + " is " + interval);

		System.out.println("\nTESTING addTime");
		t1 = new Time(1, 20, 31, 99);
		t2 = new Time(1, 1, 1, 1);
		Time totalTime = t1.addTime(t2);
		System.out.println(t1 + " + " + t2 + " is " + totalTime);

        t1 = new Time(23, 59, 59, 99);
		t2 = new Time(0, 0, 0, 1);
		totalTime = t1.addTime(t2);
		System.out.println(t1 + " + " + t2 + " is " + totalTime);

		t1 = new Time(1, 20, 31, 12);
		t2 = new Time(0, 0, 0, 0);

		totalTime = t1.addTime(t2);
		System.out.println(t1 + " add " + t2 + " is " + totalTime);

		t1 = new Time(1, 0, 0, 1);
		t2 = new Time(23, 59, 59, 99);
		totalTime = t1.addTime(t2);
		System.out.println(t1 + " add " + t2 + " is " + totalTime);

		t1 = new Time(23, 59, 59, 99);
		t2 = new Time(0, 0, 1, 0);
		totalTime = t1.addTime(t2);
		System.out.println(t1 + " add " + t2 + " is " + totalTime);

		t1 = new Time(1, 59, 59, 59);
		t2 = new Time(0, 1, 0, 0);

		totalTime = t1.addTime(t2);
		System.out.println(t1 + " add " + t2 + " is " + totalTime);

		t1 = new Time(0, 0, 0, 99);
		t2 = new Time(0, 0, 0, 1);

		totalTime = t1.addTime(t2);
		System.out.println(t1 + " add " + t2 + " is " + totalTime);


		// test compareTo

		System.out.println("\nTESTING compareTo ");
		t1 = new Time(0, 0, 0, 99);
		t2 = new Time(0, 0, 0, 99);

		System.out.println(t1 + " compare to " + t2 + " is " + t1.compareTo(t2));

		t1 = new Time(23, 5, 8, 99);
		t2 = new Time(23, 5, 8, 98);

		System.out.println(t1 + " compare to " + t2 + " is " + t1.compareTo(t2));

		t1 = new Time(1, 4, 3, 34);
		t2 = new Time(8, 23, 13, 31);

		System.out.println(t1 + " compare to " + t2 + " is " + t1.compareTo(t2));

        t1 = new Time(23, 0, 0, 1);
		int check = t1.compareTo(t1.addTime(new Time(1, 0, 0, 99)));

		System.out.println(t1 + " compare to " + t1.addTime(new Time(1, 0, 0, 99)) + " is " + check);
		
        
        //should generate exceptions
        //t1 = new Time(25, 0, 0, 0);
        //t2 = new Time(23, -2, 1, 0);
        //t2 = new Time(1, 1, 1, 100);
        
	}


}
	

