package springMVC.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "DEP")
public class DepNode {
	private String id;
	private String pId;
	private String name;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Column(name = "PID", nullable = true, length = 500)
	public void setpId(String pId) {
		this.pId = pId;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name = "NAME", nullable = true, length = 500)
	public String getName() {
		return name;
	}
	public String getpId() {
		return pId;
	}
	
}
