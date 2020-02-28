/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fil_rouge.fougicrok.DAL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;	
import java.util.ArrayList;
import java.util.HashMap;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class FormValidation 
{
	Pattern multi = Pattern.compile("^[A-Za-z ÁÀÂÄÃÅÉÈÊËÍÌÎÏÑÓÒÔÖÕÚÙÛÜÝÆÇáàâäãåéèêëíìîïñóòôöõúùûüýÿæç\\\\'0-9,.\\-_\"]+$");
	Pattern ref = Pattern.compile("^[a-z]{3}[0-9]{3}$");
	Pattern price = Pattern.compile("^[0-9]{1,4}(\\.[0-9]{1,2})?");
	Pattern stock = Pattern.compile("^[0-9]{1,4}");
	Pattern letters = Pattern.compile("^[A-Za-z ÁÀÂÄÃÅÉÈÊËÍÌÎÏÑÓÒÔÖÕÚÙÛÜÝÆÇáàâäãåéèêëíìîïñóòôöõúùûüýÿæç\\'-]+$");
	
	
	public HashMap productsVLD(Products x, boolean y)
	{
		Matcher rgxLabel = multi.matcher(x.getLabel());
		Matcher rgxRef = ref.matcher(x.getRef());
		Matcher rgxPrice = price.matcher(x.getPriceStr());
		Matcher rgxStock = stock.matcher(x.getStockStr());
		Matcher rgxDesc = multi.matcher(x.getDesc());
		
		HashMap<String, String> error = new HashMap();
		
		// LABEL
		if(x.getLabel().matches(""))
		{
			error.put("label", "Nom du produit obligatoire");
		}
		else
		{
			if(!rgxLabel.matches())
			{
				error.put("label", "Saisie incorrecte");
			}
			else
			{
				if(y)
				{
					try 
					{
						String url1 = "jdbc:mysql://localhost:3306/fil_rouge?serverTimezone=UTC";
						Connection fr = DriverManager.getConnection(url1, "root", "");


						PreparedStatement stm = fr.prepareStatement("SELECT prod_name FROM products WHERE prod_name = ?");	
						stm.setString(1, x.getLabel());
						ResultSet result = stm.executeQuery();

						if(result.next())
						{
							error.put("label", "Nom de produit déjà éxistant");
						}

						result.close();
						stm.close();	
						fr.close();
					} 
					catch (Exception e) 
					{	
						System.out.println(e.getMessage());
					}
				}
			}
		}
		
		// REFERENCE
		if(x.getRef().matches(""))
		{
			error.put("ref", "Référence obligatoire");
		}
		else
		{
			if(!rgxRef.matches())
			{
				error.put("ref", "Saisie incorrecte");
			}
			else
			{
				if(y)
				{
					try 
					{
						String url1 = "jdbc:mysql://localhost:3306/fil_rouge?serverTimezone=UTC";
						Connection fr = DriverManager.getConnection(url1, "root", "");


						PreparedStatement stm = fr.prepareStatement("SELECT prod_ref FROM products WHERE prod_ref = ?");	
						stm.setString(1, x.getRef());
						ResultSet result = stm.executeQuery();

						if(result.next())
						{
							error.put("ref", "Référence déjà éxistante");
						}

						result.close();
						stm.close();	
						fr.close();
					} 
					catch (Exception e) 
					{	
						System.out.println(e.getMessage());
					}
				}
			}
		}
		
		// PRICE
		if(x.getPriceStr().matches(""))
		{
			error.put("price", "Prix obligatoire");
		}
		else
		{
			if(!rgxPrice.matches())
			{
				error.put("price", "Saisie incorrecte");
			}
		}
		
		// STOCK
		if(x.getStockStr().matches(""))
		{
			error.put("stock", "Stock obligatoire");
		}
		else
		{
			if(!rgxStock.matches())
			{
				error.put("stock", "Saisie incorrecte");
			}
		}
		
		// DESCRIPTION
		if(x.getDesc().matches(""))
		{
			error.put("desc", "Description obligatoire");
		}
		else
		{
			if(!rgxDesc.matches())
			{
				error.put("desc", "Saisie incorrecte");
			}
		}
		// IMAGE
		if(x.getImg().matches("FALSE"))
		{
			error.put("img", "Extension de la photo obligatoire");
		}
		
		// CATEGORY
		if(x.getCatType().matches("FALSE"))
		{
			error.put("cat", "Catégorie obligatoire");
		}
		
		return error;
	}
	
	public HashMap categoryVLD(Category x, boolean y)
	{
		Matcher rgx = letters.matcher(x.getType());
		HashMap<String, String> error = new HashMap();
		// LABEL
		if(x.getType().matches(""))
		{
			error.put("input", "Label de la catégorie obligatoire");
		}
		else
		{
			if(!rgx.matches())
			{
				error.put("input", "Saisie incorrecte");
			}
			else
			{
				if(y)
				{
					try 
					{
						String url1 = "jdbc:mysql://localhost:3306/fil_rouge?serverTimezone=UTC";
						Connection fr = DriverManager.getConnection(url1, "root", "");


						PreparedStatement stm = fr.prepareStatement("SELECT cat_type FROM category WHERE cat_type = ?");	
						stm.setString(1, x.getType());
						ResultSet result = stm.executeQuery();

						if(result.next())
						{
							error.put("input", "Catégorie déjà éxistante");
						}

						result.close();
						stm.close();	
						fr.close();
					} 
					catch (Exception e) 
					{	
						System.out.println(e.getMessage());
					}
				}
			}
		}
		return error;
	}

	public HashMap OrderVLD(Order x, int nbItems)
	{
		HashMap<String, String> error = new HashMap();

		// USER
		if(x.getUserId() == 0)
		{
			error.put("user", "Veuillez choisir un client");
		}

		// FACTURATION ADDRESS
		if(x.getfAddress() == 0)
		{
			error.put("fAddress", "Veuillez choisir une adresse");
		}

		// DELIVERY ADDRESS
		if(x.getdAddress() == 0)
		{
			error.put("dAddress", "Veuillez choisir une adresse");
		}

		// BASKET
		if(nbItems < 1)
		{
			error.put("basket", "La commande est vide!");
		}

		return error;
	}
}
