package fr.m2i.medical.service;

import fr.m2i.medical.api.PatientAPIController;
import fr.m2i.medical.entities.PatientEntity;
import fr.m2i.medical.entities.VilleEntity;
import fr.m2i.medical.repositories.PatientRepository;
import org.springframework.stereotype.Service;

import java.io.InvalidObjectException;
import java.util.NoSuchElementException;

@Service
public class PatientService {

    private PatientRepository pr;

    public PatientService( PatientRepository pr ){
        this.pr = pr;
    }

    private void checkPatient( PatientEntity p ) throws InvalidObjectException {

        if( p.getAdresse().length() < 10  ){
            throw new InvalidObjectException("Adresse invalide");
        }

        if( p.getNom().length() < 2  ){
            throw new InvalidObjectException("Nom invalide");
        }

        if( p.getPrenom().length() < 2  ){
            throw new InvalidObjectException("Prenom invalide");
        }

    }

    public void addPatient( PatientEntity p ) throws InvalidObjectException {
        checkPatient(p);
        pr.save(p);
    }

    public Iterable<PatientEntity> findAll() {
        return pr.findAll();
    }

    public PatientEntity findPatient(int id) {
        return pr.findById(id).get();
    }

    public void delete(int id) {
        pr.deleteById(id);
    }

    public void editPatient( int id , PatientEntity p) throws InvalidObjectException , NoSuchElementException {
        checkPatient(p);
        try{
            PatientEntity pExistante = pr.findById(id).get();

            pExistante.setAdresse( p.getAdresse() );
            pExistante.setNom( p.getNom() );
            pExistante.setPrenom( p.getPrenom() );
            pExistante.setDatenaissance( p.getDatenaissance() );
            pExistante.setVille( p.getVille() );
            pr.save( pExistante );

        }catch ( NoSuchElementException e ){
            throw e;
        }

    }

}
