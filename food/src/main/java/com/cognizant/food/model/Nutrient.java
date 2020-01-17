package com.cognizant.food.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="nutrient")
public class Nutrient {

	@Id
	@Column(name="nu_id")
	private int nutrient_id;
	
	@Column(name="nu_name")
	private String name;
	
	@Column(name="nu_der")
	private String derivations;
	
	@Column(name="nu_group")
	private String group;
	
	@Column(name="nu_unit")
	private String unit;
	
	@Column(name="nu_value")
	private double value;
	
	@ManyToMany(mappedBy = "nutrients", fetch = FetchType.EAGER)
	private List<FoodItem> foodItems;
	//private List<Measure> measures;

	public Nutrient() {
		super();
	}
	public Nutrient(int nutrient_id, String name, String derivations, String group, String unit, double value,
			List<FoodItem> foodItems) {
		super();
		this.nutrient_id = nutrient_id;
		this.name = name;
		this.derivations = derivations;
		this.group = group;
		this.unit = unit;
		this.value = value;
		this.foodItems = foodItems;
	}
	public int getNutrient_id() {
		return nutrient_id;
	}
	public void setNutrient_id(int nutrient_id) {
		this.nutrient_id = nutrient_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDerivations() {
		return derivations;
	}
	public void setDerivations(String derivations) {
		this.derivations = derivations;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	public List<FoodItem> getFoodItems() {
		return foodItems;
	}
	public void setFoodItems(List<FoodItem> foodItems) {
		this.foodItems = foodItems;
	}
	
	
}
