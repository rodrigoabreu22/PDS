package lab05.ex01.src;

public class PPEletrico extends PesadoPassageiros implements VeiculoEletrico {
    protected int max_autonomia;
    protected int autonomia = max_autonomia;
    
    public PPEletrico(String omatricula, String omarca, String omodelo, int cilindrada, int potencia,String oquadro, int omax_passageiros,int consumo, int oautonomia){
        super(omatricula, omarca, omodelo, cilindrada,potencia, oquadro, omax_passageiros);
        this.max_autonomia = oautonomia;
    }

    public void trajeto(int distância){
        if (autonomia>=distância){
            super.trajeto(distância);
            autonomia -= distância;
        }
    }

    @Override
    public int autonomia() {
        return this.autonomia;
    }

    @Override
    public void carregar(int percentagem) {
        this.autonomia += percentagem*this.max_autonomia/100;
        if (this.autonomia > this.max_autonomia) this.autonomia = this.max_autonomia;
    }
}
