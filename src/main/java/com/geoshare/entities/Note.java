package com.geoshare.entities;

import javax.persistence.*;

@Entity
@Table(name="Note")
public class Note {
	
	   @Id
	   @GeneratedValue(strategy = GenerationType.AUTO) 		   
	   private int id;
	   
	   private String text; 
	   
	   private float latitude;
	   
	   private float longitude;
	
	   public int getId( ) {
	      return id;
	   }
	   
	   public void setId(int id) {
	      this.id = id;
	   }
	   
	   public String getText( ) {
	      return text;
	   }
	   
	   public void setText(String text) {
	      this.text = text;
	   }
	   
	   public float getLatitude() {
		   return latitude;
	   }
	
		public void setLatitude(float latitude) {
			this.latitude = latitude;
		}
	
		public float getLongitude() {
			return longitude;
		}
	
		public void setLongitude(float longitude) {
			this.longitude = longitude;
		}
	
}
