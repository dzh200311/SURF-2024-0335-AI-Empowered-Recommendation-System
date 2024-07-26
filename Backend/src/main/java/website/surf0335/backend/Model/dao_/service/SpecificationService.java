package website.surf0335.backend.Model.dao_.service;

import java.util.List;

public interface SpecificationService {

    String[] getSpecificationByID(int id);
    public List<String[]> getAllSpecification();
    public boolean addSpecification(String content );

    public boolean modifySpecification(String content, int id);

}
