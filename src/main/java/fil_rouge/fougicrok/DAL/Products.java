package fil_rouge.fougicrok.DAL;

import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Products 
{
	private int id;
	private String label;
	private String ref;
	private String desc;
	private float price;
	private String priceStr;
	private int stock;
	private String stockStr;
	private String img;
	private Date adding;
	private Date update;
	private int cat;
	private String catType;
	private int catParent;
	private String imgFull;
	private Category catObj;

	public Products()
	{

	}

	public Products(ResultSet x)
	{
		try
		{
			this.id = x.getInt("prod_id");
			this.label = x.getString("prod_name");
			this.ref = x.getString("prod_ref");
			this.desc = x.getString("prod_description");
			this.price = x.getFloat("prod_price");
			this.img = x.getString("prod_img");
			this.stock = x.getInt("prod_stock");
			this.adding = x.getDate("prod_adding");
			this.update = x.getDate("prod_update");
			this.cat = x.getInt("cat_id");
			this.imgFull = ref+"."+img;
			this.catType = x.getString("cat_type");
			this.catParent = x.getInt("cat_parent");
			this.catObj = new Category(x.getInt("cat_id"), x.getString("cat_type"), x.getInt("cat_parent"));
			
		} 
		catch (SQLException ex)
		{
			System.out.println(ex.getMessage());
		}
	}

	public Products(String label, String ref, String desc, float price, int stock, String img, int cat)
	{
		this.label = label;
		this.ref = ref;
		this.desc = desc;
		this.price = price;
		this.stock = stock;
		this.img = img;
		this.cat = cat;
	}

	public Products(int id, String label, String ref, String desc, float price, int stock, String img, Date adding, Date update, int cat, String catType, int catParent, String imgFull, Category catObj) {
		this.id = id;
		this.label = label;
		this.ref = ref;
		this.desc = desc;
		this.price = price;
		this.stock = stock;
		this.img = img;
		this.adding = adding;
		this.update = update;
		this.cat = cat;
		this.catType = catType;
		this.catParent = catParent;
		this.imgFull = imgFull;
		this.catObj = catObj;
	}

	public Products(String label, String ref, String desc, String priceStr, String stockStr, String img, String catType)
	{
		this.label = label;
		this.ref = ref;
		this.desc = desc;
		this.priceStr = priceStr;
		this.stockStr = stockStr;
		this.img = img;
		this.catType = catType;
	}

	public Category getCatObj()
	{
		return catObj;
	}

	public void setCatObj(Category catObj)
	{
		this.catObj = catObj;
	}
	
	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getLabel()
	{
		return label;
	}

	public void setLabel(String label)
	{
		this.label = label;
	}

	public String getRef()
	{
		return ref;
	}

	public void setRef(String ref)
	{
		this.ref = ref;
	}

	public String getDesc()
	{
		return desc;
	}

	public void setDesc(String desc)
	{
		this.desc = desc;
	}

	public float getPrice()
	{
		return price;
	}

	public void setPrice(float price)
	{
		this.price = price;
	}

	public int getStock()
	{
		return stock;
	}

	public void setStock(int stock)
	{
		this.stock = stock;
	}

	public Date getAdding()
	{
		return adding;
	}

	public void setAdding(Date adding)
	{
		this.adding = adding;
	}

	public Date getUpdate()
	{
		return update;
	}

	public void setUpdate(Date update)
	{
		this.update = update;
	}

	public int getCat()
	{
		return cat;
	}

	public void setCat(int cat)
	{
		this.cat = cat;
	}

	public String getImg()
	{
		return img;
	}

	public void setImg(String img)
	{
		this.img = img;
	}

	public String getImgFull()
	{
		return imgFull;
	}

	public void setImgFull(String imgFull)
	{
		this.imgFull = imgFull;
	}

	public String getCatType()
	{
		return catType;
	}

	public void setCatType(String catType)
	{
		this.catType = catType;
	}

	public int getCatParent()
	{
		return catParent;
	}

	public void setCatParent(int catParent)
	{
		this.catParent = catParent;
	}

	public String getPriceStr()
	{
		return priceStr;
	}

	public void setPriceStr(String priceStr)
	{
		this.priceStr = priceStr;
	}

	public String getStockStr()
	{
		return stockStr;
	}

	public void setStockStr(String stockStr)
	{
		this.stockStr = stockStr;
	}
	
	@Override
	public String toString()
	{
		return ref+" • "+label+" • "+price+"€ • ("+stock+")";
	}
}
