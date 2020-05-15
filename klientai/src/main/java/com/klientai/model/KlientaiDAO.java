package com.klientai.model;

import java.util.List;

public interface KlientaiDAO {
    void insertEntity(Klientai klientai);
    Klientai finEntityByID(int id);
    List<Klientai>findEntities();
    void updateEntity(Klientai klientai);
    void removeEntityByID(int id);
    List<Klientai>search(String name);
}
