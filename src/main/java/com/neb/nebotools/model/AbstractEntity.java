package com.neb.nebotools.model;

import java.util.Objects;

public abstract class AbstractEntity {
    protected String id;
    protected int row;

    public AbstractEntity(String id) {
        super();
        this.id = id;
    }

    public AbstractEntity() {
        super();
        this.id = "";
    }

    public String getId() {
        return id;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public abstract void setEntity(AbstractEntity t);
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        AbstractEntity other = (AbstractEntity) obj;
        return Objects.equals(id, other.id);
    }
}
