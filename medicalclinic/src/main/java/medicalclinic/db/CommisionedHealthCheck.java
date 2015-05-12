/**
 * 
 */
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

/**
 * Klasa reprezentuje zlecone badania
 * @author £ukasz Kochanek
 * @version 1.0
 */
@Entity
@Table(name = "COMMISSIONED_HELATH_CHECK")
public class CommisionedHealthCheck implements ObjectDB
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1631817585225939373L;

	@Id
	@Column(name = "ID_CHC")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="IDCOMMISIONED_HEALTH_CHECK") 
	@SequenceGenerator(name="IDCOMMISIONED_HEALTH_CHECK", sequenceName = "IDCOMMISIONED_HEALTH_CHECK", allocationSize=1)
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "ID_HEALTH")
	private HealthCheck idHealth;
	
	@ManyToOne
	@JoinColumn(name = "ID_VISIT")
	private Visits idVisit;
	
	@ManyToOne
	@JoinColumn(name = "ID_PATIENT")
	private Patient idPatient;
	
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * @param idHealth the idHealth to set
	 */
	public void setIdHealth(HealthCheck idHealth) {
		this.idHealth = idHealth;
	}
	
	/**
	 * @param idPatient the idPatient to set
	 */
	public void setIdPatient(Patient idPatient) {
		this.idPatient = idPatient;
	}
	
	/**
	 * @param idVisit the idVisit to set
	 */
	public void setIdVisit(Visits idVisit) {
		this.idVisit = idVisit;
	}
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * @return the idHealth
	 */
	public HealthCheck getIdHealth() {
		return idHealth;
	}
	
	/**
	 * @return the idPatient
	 */
	public Patient getIdPatient() {
		return idPatient;
	}
	
	/**
	 * @return the idVisit
	 */
	public Visits getIdVisit() {
		return idVisit;
	}
}
