package lab05.ex01.src;

public class Taxi extends AutomovelLigeiro {
    protected String licenca;

    public Taxi(String matricula, String marca, String modelo, int cilindrada, String quadro, int capacidade, String licenca){
        super(matricula, marca, modelo, cilindrada, quadro, capacidade);
        this.licenca = licenca;
    }

    public String getLicenca(){
        return this.licenca;
    }
    public void setLicenca(String licenca){
        this.licenca = licenca;
    }
}
