package com.example.phms;


public class Medicine 
{
	private String username;
	private String name;
	private int timesPer;
	private int dosage;
	private String unit;
	private String conflictions;
	
	public Medicine (String _username, String _name, int _timesPer, int _dosage, String _unit, String _conflictions)
	{
		this.username = _username;
		this.name = _name;
		this.timesPer = _timesPer;
		this.dosage = _dosage;
		this.unit = _unit;
		this.conflictions = _conflictions;				 		//create own class maybe
	}
	//getters!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	public String getUserName()
	{
		return this.username;
	}
	public String getName()
	{
		return this.name;
	}
	public int getTimesPer()
	{
		return this.timesPer;
	}
	public int getDosage()
	{
		return this.dosage;
	}
	public String getUnit()
	{
		return this.unit;
	}
	public String getConflictions()
	{
		return this.conflictions;
	}
	//setters!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!11
	public void setUserName(String _username)
	{
		this.username = _username;
	}
	public void setName(String _name)
	{
		this.name = _name;
	}
	public void setTimesPer(int _timesPer)
	{
		this.timesPer = _timesPer;
	}
	public void setDosage(int _dosage)
	{
		this.dosage = _dosage;
	}
	public void setUnit(String _unit)
	{
		this.unit = _unit;
	}
	public void setConflictions(String _conflictions)
	{
		this.conflictions = _conflictions;
	}
}