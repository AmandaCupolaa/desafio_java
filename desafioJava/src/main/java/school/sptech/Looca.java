package school.sptech;

import com.github.britooo.looca.api.group.discos.DiscoGrupo;
import com.github.britooo.looca.api.group.memoria.Memoria;
import com.github.britooo.looca.api.group.processador.Processador;

public class Looca {
    private final Looca looca = new Looca ();
    private final DiscoGrupo grupoDiscos;

    public Looca() {
        this.memoria = looca.getMemoria ();
        this.grupoDiscos = looca.getGrupoDiscos ();
    }

    public Processador getProcessador () {
        return processador;
    }

    public Memoria getMemoria () {
        return memoria;
    }

    public DiscoGrupo getGrupoDiscos () {
        return grupoDiscos;
    }
}
