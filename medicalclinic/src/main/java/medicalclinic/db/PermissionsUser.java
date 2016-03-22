package medicalclinic.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "PERMISSIONS_USER")
public class PermissionsUser implements ObjectDB {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID_PER_USR")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="IDUSERPER") 
	@SequenceGenerator(name="IDUSERPER", sequenceName = "IDUSERPER", allocationSize=1)
	private Integer idPerUser;

	@ManyToOne
    @JoinColumn(name = "ID_PER")
    private Permissions perm;
	
	@ManyToOne
    @JoinColumn(name="ID_USER")
    private Users usr;
	
	public PermissionsUser() {}
	
	public PermissionsUser(Integer id, Users usr, Permissions perm) {
		this.idPerUser = id;
		this.perm = perm;
		this.usr = usr;
	}
	
	public void setPerm(Permissions perm) {
		this.perm = perm;
	}
	
	public void setIdPerUser(Integer idPerUser) {
		this.idPerUser = idPerUser;
	}
	
	public void setUsr(Users usr) {
		this.usr = usr;
	}
	
	public Users getUsr() {
		return usr;
	}
		
	public Permissions getPerm() {
		return perm;
	}
	
	public Integer getIdPerUser() {
		return idPerUser;
	}
		
}

