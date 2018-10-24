package dmit2015.oe.entity;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.Type;
import dmit2015.hr.entity.Employee;
import dmit2015.oe.usertype.CustomerAddress;
import oracle.spatial.geometry.JGeometry;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the CUSTOMERS database table.
 * 
 */
@Entity
@Table(name="CUSTOMERS", schema="OE")
@NamedQuery(name="Customer.findAll", query="SELECT c FROM Customer c")
public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="CUSTOMER_ID")
	private long customerId;

	//bi-directional many-to-one association to Employee
	@ManyToOne
	@JoinColumn(name="ACCOUNT_MGR_ID")
	private Employee accountManager;

	@Column(name="CREDIT_LIMIT")
	private BigDecimal creditLimit;

	@Column(name="CUST_ADDRESS", columnDefinition="CUST_ADDRESS_TYP")
	@Type(type="dmit2015.oe.usertype.CustomerAddressUserType")
	private CustomerAddress custAddress;

	@Column(name="CUST_EMAIL")
	private String custEmail;

	@Column(name="CUST_FIRST_NAME")
	private String custFirstName;

//	@Column(name="CUST_GEO_LOCATION", columnDefinition="MDSYS.SDO_GEOMETRY")
//	@Type(type="dmit2015.oe.usertype.JGeometryUserType")
//	private JGeometry custGeoLocation;
	@Transient
	private Object custGeoLocation;
	
	@Column(name="CUST_LAST_NAME")
	private String custLastName;

	@Temporal(TemporalType.DATE)
	@Column(name="DATE_OF_BIRTH")
	private Date dateOfBirth;

	private String gender;

	@Column(name="INCOME_LEVEL")
	private String incomeLevel;

	@Column(name="MARITAL_STATUS")
	private String maritalStatus;

	@Column(name="NLS_LANGUAGE")
	private String nlsLanguage;

	@Column(name="NLS_TERRITORY")
	private String nlsTerritory;

	@Column(name="PHONE_NUMBERS", columnDefinition="PHONE_LIST_TYP")
	@Type(type="dmit2015.oe.usertype.StringArrayUserType")
	private List<String> phoneNumbers;

	//bi-directional many-to-one association to Order
	@OneToMany(mappedBy="customer")
	private List<Order> orders;

	public Customer() {
	}

	public long getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public BigDecimal getCreditLimit() {
		return this.creditLimit;
	}

	public Employee getAccountManager() {
		return accountManager;
	}

	public void setAccountManager(Employee accountManager) {
		this.accountManager = accountManager;
	}

	public void setCreditLimit(BigDecimal creditLimit) {
		this.creditLimit = creditLimit;
	}

	public CustomerAddress getCustAddress() {
		return this.custAddress;
	}

	public void setCustAddress(CustomerAddress custAddress) {
		this.custAddress = custAddress;
	}

	public String getCustEmail() {
		return this.custEmail;
	}

	public void setCustEmail(String custEmail) {
		this.custEmail = custEmail;
	}

	public String getCustFirstName() {
		return this.custFirstName;
	}

	public void setCustFirstName(String custFirstName) {
		this.custFirstName = custFirstName;
	}

	public Object getCustGeoLocation() {
		return this.custGeoLocation;
	}

	public void setCustGeoLocation(Object custGeoLocation) {
		this.custGeoLocation = custGeoLocation;
	}

	public String getCustLastName() {
		return this.custLastName;
	}

	public void setCustLastName(String custLastName) {
		this.custLastName = custLastName;
	}

	public Date getDateOfBirth() {
		return this.dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getIncomeLevel() {
		return this.incomeLevel;
	}

	public void setIncomeLevel(String incomeLevel) {
		this.incomeLevel = incomeLevel;
	}

	public String getMaritalStatus() {
		return this.maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getNlsLanguage() {
		return this.nlsLanguage;
	}

	public void setNlsLanguage(String nlsLanguage) {
		this.nlsLanguage = nlsLanguage;
	}

	public String getNlsTerritory() {
		return this.nlsTerritory;
	}

	public void setNlsTerritory(String nlsTerritory) {
		this.nlsTerritory = nlsTerritory;
	}

	public List<String> getPhoneNumbers() {
		return this.phoneNumbers;
	}

	public void setPhoneNumbers(List<String> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}

	public List<Order> getOrders() {
		return this.orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public Order addOrder(Order order) {
		getOrders().add(order);
		order.setCustomer(this);

		return order;
	}

	public Order removeOrder(Order order) {
		getOrders().remove(order);
		order.setCustomer(null);

		return order;
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", accountManager=" + accountManager + ", creditLimit="
				+ creditLimit + ", custAddress=" + custAddress + ", custEmail=" + custEmail + ", custFirstName="
				+ custFirstName + ", custGeoLocation=" + custGeoLocation + ", custLastName=" + custLastName
				+ ", dateOfBirth=" + dateOfBirth + ", gender=" + gender + ", incomeLevel=" + incomeLevel
				+ ", maritalStatus=" + maritalStatus + ", nlsLanguage=" + nlsLanguage + ", nlsTerritory=" + nlsTerritory
				+ ", phoneNumbers=" + phoneNumbers + "]";
	}

	
}