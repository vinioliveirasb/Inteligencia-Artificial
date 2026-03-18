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

    // verifica se chegou no objetivo
    @Override
    public boolean ehMeta() {
        return missionariosEsq == 0 && canibaisEsq == 0 && barco == 1;
    }

    // gera os próximos estados possíveis
    @Override
    public List<Estado> sucessores() {

        List<Estado> suc = new ArrayList<>();

        int[][] movimentos = {
            {1,0}, // 1 missionário
            {2,0}, // 2 missionários
            {0,1}, // 1 canibal
            {0,2}, // 2 canibais
            {1,1}  // 1 missionário + 1 canibal
        };

        for (int[] mov : movimentos) {

            int m = mov[0];
            int c = mov[1];

            MissionariosCanibais novo;

            if (barco == 0) { // barco na esquerda
                novo = new MissionariosCanibais(
                        missionariosEsq - m,
                        canibaisEsq - c,
                        1
                );
            } else { // barco na direita
                novo = new MissionariosCanibais(
                        missionariosEsq + m,
                        canibaisEsq + c,
                        0
                );
            }

            if (estadoValido(novo)) {
                suc.add(novo);
            }
        }

        return suc;
    }

    // verifica se o estado é válido
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

    // custo de cada ação
    @Override
    public int custo() {
        return 1;
    }

    // descrição do estado (necessário pro buscaJava)
    @Override
    public String getDescricao() {
        return "(" + missionariosEsq + "M," + canibaisEsq + "C," + (barco == 0 ? "E" : "D") + ")";
    }

    @Override
    public String toString() {
        return getDescricao();
    }
}
