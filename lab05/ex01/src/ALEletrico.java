package lab05.ex01.src;

public class ALEletrico extends AutomovelLigeiro implements VeiculoEletrico {
    protected int max_autonomia;
    protected int autonomia = max_autonomia;
    
    public ALEletrico(String omatricula, String omarca, String omodelo,int opotencia, String oquadro, int ocapacidade, int oautonomia, int oconsumo){
        super(omatricula, omarca, omodelo, opotencia, oquadro, ocapacidade);
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
