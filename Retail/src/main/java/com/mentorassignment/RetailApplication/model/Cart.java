package com.mentorassignment.RetailApplication.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long userId;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "cart_id")
	private List<ItemsItem> items;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public List<ItemsItem> getItems() {
		return items;
	}

	public void setItems(List<ItemsItem> items) {
		this.items = items;
	}
}
