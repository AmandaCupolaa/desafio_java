package school.sptech;

import java.util.Scanner;

public class TesteMain {
    public static void main(String[] args) {

        Servidor servidor = new Servidor();

        Scanner leitor = new Scanner(System.in);
        int opcaoEscolhida;

        do {
            servidor.menuInicial();
            opcaoEscolhida = leitor.nextInt();

            switch (opcaoEscolhida){
                case 1:
                    servidor.menuAdicionar();
                    break;
                case 2:
                    servidor.menuRemover();
                    break;
                case 3:
                    servidor.exibirComponentes();
                    break;
                case 4:
                    servidor.menuMonitoramento();
                    break;
            }
        } while (opcaoEscolhida != 5);
    }
}
