package lab05.ex01.src;

public class PesadoPassageiros extends Pesado {
    protected int max_passageiros;
    protected int potencia;

    public PesadoPassageiros(String matricula, String marca, String modelo, int cilindrada, int potencia, String quadro, int max_passageiros){
        super(matricula, marca, modelo, cilindrada, quadro, max_passageiros);
        this.max_passageiros = max_passageiros;
    }

    public int getPassageiros(){
        return this.max_passageiros;
    }
    public void setPassageiros(int max_passageiros){
        this.max_passageiros = max_passageiros;
    }
}
