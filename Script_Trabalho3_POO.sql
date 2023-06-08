CREATE TABLE Cliente 
(   CPF varchar2(15) PRIMARY KEY,
    Nome varchar2(25),
    Endereco varchar2(25),
    Cidade varchar2(25),
    CEP varchar2(10),
    UF varchar2(2),
    TelefoneDDD varchar2(5),
    TelefoneNumero varchar2(10),
    LimiteCredito NUMBER(6,2),
    LimiteDisp NUMBER(6, 2)
);

CREATE TABLE Vendedor 
(   CPF varchar2(15) PRIMARY KEY,
    Nome varchar2(25),
    Endereco varchar2(25),
    Cidade varchar2(25),
    CEP varchar2(10),
    UF varchar2(2),
    TelefoneDDD varchar2(5),
    TelefoneNumero varchar2(10),
    SalarioBase NUMBER(6,2),
    Comissao NUMBER(6,2)
);

CREATE TABLE Pedido 
(   Numero NUMBER(5,1) PRIMARY KEY, /*VAI VIRAR STRING*/
    CPFCliente VARCHAR2(15) ,
    CPFVendedor VARCHAR2(15),
    Status char(1) check (Status in ('T', 'F')),	
    DataPedido DATE, /*VAI VIRAR STRING*/
    DataPagto DATE, /*VAI VIRAR STRING*/
    FOREIGN KEY (CPFCliente) REFERENCES Cliente,
    FOREIGN KEY (CPFVendedor) REFERENCES Vendedor
);
/*ALTER TABLE Pedido ADD FormaPagamento char(1) check(FormaPagamento in ('D', 'C'));*/
/*Adicionando o campo de Forma Pagamento; TRABALHO 4.*/

CREATE TABLE Produto 
(   CodigoProduto VARCHAR2(10) PRIMARY KEY,
    Descricao VARCHAR2(50),
    UnidadeMedida VARCHAR2(2),
    QtdeEstoque NUMBER(5, 1),
    PrecoUnit NUMBER(5,2),
    EstoqueMinimo NUMBER(5, 1)
);

CREATE TABLE ItemPedido 
(   NumeroPedido NUMBER(5, 1) , /*vai ficar apenas um INT*/
    CodigoProduto VARCHAR2(10),
    QtVendida NUMBER(5, 1),
    FOREIGN KEY (NumeroPedido) REFERENCES Pedido,
    FOREIGN KEY (CodigoProduto) REFERENCES Produto
);
