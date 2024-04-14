// Coffee.java
package com.shop.coffee.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "coffees")
public class Coffee {
	@Id
	public String _id;
	public String title;
	public String price;
	public String image;
	public Integer quantityInStorage;

	public String getId() {
		return _id;
	}

	public void setId(String _id) {
		this._id = _id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getQuantityInStorage() {
		return quantityInStorage;
	}

	public void setQuantityInStorage(int quantityInStorage) {
		this.quantityInStorage = quantityInStorage;
	}
}
