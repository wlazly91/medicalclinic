package medicalclinic.model;

public enum Permission {
	ADMIN(1), USER(2), NURSE(3), DOCTOR(4);
	
    private int idPermission;

    Permission(int rozmiar) {
        this.idPermission = rozmiar;
    }

    public int getIdPermision() {
        return idPermission;
    }
}
