package com.grocery.demo.Service;

import com.grocery.demo.Model.Product;
import com.grocery.demo.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

//    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//
//    String username = auth.getName();


    public void saveProduct (Product product, MultipartFile file, String username){

        try {

           // System.out.println("See the username: "+username);

            byte[] bytes = file.getBytes();//read the image data
            String absolutePath = "/home/fred/Videos/Uploads/"+file.getOriginalFilename();//store the image in this location
            Path path = Paths.get(absolutePath);//get the path to write to
            Files.write(path,bytes);//write the image to the OS system

            product.setStatus("PENDING");
          product.setDealerName(username);
            product.setCarImage(file.getOriginalFilename());//save the image address to the database.

            productRepository.save(product);


        }catch (Exception e){
            System.out.println(e.getCause());
        }
    }


    // GET-ALL pending approval
    public List<Product> pendingApproval() {
        List<Product> pendingProducts = new ArrayList<>();
        productRepository.findByStatus("PENDING").forEach(pendingProducts::add);
        return pendingProducts;
    }

   // find all  products
    public List<Product> getAllProducts(){

        List<Product> products = new ArrayList<>();
        productRepository.findAll().forEach(products::add);

        return products;
    }

    //enable a dealer find his products.
    public List<Product> myProducts(String username){

        List<Product> myProducts = new ArrayList<>();
        productRepository.findByDealerName(username).forEach(myProducts::add);
        return myProducts;
    }

    public List<Product> approvedProucts(){

        List<Product> approvedProducts = new ArrayList<>();
        productRepository.findByStatus("APPROVED").forEach(approvedProducts::add);
        return approvedProducts;
    }

    @Transactional
        public void updateStatus( Long carId)
    {

        productRepository.setUpdateStatus("APPROVED",carId);
    }

    @Transactional
    public void deleteProduct(Long id)
    {
        productRepository.deleteProduct(id);
    }
}
