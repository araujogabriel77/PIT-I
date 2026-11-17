package br.com.catalogo.controller;

import br.com.catalogo.model.Avaliacao;
import br.com.catalogo.service.ItemMidiaService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/avaliar")
public class AvaliacaoServlet extends HttpServlet {

    private ItemMidiaService service;

    @Override
    public void init() throws ServletException {
        super.init();
        this.service = new ItemMidiaService();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        try {
            int itemMidiaId = Integer.parseInt(request.getParameter("itemMidiaId"));
            int nota = Integer.parseInt(request.getParameter("nota"));
            String comentario = request.getParameter("comentario");

            Avaliacao avaliacao = new Avaliacao(itemMidiaId, nota, comentario, LocalDate.now());
            service.adicionarAvaliacao(avaliacao);

            response.sendRedirect("itens?acao=detalhes&id=" + itemMidiaId);
        } catch (Exception e) {
            request.setAttribute("erro", "Erro ao registrar avaliação: " + e.getMessage());
            response.sendRedirect("itens?acao=listar");
        }
    }
}