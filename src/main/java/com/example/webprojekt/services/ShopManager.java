package com.example.webprojekt.services;

import com.example.webprojekt.entities.*;
import com.example.webprojekt.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopManager {
    private final AdminRepository adminRepo;
    private final AdminTokenRepository adminTokenRepo;
    private final ProductRepository productRepo;
    private final ACmotorRepository acmotorRepo;
    private final DCmotorRepository dcmotorRepo;

    private final OrderEntityRepository orderRepo;

    private final CustomerRepository customerRepo;

    @Autowired
    public ShopManager(AdminRepository adminRepo, AdminTokenRepository adminTokenRepo, ProductRepository productRepo, ACmotorRepository acmotorRepo, DCmotorRepository dcmotorRepo, OrderEntityRepository orderRepo, CustomerRepository customerRepo) {
        this.adminRepo = adminRepo;
        this.adminTokenRepo = adminTokenRepo;
        this.productRepo = productRepo;
        this.acmotorRepo = acmotorRepo;
        this.dcmotorRepo = dcmotorRepo;
        this.orderRepo = orderRepo;
        this.customerRepo = customerRepo;
    }

    public Iterable<Admin> getAllAdmins(){
        return adminRepo.findAll();
    }

    public Admin getAdminByNameAndPwd(String aname, String apwd){
        return adminRepo.findAdminByAdminnameAndAdminpwd(aname=aname, apwd=apwd);
    }

    public AdminToken getToken() {return adminTokenRepo.findFirstBy();}

    public Iterable<Product> getAllProducts(){
        return productRepo.findAll();
    }

    public Iterable<Product> getProductsByType(String type){
        return productRepo.findByTypeEquals(type);
    }

    public Iterable<Product> getProductsByPower(Float power_low, Float power_high){
        return productRepo.findByPowerGreaterThanAndPowerLessThan(power_low, power_high);
    }

    public Iterable<Product> getProductsByPrice(Float price_low, Float price_high){
        return productRepo.findByPriceGreaterThanAndPowerLessThan(price_low, price_high);
    }

    public Iterable<Product> getProductsByAllFilters(String type, Float power_low, Float power_high, Float price_low, Float price_high){
        return productRepo.findByAllFilters(type, power_low, power_high, price_low, price_high);
    }

    public Iterable<Product> getProductsByPowerAndPrice(Float power_low, Float power_high, Float price_low, Float price_high){
        return productRepo.findByPowerAndPrice(power_low, power_high, price_low, price_high);
    }

    public Long getCustomerIdByPersonalData(String first_name, String last_name, String email, String tel_num, String shipping_city, String shipping_street, String shipping_building){
        return customerRepo.getCustomerIdByPersonalData(first_name, last_name, email, tel_num, shipping_city, shipping_street, shipping_building);
    }

    public void setOrderAsCompleted(String order_id){
        orderRepo.setOrderAsCompleted(order_id);
    }

    public Float getPriceById(Long id){
        return productRepo.getPriceById(id);
    }

    public void saveCustomer(Customer customer) {
        customerRepo.save(customer);
    }

    public void saveOrder(OrderEntity order) {
        orderRepo.save(order);
    }

    public Customer findCustomerById(Long id) {
        return customerRepo.findCustomerById(id);
    }
    public Product findProductById(Long id) {
        return productRepo.findProductById(id);
    }

    public ACmotor findACmotorById(Long id){
        return acmotorRepo.findACmotorById(id);
    }

    public DCmotor findDCmotorById(Long id){
        return dcmotorRepo.findDCmotorById(id);
    }

    public Iterable<OrderEntity> getAllOrder(){
        return orderRepo.findAll();
    }

    public Iterable<Customer> getAllCustomer(){
        return customerRepo.findAll();
    }

    public Iterable<OrderEntity> getOrdersByCustomerId(Long id){
        return orderRepo.getOrdersByCustomerId(id);
    }

    public Iterable<OrderEntity> getNotCompletedOrdersByCustomerId(Long id){
        return orderRepo.getNotCompletedOrdersByCustomerId(id);
    }
}
