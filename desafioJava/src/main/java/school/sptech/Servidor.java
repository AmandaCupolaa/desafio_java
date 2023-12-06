package school.sptech;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import school.sptech.bancoDeDados.Conexao;
import school.sptech.componentes.Componente;
import school.sptech.componentes.Cpu;
import school.sptech.componentes.Disco;
import school.sptech.componentes.Memoria;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Servidor {
    Conexao conexao = new Conexao();
    JdbcTemplate con = conexao.getConexaoDoBanco();
    private List<Componente> componentes;

    public Servidor() {
        this.componentes = new ArrayList<>();
    }

    public List<Componente> getComponentes() {
        return componentes;
    }

    public void setComponentes(List<Componente> componentes) {
        this.componentes = componentes;
    }

    public void adicionarComponente() {

        Scanner leitor = new Scanner(System.in);

        System.out.println("""
                +---------------------------------------------+
                |    Escolha o componente a ser adicionado    |
                +---------------------------------------------+
                |    1) CPU                                   |
                |    2) Disco                                 |
                |    3) Memória                               |
                +---------------------------------------------+
                 """);

        int escolha = leitor.nextInt();

        System.out.println("""
                +-------------------------------------------------+
                |      Defina a métrica para esse componente      |
                +-------------------------------------------------+
                 """);

        double metricaComponente = leitor.nextInt();

        switch (escolha) {
            case 1:
                try {

                    List<Integer> idCpus = con.queryForList("SELECT idComponente FROM Componente WHERE nomeComponente = 'Cpu'", Integer.class);

                    if (!idCpus.isEmpty()) {
                        System.out.println("Componente Cpu já existe no banco de dados.");
                    } else {

                        Cpu cpu = new Cpu("Cpu", metricaComponente, "%");

                        con.update("INSERT INTO Metrica (limiteMaximo) VALUES (?)", cpu.getLimiteMaximo());
                        Integer idMetrica = con.queryForObject("SELECT idMetrica FROM Metrica ORDER BY idMetrica DESC LIMIT 1", Integer.class);
                        con.update("INSERT INTO Componente (nomeComponente, unidadeMedida, fkMetrica) VALUES (?,?,?)", cpu.getNome(), cpu.getMedida(), idMetrica);

                        System.out.println(cpu + " adicionado com sucesso!!!");
                        componentes.add(cpu);
                    }
                } catch (EmptyResultDataAccessException e) {
                    System.out.println("Nenhum resultado encontrado.");
                }

                break;

            case 2:

                try {

                    List<Integer> idCpus = con.queryForList("SELECT idComponente FROM Componente WHERE nomeComponente = 'Disco'", Integer.class);

                    if (!idCpus.isEmpty()) {
                        System.out.println("Componente Cpu já existe no banco de dados.");
                    } else {

                        Disco disco = new Disco("Disco", 20.0, "%");

                        con.update("INSERT INTO Metrica (limiteMaximo) VALUES (?)", disco.getLimiteMaximo());
                        Integer idMetrica = con.queryForObject("SELECT idMetrica FROM Metrica ORDER BY idMetrica DESC LIMIT 1", Integer.class);
                        con.update("INSERT INTO Componente (nomeComponente, unidadeMedida, fkMetrica) VALUES (?,?,?)", disco.getNome(), disco.getMedida(), idMetrica);

                        System.out.println(disco + "adicionado com sucesso !!!");
                        componentes.add(disco);
                    }
                } catch (EmptyResultDataAccessException e) {
                    System.out.println("Nenhum resultado encontrado.");
                }

                break;
            case 3:
                try {

                    List<Integer> idCpus = con.queryForList("SELECT idComponente FROM Componente WHERE nomeComponente = 'Memória'", Integer.class);

                    if (!idCpus.isEmpty()) {
                        System.out.println("Componente Cpu já existe no banco de dados.");
                    } else {

                        Memoria memoria = new Memoria("Memória", 20.0, "%");

                        con.update("INSERT INTO Metrica (limiteMaximo) VALUES (?)", memoria.getLimiteMaximo());
                        Integer idMetrica = con.queryForObject("SELECT idMetrica FROM Metrica ORDER BY idMetrica DESC LIMIT 1", Integer.class);

                        con.update("INSERT INTO Componente (nomeComponente, unidadeMedida,fkMetrica ) VALUES (?,?,?)", memoria.getNome(), memoria.getMedida(), idMetrica);
                        System.out.println(memoria + "adicionado com sucesso !!!");
                        componentes.add(memoria);
                    }
                } catch (EmptyResultDataAccessException e) {
                    System.out.println("Nenhum resultado encontrado.");
                }

                break;
        }

    }

    public void removerComponente() {

        Scanner leitor = new Scanner(System.in);
        System.out.println("""
                +-------------------------+
                |      Monitoramento      |
                +-------------------------+
                | 1) CPU                  |
                | 2) Disco                |
                | 3) Memória              |
                +-------------------------+
                 """);

        int escolha = leitor.nextInt();

        switch (escolha) {
            case 1:
                try {
                    int count = con.queryForObject("SELECT COUNT(*) FROM Componente WHERE nomeComponente = 'Cpu'", Integer.class);

                    if (count == 0) {
                        System.out.println("Nenhum componente Cpu encontrado.");
                    } else {
                        con.update("DELETE FROM Componente WHERE nomeComponente = 'Cpu'");
                        System.out.println("Componente Cpu removido com sucesso.");

                        for (Componente componente: componentes){
                            if (componente.getNome().equalsIgnoreCase("Cpu")){
                                 componentes.remove(componente);
                            }
                        }

                    }
                } catch (EmptyResultDataAccessException e) {
                    System.out.println("Nenhum componente Cpu encontrado.");
                }
                break;

            case 2:
                try {
                    int count = con.queryForObject("SELECT COUNT(*) FROM Componente WHERE nomeComponente = 'Disco'", Integer.class);

                    if (count == 0) {
                        System.out.println("Nenhum componente Disco encontrado.");
                    } else {
                        con.update("DELETE FROM Componente WHERE nomeComponente = 'Disco'");
                        System.out.println("Componente Disco removido com sucesso.");

                        for (Componente componente: componentes){
                            if (componente.getNome().equalsIgnoreCase("Cpu")){
                                componentes.remove(componente);
                            }
                        }
                    }
                } catch (EmptyResultDataAccessException e) {
                    System.out.println("Nenhum componente Disco encontrado.");
                }
                break;
            case 3:
                try {
                    int count = con.queryForObject("SELECT COUNT(*) FROM Componente WHERE nomeComponente = 'Memória'", Integer.class);

                    if (count == 0) {
                        System.out.println("Nenhum componente Memória encontrado.");
                    } else {
                        con.update("DELETE FROM Componente WHERE nomeComponente = 'Memória'");
                        System.out.println("Componente Memória removido com sucesso.");

                        for (Componente componente: componentes){
                            if (componente.getNome().equalsIgnoreCase("Cpu")){
                                componentes.remove(componente);
                            }
                        }
                    }
                } catch (EmptyResultDataAccessException e) {
                    System.out.println("Nenhum componente Memória encontrado.");
                }
                break;
        }

    }

    public List<Componente> exibirComponentes() {
        System.out.println("Componentes sendo monitorados: " + componentes);
        return componentes;
    }

}
