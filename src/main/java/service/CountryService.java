package service;

import model.Country;
import java.util.List;
import java.util.Optional;

public interface CountryService {
    Optional<Country> findById(Long id);
    Optional<Country> create(String name, String continent);
    List<Country> listAll();
}
