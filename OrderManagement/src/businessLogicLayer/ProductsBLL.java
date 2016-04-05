package businessLogicLayer;

import java.sql.ResultSet;
import java.util.List;


import dataAccessLayer.ProductsDAO;

import entities.Products;

public class ProductsBLL {
	
	
 private ProductsDAO productsDAO;
	
	
	public ProductsBLL(){
		productsDAO= new ProductsDAO();
		
	}
	
	
	public void insertNewProduct(int productId, String name, String brand, String colour, String size, String ram, double price,int quantity){
		productsDAO.insertProduct(productId,name, brand, colour, size, ram,price, quantity);
	}
	
	public void deleteProduct(int productId){
		productsDAO.deleteProduct(productId);
	}
	
	public void updateProduct(int productId, String name, String brand, String colour, String size, String ram, double price, int quantity){
		productsDAO.updateProduct(productId,name, brand, colour, size, ram,price,quantity);
	}
	
	public List<Products> getAllClients(){
		return productsDAO.getAllProducts();
	}
	
	public ResultSet getResultSet(){
		return this.productsDAO.getAllProductResultSet();
	}
	public ResultSet getFilteredResultSet(String brand){
		return this.productsDAO.getAllProductFilteredByBrandResultSet(brand);
	}
	
	public void updateProductQuantity(int productId, int quantity){
		productsDAO.updateProductQuantity(productId, quantity);
	}
}
