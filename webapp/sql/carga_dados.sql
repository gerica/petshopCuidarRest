-- adicionar um usuaŕio//
INSERT INTO public.tb_usuario(
            id_usuario,is_account_locked, ds_email, is_enabled,dt_last_password,_reset, ds_senha, ds_nome)
    VALUES (1, false, 'ROGERIO@CARDOSO.COM.BR',true,to_date('29/11/2016','dd/MM/yyyy') 'FB8A7914134C70876287EB47A868C4570AFC4BD807E250267A43A0A52F0211EB', 'ROGERIO CARDOSO');


-- Adicionar role
INSERT INTO public.tb_role(id_role, ds_role) VALUES (1, 'ADMIN');
INSERT INTO public.tb_role(id_role, ds_role) VALUES (2, 'CONVIDADO');
INSERT INTO public.tb_role(id_role, ds_role) VALUES (3, 'FINANCEIRO');
INSERT INTO public.tb_role(id_role, ds_role) VALUES (4, 'ESTOQUE');
INSERT INTO public.tb_role(id_role, ds_role) VALUES (5, 'RELACIONAMENTO');
INSERT INTO public.tb_role(id_role, ds_role) VALUES (6, 'VENDA');

--Adicionar usuario role
INSERT INTO public.tb_usuario_role(id_usuario_role, id_role, id_usuario)    VALUES (1, 1, 1);