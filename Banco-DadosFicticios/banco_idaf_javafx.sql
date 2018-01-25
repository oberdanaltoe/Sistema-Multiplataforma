-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: 14-Nov-2017 às 12:45
-- Versão do servidor: 10.1.21-MariaDB
-- PHP Version: 7.0.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `banco_idaf_javafx`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `abrigo`
--

CREATE TABLE `abrigo` (
  `cod_abrigo` int(11) NOT NULL,
  `cod_tipo_abrigo` int(11) NOT NULL,
  `longitude` decimal(11,8) DEFAULT NULL,
  `latitude` decimal(11,8) DEFAULT NULL,
  `ponto_referencia` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `abrigo`
--

INSERT INTO `abrigo` (`cod_abrigo`, `cod_tipo_abrigo`, `longitude`, `latitude`, `ponto_referencia`) VALUES
(12, 1, '-40.99661636', '-20.75725937', 'teste '),
(15, 1, '-40.99670410', '-20.75718498', 'teste novo'),
(17, 1, '-41.15498352', '-20.80293846', 'ifes'),
(21, 1, '0.00000000', '0.00000000', 'hff'),
(22, 1, '-41.15467834', '-20.80275154', 'Ifes bloco 4'),
(24, 1, '-41.10707092', '-20.85113716', ''),
(25, 1, '-41.11952972', '-20.98884773', 'teste'),
(23, 2, '-41.10707092', '-20.85113716', 'Matriz velha'),
(10, 3, '-41.05233002', '-20.76125717', 'teste'),
(18, 3, '-41.15496826', '-20.80311203', 'Ifes 2'),
(19, 3, '-41.17299271', '-20.96897507', 'perto da igreja'),
(6, 4, '-41.05233002', '-20.76125717', 'linha x'),
(14, 4, '-41.01517487', '-20.71759033', 'Perto de Vargem Alta '),
(7, 5, '-41.05233002', '-20.76125717', 'Árvore de Pinho ao lado'),
(8, 6, '-41.05233002', '-20.76125717', 'Casa laranja antiga'),
(9, 8, '-41.05233002', '-20.76125717', 'Curral do Santo Antonio'),
(20, 8, '-41.14501572', '-20.96565628', 'na reta do Becka');

-- --------------------------------------------------------

--
-- Estrutura da tabela `agendamento_visita`
--

CREATE TABLE `agendamento_visita` (
  `cod_agendamento_visita` int(11) NOT NULL,
  `rua` varchar(50) NOT NULL,
  `numero` int(11) NOT NULL,
  `tipo_visita` varchar(50) DEFAULT NULL,
  `data` date DEFAULT NULL,
  `longitude` float DEFAULT NULL,
  `latitude` float DEFAULT NULL,
  `cod_funcionario` int(11) DEFAULT NULL,
  `cod_bairro` int(11) NOT NULL,
  `cod_solicitacao` int(11) DEFAULT NULL,
  `status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `agendamento_visita`
--

INSERT INTO `agendamento_visita` (`cod_agendamento_visita`, `rua`, `numero`, `tipo_visita`, `data`, `longitude`, `latitude`, `cod_funcionario`, `cod_bairro`, `cod_solicitacao`, `status`) VALUES
(35, 'Teste', 98, 'Visita em Abrigo', '2018-09-14', 0, 0, NULL, 2, 67, 2),
(37, 'Limeira', 4, 'Visita de Recolhimento de Cerebro', '2017-10-02', 0, 0, NULL, 1, 118, 2),
(39, 'Limeira', 4, 'Visita em Abrigo', '2017-10-04', 0, 0, NULL, 1, 59, 2),
(42, 'Teste', 98, 'Visita de Recolhimento de Cerebro', '2017-10-03', 0, 0, NULL, 2, 67, 2),
(49, 'Limeira', 4, 'Visita em Curral', '2017-10-18', 0, 0, NULL, 1, 64, 2),
(50, 'Limeira', 4, 'Visita em Abrigo', '2017-10-05', 0, 0, NULL, 1, 69, 2),
(51, 'Teste', 98, 'Visita em Abrigo', '2017-10-27', 0, 0, NULL, 2, 130, 2),
(52, 'Limeira', 4, 'Visita de Recolhimento de Cerebro', '2017-10-27', 0, 0, NULL, 1, 132, 2),
(53, 'Limeira', 4, 'Visita de Recolhimento de Cerebro', '2017-10-30', 0, 0, NULL, 1, 131, 2),
(54, 'Limeira', 4, 'Visita de Recolhimento de Cerebro', '2017-11-01', 0, 0, NULL, 1, 133, 2),
(55, 'Teste   345', 888, 'Visita em Abrigo', '2017-10-31', 0, 0, NULL, 3, 135, 1),
(56, 'Limeira', 4, 'Visita em Curral', '2017-10-30', 0, 0, NULL, 1, 134, 1),
(57, 'Limeira', 4, 'Visita em Abrigo', '2017-11-17', 0, 0, NULL, 1, 144, 1);

-- --------------------------------------------------------

--
-- Estrutura da tabela `bairro`
--

CREATE TABLE `bairro` (
  `cod_bairro` int(11) NOT NULL,
  `nome_bairro` varchar(45) NOT NULL,
  `cod_cidade` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `bairro`
--

INSERT INTO `bairro` (`cod_bairro`, `nome_bairro`, `cod_cidade`) VALUES
(1, 'Jaciguá', 2),
(2, 'Boa Esperança', 2),
(3, 'Aeroporto', 4),
(4, 'Corrego Mônos', 4),
(5, 'Teste', 4);

-- --------------------------------------------------------

--
-- Estrutura da tabela `captura`
--

CREATE TABLE `captura` (
  `cod_captura` int(11) NOT NULL,
  `longitude` float DEFAULT NULL,
  `latitude` float DEFAULT NULL,
  `qtd_morcegos_capturados` int(11) NOT NULL,
  `cod_abrigo` int(11) NOT NULL,
  `qtd_morcegos_tratados` int(11) NOT NULL,
  `qtd_morcegos_enviados_laboratorio` int(11) NOT NULL,
  `data_captura` date NOT NULL,
  `cod_visita` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `captura`
--

INSERT INTO `captura` (`cod_captura`, `longitude`, `latitude`, `qtd_morcegos_capturados`, `cod_abrigo`, `qtd_morcegos_tratados`, `qtd_morcegos_enviados_laboratorio`, `data_captura`, `cod_visita`) VALUES
(3, -41.173, -20.969, 5, 9, 1, 2, '2017-09-25', 12),
(4, -41.173, -20.969, 6, 15, 1, 1, '2017-09-25', 9),
(5, -41.173, -20.969, 21, 19, 1, 2, '2017-09-25', 26),
(6, -41.145, -20.9657, 3, 20, 1, 2, '2017-09-25', 11),
(7, -41.145, -20.9657, 2, 20, 1, 1, '2017-09-25', 26),
(8, -40.9966, -20.7573, 8, 15, 2, 3, '2017-09-25', 10),
(9, -40.9966, -20.7574, 5, 18, 1, 1, '2017-10-02', 28),
(10, -41.1547, -20.803, 6, 18, 1, 2, '2017-10-02', 27),
(11, -40.9968, -20.7574, 8, 21, 1, 1, '2017-10-25', 29),
(12, -40.9965, -20.7575, 3, 12, 0, 1, '2017-10-25', 31),
(13, -41.1448, -20.9658, 5, 20, 1, 2, '2017-10-26', 30),
(14, -41.1071, -20.8512, 3, 24, 3, 0, '2017-10-27', 32),
(15, -41.1195, -20.9888, 5, 25, 5, 6, '2017-11-13', NULL);

-- --------------------------------------------------------

--
-- Estrutura da tabela `cidade`
--

CREATE TABLE `cidade` (
  `cod_cidade` int(11) NOT NULL,
  `nome_cidade` varchar(60) NOT NULL,
  `UF` char(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `cidade`
--

INSERT INTO `cidade` (`cod_cidade`, `nome_cidade`, `UF`) VALUES
(2, 'Vargem Alta', 'ES'),
(3, 'Vitória', 'ES'),
(4, 'Cachoeiro de Itapemirim', 'ES'),
(6, 'Serra', 'ES'),
(8, 'São Paulo', 'SP'),
(11, 'Alágoas', 'PI'),
(15, 'Iconhas', 'ES'),
(16, 'Outro Teste', 'AM'),
(17, 'Teresina ', 'PE');

-- --------------------------------------------------------

--
-- Estrutura da tabela `clientes`
--

CREATE TABLE `clientes` (
  `cdCliente` int(11) NOT NULL,
  `nome` varchar(50) NOT NULL,
  `cpf` varchar(14) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `clientes`
--

INSERT INTO `clientes` (`cdCliente`, `nome`, `cpf`) VALUES
(2, 'Cliente Alterar', '111.222.333-44'),
(3, 'Cliente Inserir', '111.222.333-44'),
(4, 'Cliente Android', '11231231263');

-- --------------------------------------------------------

--
-- Estrutura da tabela `especialidade_funcionario`
--

CREATE TABLE `especialidade_funcionario` (
  `cod_especialidade` int(11) NOT NULL,
  `desc_especialidade` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `especialidade_funcionario`
--

INSERT INTO `especialidade_funcionario` (`cod_especialidade`, `desc_especialidade`) VALUES
(6, 'Geologo'),
(7, 'Veterinário'),
(8, 'Agronomo'),
(9, 'Técnico Agricola');

-- --------------------------------------------------------

--
-- Estrutura da tabela `especie_morcego`
--

CREATE TABLE `especie_morcego` (
  `cod_especie` int(11) NOT NULL,
  `nome_especie` varchar(50) NOT NULL,
  `cod_habito_alimentar` int(11) NOT NULL,
  `cod_genero` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela `familia_morcego`
--

CREATE TABLE `familia_morcego` (
  `cod_familia` int(11) NOT NULL,
  `nome_familia` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `familia_morcego`
--

INSERT INTO `familia_morcego` (`cod_familia`, `nome_familia`) VALUES
(2, 'Familias');

-- --------------------------------------------------------

--
-- Estrutura da tabela `funcao_funcionario`
--

CREATE TABLE `funcao_funcionario` (
  `cod_funcao` int(11) NOT NULL,
  `desc_funcao` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `funcao_funcionario`
--

INSERT INTO `funcao_funcionario` (`cod_funcao`, `desc_funcao`) VALUES
(7, 'Funcionário'),
(8, 'Coordenador');

-- --------------------------------------------------------

--
-- Estrutura da tabela `funcionario`
--

CREATE TABLE `funcionario` (
  `cod_funcionario` int(11) NOT NULL,
  `nome` varchar(30) NOT NULL,
  `login` varchar(100) NOT NULL,
  `senha` varchar(40) NOT NULL,
  `cpf` char(11) NOT NULL,
  `cod_funcao` int(11) NOT NULL,
  `cod_especialidade` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `funcionario`
--

INSERT INTO `funcionario` (`cod_funcionario`, `nome`, `login`, `senha`, `cpf`, `cod_funcao`, `cod_especialidade`) VALUES
(2, 'Oberdan', 'oberdan@gmail.com', '202cb962ac59075b964b07152d234b70', '14345411778', 7, 6),
(3, 'Andre', 'andre@gmail.com', '202cb962ac59075b964b07152d234b70', '12323422133', 7, 7),
(4, 'Erick', 'erick@gmail.com', '202cb962ac59075b964b07152d234b70', '12332122311', 7, 8),
(5, 'Usuario Teste', 'coordenador@gmail.com', '7363a0d0604902af7b70b271a0b96480', '12312312640', 7, 6);

-- --------------------------------------------------------

--
-- Estrutura da tabela `funcionario_agenda`
--

CREATE TABLE `funcionario_agenda` (
  `cod_funcionario` int(11) NOT NULL,
  `cod_agendamento_visita` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `funcionario_agenda`
--

INSERT INTO `funcionario_agenda` (`cod_funcionario`, `cod_agendamento_visita`) VALUES
(2, 49),
(2, 51),
(2, 56),
(3, 39),
(3, 49),
(3, 50),
(3, 51),
(3, 52),
(3, 53),
(3, 54),
(4, 37),
(4, 52),
(4, 54),
(4, 55),
(4, 56),
(4, 57),
(5, 42),
(5, 49),
(5, 55),
(5, 57);

-- --------------------------------------------------------

--
-- Estrutura da tabela `genero_morcego`
--

CREATE TABLE `genero_morcego` (
  `cod_genero` int(11) NOT NULL,
  `nome_genero` varchar(50) NOT NULL,
  `cod_familia` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela `habito_alimentar_morcego`
--

CREATE TABLE `habito_alimentar_morcego` (
  `cod_habito_alimentar` int(11) NOT NULL,
  `desc_habito_alimentar` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `habito_alimentar_morcego`
--

INSERT INTO `habito_alimentar_morcego` (`cod_habito_alimentar`, `desc_habito_alimentar`) VALUES
(1, 'Hematofago');

-- --------------------------------------------------------

--
-- Estrutura da tabela `mensagens`
--

CREATE TABLE `mensagens` (
  `cod_mensagem` int(11) NOT NULL,
  `titulo` varchar(70) NOT NULL,
  `mensagem` varchar(250) NOT NULL,
  `data_expira` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `mensagens`
--

INSERT INTO `mensagens` (`cod_mensagem`, `titulo`, `mensagem`, `data_expira`) VALUES
(1, 'Mensagem Teste', 'Essa é uma mensagem teste', '2017-10-01'),
(2, 'Nova mensagem', 'Nova mesagem teste de alteração', '2017-10-01'),
(3, 'Vacinação de Raiva', 'VAcinação ......', '2017-10-31');

-- --------------------------------------------------------

--
-- Estrutura da tabela `recolhimento_cerebro`
--

CREATE TABLE `recolhimento_cerebro` (
  `cod_recolhimento_cerebro` int(11) NOT NULL,
  `longitude` float DEFAULT NULL,
  `latitude` float DEFAULT NULL,
  `animal_morto` varchar(15) NOT NULL,
  `data` date DEFAULT NULL,
  `cod_visita` int(11) DEFAULT NULL,
  `estado_carcaca` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `recolhimento_cerebro`
--

INSERT INTO `recolhimento_cerebro` (`cod_recolhimento_cerebro`, `longitude`, `latitude`, `animal_morto`, `data`, `cod_visita`, `estado_carcaca`) VALUES
(3, -40.9966, -20.7573, 'Cachorro', '2017-09-26', 11, 'Inicial'),
(4, -41.0152, -20.7176, 'Bovino', '2017-10-02', 26, 'Putrefação'),
(5, -41.1547, -20.8028, 'Bovino', '2017-10-02', 27, 'Inicial'),
(6, -40.9966, -20.7573, 'Gato', '2017-10-10', 28, 'Putrefação Escura'),
(7, -40.9968, -20.7574, 'Cachorro', '2017-10-25', 29, 'Inicial'),
(8, -41.1448, -20.9657, 'Bovino', '2017-10-26', 30, 'Putrefação Escura'),
(9, -41.1071, -20.8512, 'Bovino', '2017-10-27', 31, 'Putrefação Escura');

-- --------------------------------------------------------

--
-- Estrutura da tabela `solicitacao_visita`
--

CREATE TABLE `solicitacao_visita` (
  `cod_solicitacao_visita` int(11) NOT NULL,
  `status` int(11) DEFAULT NULL,
  `possui_propriedade` varchar(15) NOT NULL,
  `solicitar_recolhimento_cerebro` varchar(15) DEFAULT NULL,
  `qtd_media_animais` int(11) NOT NULL,
  `qtd_animais_mordidos` int(11) NOT NULL,
  `casos_morte_regiao` varchar(15) NOT NULL,
  `proprie_locais_proximos` varchar(60) DEFAULT NULL,
  `tempo_ocorrido_morte` varchar(60) DEFAULT NULL,
  `existe_abrigo_morcego` varchar(15) NOT NULL,
  `observacoes` varchar(200) DEFAULT NULL,
  `foto` varchar(200) DEFAULT NULL,
  `cod_usuario` int(11) DEFAULT NULL,
  `data_solcicitacao` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `solicitacao_visita`
--

INSERT INTO `solicitacao_visita` (`cod_solicitacao_visita`, `status`, `possui_propriedade`, `solicitar_recolhimento_cerebro`, `qtd_media_animais`, `qtd_animais_mordidos`, `casos_morte_regiao`, `proprie_locais_proximos`, `tempo_ocorrido_morte`, `existe_abrigo_morcego`, `observacoes`, `foto`, `cod_usuario`, `data_solcicitacao`) VALUES
(59, 2, 'Sim', NULL, 3, 1, 'NÃ£o', NULL, NULL, 'Sim', 'tese', ' Em produÃ§Ã£o ', 1, '0000-00-00'),
(63, 1, 'Sim', 'NÃ£o', 5, 4, 'Sim', 'Propriedade', NULL, 'Sim', 'asda', ' Em produÃ§Ã£o ', 1, NULL),
(64, 2, 'Sim', 'Sim', 45, 34, 'Sim', 'Propriedade', 'Menos de uma Semana', 'Sim', 'sdf', ' Em produção ', 1, '2017-09-05'),
(65, 1, 'Sim', 'Sim', 6, 5, 'Sim', 'Propriedade', NULL, 'Sim', '', ' Em produÃ§Ã£o ', 1, NULL),
(66, 1, 'Sim', NULL, 56, 4, 'NÃ£o', NULL, NULL, 'NÃ£o', 'ads', ' Em produÃ§Ã£o ', 1, NULL),
(67, 2, 'Sim', 'Sim', 56, 34, 'Sim', 'Propriedade', 'Menos de uma Semana', 'NÃ£o', '345', ' Em produção ', 2, '2017-09-15'),
(69, 2, 'Sim', 'NÃ£o', 45, 4, 'Sim', 'Propriedade', NULL, 'NÃ£o', 'sdf', ' Em produção ', 1, '2017-09-04'),
(70, 1, 'Sim', 'Sim', 6, 5, 'Sim', 'Propriedade', NULL, 'Sim', 'rty', ' Em produÃ§Ã£o ', 1, NULL),
(71, 1, 'Sim', 'NÃ£o', 6, 7, 'Sim', 'Propriedade', NULL, 'NÃ£o', 'sdf', ' Em produÃ§Ã£o ', 1, NULL),
(72, 1, 'Sim', NULL, 7, 6, 'NÃ£o', NULL, NULL, 'Sim', '65', ' Em produÃ§Ã£o ', 1, NULL),
(73, 1, 'Sim', 'Sim', 7, 6, 'Sim', 'Propriedade', NULL, 'NÃ£o', '67', ' Em produÃ§Ã£o ', 1, NULL),
(76, 1, 'Sim', NULL, 5, 3, 'Não', NULL, NULL, 'Sim', 'erre', ' Em produção ', 1, NULL),
(77, 1, 'Sim', NULL, 34, 2, 'Não', NULL, NULL, 'Sim', 'sdfssdf', ' Em produção ', 1, NULL),
(78, 1, 'Sim', 'Não', 34, 2, 'Sim', 'Propriedade', 'Menos de uma Semana', 'Sim', 'sdfsdf', ' Em produção ', 1, NULL),
(79, 1, 'Sim', NULL, 45, 3, 'Não', NULL, NULL, 'Sim', 'srsr', ' Em produção ', 1, NULL),
(80, 2, 'Sim', NULL, 45, 3, 'Não', NULL, NULL, 'Sim', 'srs', ' Em produção ', 1, NULL),
(81, 1, 'Sim', NULL, 6, 5, 'Não', NULL, NULL, 'Sim', 'hhh', ' Em produção ', 1, NULL),
(82, 1, 'Sim', NULL, 65, 4, 'Não', NULL, NULL, 'Sim', 'trtyrt', ' Em produção ', 1, NULL),
(83, 1, 'Sim', NULL, 56, 4, 'Não', NULL, NULL, 'Sim', 'wwwwww', ' Em produção ', 1, NULL),
(84, 1, 'Sim', NULL, 67, 5, 'Não', NULL, NULL, 'Sim', 'fhjfhj', ' Em produção ', 1, NULL),
(85, 1, 'Sim', NULL, 56, 4, 'Não', NULL, NULL, 'Sim', 'trhytyh', ' Em produção ', 1, NULL),
(86, 1, 'Sim', NULL, 23, 2, 'Não', NULL, NULL, 'Não', 'asd', ' Em produção ', 1, NULL),
(87, 1, 'Sim', NULL, 34, 2, 'Não', NULL, NULL, 'Sim', 'sdas', ' Em produção ', 1, NULL),
(88, 1, 'Sim', NULL, 34, 3, 'Não', NULL, NULL, 'Sim', 'asd', ' Em produção ', 3, NULL),
(89, 1, 'Sim', NULL, 56, 4, 'Não', NULL, NULL, 'Sim', 'asd', ' Em produção ', 1, NULL),
(90, 1, 'Não', 'Não', 56, 4, 'Sim', 'Propriedade', 'Menos de uma Semana', 'Não', 'asd', ' Em produção ', 5, NULL),
(91, 1, 'Não', NULL, 45, 4, 'Não', NULL, NULL, 'Sim', 'teste', ' Em produção ', 1, NULL),
(99, 1, 'Sim', 'Não', 65, 5, 'Sim', 'Propriedade', 'Duas Semanas', 'Sim', 'jhffyhjn', NULL, 1, NULL),
(100, 1, 'Sim', 'Não', 25, 5, 'Sim', 'Propriedade', 'Mais de um mês', 'Sim', 'Montgomeryhhf', NULL, 1, NULL),
(101, 1, 'Sim', 'Sim', 85, 54, 'Sim', 'Propriedade', 'Duas Semanas', 'Sim', 'uytreth', NULL, 1, NULL),
(102, 1, 'Sim', 'Não', 22, 12, 'Sim', 'Propriedade', 'Duas Semanas', 'Sim', 'hjgtujb', NULL, 1, NULL),
(103, 1, 'Sim', 'Não', 25, 65, 'Sim', 'Propriedade', 'Duas Semanas', 'Sim', 'jhftu', NULL, 1, NULL),
(104, 1, 'Sim', 'Não', 45, 5, 'Sim', 'Propriedade', 'Menos de uma Semana', 'Sim', 'kjhgfdsaoiug', NULL, 1, NULL),
(105, 1, 'Sim', 'Não', 565, 45, 'Sim', 'Propriedade', 'Mais de um mês', 'Sim', 'gddcg', NULL, 1, NULL),
(106, 1, 'Sim', 'Não', 25, 5, 'Sim', 'Propriedade', 'Mais de um mês', 'Sim', 'hvgy', NULL, 1, NULL),
(107, 1, 'Sim', 'Não', 254, 100, 'Sim', 'Propriedade', 'Mais de um mês', 'Sim', 'iiiiuy', NULL, 1, NULL),
(108, 1, 'Sim', 'Sim', 25, 8, 'Sim', 'Propriedade', 'Mais de um mês', 'Sim', 'hgfd', NULL, 1, NULL),
(109, 1, 'Sim', 'Sim', 41, 4, 'Sim', 'Propriedade', 'Duas Semanas', 'Sim', 'hahgshaja', NULL, 1, NULL),
(110, 1, 'Sim', 'Não', 52, 25, 'Sim', 'Propriedade', 'Duas Semanas', 'Sim', 'jhfdsryujbv', NULL, 1, NULL),
(111, 1, 'Sim', 'Sim', 42, 40, 'Sim', 'Propriedade', 'Duas Semanas', 'Sim', 'teste', NULL, 1, NULL),
(112, 1, 'Sim', 'Sim', 54, 25, 'Sim', 'Propriedade', 'Mais de um mês', 'Sim', 'teste Android teste', NULL, 1, NULL),
(113, 1, 'Sim', 'Sim', 25, 5, 'Sim', 'Propriedade', 'Duas Semanas', 'Sim', 'jhfsty', NULL, 1, NULL),
(114, 1, 'Sim', 'Não', 25, 5, 'Sim', 'Propriedade', 'Mais de um mês', 'Sim', 'jjftyj', NULL, 1, NULL),
(115, 1, 'Sim', 'Sim', 2, 1, 'Sim', 'Propriedade', 'Menos de uma Semana', 'Sim', '', NULL, 1, NULL),
(116, 1, 'Sim', 'Não', 5, 1, 'Sim', 'Propriedade', 'Duas Semanas', 'Sim', '', NULL, 1, NULL),
(118, 2, 'Sim', 'Não', 987, 56, 'Sim', 'Propriedade', 'Mais de um mês', 'Não', 'teste', NULL, 1, '2017-10-01'),
(119, 1, 'Sim', 'Não', 25, 5, 'Sim', 'Propriedade', 'Menos de uma Semana', 'Não', 'khfg', NULL, 1, NULL),
(120, -1, 'Sim', 'Não', 5, 2, 'Sim', 'Propriedade', 'Duas Semanas', 'Não', 'iuhbh', NULL, 1, NULL),
(121, 1, 'Sim', 'Sim', 258, 56, 'Sim', 'Propriedade', 'Mais de um mês', 'Sim', 'jgffv', NULL, 1, NULL),
(125, 1, 'Sim', 'Sim', 68, 5, 'Sim', 'Propriedade', 'Duas Semanas', 'Sim', 'hychjb', NULL, 1, NULL),
(126, 1, 'Sim', 'Não', 25, 5, 'Sim', 'Propriedade', 'Menos de uma Semana', 'Sim', 'jhgbj', NULL, 1, NULL),
(127, 1, 'Sim', 'Não', 36, 5, 'Sim', 'Propriedade', 'Menos de uma Semana', 'Sim', 'ihvhj', NULL, 1, NULL),
(128, 1, 'Sim', 'Não', 45, 3, 'Sim', 'Propriedade', 'Menos de uma Semana', 'Sim', 'dfgdfgdfg', ' Em produção ', 1, NULL),
(129, 1, 'Sim', 'Sim', 45, 12, 'Sim', 'Propriedade', 'Duas Semanas', 'Não', 'morreu e já enterrei o animal ', NULL, 1, NULL),
(130, 2, 'Sim', 'Sim', 34, 3, 'Sim', 'Propriedade', 'Mais de um mês', 'Não', 'O animak já está morte a 2 meses, não sei se vai ser possivle fazer a coleta de material para exame', ' Em produção ', 2, '2017-10-05'),
(131, 2, 'Sim', 'Não', 25, 5, 'Não', ' ', ' ', 'Sim', 'nada a declarar', NULL, 1, '2017-10-05'),
(132, 2, 'Sim', 'Sim', 26, 6, 'Sim', 'Propriedade', 'Menos de uma Semana', 'Sim', 'urgente ', NULL, 1, '2017-10-26'),
(133, 2, 'Sim', 'Não', 100, 8, 'Sim', 'Regiões Proximas', 'Mais de um mês', 'Sim', 'Não sei', NULL, 1, '2017-10-26'),
(134, 2, 'Não', 'Não', 50, 6, 'Sim', 'Regiões Proximas', 'Menos de uma Semana', 'Sim', 'animais na propriedade do Zé morreram com sinais de raiva', NULL, 1, '2017-10-27'),
(135, 2, 'Sim', 'Sim', 89, 9, 'Sim', 'Propriedade', 'Menos de uma Semana', 'Sim', 'animal morreu com sintomas de raiva, próximo da casa do Oberdan, gostaria de que fossem na propriedade dele pois conheço furna na propriedade dele', ' Em produção ', 14, '2017-10-27'),
(136, 1, 'Sim', 'Sim', 12, 5, 'Não', ' ', ' ', 'Não', 'nxkfj', NULL, 1, '2017-11-13'),
(137, 1, 'Sim', 'Sim', 5, 2, 'Não', ' ', ' ', 'Não', 'jdkdk', NULL, 1, '2017-11-13'),
(138, 1, 'Sim', 'Sim', 5, 2, 'Não', ' ', ' ', 'Não', 'jdkdk', NULL, 1, '2017-11-13'),
(139, 1, 'Sim', 'Sim', 5, 8, 'Não', ' ', ' ', 'Sim', 'kkk', NULL, 1, '2017-11-13'),
(140, 1, 'Sim', 'Sim', 5, 2, 'Não', ' ', ' ', 'Não', 'kkhh', NULL, 1, '2017-11-13'),
(141, 1, 'Sim', 'Sim', 25, 5, 'Não', ' ', ' ', 'Não', 'jdjd', NULL, 1, '2017-11-13'),
(142, 1, 'Sim', 'Sim', 3, 2, 'Sim', 'Propriedade', ' ', 'Não', 'jdk', NULL, 1, '2017-11-13'),
(143, 1, 'Sim', 'Sim', 26, 3, 'Sim', 'Propriedade', 'Menos de uma Semana', 'Sim', 'Vários animais mordidos.', NULL, 1, '2017-11-13'),
(144, 2, 'Sim', 'Não', 14, 4, 'Sim', 'Propriedade', 'Mais de um mês', 'Sim', 'Vários animais mordidos. ', NULL, 1, '2017-11-13');

-- --------------------------------------------------------

--
-- Estrutura da tabela `tipo_abrigo`
--

CREATE TABLE `tipo_abrigo` (
  `cod_tipo_abrigo` int(11) NOT NULL,
  `desc_tipo_abrigo` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `tipo_abrigo`
--

INSERT INTO `tipo_abrigo` (`cod_tipo_abrigo`, `desc_tipo_abrigo`) VALUES
(1, 'Oco de Àrvores'),
(2, 'Bueiro'),
(3, 'Casa velha '),
(4, 'Túnel de Trem'),
(5, 'Cavernas'),
(6, 'Forro de Casa'),
(8, 'Curral');

-- --------------------------------------------------------

--
-- Estrutura da tabela `tipo_abrigo_solicitacao`
--

CREATE TABLE `tipo_abrigo_solicitacao` (
  `cod_solicitacao_visita` int(11) NOT NULL,
  `cod_tipo_abrigo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `tipo_abrigo_solicitacao`
--

INSERT INTO `tipo_abrigo_solicitacao` (`cod_solicitacao_visita`, `cod_tipo_abrigo`) VALUES
(63, 1),
(64, 1),
(65, 1),
(70, 1),
(72, 1),
(76, 1),
(77, 1),
(82, 1),
(88, 1),
(89, 1),
(90, 1),
(91, 1),
(111, 1),
(112, 1),
(113, 1),
(114, 1),
(115, 1),
(116, 1),
(121, 1),
(128, 1),
(133, 1),
(135, 1),
(143, 1),
(144, 1),
(63, 2),
(65, 2),
(73, 2),
(76, 2),
(79, 2),
(82, 2),
(87, 2),
(89, 2),
(90, 2),
(91, 2),
(63, 3),
(73, 3),
(76, 3),
(78, 3),
(80, 3),
(81, 3),
(83, 3),
(88, 3),
(89, 3),
(111, 3),
(112, 3),
(113, 3),
(114, 3),
(115, 3),
(116, 3),
(121, 3),
(127, 3),
(128, 3),
(132, 3),
(143, 3),
(144, 3),
(112, 4),
(116, 4),
(125, 4),
(127, 4),
(139, 4),
(111, 5),
(113, 5),
(115, 5),
(131, 5),
(134, 5),
(135, 5),
(125, 6),
(127, 6),
(131, 6);

-- --------------------------------------------------------

--
-- Estrutura da tabela `uf`
--

CREATE TABLE `uf` (
  `sigla_UF` char(2) NOT NULL,
  `nome_UF` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `uf`
--

INSERT INTO `uf` (`sigla_UF`, `nome_UF`) VALUES
('AM', 'Amazonas'),
('CU', 'Curitiba'),
('ES', 'Espirito Santo'),
('MG', 'Minas Gerais'),
('PA', 'Pará '),
('PE', 'Pernambuco'),
('PI', 'Piaui'),
('RJ', 'Rio de Janeiro'),
('SC', 'Santa Catarina'),
('SP', 'São Paulo'),
('TO', 'Tocantins');

-- --------------------------------------------------------

--
-- Estrutura da tabela `usuario_comum`
--

CREATE TABLE `usuario_comum` (
  `cod_usuario` int(11) NOT NULL,
  `nome_usuario` varchar(50) NOT NULL,
  `login` varchar(20) DEFAULT NULL,
  `senha` varchar(40) NOT NULL,
  `telefone` varchar(15) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `cpf` char(11) NOT NULL,
  `rua` varchar(50) NOT NULL,
  `numero` int(11) NOT NULL,
  `ponto_referencia` varchar(100) NOT NULL,
  `cod_bairro` int(11) NOT NULL,
  `data_cadastro` date DEFAULT NULL,
  `longitude` decimal(11,8) DEFAULT NULL,
  `latitude` decimal(10,8) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `usuario_comum`
--

INSERT INTO `usuario_comum` (`cod_usuario`, `nome_usuario`, `login`, `senha`, `telefone`, `email`, `cpf`, `rua`, `numero`, `ponto_referencia`, `cod_bairro`, `data_cadastro`, `longitude`, `latitude`) VALUES
(1, 'Oberdan', 'oberdan', '202cb962ac59075b964b07152d234b70', '999999999', 'oberdan@gmail.com', '12312312323', 'Limeira', 4, 'Perto da igreja', 1, '2017-08-06', '0.00000000', '0.00000000'),
(2, 'Luiz', 'luiz', '123', '999999999', NULL, '1231231231', 'Teste', 98, 'perto do bar', 2, '2017-09-01', NULL, NULL),
(3, 'Andre', 'andre', '1234', '999999999', NULL, '09876544333', 'teste', 23, 'adasd asdad asdsad', 4, '2017-09-04', '0.00000000', '0.00000000'),
(5, 'Matheus Mauricio', 'matheusmm', '1235', '999999999', NULL, '87934255433', 'Columbia', 43, 're', 3, '2017-10-01', '0.00000000', '0.00000000'),
(7, 'Piguimeu Santos', 'piguimeu', '12565', '999999999', NULL, '89867877800', 'eeretr', 356, 'lshdflksjjhfdkjs', 4, '2017-10-04', '0.00000000', '0.00000000'),
(12, 'teste teste ', 'teste', '698dc19d489c4e4db73e28a713eab07b', '8888888888', 'oberdandebona@yahoo..com', '12345678955', 'tesdfr', 5, 'gesf', 1, '2017-10-06', '0.00000000', '0.00000000'),
(13, 'João ', 'teste', '202cb962ac59075b964b07152d234b70', '28999965255', 'teste@gmail.com', '25687588544', 'teste', 5, 'k', 1, '2017-10-26', '0.00000000', '0.00000000'),
(14, 'João Victor', 'joeao@gmail.com', '202cb962ac59075b964b07152d234b70', '028999889977', 'joeao@gmail.com', '12376577655', 'Teste   345', 888, 'Perto de Padaria Brasil 123', 3, '2017-10-27', '0.00000000', '0.00000000'),
(15, 'Zé Francisco ', 'josefran', '5b1b68a9abf4d2cd155c81a9225fd158', '28999058874', 'Joséfran@gmail.com', '2254417896', 'foi', 22, 'próximo a casa abandonada ', 1, '2017-10-27', '0.00000000', '0.00000000');

-- --------------------------------------------------------

--
-- Estrutura da tabela `visita`
--

CREATE TABLE `visita` (
  `cod_visita` int(11) NOT NULL,
  `data_visita` date NOT NULL,
  `cod_funcionario` int(11) NOT NULL,
  `cod_agendamento` int(11) NOT NULL,
  `observacoes` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `visita`
--

INSERT INTO `visita` (`cod_visita`, `data_visita`, `cod_funcionario`, `cod_agendamento`, `observacoes`) VALUES
(9, '2017-09-28', 4, 35, 'jhfgu'),
(10, '2017-09-28', 4, 35, 'teste'),
(11, '2017-10-01', 4, 37, 'teste novo'),
(12, '2017-10-01', 4, 39, 'u Hg fbbh'),
(26, '2017-10-02', 4, 42, 'kkkkkkkkk'),
(27, '2017-10-02', 4, 49, 'ifes'),
(28, '2017-10-25', 4, 50, 'nada'),
(29, '2017-10-25', 4, 51, 'nada'),
(30, '2017-10-26', 4, 52, 'ok'),
(31, '2017-10-27', 4, 53, 'boi morto e cérebro coletado'),
(32, '2017-11-13', 4, 54, 'jd');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `abrigo`
--
ALTER TABLE `abrigo`
  ADD PRIMARY KEY (`cod_tipo_abrigo`,`cod_abrigo`),
  ADD KEY `fk_tipo_abrigo_visita_visita_abrigo` (`cod_abrigo`);

--
-- Indexes for table `agendamento_visita`
--
ALTER TABLE `agendamento_visita`
  ADD PRIMARY KEY (`cod_agendamento_visita`),
  ADD KEY `fk_funcionario_visita_agendamento` (`cod_funcionario`),
  ADD KEY `fk_usuario_bairro_agendamento` (`cod_bairro`),
  ADD KEY `fk_agenda_solicitacao` (`cod_solicitacao`);

--
-- Indexes for table `bairro`
--
ALTER TABLE `bairro`
  ADD PRIMARY KEY (`cod_bairro`),
  ADD KEY `fk_bairro_cidade` (`cod_cidade`);

--
-- Indexes for table `captura`
--
ALTER TABLE `captura`
  ADD PRIMARY KEY (`cod_captura`),
  ADD KEY `fk_visita_captura` (`cod_visita`),
  ADD KEY `fk_captura_abrigo` (`cod_abrigo`);

--
-- Indexes for table `cidade`
--
ALTER TABLE `cidade`
  ADD PRIMARY KEY (`cod_cidade`),
  ADD KEY `fk_cidade_UF` (`UF`);

--
-- Indexes for table `clientes`
--
ALTER TABLE `clientes`
  ADD PRIMARY KEY (`cdCliente`);

--
-- Indexes for table `especialidade_funcionario`
--
ALTER TABLE `especialidade_funcionario`
  ADD PRIMARY KEY (`cod_especialidade`);

--
-- Indexes for table `especie_morcego`
--
ALTER TABLE `especie_morcego`
  ADD PRIMARY KEY (`cod_especie`),
  ADD KEY `fk_especie_habito_alimentar` (`cod_habito_alimentar`),
  ADD KEY `fk_especie_genero_morcego` (`cod_genero`);

--
-- Indexes for table `familia_morcego`
--
ALTER TABLE `familia_morcego`
  ADD PRIMARY KEY (`cod_familia`);

--
-- Indexes for table `funcao_funcionario`
--
ALTER TABLE `funcao_funcionario`
  ADD PRIMARY KEY (`cod_funcao`);

--
-- Indexes for table `funcionario`
--
ALTER TABLE `funcionario`
  ADD PRIMARY KEY (`cod_funcionario`),
  ADD UNIQUE KEY `cpf` (`cpf`),
  ADD KEY `fk_funcionario_funcao` (`cod_funcao`),
  ADD KEY `fk_funcionario_especialidade` (`cod_especialidade`);

--
-- Indexes for table `funcionario_agenda`
--
ALTER TABLE `funcionario_agenda`
  ADD PRIMARY KEY (`cod_funcionario`,`cod_agendamento_visita`),
  ADD KEY `fk_funcionario_agenda_agendamento` (`cod_agendamento_visita`);

--
-- Indexes for table `genero_morcego`
--
ALTER TABLE `genero_morcego`
  ADD PRIMARY KEY (`cod_genero`),
  ADD KEY `fk_familia_genero` (`cod_familia`);

--
-- Indexes for table `habito_alimentar_morcego`
--
ALTER TABLE `habito_alimentar_morcego`
  ADD PRIMARY KEY (`cod_habito_alimentar`);

--
-- Indexes for table `mensagens`
--
ALTER TABLE `mensagens`
  ADD PRIMARY KEY (`cod_mensagem`);

--
-- Indexes for table `recolhimento_cerebro`
--
ALTER TABLE `recolhimento_cerebro`
  ADD PRIMARY KEY (`cod_recolhimento_cerebro`),
  ADD KEY `fk_visita_recolhimento_cerebro` (`cod_visita`);

--
-- Indexes for table `solicitacao_visita`
--
ALTER TABLE `solicitacao_visita`
  ADD PRIMARY KEY (`cod_solicitacao_visita`),
  ADD KEY `fk_solicitacao_usuario` (`cod_usuario`);

--
-- Indexes for table `tipo_abrigo`
--
ALTER TABLE `tipo_abrigo`
  ADD PRIMARY KEY (`cod_tipo_abrigo`);

--
-- Indexes for table `tipo_abrigo_solicitacao`
--
ALTER TABLE `tipo_abrigo_solicitacao`
  ADD PRIMARY KEY (`cod_tipo_abrigo`,`cod_solicitacao_visita`),
  ADD KEY `fk_tipo_abrigo_solicitacao_solicitacao` (`cod_solicitacao_visita`);

--
-- Indexes for table `uf`
--
ALTER TABLE `uf`
  ADD PRIMARY KEY (`sigla_UF`);

--
-- Indexes for table `usuario_comum`
--
ALTER TABLE `usuario_comum`
  ADD PRIMARY KEY (`cod_usuario`),
  ADD UNIQUE KEY `cpf` (`cpf`),
  ADD KEY `fk_usuario_bairro` (`cod_bairro`);

--
-- Indexes for table `visita`
--
ALTER TABLE `visita`
  ADD PRIMARY KEY (`cod_visita`),
  ADD KEY `fk_visita_agendamento` (`cod_agendamento`),
  ADD KEY `fk_visita_funcionario` (`cod_funcionario`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `abrigo`
--
ALTER TABLE `abrigo`
  MODIFY `cod_abrigo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;
--
-- AUTO_INCREMENT for table `agendamento_visita`
--
ALTER TABLE `agendamento_visita`
  MODIFY `cod_agendamento_visita` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=58;
--
-- AUTO_INCREMENT for table `bairro`
--
ALTER TABLE `bairro`
  MODIFY `cod_bairro` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `captura`
--
ALTER TABLE `captura`
  MODIFY `cod_captura` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;
--
-- AUTO_INCREMENT for table `cidade`
--
ALTER TABLE `cidade`
  MODIFY `cod_cidade` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;
--
-- AUTO_INCREMENT for table `clientes`
--
ALTER TABLE `clientes`
  MODIFY `cdCliente` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `especialidade_funcionario`
--
ALTER TABLE `especialidade_funcionario`
  MODIFY `cod_especialidade` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
--
-- AUTO_INCREMENT for table `especie_morcego`
--
ALTER TABLE `especie_morcego`
  MODIFY `cod_especie` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `familia_morcego`
--
ALTER TABLE `familia_morcego`
  MODIFY `cod_familia` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `funcao_funcionario`
--
ALTER TABLE `funcao_funcionario`
  MODIFY `cod_funcao` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT for table `funcionario`
--
ALTER TABLE `funcionario`
  MODIFY `cod_funcionario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `funcionario_agenda`
--
ALTER TABLE `funcionario_agenda`
  MODIFY `cod_funcionario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `genero_morcego`
--
ALTER TABLE `genero_morcego`
  MODIFY `cod_genero` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `habito_alimentar_morcego`
--
ALTER TABLE `habito_alimentar_morcego`
  MODIFY `cod_habito_alimentar` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `mensagens`
--
ALTER TABLE `mensagens`
  MODIFY `cod_mensagem` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `recolhimento_cerebro`
--
ALTER TABLE `recolhimento_cerebro`
  MODIFY `cod_recolhimento_cerebro` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
--
-- AUTO_INCREMENT for table `solicitacao_visita`
--
ALTER TABLE `solicitacao_visita`
  MODIFY `cod_solicitacao_visita` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=145;
--
-- AUTO_INCREMENT for table `tipo_abrigo`
--
ALTER TABLE `tipo_abrigo`
  MODIFY `cod_tipo_abrigo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT for table `tipo_abrigo_solicitacao`
--
ALTER TABLE `tipo_abrigo_solicitacao`
  MODIFY `cod_solicitacao_visita` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=145;
--
-- AUTO_INCREMENT for table `usuario_comum`
--
ALTER TABLE `usuario_comum`
  MODIFY `cod_usuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;
--
-- AUTO_INCREMENT for table `visita`
--
ALTER TABLE `visita`
  MODIFY `cod_visita` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;
--
-- Constraints for dumped tables
--

--
-- Limitadores para a tabela `abrigo`
--
ALTER TABLE `abrigo`
  ADD CONSTRAINT `fk_tipo_abrigo_visita_tipo_abrigo` FOREIGN KEY (`cod_tipo_abrigo`) REFERENCES `tipo_abrigo` (`cod_tipo_abrigo`);

--
-- Limitadores para a tabela `agendamento_visita`
--
ALTER TABLE `agendamento_visita`
  ADD CONSTRAINT `fk_agenda_solicitacao` FOREIGN KEY (`cod_solicitacao`) REFERENCES `solicitacao_visita` (`cod_solicitacao_visita`),
  ADD CONSTRAINT `fk_funcionario_visita_agendamento` FOREIGN KEY (`cod_funcionario`) REFERENCES `funcionario` (`cod_funcionario`),
  ADD CONSTRAINT `fk_usuario_bairro_agendamento` FOREIGN KEY (`cod_bairro`) REFERENCES `bairro` (`cod_bairro`);

--
-- Limitadores para a tabela `bairro`
--
ALTER TABLE `bairro`
  ADD CONSTRAINT `fk_bairro_cidade` FOREIGN KEY (`cod_cidade`) REFERENCES `cidade` (`cod_cidade`);

--
-- Limitadores para a tabela `captura`
--
ALTER TABLE `captura`
  ADD CONSTRAINT `fk_captura_abrigo` FOREIGN KEY (`cod_abrigo`) REFERENCES `abrigo` (`cod_abrigo`),
  ADD CONSTRAINT `fk_visita_captura` FOREIGN KEY (`cod_visita`) REFERENCES `visita` (`cod_visita`);

--
-- Limitadores para a tabela `cidade`
--
ALTER TABLE `cidade`
  ADD CONSTRAINT `fk_cidade_UF` FOREIGN KEY (`UF`) REFERENCES `uf` (`sigla_UF`);

--
-- Limitadores para a tabela `especie_morcego`
--
ALTER TABLE `especie_morcego`
  ADD CONSTRAINT `fk_especie_genero_morcego` FOREIGN KEY (`cod_genero`) REFERENCES `genero_morcego` (`cod_genero`),
  ADD CONSTRAINT `fk_especie_habito_alimentar` FOREIGN KEY (`cod_habito_alimentar`) REFERENCES `habito_alimentar_morcego` (`cod_habito_alimentar`);

--
-- Limitadores para a tabela `funcionario`
--
ALTER TABLE `funcionario`
  ADD CONSTRAINT `fk_funcionario_especialidade` FOREIGN KEY (`cod_especialidade`) REFERENCES `especialidade_funcionario` (`cod_especialidade`),
  ADD CONSTRAINT `fk_funcionario_funcao` FOREIGN KEY (`cod_funcao`) REFERENCES `funcao_funcionario` (`cod_funcao`);

--
-- Limitadores para a tabela `funcionario_agenda`
--
ALTER TABLE `funcionario_agenda`
  ADD CONSTRAINT `fk_funcionario_agenda_agendamento` FOREIGN KEY (`cod_agendamento_visita`) REFERENCES `agendamento_visita` (`cod_agendamento_visita`),
  ADD CONSTRAINT `fk_funcionario_agenda_funcionario` FOREIGN KEY (`cod_funcionario`) REFERENCES `funcionario` (`cod_funcionario`);

--
-- Limitadores para a tabela `genero_morcego`
--
ALTER TABLE `genero_morcego`
  ADD CONSTRAINT `fk_familia_genero` FOREIGN KEY (`cod_familia`) REFERENCES `familia_morcego` (`cod_familia`);

--
-- Limitadores para a tabela `recolhimento_cerebro`
--
ALTER TABLE `recolhimento_cerebro`
  ADD CONSTRAINT `fk_visita_recolhimento_cerebro` FOREIGN KEY (`cod_visita`) REFERENCES `visita` (`cod_visita`);

--
-- Limitadores para a tabela `solicitacao_visita`
--
ALTER TABLE `solicitacao_visita`
  ADD CONSTRAINT `fk_solicitacao_usuario` FOREIGN KEY (`cod_usuario`) REFERENCES `usuario_comum` (`cod_usuario`);

--
-- Limitadores para a tabela `tipo_abrigo_solicitacao`
--
ALTER TABLE `tipo_abrigo_solicitacao`
  ADD CONSTRAINT `fk_tipo_abrigo_solicitacao_solicitacao` FOREIGN KEY (`cod_solicitacao_visita`) REFERENCES `solicitacao_visita` (`cod_solicitacao_visita`),
  ADD CONSTRAINT `fk_tipo_abrigo_solicitacao_tipo_abrigo` FOREIGN KEY (`cod_tipo_abrigo`) REFERENCES `tipo_abrigo` (`cod_tipo_abrigo`);

--
-- Limitadores para a tabela `usuario_comum`
--
ALTER TABLE `usuario_comum`
  ADD CONSTRAINT `fk_usuario_bairro` FOREIGN KEY (`cod_bairro`) REFERENCES `bairro` (`cod_bairro`);

--
-- Limitadores para a tabela `visita`
--
ALTER TABLE `visita`
  ADD CONSTRAINT `fk_visita_agendamento` FOREIGN KEY (`cod_agendamento`) REFERENCES `agendamento_visita` (`cod_agendamento_visita`),
  ADD CONSTRAINT `fk_visita_funcionario` FOREIGN KEY (`cod_funcionario`) REFERENCES `funcionario` (`cod_funcionario`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
