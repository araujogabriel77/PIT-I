package br.com.catalogo.util;

import br.com.catalogo.model.ItemMidia;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ValidadorUtil {

    public static List<String> validarCamposObrigatorios(ItemMidia item) {
        List<String> erros = new ArrayList<>();

        if (item.getTitulo() == null || item.getTitulo().trim().isEmpty()) {
            erros.add("O campo Título é obrigatório.");
        }
        if (item.getTipoMidia() == null || item.getTipoMidia().trim().isEmpty()) {
            erros.add("O campo Tipo de Mídia é obrigatório.");
        }
        if (!validarAno(item.getAnoLancamento())) {
            erros.add("O Ano de Lançamento é inválido.");
        }

        return erros;
    }

    public static boolean validarAno(int ano) {
        int anoAtual = LocalDate.now().getYear();
        return ano > 1800 && ano <= (anoAtual + 1);
    }

    public static boolean validarNota(int nota) {
        return nota >= 1 && nota <= 5;
    }
}