package com.g128.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="log_offset")
public class OffsetRecords {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@Column
	private Timestamp timeIn;
	
	@Column
	private String description;
	@Column
	private long value;
	
//	@ManyToMany(fetch=FetchType.LAZY, cascade= {CascadeType.ALL})
//	@JoinTable(
//	        name="logger_record", 
//	        joinColumns={@JoinColumn(name="log_time", referencedColumnName="time")}, 
//	        inverseJoinColumns={@JoinColumn(name="record_time", referencedColumnName="timeIn") }
//	)
//	private List<logger> log;
	
	
//	public List<logger> getLog() {
//		return log;
//	}
//	public void setLog(List<logger> log) {
//		this.log = log;
//	}
	public Timestamp getTimeIn() {
		return timeIn;
	}
	public void setTimeIn(Timestamp timeIn) {
		this.timeIn = timeIn;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public long getValue() {
		return value;
	}
	public void setValue(long value) {
		this.value = value;
	}
//	@Override
//	public String toString() {
//		return "OffsetRecords [timeIn=" + timeIn + ", description=" + description + ", value=" + value + ", log=" + log
//				+ "]";
//	}
	@Override
	public String toString() {
		return "OffsetRecords [id=" + id + ", timeIn=" + timeIn + ", description=" + description + ", value=" + value
				+ "]";
	}
	
//	@Override
//	public String toString() {
//		return "OffsetRecords [id=" + id + ", description=" + description + ", value=" + value + ", log=" + log + "]";
//	}
	
	
}
