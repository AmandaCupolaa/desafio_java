package school.sptech.componentes;

public abstract class Componente{
    private int id;
    private String nome;
    private double metrica;

    public Componente(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getMetrica() {
        return metrica;
    }

    public void setMetrica(double metrica) {
        this.metrica = metrica;
    }

    @Override
    public String toString() {
        return "Componente{" +
                "nome='" + nome + '\'' +
                ", metrica='" + metrica + '\'' +
                '}';
    }
}
