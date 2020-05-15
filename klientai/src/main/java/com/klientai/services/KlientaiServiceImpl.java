package com.klientai.services;

import com.klientai.model.Klientai;
import com.klientai.model.KlientaiDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class KlientaiServiceImpl implements KlientaiService {
    @Autowired
    @Qualifier("KlientaiDAO")
    private KlientaiDAO klientaiDAO;

    @Override
    public List<Klientai> getAll() {
        return klientaiDAO.findEntities();
    }
    @Override
    public void save(Klientai klientai) {
       klientaiDAO.insertEntity(klientai);
    }

    @Override
    public Klientai getById(int id) {
        return klientaiDAO.finEntityByID(id);
    }

    @Override
    public void update(Klientai klientai) {
        klientaiDAO.updateEntity(klientai);

    }

    @Override
    public void delete(int id) {
        klientaiDAO.removeEntityByID(id);

    }

    @Override
    public List<Klientai> search(@RequestParam(value = "name") String name) {
        System.out.println(name);
        return klientaiDAO.search(name);
    }


}
