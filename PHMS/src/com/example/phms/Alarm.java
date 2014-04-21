package com.example.phms;
public class Alarm 
{
	private String medName;
	private int time;
	//constructor
	public Alarm(String _medName, int _time)
	{
		this.medName = _medName;
		this.time = _time;
	}
	//getters!!!!!!!!!!!!!!
	public String getMedName()
	{
		return this.medName;
	}
	public int getTime()
	{
		return this.time;
	}
	//setters!!!!!!!!!!!!!!!!!!!!!!!!!
	public void setMedName(String _medName)
	{
		this.medName = _medName;
	}
	public void setTime(int _time)
	{
		this.time = _time;
	}
}