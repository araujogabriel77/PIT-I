<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Detalhes da Mídia</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container mt-5" style="max-width: 800px;">
    <div class="card shadow mb-4">
        <div class="card-header bg-dark text-white d-flex justify-content-between align-items-center">
            <h3><c:out value="${item.titulo}"/></h3>
            <span class="badge bg-warning text-dark fs-6">Média:
                <c:choose>
                    <c:when test="${mediaAvaliacoes > 0}">
                        ${String.format("%.1f", mediaAvaliacoes)} / 5.0
                    </c:when>
                    <c:otherwise>Sem avaliações</c:otherwise>
                </c:choose>
            </span>
        </div>
        <div class="card-body">
            <p><strong>Autor / Diretor:</strong> <c:out value="${item.autorDiretor}"/></p>
            <p><strong>Ano de Lançamento:</strong> <c:out value="${item.anoLancamento}"/></p>
            <p><strong>Gênero:</strong> <c:out value="${item.genero}"/></p>
            <p><strong>Tipo de Mídia:</strong> <c:out value="${item.tipoMidia}"/></p>
            <hr>
            <h5>Sinopse</h5>
            <p class="text-muted"><c:out value="${item.sinopse}"/></p>

            <a href="itens?acao=listar" class="btn btn-secondary mt-3">Voltar à Listagem</a>
        </div>
    </div>

    <!-- Seção de Avaliações -->
    <div class="card shadow">
        <div class="card-header bg-secondary text-white">
            <h4>Avaliações e Comentários</h4>
        </div>
        <div class="card-body">
            <!-- Formulário para adicionar nova avaliação -->
            <form action="avaliar" method="post" class="mb-4 bg-light p-3 rounded border">
                <input type="hidden" name="itemMidiaId" value="${item.id}">
                <div class="mb-3">
                    <label for="nota" class="form-label">Nota (1 a 5):</label>
                    <select class="form-select" id="nota" name="nota" required style="max-width: 150px;">
                        <option value="5">⭐⭐⭐⭐⭐ (5)</option>
                        <option value="4">⭐⭐⭐⭐ (4)</option>
                        <option value="3">⭐⭐⭐ (3)</option>
                        <option value="2">⭐⭐ (2)</option>
                        <option value="1">⭐ (1)</option>
                    </select>
                </div>
                <div class="mb-3">
                    <label for="comentario" class="form-label">Comentário:</label>
                    <textarea class="form-control" id="comentario" name="comentario" rows="2" placeholder="Escreva sua opinião..."></textarea>
                </div>
                <button type="submit" class="btn btn-primary btn-sm">Enviar Avaliação</button>
            </form>

            <!-- Lista de avaliações existentes -->
            <h5 class="mb-3">Comentários anteriores:</h5>
            <c:forEach var="av" items="${avaliacoes}">
                <div class="border-bottom pb-2 mb-2">
                    <div class="d-flex justify-content-between">
                        <strong>Nota: <c:out value="${av.nota}"/> / 5</strong>
                        <small class="text-muted"><c:out value="${av.dataAvaliacao}"/></small>
                    </div>
                    <p class="mb-1"><c:out value="${av.comentario}"/></p>
                </div>
            </c:forEach>
            <c:if test="${empty avaliacoes}">
                <p class="text-muted">Ainda não há avaliações para este item. Seja o primeiro a avaliar!</p>
            </c:if>
        </div>
    </div>
</div>
</body>
</html>