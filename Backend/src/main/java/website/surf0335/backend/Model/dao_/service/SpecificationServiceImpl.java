package website.surf0335.backend.Model.dao_.service;

import org.springframework.stereotype.Repository;
import website.surf0335.backend.Model.dao_.dao.SpecificationDao;
import website.surf0335.backend.Model.dao_.domain.Specification;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class SpecificationServiceImpl implements SpecificationService{

    private SpecificationDao specificationDao = new SpecificationDao();
    @Override
    public String[] getSpecificationByID(int id) {
        Specification specification = specificationDao.querySingle("select * from `specification` where `id` = ?", Specification.class,id);
        return specification.getContent().split("&");
    }

    @Override
    public List<String[]> getAllSpecification() {
        List<Specification> specificationList = specificationDao.queryMultiple("select * from `specification`", Specification.class);
        List<String[]> result = new ArrayList<>();
        for(Specification specification : specificationList){
            result.add(specification.getContent().split("&"));
        }
        return result;
    }

    @Override
    public boolean addSpecification(String content ) {
        int update = specificationDao.update("INSERT INTO `specification` (`content`) VALUES (?)", content);
        return update > 0;
    }



    @Override
    public boolean modifySpecification(String content, int id) {
        int update = specificationDao.update("UPDATE `specification` SET `content` = ? WHERE `id` = ?", content, id);
        return update > 0;
    }

    public static void main(String[] args) {
        SpecificationServiceImpl specificationService = new SpecificationServiceImpl();
        String[] specificationByID = specificationService.getSpecificationByID(5);
        System.out.println(Arrays.toString(specificationByID));
        System.out.println("asasasa");
        for (String[] strings : specificationService.getAllSpecification()) {
            System.out.println(Arrays.toString(strings));
        }
        System.out.println(specificationService.addSpecification("oo&oou&ooo2&oo1"));
        System.out.println(specificationService.modifySpecification("oo&oou", 6));
    }
}
