package com.example.demo.bean;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.StringWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.UserType;

import com.fasterxml.jackson.databind.ObjectMapper;

public class StudentAddress implements UserType{

	@Override
	public int[] sqlTypes() {
		return new int[] {Types.JAVA_OBJECT};
	}

	@Override
	public Class<StudentAddress> returnedClass() {
		return StudentAddress.class;
	}

	@Override
	public boolean equals(final Object obj1, final Object obj2) throws HibernateException {
		if(obj1 == null) {
			return obj2 == null;
		}
		return obj1.equals(obj2);
	}

	@Override
	public int hashCode(final Object obj) throws HibernateException {
		return obj.hashCode();
	}

	@Override
	public Object nullSafeGet(ResultSet rs, String[] names, SharedSessionContractImplementor session, Object owner)
			throws HibernateException, SQLException {
		final String cellContent = rs.getString(names[0]);
		if(cellContent == null) {
			return null;
		}
		try {
			final ObjectMapper mapper = new ObjectMapper();
			return mapper.readValue(cellContent.getBytes("UTF-8"), returnedClass());
		
		}catch(final Exception ex) {
			throw new RuntimeException("Failed to convert String : " + ex.getMessage(), ex);
		}
		
	}

	@Override
	public void nullSafeSet(PreparedStatement st, Object value, int index, SharedSessionContractImplementor session)
			throws HibernateException, SQLException {
		if(value == null) {
			st.setNull(index, Types.OTHER);
			return;
		}
		try {
			final ObjectMapper mapper = new ObjectMapper();
			final StringWriter w = new StringWriter();
			mapper.writeValue(w, value);
			w.flush();
			st.setObject(index, w.toString(), Types.OTHER);
		} catch (final Exception ex) {
			throw new RuntimeException("Failed to convert to String: " + ex.getMessage(), ex);
		}
		
		
	}

	@Override
	public Object deepCopy(final Object value) throws HibernateException {
		try {
			
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(bos);
			oos.writeObject(value);
			oos.flush();
			oos.close();
			bos.close();
			
			ByteArrayInputStream bais = new ByteArrayInputStream(bos.toByteArray());
			return new ObjectInputStream(bais).readObject();
			
		} catch(ClassNotFoundException | IOException ex) {
			throw new HibernateException(ex);
		}
	}

	@Override
	public boolean isMutable() {
		return true;
	}

	@Override
	public Serializable disassemble(final Object value) throws HibernateException {
		return (Serializable) this.deepCopy(value);
	}

	@Override
	public Object assemble(final Serializable cached, final Object owner) throws HibernateException {
		return this.deepCopy(cached);
	}

	@Override
	public Object replace(final Object original, final Object target, final Object owner) throws HibernateException {
		return this.deepCopy(original);
	}
	
	

}
