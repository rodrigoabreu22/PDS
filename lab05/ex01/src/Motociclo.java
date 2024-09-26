package lab05.ex01.src;

public class Motociclo extends Veiculo {
    protected Make tipo;

    public Motociclo(String matricula, String marca, String modelo, int cilindrada, String tipo) {
        super(matricula, marca, modelo, cilindrada);
        if (isValidMake(tipo)) this.tipo = Make.valueOf(tipo);
        else throw new IllegalArgumentException("Tipo de motociclo inválido");
    }

    public boolean isValidMake(String tipo){
        if (tipo.equals("desportivo") || tipo.equals("estrada")) return true;
        return false;
    }

    public Make getTipo(){
        return this.tipo;
    }

    public void setTipo(Make otipo){
        this.tipo = otipo;
    }

    @Override public String toString(){
        return super.toString() +"; Tipo: " + this.tipo;
    }
}
