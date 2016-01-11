package br.com.casadocodigo.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Model;
import javax.json.Json;
import javax.json.JsonArrayBuilder;

@Model
@SessionScoped
public class ShoppingCart implements Serializable {
	private static final long serialVersionUID = 484713091607592003L;
	
	private Map<ShoppingItem, Integer> items = new LinkedHashMap<ShoppingItem, Integer>();

	public void add(ShoppingItem item) {
		items.put(item, getQuantity(item) + 1);
	}

	public Integer getQuantity(ShoppingItem item) {
		if (!items.containsKey(item)) {
			items.put(item, 0);
		}
		return items.get(item);
	}

	public Integer getQuantity() {
		return items.values().stream()
				.reduce(0, (next, accumulator) -> next + accumulator);
	}

	public Collection<ShoppingItem> getList() {
		return new ArrayList<>(items.keySet());
	}

	public BigDecimal getTotal(ShoppingItem item) {
		return item.getTotal(getQuantity(item));
	}

	public BigDecimal getTotal() {
		BigDecimal total = BigDecimal.ZERO;
	
		for (ShoppingItem item : items.keySet()) {
			total = total.add(getTotal(item));
		}
		return total;
	}

	public void remove(ShoppingItem shoppingItem) {
		items.remove(shoppingItem);
	}

	public boolean isEmpty() {
		return items.isEmpty();
	}

	public String toJson() {
		JsonArrayBuilder items = Json.createArrayBuilder();
		for (ShoppingItem listCart : getList()) {
			items.add(Json.createObjectBuilder()
					.add("title", listCart.getBook().getTitle())
					.add("price", listCart.getPrice())
					.add("quantity", this.getQuantity(listCart))
					.add("sum", this.getTotal(listCart)));
		}
		
		return items.build().toString();
	}
}