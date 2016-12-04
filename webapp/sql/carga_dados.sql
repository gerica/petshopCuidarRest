-- adicionar um usuaŕio//
INSERT INTO public.tb_usuario(
            id_usuario,is_account_locked, ds_email, is_enabled,dt_last_password_reset, ds_senha, ds_nome)
    VALUES (1, false, 'ROGERIO@CARDOSO.COM.BR',true,to_date('29/11/2016','dd/MM/yyyy'), 'FB8A7914134C70876287EB47A868C4570AFC4BD807E250267A43A0A52F0211EB', 'ROGERIO CARDOSO');

-- Adicionar role
INSERT INTO public.tb_role(id_role, ds_role) VALUES (1, 'ADMIN');
INSERT INTO public.tb_role(id_role, ds_role) VALUES (2, 'CONVIDADO');
INSERT INTO public.tb_role(id_role, ds_role) VALUES (3, 'FINANCEIRO');
INSERT INTO public.tb_role(id_role, ds_role) VALUES (4, 'ESTOQUE');
INSERT INTO public.tb_role(id_role, ds_role) VALUES (5, 'RELACIONAMENTO');
INSERT INTO public.tb_role(id_role, ds_role) VALUES (6, 'VENDA');

--Adicionar usuario role
INSERT INTO public.tb_usuario_role(id_usuario_role, id_role, id_usuario)    VALUES (1, 1, 1);

-- Adicionar estados
INSERT INTO cliente.tb_estado VALUES(1, 'Acre', 'AC');
INSERT INTO cliente.tb_estado VALUES(2, 'Alagoas', 'AL');
INSERT INTO cliente.tb_estado VALUES(3, 'Amazonas', 'AM');
INSERT INTO cliente.tb_estado VALUES(4, 'Amapá', 'AP');
INSERT INTO cliente.tb_estado VALUES(5, 'Bahia', 'BA');
INSERT INTO cliente.tb_estado VALUES(6, 'Ceará', 'CE');
INSERT INTO cliente.tb_estado VALUES(7, 'Distrito Federal', 'DF');
INSERT INTO cliente.tb_estado VALUES(8, 'Espírito Santo', 'ES');
INSERT INTO cliente.tb_estado VALUES(9, 'Goiás', 'GO');
INSERT INTO cliente.tb_estado VALUES(10, 'Maranhão', 'MA');
INSERT INTO cliente.tb_estado VALUES(11, 'Minas Gerais', 'MG');
INSERT INTO cliente.tb_estado VALUES(12, 'Mato Grosso do Sul', 'MS');
INSERT INTO cliente.tb_estado VALUES(13, 'Mato Grosso', 'MT');
INSERT INTO cliente.tb_estado VALUES(14, 'Pará', 'PA');
INSERT INTO cliente.tb_estado VALUES(15, 'Paraíba', 'PB');
INSERT INTO cliente.tb_estado VALUES(16, 'Pernambuco', 'PE');
INSERT INTO cliente.tb_estado VALUES(17, 'Piauí', 'PI');
INSERT INTO cliente.tb_estado VALUES(18, 'Paraná', 'PR');
INSERT INTO cliente.tb_estado VALUES(19, 'Rio de Janeiro', 'RJ');
INSERT INTO cliente.tb_estado VALUES(20, 'Rio Grande do Norte', 'RN');
INSERT INTO cliente.tb_estado VALUES(21, 'Rondônia', 'RO');
INSERT INTO cliente.tb_estado VALUES(22, 'Roraima', 'RR');
INSERT INTO cliente.tb_estado VALUES(23, 'Rio Grande do Sul', 'RS');
INSERT INTO cliente.tb_estado VALUES(24, 'Santa Catarina', 'SC');
INSERT INTO cliente.tb_estado VALUES(25, 'Sergipe', 'SE');
INSERT INTO cliente.tb_estado VALUES(26, 'São Paulo', 'SP');
INSERT INTO cliente.tb_estado VALUES(27, 'Tocantins', 'TO');

