package lab05.ex01.src;

public class AutomovelLigeiro extends Veiculo {
    protected String quadro;
    protected int capacidade;

    public AutomovelLigeiro(String matricula, String marca, String modelo, int cilindrada, String num_quadro, int capacidade){
        super(matricula, marca, modelo, cilindrada);
        this.quadro = num_quadro;
        this.capacidade = capacidade;
    }
    
    public String getQuadro(){
        return this.quadro;
    }
    public double getCapacidade(){
        return this.capacidade;
    }

    public void setQuadro(String quadro){
        this.quadro = quadro;
    }
    public void setCapacidade(int ocapacidade){
        this.capacidade = ocapacidade;
    }

    @Override public String toString(){
        return super.toString() + "; Numero do Quadro: "+this.quadro+"; Capacidade de Passageiros: "+this.capacidade;
    }
}
