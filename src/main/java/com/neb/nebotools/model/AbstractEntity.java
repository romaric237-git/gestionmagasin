package com.neb.nebotools.model;

import javafx.scene.control.CheckBox;
import lombok.*;

import java.util.Objects;
@AllArgsConstructor
@Setter
@Getter
public abstract class AbstractEntity {
    protected String id;
    protected int row;

    protected CheckBox checkBox;
    protected int is_actif;

    public AbstractEntity(String id, int is_actif) {
        super();
        this.id = id;
        this.is_actif = is_actif;
        checkBox = new CheckBox();
        checkBox.getStyleClass().add("form-check-input");
    }

    public AbstractEntity() {
        this("",0);
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
