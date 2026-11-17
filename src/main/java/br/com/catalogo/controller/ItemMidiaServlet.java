package br.com.catalogo.controller;

import br.com.catalogo.model.ItemMidia;
import br.com.catalogo.service.ItemMidiaService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/itens")
public class ItemMidiaServlet extends HttpServlet {

    private ItemMidiaService service;

    @Override
    public void init() throws ServletException {
        super.init();
        this.service = new ItemMidiaService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String acao = request.getParameter("acao");
        if (acao == null) {
            acao = "listar";
        }

        try {
            switch (acao) {
                case "novo":
                    // Exibe formulário vazio para cadastro
                    request.getRequestDispatcher("/WEB-INF/views/formulario.jsp").forward(request, response);
                    break;
                case "editar":
                    // Busca o item pelo ID e abre o formulário preenchido
                    int idEditar = Integer.parseInt(request.getParameter("id"));
                    ItemMidia itemParaEditar = service.buscarPorId(idEditar);
                    request.setAttribute("item", itemParaEditar);
                    request.getRequestDispatcher("/WEB-INF/views/formulario.jsp").forward(request, response);
                    break;
                case "excluir":
                    // Exclui o item e redireciona para a listagem
                    int idExcluir = Integer.parseInt(request.getParameter("id"));
                    service.excluir(idExcluir);
                    response.sendRedirect("itens?acao=listar");
                    break;
                case "detalhes":
                    // Mostra detalhes e avaliações do item
                    int idDetalhes = Integer.parseInt(request.getParameter("id"));
                    ItemMidia itemDetalhes = service.buscarPorId(idDetalhes);
                    request.setAttribute("item", itemDetalhes);
                    request.setAttribute("avaliacoes", service.listarAvaliacoes(idDetalhes));
                    request.setAttribute("mediaAvaliacoes", service.calcularMediaAvaliacoes(idDetalhes));
                    request.getRequestDispatcher("/WEB-INF/views/detalhes.jsp").forward(request, response);
                    break;
                case "listar":
                default:
                    // Lista todos os itens ou busca por termo se houver pesquisa
                    String busca = request.getParameter("busca");
                    List<ItemMidia> lista = service.buscar(busca);
                    request.setAttribute("itens", lista);
                    request.getRequestDispatcher("/WEB-INF/views/lista.jsp").forward(request, response);
                    break;
            }
        } catch (Exception e) {
            request.setAttribute("erro", e.getMessage());
            request.getRequestDispatcher("/WEB-INF/views/lista.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String idStr = request.getParameter("id");
        String titulo = request.getParameter("titulo");
        String autorDiretor = request.getParameter("autorDiretor");
        int anoLancamento = request.getParameter("anoLancamento") != null && !request.getParameter("anoLancamento").isEmpty()
                ? Integer.parseInt(request.getParameter("anoLancamento")) : 0;
        String genero = request.getParameter("genero");
        String sinopse = request.getParameter("sinopse");
        String tipoMidia = request.getParameter("tipoMidia");

        ItemMidia item = new ItemMidia(titulo, autorDiretor, anoLancamento, genero, sinopse, tipoMidia);

        try {
            if (idStr == null || idStr.trim().isEmpty()) {
                // Inserir novo
                service.cadastrar(item);
            } else {
                // Atualizar existente
                item.setId(Integer.parseInt(idStr));
                service.atualizar(item);
            }
            response.sendRedirect("itens?acao=listar");
        } catch (Exception e) {
            request.setAttribute("erro", e.getMessage());
            request.setAttribute("item", item);
            request.getRequestDispatcher("/WEB-INF/views/formulario.jsp").forward(request, response);
        }
    }
}