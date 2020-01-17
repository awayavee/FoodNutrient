package com.cognizant.food.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="fooditem")
public class FoodItem {

	@Id
	@Column(name="fi_id")
	private int id;
	
	@Column(name="fi_name")
	private String name;
	
	@Column(name="fi_ndbno")
	private long ndbno;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "fooditem_nutrient", joinColumns = @JoinColumn(name = "fn_fi_id"), inverseJoinColumns = @JoinColumn(name = "fn_nu_id"))
	private List<Nutrient> nutrients;

	public FoodItem() {
		super();
	}

	public FoodItem(int id, String name, long ndbno, List<Nutrient> nutrients) {
		super();
		this.id = id;
		this.name = name;
		this.ndbno = ndbno;
		this.nutrients = nutrients;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getNdbno() {
		return ndbno;
	}

	public void setNdbno(long ndbno) {
		this.ndbno = ndbno;
	}

	public List<Nutrient> getNutrients() {
		return nutrients;
	}

	public void setNutrients(List<Nutrient> nutrients) {
		this.nutrients = nutrients;
	}
	
	
}
