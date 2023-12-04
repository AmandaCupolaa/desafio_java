package school.sptech;

import com.github.britooo.looca.api.group.discos.DiscoGrupo;
import com.github.britooo.looca.api.group.memoria.Memoria;
import com.github.britooo.looca.api.group.processador.Processador;
import com.github.britooo.looca.api.group.processos.ProcessoGrupo;
import com.github.britooo.looca.api.group.rede.Rede;
import com.github.britooo.looca.api.group.sistema.Sistema;
import com.github.britooo.looca.api.group.temperatura.Temperatura;
import oshi.SystemInfo;

public class Looca {

    private final Processador processador;
    private final Memoria memoria;
    private final DiscoGrupo disco;

    public Looca() {
        this.processador = new Processador();
        this.memoria = new Memoria();
        this.disco = new DiscoGrupo();
    }

    public Processador getProcessador() {
        return processador;
    }

    public Memoria getMemoria() {
        return memoria;
    }

    public DiscoGrupo getDisco() {
        return disco;
    }
}
