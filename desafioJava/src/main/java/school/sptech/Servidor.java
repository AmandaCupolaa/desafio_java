package school.sptech;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import school.sptech.bancoDeDados.Conexao;
import school.sptech.componentes.Componente;
import school.sptech.componentes.CpuController;
import school.sptech.componentes.DiscoController;
import school.sptech.componentes.MemoriaController;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Servidor {
    Scanner leitor;
    private List<Componente> componentes;
    private CpuController cpuController;
    private DiscoController discoController;
    private MemoriaController memoriaController;

    public Servidor() {
        this.componentes = new ArrayList<>();
        this.cpuController = new CpuController("CPU");
        this.discoController = new DiscoController("Disco");
        this.memoriaController = new MemoriaController("Memória");
        this.leitor = new Scanner(System.in);
    }

    public void menuInicial() {
        System.out.println("""
               +-----------------------------------------+
               |      Olá! Escolha a opção desejada      |
               +-----------------------------------------+
               |    1) Adicionar componente              |
               |    2) Remover componente                |
               |    3) Exibir componentes                |
               |    4) Monitorar componentes             |
               |    5) Sair                              |
               +-----------------------------------------+
                """);
    }

    public void menuAdicionar() {
        System.out.println("""
                +---------------------------------------------+
                |    Escolha o componente a ser adicionado    |
                +---------------------------------------------+
                |  1) CPU                                     |
                |  2) Disco                                   |
                   3) Memória                                 |
                +---------------------------------------------+
                 """);

        int componenteEscolhido = leitor.nextInt();

        switch (componenteEscolhido) {
            case 1:
                try {
                    boolean cpuExiste  = cpuController.verificarCPU();

                    if (!cpuExiste) {
                        cpuController.dadosCPU();
                        componentes.add(cpuController);
                    } else {
                        System.out.println("A CPU já está cadastrada.");
                    }
                } catch (EmptyResultDataAccessException e) {
                    System.out.println("Nenhum resultado encontrado.");
                }

                break;

            case 2:
                try {
                    boolean discoExiste  = discoController.verificarDisco();

                    if (!discoExiste) {
                        discoController.metricaDisco();
                        componentes.add(discoController);
                    } else {
                        System.out.println("O Disco já está cadastrado.");
                    }
                } catch (EmptyResultDataAccessException e) {
                    System.out.println("Nenhum resultado encontrado.");
                }

                break;

            case 3:
                try {
                    boolean memoriaExiste  = memoriaController.verificarMemoria();

                    if (!memoriaExiste) {
                        memoriaController.metricaMemoria();
                        componentes.add(memoriaController);
                    } else {
                        System.out.println("A Memória já está cadastrada.");
                    }
                } catch (EmptyResultDataAccessException e) {
                    System.out.println("Nenhum resultado encontrado.");
                }

                break;

        }
    }

    public void menuRemover() {
        System.out.println("""
                +-----------------------------------------------+
                |      Escolha o componente a ser removido      |
                +-----------------------------------------------+
                |   1) CPU                                      |
                |   2) Disco                                    |
                |   3) Memória                                  |
                +-----------------------------------------------+
                 """);

        int componenteEscolhido = leitor.nextInt();

        switch (componenteEscolhido) {
            case 1:
                try {
                    cpuController.removerCPU();
                    componentes.remove(cpuController);
                } catch (EmptyResultDataAccessException e) {
                    System.out.println("Nenhum componente CPU encontrado.");
                }
                break;

            case 2:
                try {
                    discoController.removerDisco();
                    componentes.remove(discoController);
                } catch (EmptyResultDataAccessException e) {
                    System.out.println("Nenhum componente Disco encontrado.");
                }
                break;
            case 3:
                try {
                    memoriaController.removerMemoria();
                    componentes.remove(memoriaController);
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

    public List<Componente> getComponentes() {
        return componentes;
    }

    public void setComponentes(List<Componente> componentes) {
        this.componentes = componentes;
    }
}
