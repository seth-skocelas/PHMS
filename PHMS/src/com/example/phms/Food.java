package com.example.phms;
public class Food 
{
	//a variable for the user will be added once I have a user
	private String name;
	private String quantity;
	private String caloriesPer;
	private String timeStamp;
	//constructor
	public Food(){}
	public Food(String _name, String _quantity, String _caloriesPer, String _timeStamp)
	{
		this.name = _name;
		this.quantity = _quantity;
		this.caloriesPer = _caloriesPer;
		this.timeStamp = _timeStamp;
	}
	public Food(String _name, String _quantity, String _caloriesPer)
	{
		this.name = _name;
		this.quantity = _quantity;
		this.caloriesPer = _caloriesPer;
	}
	//getters!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	public String getName()
	{
		return this.name;
	}
	public String getQuantity()
	{
		return this.quantity;
	}
	public String getCaloriesPer()
	{
		return caloriesPer;
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
	public void setQuantity(String _quantity)
	{
		this.quantity = _quantity;
	}
	public void setCaloriesPer(String _caloriesPer)
	{
		this.caloriesPer = _caloriesPer;
	}
	public void setTimeStamp(String _timeStamp)
	{
		this.timeStamp = _timeStamp;
	}
}