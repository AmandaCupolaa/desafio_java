package school.sptech;

import java.util.Scanner;

public class Teste {
    public static void main(String[] args) {

        Servidor servidor = new Servidor();

        Scanner leitor = new Scanner(System.in);
        System.out.println("""
               +-------------------------+
               |      Monitoramento      |
               +-------------------------+
               | 1) Adicionar componente |
               | 2) Remover componente   |
               | 3) Exibir componentes   |
               | 4) Monitorar            |
               +-------------------------+
                """);

        int escolhaOpcoes = leitor.nextInt();

        switch (escolhaOpcoes){
            case 1:
                servidor.adicionarComponente();
                break;
            case 2:
                servidor.removerComponente();
                break;
            case 3:
                servidor.exibirComponentes();
            case 4:

        }
    }
}
