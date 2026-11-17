<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Catálogo de Mídia</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container mt-5">
    <div class="d-flex justify-content-between align-items-center mb-4">
        <h2>Catálogo de Mídias e Avaliações</h2>
        <a href="itens?acao=novo" class="btn btn-primary">Adicionar Nova Mídia</a>
    </div>

    <!-- Mensagem de Erro, se houver -->
    <c:if test="${not empty erro}">
        <div class="alert alert-danger" role="alert">
            <c:out value="${erro}"/>
        </div>
    </c:if>

    <!-- Barra de Pesquisa -->
    <form action="itens" method="get" class="input-group mb-4">
        <input type="hidden" name="acao" value="listar">
        <input type="text" name="busca" class="form-control" placeholder="Pesquisar por título ou autor/diretor..." value="${param.busca}">
        <button class="btn btn-outline-secondary" type="submit">Buscar</button>
    </form>

    <!-- Tabela de Listagem -->
    <div class="card shadow-sm">
        <div class="card-body">
            <table class="table table-hover align-middle">
                <thead class="table-dark">
                <tr>
                    <th>ID</th>
                    <th>Título</th>
                    <th>Autor / Diretor</th>
                    <th>Ano</th>
                    <th>Gênero</th>
                    <th>Tipo</th>
                    <th class="text-end">Ações</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="item" items="${itens}">
                    <tr>
                        <td><c:out value="${item.id}"/></td>
                        <td><strong><c:out value="${item.titulo}"/></strong></td>
                        <td><c:out value="${item.autorDiretor}"/></td>
                        <td><c:out value="${item.anoLancamento}"/></td>
                        <td><c:out value="${item.genero}"/></td>
                        <td><span class="badge bg-info text-dark"><c:out value="${item.tipoMidia}"/></span></td>
                        <td class="text-end">
                            <a href="itens?acao=detalhes&id=${item.id}" class="btn btn-sm btn-success">Detalhes</a>
                            <a href="itens?acao=editar&id=${item.id}" class="btn btn-sm btn-warning">Editar</a>
                            <a href="itens?acao=excluir&id=${item.id}" class="btn btn-sm btn-danger" onclick="return confirm('Tem certeza que deseja excluir este item?')">Excluir</a>
                        </td>
                    </tr>
                </c:forEach>
                <c:if test="${empty itens}">
                    <tr>
                        <td colspan="7" class="text-center text-muted py-4">Nenhum item encontrado no catálogo.</td>
                    </tr>
                </c:if>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>
