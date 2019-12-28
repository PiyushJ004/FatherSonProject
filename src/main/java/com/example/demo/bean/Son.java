package com.example.demo.bean;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import io.swagger.annotations.ApiModelProperty;

@Entity(name = "Son")
@Table(name = "Son_Data", uniqueConstraints = {@UniqueConstraint(columnNames = {"sex", "s_dob"})})

public class Son implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "son_name", updatable = true)
	@NotBlank(message = "Please provide son name")
	@Size(min = 5, max = 10)
	@ApiModelProperty(notes = "For son name")
	private String sonName;
	
	@Column(name = "age", updatable = true)
	@NotBlank(message = "Please provide age")
	@ApiModelProperty(notes = "For son age")
	private Long age;
	
	@Column(name = "sex", updatable = true)
	@NotBlank(message = "Please provide your sex")
	@ApiModelProperty(notes = "For son sex identity")
	private String sex;
	
	@Column(name = "s_dob", updatable = true)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@ApiModelProperty(notes = "For Son DOB")
	private LocalDateTime sDob;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSonName() {
		return sonName;
	}

	public void setSonName(String sonName) {
		this.sonName = sonName;
	}

	public Long getAge() {
		return age;
	}

	public void setAge(Long age) {
		this.age = age;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public LocalDateTime getsDob() {
		return sDob;
	}

	public void setsDob(LocalDateTime sDob) {
		this.sDob = sDob;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((age == null) ? 0 : age.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((sDob == null) ? 0 : sDob.hashCode());
		result = prime * result + ((sex == null) ? 0 : sex.hashCode());
		result = prime * result + ((sonName == null) ? 0 : sonName.hashCode());
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
		Son other = (Son) obj;
		if (age == null) {
			if (other.age != null)
				return false;
		} else if (!age.equals(other.age))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (sDob == null) {
			if (other.sDob != null)
				return false;
		} else if (!sDob.equals(other.sDob))
			return false;
		if (sex == null) {
			if (other.sex != null)
				return false;
		} else if (!sex.equals(other.sex))
			return false;
		if (sonName == null) {
			if (other.sonName != null)
				return false;
		} else if (!sonName.equals(other.sonName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Son [id=" + id + ", sonName=" + sonName + ", age=" + age + ", sex=" + sex + ", sDob=" + sDob + "]";
	}

	public Son(Long id, @NotBlank(message = "Please provide son name") @Size(min = 5, max = 10) String sonName,
			@NotBlank(message = "Please provide age") Long age,
			@NotBlank(message = "Please provide your sex") String sex, LocalDateTime sDob) {
		super();
		this.id = id;
		this.sonName = sonName;
		this.age = age;
		this.sex = sex;
		this.sDob = sDob;
	}

	public Son() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
