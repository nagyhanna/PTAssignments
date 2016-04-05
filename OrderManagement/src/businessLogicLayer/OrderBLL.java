package businessLogicLayer;

import java.sql.ResultSet;

import javax.swing.JOptionPane;

import dataAccessLayer.OrdersDAO;
import dataAccessLayer.ProductsDAO;

public class OrderBLL {

	private OrdersDAO ordersDAO;
	private double totalPrice;
	private ProductsDAO productsDAO;

	public OrderBLL() {
		productsDAO = new ProductsDAO();
		ordersDAO = new OrdersDAO();

	}

	public Boolean checkIsQuantity(int productId, int wantedQuantity) {

		if (productsDAO.getQuantity(productId) >= wantedQuantity) {
			return true;
		}
		return false;

	}

	public void order(int clientId, int productId, int wantedQuantity) {
		if (!this.checkIsQuantity(productId, wantedQuantity)) {
			JOptionPane.showConfirmDialog(null, "There is no enough product in the warehouse");
		} else {

			ordersDAO.insertAnOrder(clientId, productId, wantedQuantity);

			this.updateProductQuantity(productId, productsDAO.getQuantity(productId) - wantedQuantity);

		}
	}

	public Double calculateTotalPrice(int clientId) {
		totalPrice = ordersDAO.calculateTotalPrice(clientId);
		return totalPrice;
	}

	public void updateProductQuantity(int productId, int quantity) {

		productsDAO.updateProductQuantity(productId, quantity);

	}

	public double getTotalPrice() {
		return this.totalPrice;
	}

	public ResultSet getOrderResultSet(int clientId) {
		return ordersDAO.getAllOrderedProduct(clientId);
	}

	public void deleteOrder(int clientId, int productId) {
		ordersDAO.deleteAnOrder(clientId, productId);
	}

	public void deleteAllOrders(int clientId) {
		ordersDAO.deleteAllOrder(clientId);
	}

}
