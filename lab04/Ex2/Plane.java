package lab04.Ex2;

public class Plane {
    private Integer[][] turistica;
    private Integer[][] executiva;
    
    /**
     * Plane constructor
     * @param turistica
     * @param executiva
     */
    public Plane(Integer[][] turistica, Integer[][] executiva) {
        this.turistica = turistica;
        this.executiva = executiva;

        for (int i = 0; i < turistica.length; i++) {
            for (int j = 0; j < turistica[i].length; j++) {
                if (turistica[i][j] == null) turistica[i][j] = 0;
            }
        }

        for (int i = 0; i < executiva.length; i++) {
            for (int j = 0; j < executiva[i].length; j++) {
                if (executiva[i][j] == null) executiva[i][j] = 0;
            }
        }
    }

    /**
     * Get number of rows in the executive class
     * @return number of rows in the executive class
     */
    public int getNumFilasExecutiva() {
        return executiva.length;
    }

    /**
     * Get number of rows in the turistic class
     * @return number of rows in the turistic class
     */
    public int getNumFilasTuristica() {
        return turistica.length;
    }

    /**
     * Get the turistic class
     * @return the turistic class
     */
    public Integer[][] getTuristica() {
        return turistica;
    }

    /**
     * Set the turistic class
     * @param turistica
     */
    public void setTuristica(Integer[][] turistica) {
        this.turistica = turistica;
    }

    /**
     * Get the executive class
     * @return the executive class
     */
    public Integer[][] getExecutiva() {
        return executiva;
    }

    /**
     * Set the executive class
     * @param executiva
     */
    public void setExecutiva(Integer[][] executiva) {
        this.executiva = executiva;
    }

    /**
     * Get the maximum capacity of the turistic class
     * @return the maximum capacity of the turistic class
     */
    public int getMaxCapacityTuristica() {
        return turistica.length * turistica[0].length;
    }

    /**
     * Get the maximum capacity of the executive class
     * @return the maximum capacity of the executive class
     */
    public int getMaxCapacityExecutiva() {
        // no executive class
        if (executiva == null || executiva.length == 0) {
            return 0;
        }
        return executiva.length * executiva[0].length;
    }

    @Override
    public String toString() {
        String info = "Lugares disponíveis: ";

        // verificar se exite classe executiva
        if (getMaxCapacityExecutiva() == 0) {
            info += getMaxCapacityTuristica() + " lugares em classe Turística.\nClasse executiva não disponível neste voo.";
        }
        else {
            info += getMaxCapacityExecutiva() + " lugares em classe Executiva; " + getMaxCapacityTuristica() + " lugares em classe Turística.";
        }
        return info;
    }
}
