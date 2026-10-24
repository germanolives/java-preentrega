package com.techlab.article.service;

import com.techlab.article.model.Category;
import com.techlab.article.repository.Repository;


public class Catalog extends Repository<Category> {


    @Override
    public void addItemToRepository(Category item) {
        if (item == null) {
            throw new IllegalArgumentException("El item debe existir");
        }
        if (this.isItemCodeIntoRepository(item.getCode())) {
            throw new IllegalArgumentException("Ese código de item ya existe en el repositorio");
        }
        if (this.isItemNameIntoRepository(item.getName())) {
            throw new IllegalArgumentException("Ese nombre de item ya existe en el repositorio");
        }
        super.list.add(item);
    }

    @Override
    public Category removeItemFromRepository (String code) {
        return super.removeItemFromRepository(code);
    }

    public Category removeItemFromRepository(String code, Inventory inventory) {
        if (code != null && !code.isBlank() && !super.list.isEmpty() && inventory != null) {
            if (!hasCategoryInInventory(code, inventory)) {
                Category category = super.list.stream()
                        .filter(item -> item != null && item.getCode() != null)
                        .filter(item -> item.getCode().equalsIgnoreCase(code.trim()))
                        .findFirst()
                        .orElse(null);
                if (category != null) {
                    super.list.remove(category);
                    return category;
                }
            }
        }
        return null;
    }


    public boolean hasCategoryInInventory(String code, Inventory inventory) {
        if (code == null || code.isBlank() || inventory == null || inventory.getList() == null) {
            return false;
        }
        return inventory.getList().stream()
                .filter(item -> item != null && item.getCategory() != null)
                .anyMatch(item -> item.getCategory().getCode().equalsIgnoreCase(code.trim()));
    }

}
