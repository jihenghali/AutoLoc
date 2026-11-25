package tn.esprit.autoloc.autolocapi.service;

import java.util.List;
import tn.esprit.autoloc.autolocapi.exception.AffectationConflictException;
import tn.esprit.autoloc.autolocapi.exception.ResourceNotFoundException;

public final class RechercheAffectation {
    private RechercheAffectation() {
    }

    public static <T> T resultatUnique(List<T> resultats, String critere) {
        if (resultats.isEmpty()) {
            throw new ResourceNotFoundException("Aucun r?sultat pour " + critere);
        }
        if (resultats.size() > 1) {
            throw new AffectationConflictException("Plusieurs r?sultats pour " + critere);
        }
        return resultats.get(0);
    }
}
