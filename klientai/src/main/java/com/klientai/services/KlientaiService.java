package com.klientai.services;


import com.klientai.model.Klientai;


import java.util.List;

public interface KlientaiService {

        List<Klientai>getAll();
        void save(Klientai klientai);

        Klientai getById(int id);

        void update(Klientai klientai);

        void delete(int id);

        List<Klientai> search(String name);
}
