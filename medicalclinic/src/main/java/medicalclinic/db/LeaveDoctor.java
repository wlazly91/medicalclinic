/**
 * 
 */
package medicalclinic.db;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Klasa reprezentuj¹ca tabelê Leave_doctor
 * (Urlopy lekarzy)
 * @author £ukasz Kochanek
 * @version 1.0
 */
@Entity
@Table(name = "LEAVE_DOCTOR")
public class LeaveDoctor implements ObjectDB {

	private static final long serialVersionUID = 3969165325192197947L;
	

	@Id
	@Column(name = "ID_LEAVE")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="IDLEAVYDOCTOR") 
	@SequenceGenerator(name="IDLEAVYDOCTOR", sequenceName = "IDLEAVYDOCTOR", allocationSize=1)
	private int idLeave;
	
	@Column(name = "ID_DOCTOR")
	private int idDoctor;
	
	@Column(name = "DATE_FROM")
	private Date dateFrom;
	
	@Column(name = "DATE_TO")
	private Date dateTo;
	
	/**
	 * @param dateFrom the dateFrom to set
	 */
	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}
	
	/**
	 * @param dateTo the dateTo to set
	 */
	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}
	
	/**
	 * @param idDoctor the idDoctor to set
	 */
	public void setIdDoctor(int idDoctor) {
		this.idDoctor = idDoctor;
	}
	
	/**
	 * @param idLeave the idLeave to set
	 */
	public void setIdLeave(int idLeave) {
		this.idLeave = idLeave;
	}
	
	/**
	 * @return the dateFrom
	 */
	public Date getDateFrom() {
		return dateFrom;
	}
	
	/**
	 * @return the dateTo
	 */
	public Date getDateTo() {
		return dateTo;
	}
	
	/**
	 * @return the idDoctor
	 */
	public int getIdDoctor() {
		return idDoctor;
	}
	
	/**
	 * @return the idLeave
	 */
	public int getIdLeave() {
		return idLeave;
	}
	
}
