/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fil_rouge.fougicrok.DAL;

import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class Users 
{
	private int id;
	private String login;
	private String pwd;
	private String name;
	private String firstname;
	private int phone;
	private String mail;
	private String question;
	private String answer;
	private Date sDate;
	private Date cDate;
	private int trials;
	private String blocked;
	private int rank;
	private String blockedShow;
	private String rankShow;
	
//	Constructors
	public Users()
	{
	}

	public Users(String login)
	{
		this.login = login;
	}

	public Users(ResultSet x)
	{
		try
		{
			this.id = x.getInt("user_id");
			this.login = x.getString("user_login");
			this.pwd = x.getString("user_password");
			this.name = x.getString("user_name");
			this.firstname = x.getString("user_firstname");
			this.phone = x.getInt("user_phone");
			this.mail = x.getString("user_mail");
			this.question = x.getString("user_question");
			this.answer = x.getString("user_answer");
			this.sDate = x.getDate("user_sDate");
			this.cDate = x.getDate("user_cDate");
			this.trials = x.getInt("user_try");
			this.blocked = x.getString("user_blocked");
			this.rank = x.getInt("user_rank_id");
			
			if (x.getString("user_blocked") != null)
			{
				this.blockedShow = "Bloqué";
			}

			switch (x.getInt("user_rank_id"))
			{
				case 1 :
					this.rankShow = "User";
					break;
				case 2 :
					this.rankShow = "Admin";
					break;
				case 3 :
					this.rankShow = "Super Admin";
					break;
				default :
					this.rankShow = "GRADE NON DEFINI";
			}


		} 
		catch (SQLException ex)
		{
			System.out.println(ex.getMessage());
		}
	}
	
	
	
//	Getter & Setter
	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getLogin()
	{
		return login;
	}

	public void setLogin(String login)
	{
		this.login = login;
	}

	public String getPwd()
	{
		return pwd;
	}

	public void setPwd(String pwd)
	{
		this.pwd = pwd;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getFirstname()
	{
		return firstname;
	}

	public void setFirstname(String firstname)
	{
		this.firstname = firstname;
	}

	public int getPhone()
	{
		return phone;
	}

	public void setPhone(int phone)
	{
		this.phone = phone;
	}

	public String getMail()
	{
		return mail;
	}

	public void setMail(String mail)
	{
		this.mail = mail;
	}

	public String getQuestion()
	{
		return question;
	}

	public void setQuestion(String question)
	{
		this.question = question;
	}

	public String getAnswer()
	{
		return answer;
	}

	public void setAnswer(String answer)
	{
		this.answer = answer;
	}

	public Date getsDate()
	{
		return sDate;
	}

	public void setsDate(Date sDate)
	{
		this.sDate = sDate;
	}

	public Date getcDate()
	{
		return cDate;
	}

	public void setcDate(Date cDate)
	{
		this.cDate = cDate;
	}

	public int getTrials()
	{
		return trials;
	}

	public void setTrials(int trials)
	{
		this.trials = trials;
	}

	public String getBlocked()
	{
		return blocked;
	}

	public void setBlocked(String blocked)
	{
		this.blocked = blocked;
	}

	public int getRank()
	{
		return rank;
	}

	public void setRank(int rank)
	{
		this.rank = rank;
	}

	public String getBlockedShow()
	{
		return blockedShow;
	}

	public void setBlockedShow(String blockedShow)
	{
		this.blockedShow = blockedShow;
	}

	public String getRankShow()
	{
		return rankShow;
	}

	public void setRankShow(String rankShow)
	{
		this.rankShow = rankShow;
	}

	@Override
	public String toString()
	{
		return firstname+" "+name+" - \""+login+"\"";
	}
	

	
}
