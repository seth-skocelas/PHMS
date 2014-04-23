package com.example.phms;

import java.io.Serializable;


public class Medicine implements Serializable
{
	private String username;
	private String name;
	private int timesPer;
	private int dosage;
	private String unit;
	private String conflictions;
	private String id;
	private int count;
	
	public Medicine (String _username, String _name, int _timesPer, int _dosage, String _unit, String _conflictions, String _id,
					 int _count)
	{
		this.username = _username;
		this.name = _name;
		this.timesPer = _timesPer;
		this.dosage = _dosage;
		this.unit = _unit;
		this.conflictions = _conflictions;
		this.id = _id;
		this.count = _count; //create own class maybe
	}
	public Medicine() {
		// TODO Auto-generated constructor stub
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
	public String getID()
	{
		return this.id;
	}
	public int getCount()
	{
		return this.count;
	}
	//setters!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!11
	public void setID(String _id)
	{
		this.id = _id;
	}
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
	public void setCount(int _count)
	{
		this.count = _count;
	}
}