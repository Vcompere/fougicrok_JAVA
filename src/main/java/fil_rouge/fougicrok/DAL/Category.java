package fil_rouge.fougicrok.DAL;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Category 
{
	private int id;
	private String type;
	private int idParent;
	private  StringProperty menu;
	private String parentStr;
	private Category parent;
	

	public Category(int id, String type, int idParent, String str)
	{
		this.id = id;
		
		if (id == 0);
		this.type = type;
		this.idParent = idParent;
		this.parentStr = str;
	}
	public Category(int id, String type, int idParent)
	{
		this.id = id;
		if (this.id == 0)
		{
			this.type = "Aucune";
		}
		else
		{
			this.type = type;
		}
		this.idParent = idParent;
	}

	public Category(String type, int idParent)
	{
		this.type = type;
		this.idParent = idParent;
	}
	
	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public int getIdParent()
	{
		return idParent;
	}

	public void setIdParent(int idParent)
	{
		this.idParent = idParent;
	}

	public StringProperty getMenu()
	{
		return menu;
	}

	public void setMenu(StringProperty menu)
	{
		this.menu = menu;
	}

	public String getParentStr()
	{
		return parentStr;
	}

	public void setParentStr(String parentStr)
	{
		this.parentStr = parentStr;
	}
	
	@Override
	public String toString()
	{
		return type;
	}
}
