package medicalclinic.model;


/**
 * Klasa reprezentuj¹ca ID uprawnieñ w bazie danych
 * ID s¹ sta³e
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
