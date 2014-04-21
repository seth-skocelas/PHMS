package com.example.phms;
public class Vitals 
{
	private int temp;
	private int bloodPres;
	private int pulseRate;
	private int respRate;
	private int chol;
	private int time;
	//constructor
	public Vitals(int _temp, int _bloodPres, int _pulseRate, int _respRate, int _chol, int _time)
	{
		this.temp = _temp;
		this.bloodPres = _bloodPres;
		this.pulseRate = _pulseRate;
		this.respRate = _respRate;
		this.chol = _chol;
		this.time = _time;		
	}
	//getters!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	public int getTemp()
	{
		return this.temp;
	}
	public int getbloodPres()
	{
		return this.bloodPres;
	}
	public int getPulseRate()
	{
		return this.pulseRate;
	}
	public int getRespRate()
	{
		return this.respRate;
	}
	public int getChol()
	{
		return this.chol;
	}
	public int getTime()
	{
		return this.time;
	}
	//setters!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	public void setTemp(int _temp)
	{
		this.temp = _temp;
	}
	public void setBloodPres(int _bloodPres)
	{
		this.bloodPres = _bloodPres;
	}
	public void setPulseRate(int _pulseRate)
	{
		this.pulseRate = _pulseRate;
	}
	public void setRespRate(int _respRate)
	{
		this.respRate = _respRate;
	}
	public void setChol(int _chol)
	{
		this.chol = _chol;
	}
	public void setTime(int _time)
	{
		this.time = _time;
	}
}