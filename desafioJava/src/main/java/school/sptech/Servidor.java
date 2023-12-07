package school.sptech;

import org.springframework.dao.EmptyResultDataAccessException;
import school.sptech.componentes.Componente;
import school.sptech.componentes.CpuController;
import school.sptech.componentes.DiscoController;
import school.sptech.componentes.MemoriaController;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Servidor {
    Scanner leitor;
    private final List<Componente> componentes;
    private final CpuController cpuController;
    private final DiscoController discoController;
    private final MemoriaController memoriaController;

    public Servidor() {
        this.componentes = new ArrayList<>();
        // sla acho que ta errado - todo
        this.cpuController = new CpuController("CPU");
        this.discoController = new DiscoController("Disco");
        this.memoriaController = new MemoriaController("Memória RAM");
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
            case 1 -> {
                try {
                    boolean cpuExiste = cpuController.isCadastrado();

                    if (!cpuExiste) {
                        cpuController.definirMetrica();
                        componentes.add(cpuController);
                    } else {
                        System.out.println("A CPU já está cadastrada.");
                    }
                } catch (EmptyResultDataAccessException e) {
                    System.out.println("Nenhum resultado encontrado.");
                }
            }
            case 2 -> {
                try {
                    boolean discoExiste = discoController.isCadastrado();

                    if (!discoExiste) {
                        discoController.definirMetrica();
                        componentes.add(discoController);
                    } else {
                        System.out.println("O Disco já está cadastrado.");
                    }
                } catch (EmptyResultDataAccessException e) {
                    System.out.println("Nenhum resultado encontrado.");
                }
            }
            case 3 -> {
                try {
                    boolean memoriaExiste = memoriaController.isCadastrado();

                    if (!memoriaExiste) {
                        memoriaController.definirMetrica();
                        componentes.add(memoriaController);
                    } else {
                        System.out.println("A Memória já está cadastrada.");
                    }
                } catch (EmptyResultDataAccessException e) {
                    System.out.println("Nenhum resultado encontrado.");
                }
            }
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
            case 1 -> {
                try {
                    cpuController.removerCPU();
                    componentes.remove(cpuController);
                } catch (EmptyResultDataAccessException e) {
                    System.out.println("Nenhum componente CPU encontrado.");
                }
            }
            case 2 -> {
                try {
                    discoController.removerDisco();
                    componentes.remove(discoController);
                } catch (EmptyResultDataAccessException e) {
                    System.out.println("Nenhum componente Disco encontrado.");
                }
            }
            case 3 -> {
                try {
                    memoriaController.removerMemoria();
                    componentes.remove(memoriaController);
                } catch (EmptyResultDataAccessException e) {
                    System.out.println("Nenhum componente Memória encontrado.");
                }
            }
        }

    }

    public void exibirComponentes() {
        System.out.println("Componentes sendo monitorados: \n" + componentes);
    }

}
