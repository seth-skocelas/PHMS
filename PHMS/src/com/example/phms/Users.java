package com.example.phms;
public class Users
{
	private String fName;
	private String lName;
	private String userName;
	private String password;
	private String email;
	private String docName;
	private int age;
	private int heightFeet;
	private int heightInches;
	private int weight;
	private String gender;
	private String answer;
	public Users(String _fName)
	{
		this.fName = _fName;		
	}
	public Users(String _fName, String _lName, String _userName, String _password, String _email, String _docName, int _age, int _heightFeet, int _heightInches, int _weight, String _gender, String _answer)
	{
		this.fName = _fName;
		this.lName = _lName;
		this.userName = _userName;
		this.password = _password;
		this.email = _email;
		this.docName = _docName;
		this.age = _age;
		this.heightFeet = _heightFeet;
		this.heightInches = _heightInches;
		this.weight = _weight;
		this.gender = _gender;
		this.answer = _answer;
	}
	public String getFName()
	{
		return this.fName;
	}
	public String getLName()
	{
		return this.lName;
	}
	public String getUserName()
	{
		return this.userName;
	}
	public String getPassword()
	{
		return this.password;
	}
	public String getEmail()
	{
		return this.email;
	}
	public String getDocName()
	{
		return this.docName;
	}
	public int getAge()
	{
		return this.age;
	}
	public int getHeightFeet()
	{
		return this.heightFeet;
	}
	public int getHeightInches()
	{
		return this.heightInches;
	}
	public int getWeight()
	{
		return weight;
	}
	public String getGender()
	{
		return this.gender;
	}
	public String getAnswer()
	{
		return this.answer;
	}
	// setters!!!!!!!!!!!!!!!!!!!!!!!!!!
	public void setFName(String _fName)
	{
		this.fName = _fName;
	}
	public void setLName(String _lName)
	{
		this.lName = _lName;
	}
	public void setUserName(String _userName)
	{
		this.userName = _userName;
	}
	public void setPassword(String _password)
	{
		this.password = _password;
	}
	public void setEmail(String _email)
	{
		this.email = _email;
	}
	public void setDocName(String _docName)
	{
		this.docName = _docName;
	}
	public void setAge(int _age)
	{
		this.age = _age;
	}
	public void setHeightFeet(int _heightFeet)
	{
		this.heightFeet = _heightFeet;
	}
	public void setHeightInches(int _heightInches)
	{
		this.heightInches = _heightInches;
	}
	public void setWeight(int _weight)
	{
		this.weight = _weight;
	}
	public void setGender(String _gender)
	{
		this.gender = _gender;
	}
	public void setAnswer(String _answer)
	{
		this.answer = _answer;
	}	
}