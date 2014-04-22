package com.example.phms;
public class Exercise 
{
	private String name;
	private String caloriesBurned;
	private String timeStamp;
	//constructor
	public Exercise(){}
	public Exercise(String _name, String _caloriesBurned, String _timeStamp)
	{
		this.name = _name;
		this.caloriesBurned = _caloriesBurned;
		this.timeStamp = _timeStamp;
	}
	//getters!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	public String getName()
	{
		return this.name;
	}

	public String getCaloriesBurned()
	{
		return caloriesBurned;
	}
	public String getTimeStamp()
	{
		return this.timeStamp;
	}
	//setters!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	public void setName(String _name)
	{
		this.name = _name;
	}

	public void setCaloriesBurned(String _caloriesBurned)
	{
		this.caloriesBurned = _caloriesBurned;
	}
	public void setTimeStamp(String _timeStamp)
	{
		this.timeStamp = _timeStamp;
	}
}