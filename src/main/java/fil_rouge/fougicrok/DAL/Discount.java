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
public class Discount 
{
	int id;
	String code;
	int value;
	int uses;

	public Discount()
	{
	}

	public Discount(int id, String code, int value, int uses)
	{
		this.id = id;
		this.code = code;
		this.value = value;
		this.uses = uses;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getCode()
	{
		return code;
	}

	public void setCode(String code)
	{
		this.code = code;
	}

	public int getValue()
	{
		return value;
	}

	public void setValue(int value)
	{
		this.value = value;
	}

	public int getUses()
	{
		return uses;
	}

	public void setUses(int uses)
	{
		this.uses = uses;
	}
	
	@Override
	public String toString()
	{
		return code+" (- "+value+" %)";
	}
}
