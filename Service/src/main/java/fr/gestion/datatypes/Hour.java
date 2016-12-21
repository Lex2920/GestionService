package fr.gestion.datatypes;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Embeddable
public final class Hour implements Serializable{
	
	/**
	 * 
	 */
	public Hour(){
		
	}
	private static final long serialVersionUID = -1898450321141268476L;
	@Column(name="Count")
	public double Count;
	
	public Hour(double count){
		this.Count = count;
	}
}
