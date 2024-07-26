package website.surf0335.backend.Model.dao_.service;

import org.springframework.stereotype.Repository;
import website.surf0335.backend.Model.dao_.domain.TotalDiscount;

import java.util.List;

@Repository
public interface TotalDiscountService {
    TotalDiscount querySingleByTotalDiscountId(int totalDiscountId);
    List<TotalDiscount> queryAllTotalDiscounts();
    boolean addTotalDiscount(int totalDiscountId, String name, String discount, String description);
    boolean deleteTotalDiscount(int totalDiscountId);
    boolean updateTotalDiscount(int totalDiscountId, String name, String discount, String description);
}
