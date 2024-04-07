package com.neb.nebotools.model;

import java.util.Objects;

public abstract class AbstractEntity {
    protected String id;
    protected int row;

    protected int is_actif;

    public AbstractEntity(String id, int is_actif) {
        super();
        this.id = id;
        this.is_actif = is_actif;
    }

    public AbstractEntity() {
        this("",1);
    }

    public String getId() {
        return id;
    }
    public void setId() {
        this.id = id;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getIs_actif() {
        return is_actif;
    }

    public void setIs_actif(int is_actif) {
        this.is_actif = is_actif;
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
