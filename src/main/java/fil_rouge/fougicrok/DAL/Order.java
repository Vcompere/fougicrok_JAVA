package fil_rouge.fougicrok.DAL;

import java.util.Date;

public class Order 
{
	private int id;
	private Date date;
	private String userStr;
	private String promo;
	private String state;
	private double price;
	private int number;
	private int userId;
	private int promoId;
	private int fAddress;
	private int dAddress;

	public Order()
	{
	}

	public Order(int userId, int promoId, int fAddress, int dAddress, String state) {
		this.userId = userId;
		this.promoId = promoId;
		this.fAddress = fAddress;
		this.dAddress = dAddress;
		this.state = state;
	}

	public Order(int id, Date date, String userStr, String promo, String state, double price, int number, int userId, int promoId, int fAddress, int dAddress) {
		this.id = id;
		this.date = date;
		this.userStr = userStr;
		this.promo = promo;
		this.state = state;
		this.price = price;
		this.number = number;
		this.userId = userId;
		this.promoId = promoId;
		this.fAddress = fAddress;
		this.dAddress = dAddress;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getUserStr() {
		return userStr;
	}

	public void setUserStr(String userStr) {
		this.userStr = userStr;
	}

	public String getPromo() {
		return promo;
	}

	public void setPromo(String promo) {
		this.promo = promo;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public double getPrice() {
		return price;
	}

	public String getPriceStr()
	{
		return String.valueOf(price)+" €";
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getPromoId() {
		return promoId;
	}

	public void setPromoId(int promoId) {
		this.promoId = promoId;
	}

	public int getfAddress() {
		return fAddress;
	}

	public void setfAddress(int fAddress) {
		this.fAddress = fAddress;
	}

	public int getdAddress() {
		return dAddress;
	}

	public void setdAddress(int dAddress) {
		this.dAddress = dAddress;
	}
}
