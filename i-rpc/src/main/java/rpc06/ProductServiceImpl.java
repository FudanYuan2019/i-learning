package rpc06;

import common.Product;
import common.ProductService;

/**
 * @Author: Jeremy
 * @Date: 2020/3/14 12:51
 */
public class ProductServiceImpl implements ProductService {
    @Override
    public Product findById(int id) {
        return new Product(id, "Mac Pro");
    }
}
