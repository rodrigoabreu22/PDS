package lab03.ex02.src;

public class Plane {
    private Integer[][] turistica;
    private Integer[][] executiva;
    
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

    public int getNumFilasExecutiva() {
        return executiva.length;
    }

    public Integer[][] getTuristica() {
        return turistica;
    }

    public void setTuristica(Integer[][] turistica) {
        this.turistica = turistica;
    }

    public Integer[][] getExecutiva() {
        return executiva;
    }

    public void setExecutiva(Integer[][] executiva) {
        this.executiva = executiva;
    }

    public int getMaxCapacityTuristica() {
        return turistica.length * turistica[0].length;
    }

    public int getMaxCapacityExecutiva() {
        // não há classe executiva
        if (executiva == null || executiva.length == 0) {
            return 0;
        }
        return executiva.length * executiva[0].length;
    }

    public int getOcupatedSeatsTuristica() {
        int ocupatedSeats = 0;

        for (int i = 0; i < turistica.length; i++) {
            for (int j = 0; j < turistica[i].length; j++) {
                if (turistica[i][j] != 0) {
                    ocupatedSeats++;
                }
            }
        }
        return ocupatedSeats;
    }

    public int getOcupatedSeatsExecutiva() {
        int ocupatedSeats = 0;

        for (int i = 0; i < executiva.length; i++) {
            for (int j = 0; j < executiva[i].length; j++) {
                if (executiva[i][j] != 0) {
                    ocupatedSeats++;
                }
            }
        }
        return ocupatedSeats;
    }

    public int getFreeSeatsTuristica() {
        return getMaxCapacityTuristica() - getOcupatedSeatsTuristica();
    }

    public int getFreeSeatsExecutiva() {
        return getMaxCapacityExecutiva() - getOcupatedSeatsExecutiva();
    }

    @Override
    public String toString() {
        String info = "Lugares disponíveis: ";

        // verificar se exite classe executiva
        if (getMaxCapacityExecutiva() == 0) {
            info += getFreeSeatsTuristica() + " lugares em classe Turística.\nClasse executiva não disponível neste voo.";
        }
        else {
            info += getMaxCapacityExecutiva() + " lugares em classe Executiva; " + getMaxCapacityTuristica() + " lugares em classe Turística.";
        }
        return info;
    }
}
