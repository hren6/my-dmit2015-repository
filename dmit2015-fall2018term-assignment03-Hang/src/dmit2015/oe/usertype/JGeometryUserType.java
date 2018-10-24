package dmit2015.oe.usertype;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Struct;
import java.sql.Types;
import java.util.Arrays;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.UserType;

import oracle.spatial.geometry.JGeometry;

public class JGeometryUserType implements UserType { 
	
	private static final int SQL_TYPE = Types.OTHER;
	
	private static final String DB_OBJECT_TYPE = "MDSYS.SDO_GEOMETRY";

	@Override
	public Object assemble(Serializable cache, Object owner) throws HibernateException {
		return cache;
	}

	@Override
	public Object deepCopy(Object value) throws HibernateException {
		if (value == null) {
			return null;
		}
		return value;
	}

	@Override
	public Serializable disassemble(Object value) throws HibernateException {
		return (Serializable) value;
	}

	@Override
	public boolean equals(Object obj1, Object obj2) throws HibernateException {
		boolean rv = false;
		if(obj1==null && obj2 == null) {
			rv = true;
		// check we are dealing with non-null objects of the correct type 
		} else if( obj1 instanceof JGeometry && obj2 instanceof JGeometry && obj1 != null && obj2 != null) {
			JGeometry geo1 = (JGeometry) obj1;
			JGeometry geo2 = (JGeometry) obj2;
 
			// check that they are the same type 
			if( geo1.getType() != geo2.getType()) {
				rv = false;
			} else { // go through the different types and check for equality 
				switch( geo1.getType()) {
				case JGeometry.GTYPE_POINT:
					rv = geo1.getJavaPoint().equals( geo2.getJavaPoint());
					break;
				case JGeometry.GTYPE_MULTIPOINT:				
				case JGeometry.GTYPE_MULTICURVE:
				case JGeometry.GTYPE_MULTIPOLYGON:
				case JGeometry.GTYPE_POLYGON:
				case JGeometry.GTYPE_CURVE: 
					rv = Arrays.equals( geo1.getOrdinatesOfElements(), geo2.getOrdinatesOfElements());
					break;
				default: 
					throw new UnsupportedOperationException(String.format("Geometry type %d is not supported", geo1.getType()));
				}			
			}
		} 
 
		return rv;
	}

	@Override
	public int hashCode(Object o) throws HibernateException {
		final JGeometry geo1 = (JGeometry) o;		
		int rv = -1;
 
		switch(geo1.getType()) {
		case JGeometry.GTYPE_POINT: 
			rv = geo1.getJavaPoint().hashCode();
			break;
		case JGeometry.GTYPE_MULTIPOINT:				
		case JGeometry.GTYPE_MULTICURVE:
		case JGeometry.GTYPE_MULTIPOLYGON:
		case JGeometry.GTYPE_POLYGON:
		case JGeometry.GTYPE_CURVE:
			rv = Arrays.hashCode(geo1.getOrdinatesOfElements());
			break;
		default: 
			throw new UnsupportedOperationException(String.format("Geometry type %d is not supported", geo1.getType()));
		}			
		return rv;
	}

	@Override
	public boolean isMutable() {
		return false;
	}

	@Override
	public Object nullSafeGet(ResultSet resultSet, String[] names, SharedSessionContractImplementor session, Object owner)
			throws HibernateException, SQLException {
		final Struct struct = (Struct) resultSet.getObject(names[0]);
		if (resultSet.wasNull()) {
			return null;
		}
		return JGeometry.loadJS(struct);
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

}
