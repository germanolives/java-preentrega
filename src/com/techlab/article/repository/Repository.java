package com.techlab.article.repository;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Collections;
import java.util.stream.IntStream;
import com.techlab.article.interfaces.Identifiable;

public abstract class Repository<T extends Identifiable> implements Iterable<T> {
    protected final List<T> list;

    public Repository() {
        this.list = new ArrayList<>();
    }

    @Override
    public Iterator<T> iterator() {
        return Collections.unmodifiableList(this.list).iterator();
    }

    public boolean isEmpty() {
        return (this.list.isEmpty());
    }

    public int getIndexByName(String name) {
        if (name == null || name.isBlank())
            return -1;
        return IntStream.range(0, this.list.size())
                .filter(i -> this.list.get(i) != null && this.list.get(i).getName() != null)
                .filter(i -> this.list.get(i).getName().equalsIgnoreCase(name.trim()))
                .findFirst()
                .orElse(-1);
    }

    public int getIndexByCode(String code) {
        if (code == null || code.isBlank())
            return -1;
        return IntStream.range(0, this.list.size())
                .filter(i -> this.list.get(i) != null && this.list.get(i).getCode() != null)
                .filter(i -> this.list.get(i).getCode().equalsIgnoreCase(code.trim()))
                .findFirst()
                .orElse(-1);
    }

    public T getItemByCode(String code) {
        if (code == null || code.isBlank() || this.list.isEmpty())
            return null;
        return this.list.stream()
                .filter(item -> item != null && item.getCode() != null)
                .filter(item -> item.getCode().equalsIgnoreCase(code.trim()))
                .findFirst()
                .orElse(null);
    }

    public T getItemByName(String name) {
        if (name == null || name.isBlank() || this.list.isEmpty())
            return null;
        return this.list.stream()
                .filter(item -> item != null && item.getName() != null)
                .filter(item -> item.getName().equalsIgnoreCase(name.trim()))
                .findFirst()
                .orElse(null);
    }

    public boolean isItemCodeIntoRepository(String code) {
        return this.getItemByCode(code) != null;
    }

    public boolean isItemNameIntoRepository(String name) {
        return this.getItemByName(name) != null;
    }

    public List<T> getList() {
        return Collections.unmodifiableList(this.list);
    }

    public void addItemToRepository(T item) {
        if (item == null) {
            throw new IllegalArgumentException("El item debe existir");
        }
        if (this.isItemCodeIntoRepository(item.getCode())) {
            throw new IllegalArgumentException("Ese código de item ya existe en el repositorio");
        }
        this.list.add(item);
    }

    public T removeItemFromRepository(String code) {
        if (code != null && !code.isBlank() && !this.list.isEmpty()) {
            T item = this.list.stream()
                    .filter(it -> it != null && it.getCode() != null)
                    .filter(it -> it.getCode().equalsIgnoreCase(code.trim()))
                    .findFirst()
                    .orElse(null);
            if (item != null) {
                this.list.remove(item);
                return item;
            }
        }
        return null;
    }
}
