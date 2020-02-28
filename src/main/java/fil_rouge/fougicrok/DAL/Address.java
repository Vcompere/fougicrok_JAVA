/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fil_rouge.fougicrok.DAL;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class Address 
{
	int id;
	String country;
	int zip;
	String street;
	String city;
	int user_id;

	public Address()
	{
	}

	public Address(int id, String country, int zip, String street, String city, int user_id)
	{
		this.id = id;
		this.country = country;
		this.zip = zip;
		this.street = street;
		this.city = city;
		this.user_id = user_id;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getCountry()
	{
		return country;
	}

	public void setCountry(String country)
	{
		this.country = country;
	}

	public int getZip()
	{
		return zip;
	}

	public void setZip(int zip)
	{
		this.zip = zip;
	}

	public String getStreet()
	{
		return street;
	}

	public void setStreet(String street)
	{
		this.street = street;
	}

	public String getCity()
	{
		return city;
	}

	public void setCity(String city)
	{
		this.city = city;
	}

	public int getUser_id()
	{
		return user_id;
	}

	public void setUser_id(int user_id)
	{
		this.user_id = user_id;
	}
	
	@Override
	public String toString()
	{
		return street+" - "+zip+" "+city+" - "+country;
	}

	public String toSlip()
	{
		return street+"\n"+zip+" "+city+"\n"+country;
	}
}
