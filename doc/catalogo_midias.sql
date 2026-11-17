-- MySQL
CREATE DATABASE catalogo_midia;
USE catalogo_midia;

CREATE TABLE item_midia (
    id              INT PRIMARY KEY AUTO_INCREMENT,
    titulo          VARCHAR(255) NOT NULL,
    autor_diretor   VARCHAR(255),
    ano_lancamento  INT,
    genero          VARCHAR(100),
    sinopse         TEXT,
    tipo_midia      VARCHAR(50) NOT NULL
);

CREATE TABLE avaliacao (
    id              INT PRIMARY KEY AUTO_INCREMENT,
    item_midia_id   INT NOT NULL,
    nota            INT NOT NULL,
    comentario      TEXT,
    data_avaliacao  DATE NOT NULL,
    CONSTRAINT fk_avaliacao_item
        FOREIGN KEY (item_midia_id) REFERENCES item_midia(id)
        ON DELETE CASCADE,
    CONSTRAINT chk_nota CHECK (nota BETWEEN 1 AND 5)
);