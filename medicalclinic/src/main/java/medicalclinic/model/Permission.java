package medicalclinic.model;


/**
 * Klasa reprezentuj�ca ID uprawnie� w bazie danych
 * ID s� sta�e
 * */
public enum Permission {
	ADMIN(1), USER(2), NURSE(3), DOCTOR(4);
	
    private int idPermission;

    Permission(int rozmiar) {
        this.idPermission = rozmiar;
    }

    /**
     * Zwraca ID podanego uprawnienia
     * */
    public int getIdPermision() {
        return idPermission;
    }
}
