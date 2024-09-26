package lab05.ex01.src;

public class Veiculo implements KmPercorridosInterface {
    protected String matricula;
    protected String marca;
    protected String modelo;
    protected int cilindrada;
    protected int ultimoTrajeto;
    protected int distanciaTotal;

    @Override
    public void trajeto(int quilometros) {
        this.ultimoTrajeto = quilometros;
        this.distanciaTotal += ultimoTrajeto;
    }
    @Override
    public int ultimoTrajeto() {
        return this.ultimoTrajeto;
    }
    @Override
    public int distanciaTotal() {
        return this.distanciaTotal;
    }

    public Veiculo(String matricula, String marca, String modelo,int cilindrada){
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.cilindrada = cilindrada;
    }

    public String getMatricula(){
        return this.matricula;
    }
    public String getMarca(){
        return this.marca;
    }
    public String getModelo(){
        return this.modelo;
    }
    public int getPotencia(){
        return this.cilindrada;
    }

    public void setMatricula(String omatricula){
        this.matricula = omatricula;
    }
    public void setMarca(String omarca){
        this.marca = omarca;
    }
    public void setModelo(String omodelo){
        this.modelo = omodelo;
    }
    public void setPotencia(int cilindrada){
        this.cilindrada = cilindrada;
    }

    public String toString(){
        return "Matrícula: "+this.matricula+"; Marca: "+this.marca+"; Modelo: "+this.modelo+"; Potencia: "+this.cilindrada+"; Distancia Percorrida: "+this.distanciaTotal;
    }

    
}
