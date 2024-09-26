package lab05.ex01.src;

public class VehicleFactory {
    public static Veiculo createMotociclo(String matricula, String marca, String modelo, int cilindrada, String tipo){
        return new Motociclo(matricula, marca, modelo, cilindrada, tipo);
    }

    public static Veiculo createAutomovelLigeiro(String matricula, String marca, String modelo, int cilindrada, String numQuadro, int potencia){
        return new AutomovelLigeiro(matricula, marca, modelo, cilindrada, numQuadro, potencia);
    }

    public static Veiculo createTaxi(String matricula, String marca, String modelo, int cilindrada, String numQuadro, int potencia, String licenca){
        return new Taxi(matricula, marca, modelo, cilindrada, numQuadro, potencia, licenca);
    }

    public static Veiculo createPPEletrico(String matricula, String marca, String modelo, int cilindrada, int potencia, String numQuadro, int autonomia, int consumo, int capacidade){
        return new PPEletrico(matricula, marca, modelo, cilindrada, potencia, numQuadro, autonomia, consumo, capacidade);
    }

    public static Veiculo createALEletrico(String matricula, String marca, String modelo, int cilindrada, String numQuadro, int autonomia, int consumo, int capacidade){
        return new ALEletrico(matricula, marca, modelo, cilindrada, numQuadro, autonomia, consumo, capacidade);
    }

    public static Veiculo createPesadoMercadorias(String matricula, String marca, String modelo, int cilindrada, String numQuadro, int carga, int peso){
        return new PesadoMercadorias(matricula, marca, modelo, cilindrada, numQuadro, carga, peso);
    }

    public static Veiculo createPesadoPassageiros(String matricula, String marca, String modelo, int cilindrada, int potencia, String numQuadro, int lotacao){
        return new PesadoPassageiros(matricula, marca, modelo, cilindrada, potencia, numQuadro, lotacao);
    }

    public static Veiculo createVeiculo(String matricula, String marca, String modelo, int cilindrada){
        return new Veiculo(matricula, marca, modelo, cilindrada);
    }


}
