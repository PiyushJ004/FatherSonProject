package com.example.demo.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

@Entity(name = "Student")
@Table(name = "student_details", uniqueConstraints = {@UniqueConstraint(columnNames = {    })})

public class Student implements Serializable{
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "sName")
	@NotBlank(message = "Please provide student name")
	private String sName;
	
	@Column(name = "sAge")
	@NotEmpty(message = "Please provide age")
	private Long sAge;
	
	@Column(name = "sAddress")
	@NotBlank(message = "Please provide address")
	private String sAddress;
	
	@Column(name = "sSex")
	@NotEmpty(message = "Please provide sex")
	private String sSex;
	
	@Column(name = "sSchool")
	@NotBlank(message = "Please provide school name")
	private String sSchool;
	
	@Column(name = "sDob")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd 'T' HH:mm:ss.SSS 'Z'")
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	private String sDob;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getsName() {
		return sName;
	}

	public void setsName(String sName) {
		this.sName = sName;
	}

	public Long getsAge() {
		return sAge;
	}

	public void setsAge(Long sAge) {
		this.sAge = sAge;
	}

	public String getsAddress() {
		return sAddress;
	}

	public void setsAddress(String sAddress) {
		this.sAddress = sAddress;
	}

	public String getsSex() {
		return sSex;
	}

	public void setsSex(String sSex) {
		this.sSex = sSex;
	}

	public String getsSchool() {
		return sSchool;
	}

	public void setsSchool(String sSchool) {
		this.sSchool = sSchool;
	}

	public String getsDob() {
		return sDob;
	}

	public void setsDob(String sDob) {
		this.sDob = sDob;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((sAddress == null) ? 0 : sAddress.hashCode());
		result = prime * result + ((sAge == null) ? 0 : sAge.hashCode());
		result = prime * result + ((sDob == null) ? 0 : sDob.hashCode());
		result = prime * result + ((sName == null) ? 0 : sName.hashCode());
		result = prime * result + ((sSchool == null) ? 0 : sSchool.hashCode());
		result = prime * result + ((sSex == null) ? 0 : sSex.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (sAddress == null) {
			if (other.sAddress != null)
				return false;
		} else if (!sAddress.equals(other.sAddress))
			return false;
		if (sAge == null) {
			if (other.sAge != null)
				return false;
		} else if (!sAge.equals(other.sAge))
			return false;
		if (sDob == null) {
			if (other.sDob != null)
				return false;
		} else if (!sDob.equals(other.sDob))
			return false;
		if (sName == null) {
			if (other.sName != null)
				return false;
		} else if (!sName.equals(other.sName))
			return false;
		if (sSchool == null) {
			if (other.sSchool != null)
				return false;
		} else if (!sSchool.equals(other.sSchool))
			return false;
		if (sSex == null) {
			if (other.sSex != null)
				return false;
		} else if (!sSex.equals(other.sSex))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", sName=" + sName + ", sAge=" + sAge + ", sAddress=" + sAddress + ", sSex=" + sSex
				+ ", sSchool=" + sSchool + ", sDob=" + sDob + "]";
	}

	public Student(Long id, @NotBlank(message = "Please provide student name") String sName,
			@NotEmpty(message = "Please provide age") Long sAge,
			@NotBlank(message = "Please provide address") String sAddress,
			@NotEmpty(message = "Please provide sex") String sSex,
			@NotBlank(message = "Please provide school name") String sSchool, String sDob) {
		super();
		this.id = id;
		this.sName = sName;
		this.sAge = sAge;
		this.sAddress = sAddress;
		this.sSex = sSex;
		this.sSchool = sSchool;
		this.sDob = sDob;
	}

	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	

}
