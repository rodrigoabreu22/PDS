package lab05.ex01.src;

public class PesadoMercadorias extends Pesado {
    protected double carga_max;

    public PesadoMercadorias(String matricula, String marca, String modelo, int cilindrada, String quadro, double peso, double carga_max){
        super(matricula, marca, modelo, cilindrada, quadro, peso);
        this.carga_max = carga_max;
    }

    public double getCarga(){
        return this.carga_max;
    }
    public void setCarga(double ocarga_max){
        this.carga_max = ocarga_max;
    }
}
