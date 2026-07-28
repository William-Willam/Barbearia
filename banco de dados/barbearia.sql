CREATE DATABASE agendamento_barbearia;
USE agendamento_barbearia;

-- 20 Clientes
INSERT INTO clientes (nome, telefone, email) VALUES
('João Silva', '61999990001', 'joao.silva@email.com'),
('Pedro Santos', '61999990002', 'pedro.santos@email.com'),
('Lucas Oliveira', '61999990003', 'lucas.oliveira@email.com'),
('Rafael Souza', '61999990004', 'rafael.souza@email.com'),
('Gabriel Costa', '61999990005', 'gabriel.costa@email.com'),
('Matheus Lima', '61999990006', 'matheus.lima@email.com'),
('Bruno Alves', '61999990007', 'bruno.alves@email.com'),
('Felipe Rocha', '61999990008', 'felipe.rocha@email.com'),
('Thiago Martins', '61999990009', 'thiago.martins@email.com'),
('Diego Pereira', '61999990010', 'diego.pereira@email.com'),
('André Ribeiro', '61999990011', 'andre.ribeiro@email.com'),
('Carlos Barbosa', '61999990012', 'carlos.barbosa@email.com'),
('Eduardo Fernandes', '61999990013', 'eduardo.fernandes@email.com'),
('Vinícius Gomes', '61999990014', 'vinicius.gomes@email.com'),
('Rodrigo Dias', '61999990015', 'rodrigo.dias@email.com'),
('Marcelo Teixeira', '61999990016', 'marcelo.teixeira@email.com'),
('Gustavo Cardoso', '61999990017', 'gustavo.cardoso@email.com'),
('Leonardo Nunes', '61999990018', 'leonardo.nunes@email.com'),
('Fábio Correia', '61999990019', 'fabio.correia@email.com'),
('Renato Farias', '61999990020', 'renato.farias@email.com');

-- 20 Serviços
INSERT INTO servicos (nome, duracao_minutos, preco) VALUES
('Corte Social', 30, 25.00),
('Corte Degradê', 40, 35.00),
('Corte na Máquina', 20, 20.00),
('Barba Completa', 25, 20.00),
('Barba Desenhada', 30, 25.00),
('Corte + Barba', 60, 50.00),
('Sobrancelha', 10, 10.00),
('Pezinho', 10, 10.00),
('Hidratação Capilar', 30, 30.00),
('Relaxamento', 40, 40.00),
('Platinado', 90, 120.00),
('Luzes', 90, 110.00),
('Coloração', 60, 80.00),
('Selagem', 60, 90.00),
('Corte Infantil', 25, 20.00),
('Corte + Sobrancelha', 40, 33.00),
('Barboterapia', 35, 28.00),
('Limpeza de Pele', 45, 45.00),
('Massagem Capilar', 20, 15.00),
('Pacote Completo', 120, 150.00);

-- 20 Agendamentos (datas e horários distintos, sem sobreposição)
INSERT INTO agendamentos (cliente_id, servico_id, data_hora, status) VALUES
(1, 1, '2026-08-03 09:00:00', 'AGENDADO'),
(2, 2, '2026-08-03 10:00:00', 'AGENDADO'),
(3, 3, '2026-08-03 11:00:00', 'AGENDADO'),
(4, 4, '2026-08-03 13:00:00', 'AGENDADO'),
(5, 5, '2026-08-03 14:00:00', 'AGENDADO'),
(6, 6, '2026-08-03 15:00:00', 'AGENDADO'),
(7, 7, '2026-08-04 09:00:00', 'AGENDADO'),
(8, 8, '2026-08-04 09:30:00', 'AGENDADO'),
(9, 9, '2026-08-04 10:00:00', 'AGENDADO'),
(10, 10, '2026-08-04 11:00:00', 'AGENDADO'),
(11, 11, '2026-08-04 13:00:00', 'CONCLUIDO'),
(12, 12, '2026-08-05 09:00:00', 'AGENDADO'),
(13, 13, '2026-08-05 10:30:00', 'AGENDADO'),
(14, 14, '2026-08-05 12:00:00', 'AGENDADO'),
(15, 15, '2026-08-05 14:00:00', 'CANCELADO'),
(16, 16, '2026-08-06 09:00:00', 'AGENDADO'),
(17, 17, '2026-08-06 10:00:00', 'AGENDADO'),
(18, 18, '2026-08-06 11:00:00', 'AGENDADO'),
(19, 19, '2026-08-06 14:00:00', 'AGENDADO'),
(20, 20, '2026-08-06 15:00:00', 'AGENDADO');
