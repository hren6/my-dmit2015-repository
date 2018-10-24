package dmit2015.oe.entity;

import java.io.Serializable;
import javax.persistence.*;

import dmit2015.hr.entity.Employee;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the ORDERS database table.
 * 
 */
@Entity
@Table(name="ORDERS", schema="OE")
@NamedQuery(name="Order.findAll", query="SELECT o FROM Order o")
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ORDERS_ORDERID_GENERATOR", sequenceName="ORDERS_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ORDERS_ORDERID_GENERATOR")
	@Column(name="ORDER_ID")
	private long orderId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="ORDER_DATE")
	private Date orderDate;

	@Column(name="ORDER_MODE")
	private String orderMode;

	@Column(name="ORDER_STATUS")
	private Integer orderStatus;

	@Column(name="ORDER_TOTAL")
	private BigDecimal orderTotal;

	@Column(name="PROMOTION_ID")
	private Integer promotionId;

	@ManyToOne
	@JoinColumn(name="SALES_REP_ID")
	private Employee salesRep;

	//bi-directional many-to-one association to Customer
	@ManyToOne
	@JoinColumn(name="CUSTOMER_ID")
	private Customer customer;

	//bi-directional many-to-one association to OrderItem
	@OneToMany(mappedBy="order")
	private List<OrderItem> orderItems;

	public Order() {
	}

	public long getOrderId() {
		return this.orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public Date getOrderDate() {
		return this.orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public String getOrderMode() {
		return this.orderMode;
	}

	public void setOrderMode(String orderMode) {
		this.orderMode = orderMode;
	}

	public Integer getOrderStatus() {
		return this.orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	public BigDecimal getOrderTotal() {
		return this.orderTotal;
	}

	public void setOrderTotal(BigDecimal orderTotal) {
		this.orderTotal = orderTotal;
	}

	public Integer getPromotionId() {
		return this.promotionId;
	}

	public void setPromotionId(Integer promotionId) {
		this.promotionId = promotionId;
	}
	
	public Customer getCustomer() {
		return this.customer;
	}

	public Employee getSalesRep() {
		return salesRep;
	}

	public void setSalesRep(Employee salesRep) {
		this.salesRep = salesRep;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<OrderItem> getOrderItems() {
		return this.orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public OrderItem addOrderItem(OrderItem orderItem) {
		getOrderItems().add(orderItem);
		orderItem.setOrder(this);

		return orderItem;
	}

	public OrderItem removeOrderItem(OrderItem orderItem) {
		getOrderItems().remove(orderItem);
		orderItem.setOrder(null);

		return orderItem;
	}

}