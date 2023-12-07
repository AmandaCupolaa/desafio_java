package school.sptech.componentes;

public abstract class Componente{
    private int id;
    private String nome;
    private double metrica;

    public Componente(String nome) {
        this.nome = nome;
    }
    public abstract boolean isCadastrado();
    public abstract void definirMetrica();
    public abstract void dadosComponente();

    public double getMetrica() {
        return metrica;
    }

    public void setMetrica(double metrica) {
        this.metrica = metrica;
    }

    @Override
    public String toString() {
        return """
                \nComponente - %s
                Métrica - %.2f
                """.formatted(nome, metrica);
    }
}
