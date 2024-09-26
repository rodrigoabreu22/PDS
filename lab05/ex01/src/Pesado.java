package lab05.ex01.src;

public class Pesado extends Veiculo {
    protected String quadro;
    protected double peso;

    public Pesado(String omatricula, String omarca, String omodelo, int cilindrada, String oquadro, double opeso){
        super(omatricula, omarca, omodelo, cilindrada);
        this.quadro = oquadro;
        this.peso = opeso;
    }

    public String getQuadro(){
        return this.quadro;
    }
    public double getPeso(){
        return this.peso;
    }

    public void setQuadro(String oquadro){
        this.quadro = oquadro;
    }
    public void setPeso(double opeso){
        this.peso = opeso;
    }

    @Override public String toString(){
        return super.toString() + "; Numero de Quadro: "+this.quadro+"; Peso: "+this.peso;
    }
}
