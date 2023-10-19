/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package product;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author ngtronghao <ngtronghao02@gmail.com>
 */
public class ProductCart {

    Map<String, ProductDTO> products;

    public ProductCart() {
    }

    public Map<String, ProductDTO> getProducts() {
        return products;
    }

    public void addProduct(String productID, ProductDTO dto) {
        if (this.products == null) {
            this.products = new HashMap<>();
        }
        this.products.put(productID, dto);
    }

    public ProductDTO getProduct(String productName) {
        ProductDTO dto = null;
        for (ProductDTO product : products.values()) {
            if (product.getName().equals(productName)) {
                dto = product;
            }
        }
        return dto;
    }

    public ProductDTO getProductByID(String productID) {
        return products.get(productID);
    }
}
