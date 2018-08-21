package com.g128.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="logger_info")
public class logger {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@Column
	private Timestamp time;
	
	
	@Column
	private String description;
	
	@Column
	private long assocTime;
	@Column
	private long fetchingCount;
	@Column
	private double differentialScore;
//	@ManyToMany(mappedBy="log", fetch=FetchType.LAZY, cascade= {CascadeType.ALL})
//	private List<OffsetRecords> rec;
	
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
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	public long getAssocTime() {
		return assocTime;
	}
	public void setAssocTime(long assocTime) {
		this.assocTime = assocTime;
	}
	public long getFetchingCount() {
		return fetchingCount;
	}
	public void setFetchingCount(long fetchingCount) {
		this.fetchingCount = fetchingCount;
	}
	public double getDifferentialScore() {
		return differentialScore;
	}
	public void setDifferentialScore(double differentialScore) {
		this.differentialScore = differentialScore;
	}
//	public List<OffsetRecords> getRec() {
//		return rec;
//	}
//	public void setRec(List<OffsetRecords> rec) {
//		this.rec = rec;
//	}
//	@Override
//	public String toString() {
//		return "logger [id=" + id + ", description=" + description + ", time=" + time + ", assocTime=" + assocTime
//				+ ", fetchingCount=" + fetchingCount + ", differentialScore=" + differentialScore + ", rec=" + rec
//				+ "]";
//	}
//	@Override
//	public String toString() {
//		return "logger [time=" + time + ", description=" + description + ", assocTime=" + assocTime + ", fetchingCount="
//				+ fetchingCount + ", differentialScore=" + differentialScore + ", rec=" + rec + "]";
//	}
	@Override
	public String toString() {
		return "logger [id=" + id + ", time=" + time + ", description=" + description + ", assocTime=" + assocTime
				+ ", fetchingCount=" + fetchingCount + ", differentialScore=" + differentialScore + "]";
	}
	
	
}
