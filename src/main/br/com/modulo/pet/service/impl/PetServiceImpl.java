package br.com.modulo.pet.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.compartilhado.execao.PetShopBusinessException;
import br.com.compartilhado.service.AuthenticationService;
import br.com.modulo.cliente.entidade.Pessoa;
import br.com.modulo.cliente.service.PessoaService;
import br.com.modulo.pet.entidade.Pet;
import br.com.modulo.pet.repository.PetRepository;
import br.com.modulo.pet.service.PetService;

@Service
public class PetServiceImpl implements PetService {

	private static final Logger logger = LoggerFactory.getLogger(PetServiceImpl.class);

	@Autowired
	private PetRepository petRepository;

	@Autowired
	private PessoaService pessoaService;

	@Autowired
	private AuthenticationService authenticationService;

	@Override
	public void excluir(Long idPet) throws PetShopBusinessException {
		logger.info("PetServiceImpl.excluir()");
		petRepository.delete(idPet);
	}

	@Override
	public List<Pet> findAll() {
		logger.info("PetServiceImpl.findAll()");
		return (List<Pet>) petRepository.findAll();
	}

	@Override
	public List<Pet> findPetByDsNome(String valor) {
		logger.info("PetServiceImpl.findPetByDsNome()");
		return (List<Pet>) petRepository.findByDsNomeContainingIgnoreCase(valor);
	}

	@Override
	public List<Pet> findPetByIdPessoa(Long idPessoa) {
		logger.info("PetServiceImpl.findPetByPessoa()");
		return (List<Pet>) petRepository.findPetByIdPessoa(idPessoa);
	}

	@Override
	public void gravar(Pet pet, Long idPessoa) throws PetShopBusinessException {
		Pessoa pessoa = pessoaService.findById(idPessoa);
		pet.setPessoa(pessoa);
		pet.setUsuario(authenticationService.get());
		petRepository.save(pet);
		logger.info("PetServiceImpl.incluir()");
	}

}
