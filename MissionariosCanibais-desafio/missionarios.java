package MissionariosCanibais;

import busca.Estado;
import java.util.ArrayList;
import java.util.List;

public class MissionariosCanibais implements Estado {

    int missionariosEsq;
    int canibaisEsq;
    int barco; // 0 = esquerda, 1 = direita

    public MissionariosCanibais(int m, int c, int b) {
        missionariosEsq = m;
        canibaisEsq = c;
        barco = b;
    }

    // estado objetivo: todos atravessaram para a direita
    public boolean ehMeta() {
        return missionariosEsq == 0 && canibaisEsq == 0 && barco == 1;
    }

    // gera as possíveis travessias do barco
    public List<Estado> sucessores() {

        List<Estado> suc = new ArrayList<>();

        // combinações possíveis de pessoas no barco (máx 2)
        int[][] movimentos = {
            {1,0},
            {2,0},
            {0,1},
            {0,2},
            {1,1}
        };

        for (int[] mov : movimentos) {

            int m = mov[0];
            int c = mov[1];

            MissionariosCanibais novo;

            // se o barco está na esquerda, pessoas atravessam para direita
            if (barco == 0) {
                novo = new MissionariosCanibais(
                        missionariosEsq - m,
                        canibaisEsq - c,
                        1
                );
            } 
            // se está na direita, o barco volta
            else {
                novo = new MissionariosCanibais(
                        missionariosEsq + m,
                        canibaisEsq + c,
                        0
                );
            }

            // adiciona apenas estados válidos
            if (estadoValido(novo)) {
                suc.add(novo);
            }
        }

        return suc;
    }

    // garante que os canibais nunca superem os missionários
    private boolean estadoValido(MissionariosCanibais e) {

        int mEsq = e.missionariosEsq;
        int cEsq = e.canibaisEsq;

        int mDir = 3 - mEsq;
        int cDir = 3 - cEsq;

        if (mEsq < 0 || cEsq < 0 || mEsq > 3 || cEsq > 3)
            return false;

        if (mEsq > 0 && cEsq > mEsq)
            return false;

        if (mDir > 0 && cDir > mDir)
            return false;

        return true;
    }

    public int custo() {
        return 1;
    }

    public String getDescricao() {
        return "(" + missionariosEsq + "M," + canibaisEsq + "C," + (barco == 0 ? "E" : "D") + ")";
    }

    public String toString() {
        return getDescricao();
    }
}
