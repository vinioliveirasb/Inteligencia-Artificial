
package MissionariosCanibais;

import busca.*;

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

    // estado objetivo
    public boolean ehMeta() {
        return missionariosEsq == 0 && canibaisEsq == 0 && barco == 1;
    }

    // gera sucessores
    public List<Estado> sucessores() {

        List<Estado> suc = new ArrayList<>();

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

            if (barco == 0) { // esquerda -> direita
                novo = new MissionariosCanibais(
                        missionariosEsq - m,
                        canibaisEsq - c,
                        1
                );
            } else { // direita -> esquerda
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

    public String toString() {
        return "(" + missionariosEsq + "M," + canibaisEsq + "C," + (barco == 0 ? "E" : "D") + ")";
    }

    @Override
    public String getDescricao() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
