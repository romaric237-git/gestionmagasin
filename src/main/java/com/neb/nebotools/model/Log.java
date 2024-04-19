package com.neb.nebotools.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
public class Log extends AbstractEntity {
    private String employee;
    private String type;
    private String entity;
    private String entityID;
    private Date date_log;

    @Override
    public void setEntity(AbstractEntity t) {

    }
}
