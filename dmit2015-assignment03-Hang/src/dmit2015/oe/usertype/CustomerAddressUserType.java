package dmit2015.oe.usertype;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Struct;
import java.sql.Types;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.UserType;

public class CustomerAddressUserType implements UserType { 
	
	private static final int SQL_TYPE = Types.STRUCT;
	
	private static final String DB_OBJECT_TYPE = "CUST_ADDRESS_TYP";

	@Override
	public Object assemble(Serializable cache, Object owner) throws HibernateException {
		return cache;
	}

	@Override
	public Object deepCopy(Object value) throws HibernateException {
		if (value == null) {
			return null;
		}
		final CustomerAddress address = (CustomerAddress) value;
		final CustomerAddress clone = new CustomerAddress();
		clone.setStreetAddress( address.getStreetAddress() );
		clone.setPostalCode( address.getPostalCode() );
		clone.setCity( address.getCity() );
		clone.setCountryId( address.getCountryId() );
		clone.setStateProvince( address.getStateProvince() );
		return clone;
	}

	@Override
	public Serializable disassemble(Object value) throws HibernateException {
		return (Serializable) value;
	}

	@Override
	public boolean equals(Object o1, Object o2) throws HibernateException {
		if (o1 == o2) {
			return true;
		}
		if (o1 == null || o2 == null) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode(Object o) throws HibernateException {
		return o.hashCode();
	}

	@Override
	public boolean isMutable() {
		return false;
	}

	@Override
	public Object replace(Object original, Object target, Object owner) throws HibernateException {
		return original;
	}

	@Override
	public Class<?> returnedClass() {
		return CustomerAddress.class;
	}

	@Override
	public int[] sqlTypes() {
		return new int[] { SQL_TYPE };
	}

	@Override
	public Object nullSafeGet(ResultSet resultSet, String[] names, SharedSessionContractImplementor session, Object owner)
			throws HibernateException, SQLException {
		final Struct struct = (Struct) resultSet.getObject(names[0]);
		if (resultSet.wasNull()) {
			return null;
		}
		final CustomerAddress address = new CustomerAddress();
		address.setStreetAddress( (String) struct.getAttributes()[0]); 
		address.setPostalCode( (String) struct.getAttributes()[1]);
		address.setCity( (String) struct.getAttributes()[2]);
		address.setStateProvince( (String) struct.getAttributes()[3]);
		address.setCountryId( (String) struct.getAttributes()[4]);
		return address;
	}

	@Override
	public void nullSafeSet(PreparedStatement statement, Object value, int index, SharedSessionContractImplementor session)
			throws HibernateException, SQLException {
		if (value == null) {
			statement.setNull(index,  SQL_TYPE, DB_OBJECT_TYPE);
		} else {
			final CustomerAddress address = (CustomerAddress) value;
			final Object[] values = new Object[] {
				address.getStreetAddress(),
				address.getPostalCode(),
				address.getCity(),
				address.getStateProvince(),
				address.getCountryId()
			};
			final Connection connection = statement.getConnection();
			Struct dbStruct = connection.createStruct(DB_OBJECT_TYPE, values);
			statement.setObject(index, dbStruct, SQL_TYPE);
		}
		
	}

}
