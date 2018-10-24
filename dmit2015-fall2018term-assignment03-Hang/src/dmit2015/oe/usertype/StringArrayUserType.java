package dmit2015.oe.usertype;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Arrays;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.UserType;

import oracle.jdbc.driver.OracleConnection;

public class StringArrayUserType implements UserType {
	private static final int SQL_TYPE = Types.ARRAY;
	
	private static final String DB_OBJECT_TYPE = "PHONE_LIST_TYP";

	@Override
	public Object assemble(Serializable cache, Object owner) throws HibernateException {
		return cache;
	}

	@Override
	public Object deepCopy(Object value) throws HibernateException {
		if (value == null) 
			return null;

//		@SuppressWarnings("unchecked")
//		List<String> phoneNumbers = (List<String>) value;
//	    return new ArrayList<String>(phoneNumbers);
		return value;
	}

	@Override
	public Serializable disassemble(Object value) throws HibernateException {
		return (Serializable) value;
	}

	@Override
	public boolean equals(Object o1, Object o2) throws HibernateException {
		if (o1 == null || o2 == null)
			return false;
		if (o1 == o2 ) 
			return true;
		return o1.equals(o2);
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
		return List.class;
	}

	@Override
	public int[] sqlTypes() {
		return new int[] { SQL_TYPE };
	}
	
	public static OracleConnection getOracleConnection(Connection conFromPool) throws SQLException {
		try {
			Class[] parms = null;
			Method method = (conFromPool.getClass()).getMethod("getUnderlyingConnection", parms);
			return (OracleConnection) method.invoke(conFromPool, parms);

		} catch (InvocationTargetException ite) {
			throw new SQLException(ite.getMessage());
		} catch (Exception e) {
			throw new SQLException(e.getMessage());
		}
	}

	@Override
	public Object nullSafeGet(ResultSet resultSet, String[] names, SharedSessionContractImplementor session, Object owner)
			throws HibernateException, SQLException {
		Array phoneArray = resultSet.getArray( names[0] );
		if (phoneArray == null) {
			return null;
		} else {
		    String[] phoneStringArray = (String[]) phoneArray.getArray();
		    return Arrays.asList(phoneStringArray);			
		}
	}

	@Override
	public void nullSafeSet(PreparedStatement statement, Object value, int index, SharedSessionContractImplementor session)
			throws HibernateException, SQLException {
		if (value == null) {
			statement.setNull(index,  SQL_TYPE, DB_OBJECT_TYPE);
		} else { 
			@SuppressWarnings("unchecked")
			List<String> valueList = (List<String>) value;
			String[] elements = valueList.toArray(new String[valueList.size()]);

			final Connection connection = statement.getConnection();
			final OracleConnection oracleConnection = getOracleConnection(connection);
			
			Array phoneNumbers = oracleConnection.createOracleArray("PHONE_LIST_TYP", elements);
			
			statement.setArray(index, phoneNumbers);
		}	
		
	}

}
