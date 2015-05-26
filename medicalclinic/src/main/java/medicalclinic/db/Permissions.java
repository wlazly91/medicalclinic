/**
 * 
 */
package medicalclinic.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Klasa reprezentuj¹ca tabelê permissions
 * (uprawnienia)
 * @author £ukasz Kochanek
 * @version 1.0
 */
@Entity
@Table(name= "PERMISSIONS")
public class Permissions implements ObjectDB {

	private static final long serialVersionUID = -7675439514054378439L;

	@Id
	@Column(name = "ID_PERMISSIONS")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="IDPERMISSIONS") 
	@SequenceGenerator(name="IDPERMISSIONS", sequenceName = "IDPERMISSIONS", allocationSize=1)
	private int idPermissions;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "MODIFY_USER")
	private int modifyUser;
	
	@Column(name = "ADD_USER")
	private int addUser;
	
	@Column(name = "DELETE_USER")
	private int deleteUser;
	
	@Column(name = "SAVE_DOCTOR")
	private int saveDoctor;
	
	@Column(name = "SHOW_HISTORY")
	private int showHistory;
	
	@Column(name = "COMMISSIONED_HEALTH")
	private int comHealth;
	
	@Column(name = "SAVE_DRUGS")
	private int saveDrugs;
	
	@Column(name = "ADD_RESULTS")
	private int addResults;
	
	@Column(name = "SHOW_RESULTS_HEALTH")
	private int showResulsHealth;
	
	
	public Permissions() {}
	
	
	public Permissions(String nameN, int modify, int add, int delete, int save_doctor, int show, int commissioned, int save_drugs, int add_result, int show_result) {
		this.name = nameN;
		this.modifyUser = modify;
		this.addUser = add;
		this.deleteUser = delete;
		this.saveDoctor = save_doctor;
		this.showHistory = show;
		this.comHealth = commissioned;
		this.saveDrugs = save_drugs;
		this.addResults = add_result;
		this.showResulsHealth = show_result;
	}
	
	/**
	 * @param addResults the addResults to set
	 */
	public void setAddResults(int addResults) {
		this.addResults = addResults;
	}
	
	/**
	 * @param addUser the addUser to set
	 */
	public void setAddUser(int addUser) {
		this.addUser = addUser;
	}
	
	/**
	 * @param comHealth the comHealth to set
	 */
	public void setComHealth(int comHealth) {
		this.comHealth = comHealth;
	}
	
	/**
	 * @param deleteUser the deleteUser to set
	 */
	public void setDeleteUser(int deleteUser) {
		this.deleteUser = deleteUser;
	}
	
	/**
	 * @param idPermissions the idPermissions to set
	 */
	public void setIdPermissions(int idPermissions) {
		this.idPermissions = idPermissions;
	}
	
	/**
	 * @param modifyUser the modifyUser to set
	 */
	public void setModifyUser(int modifyUser) {
		this.modifyUser = modifyUser;
	}
	
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @param saveDoctor the saveDoctor to set
	 */
	public void setSaveDoctor(int saveDoctor) {
		this.saveDoctor = saveDoctor;
	}
	
	/**
	 * @param saveDrugs the saveDrugs to set
	 */
	public void setSaveDrugs(int saveDrugs) {
		this.saveDrugs = saveDrugs;
	}
	
	/**
	 * @param showHistory the showHistory to set
	 */
	public void setShowHistory(int showHistory) {
		this.showHistory = showHistory;
	}
	
	/**
	 * @param showResulsHealth the showResulsHealth to set
	 */
	public void setShowResulsHealth(int showResulsHealth) {
		this.showResulsHealth = showResulsHealth;
	}
	
	/**
	 * @return the addResults
	 */
	public int getAddResults() {
		return addResults;
	}
	
	/**
	 * @return the addUser
	 */
	public int getAddUser() {
		return addUser;
	}
	
	/**
	 * @return the comHealth
	 */
	public int getComHealth() {
		return comHealth;
	}
	
	/**
	 * @return the deleteUser
	 */
	public int getDeleteUser() {
		return deleteUser;
	}
	
	/**
	 * @return the idPermissions
	 */
	public int getIdPermissions() {
		return idPermissions;
	}
	
	/**
	 * @return the modifyUser
	 */
	public int getModifyUser() {
		return modifyUser;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @return the saveDoctor
	 */
	public int getSaveDoctor() {
		return saveDoctor;
	}
	
	/**
	 * @return the saveDrugs
	 */
	public int getSaveDrugs() {
		return saveDrugs;
	}
	
	/**
	 * @return the showHistory
	 */
	public int getShowHistory() {
		return showHistory;
	}
	
	/**
	 * @return the showResulsHealth
	 */
	public int getShowResulsHealth() {
		return showResulsHealth;
	}
	
}
