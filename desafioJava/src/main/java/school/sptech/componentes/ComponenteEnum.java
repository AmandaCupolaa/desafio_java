package school.sptech.componentes;

public enum ComponenteEnum {

    CPU("CPU"),
    DISCO("Disco"),
    MEMORIA("Memoria");
    private String nome;
    private double metrica;


    ComponenteEnum(String nome) {
        this.nome = nome;
        this.metrica = metrica;
    }

    public double getMetrica() {
        return metrica;
    }

    public void setMetrica(double metrica) {
        this.metrica = metrica;
    }

    @Override
    public String toString() {
        return "ComponenteEnum{" +
                "nome='" + nome + '\'' +
                ", metrica=" + metrica +
                "} " + super.toString();
    }
}
