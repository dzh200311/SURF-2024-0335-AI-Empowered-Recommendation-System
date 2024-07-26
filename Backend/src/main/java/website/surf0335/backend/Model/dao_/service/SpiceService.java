package website.surf0335.backend.Model.dao_.service;

import org.springframework.stereotype.Repository;
import website.surf0335.backend.Model.dao_.domain.Spice;

import java.util.List;

@Repository
public interface SpiceService {
    Spice querySingleBySpiceId(int spiceId);
    List<Spice> queryAllSpices();
    boolean addSpice(int spiceId, String category, String countryOfOrigin, Double price, Integer stockQuantity, Integer storeId);
    boolean deleteSpice(int spiceId);
    boolean updateSpice(int spiceId, String category, String countryOfOrigin, Double price, Integer stockQuantity, Integer storeId);
}
