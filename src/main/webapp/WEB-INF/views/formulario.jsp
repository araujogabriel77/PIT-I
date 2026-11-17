<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>${empty item.id ? 'Nova Mídia' : 'Editar Mídia'}</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container mt-5" style="max-width: 700px;">
  <div class="card shadow">
    <div class="card-header bg-primary text-white">
      <h3>${empty item.id ? 'Cadastrar Novo Item' : 'Editar Item de Mídia'}</h3>
    </div>
    <div class="card-body">
      <c:if test="${not empty erro}">
        <div class="alert alert-danger"><c:out value="${erro}"/></div>
      </c:if>

      <form action="itens" method="post">
        <!-- Se houver ID, é uma atualização -->
        <input type="hidden" name="id" value="${item.id}">

        <div class="mb-3">
          <label for="titulo" class="form-label">Título *</label>
          <input type="text" class="form-control" id="titulo" name="titulo" value="${item.titulo}" required>
        </div>

        <div class="mb-3">
          <label for="autorDiretor" class="form-label">Autor / Diretor</label>
          <input type="text" class="form-control" id="autorDiretor" name="autorDiretor" value="${item.autorDiretor}">
        </div>

        <div class="row">
          <div class="col-md-6 mb-3">
            <label for="anoLancamento" class="form-label">Ano de Lançamento *</label>
            <input type="number" class="form-control" id="anoLancamento" name="anoLancamento" value="${item.anoLancamento}" required>
          </div>
          <div class="col-md-6 mb-3">
            <label for="tipoMidia" class="form-label">Tipo de Mídia *</label>
            <select class="form-select" id="tipoMidia" name="tipoMidia" required>
              <option value="">Selecione...</option>
              <option value="Livro" ${item.tipoMidia == 'Livro' ? 'selected' : ''}>Livro</option>
              <option value="Filme" ${item.tipoMidia == 'Filme' ? 'selected' : ''}>Filme</option>
              <option value="Série" ${item.tipoMidia == 'Série' ? 'selected' : ''}>Série</option>
              <option value="Jogo" ${item.tipoMidia == 'Jogo' ? 'selected' : ''}>Jogo</option>
              <option value="Álbum" ${item.tipoMidia == 'Álbum' ? 'selected' : ''}>Álbum</option>
            </select>
          </div>
        </div>

        <div class="mb-3">
          <label for="genero" class="form-label">Gênero</label>
          <input type="text" class="form-control" id="genero" name="genero" value="${item.genero}">
        </div>

        <div class="mb-3">
          <label for="sinopse" class="form-label">Sinopse</label>
          <textarea class="form-control" id="sinopse" name="sinopse" rows="4"><c:out value="${item.sinopse}"/></textarea>
        </div>

        <div class="d-flex justify-content-between">
          <a href="itens?acao=listar" class="btn btn-secondary">Cancelar</a>
          <button type="submit" class="btn btn-success">Salvar</button>
        </div>
      </form>
    </div>
  </div>
</div>
</body>
</html>