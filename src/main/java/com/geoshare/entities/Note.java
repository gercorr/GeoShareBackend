package com.geoshare.entities;

import javax.persistence.*;

import org.hibernate.annotations.Index;

import java.util.Date;

@Entity
@org.hibernate.annotations.Table(appliesTo = "Note", indexes = {
		@Index(name = "IDX_Location_Search", columnNames = { "latitude", "longitude" }) })
@Table(name = "Note")
public class Note
{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String text;

	@Index(name = "IDX_Latitude")
	private double latitude;

	@Index(name = "IDX_Longitude")
	private double longitude;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="user_id", referencedColumnName="id")
	private User user;

	@Index(name = "IDX_CreatedDate")
	private Date createdDate;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getText()
	{
		return text;
	}

	public void setText(String text)
	{
		this.text = text;
	}

	public double getLatitude()
	{
		return latitude;
	}

	public void setLatitude(double latitude)
	{
		this.latitude = latitude;
	}

	public double getLongitude()
	{
		return longitude;
	}

	public void setLongitude(double longitude)
	{
		this.longitude = longitude;
	}

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

	public Date getCreatedDate()
	{
		return createdDate;
	}

	public void setCreatedDate(Date createdDate)
	{
		this.createdDate = createdDate;
	}
}
