package fr.m2i.medical.service;

import fr.m2i.medical.entities.PatientEntity;
import fr.m2i.medical.entities.VilleEntity;
import fr.m2i.medical.repositories.PatientRepository;
import fr.m2i.medical.repositories.VilleRepository;
import org.springframework.stereotype.Service;

import java.io.InvalidObjectException;
import java.util.NoSuchElementException;

@Service
public class VilleService {

    private VilleRepository vr;

    public VilleService( VilleRepository vr ){
        this.vr = vr;
    }

    public Iterable<VilleEntity> findAll() {
        return vr.findAll();
    }

    private void checkVille( VilleEntity v ) throws InvalidObjectException {

        if( v.getNom().length() <= 2  ){
            throw new InvalidObjectException("Nom de ville invalide");
        }

        if( v.getPays().length() <= 3  ){
            throw new InvalidObjectException("Nom du pays invalide");
        }

    }

    public VilleEntity findVille(int id) {
        return vr.findById(id).get();
    }

    public void addVille( VilleEntity v ) throws InvalidObjectException {
        checkVille(v);
        vr.save(v);
    }

    public void delete(int id) {
        vr.deleteById(id);
    }

    public void editVille( int id , VilleEntity v) throws InvalidObjectException , NoSuchElementException {
        checkVille(v);
        try{
            VilleEntity vExistante = vr.findById(id).get();

            vExistante.setCodePostal( v.getCodePostal() );
            vExistante.setNom( v.getNom() );
            vExistante.setPays( v.getPays() );
            vr.save( vExistante );

        }catch ( NoSuchElementException e ){
            throw e;
        }

    }
}
