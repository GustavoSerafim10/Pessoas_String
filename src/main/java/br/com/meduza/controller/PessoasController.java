package br.com.meduza.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.meduza.model.Pessoas;
import br.com.meduza.repository.PessoasRepository;

@RestController
@RequestMapping("/Pessoas")
public class PessoasController {
	
	@Autowired
	private PessoasRepository pessoasRepository;
	
	/*Abaixo será criado a minha listagem de pessoas*/
	@GetMapping("/listar_pessoas")
	public List<Pessoas> listarPessoas(){
		/*Pessoas a1 = new Pessoas("Gustavo Vinicius","07278956712", "06091998", "Alphaville Rj","6197785612");
		Pessoas a2 = new Pessoas("Bernardo Leoncio","89067812345", "23082020", "Brasília DF","61978901234");
		Pessoas a3 = new Pessoas("Roberto ferreira","12345678909", "06091876", "São Paulo SP", "61987651234");
		return Arrays.asList(a1, a2, a3);
		}*/
		List<Pessoas> Pessoas = pessoasRepository.findAll();
		return Pessoas;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Pessoas>findByid(@PathVariable Long id){
		return pessoasRepository.findById(id)
		.map(gravado -> ResponseEntity.ok().body(gravado))
		.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	public ResponseEntity<Pessoas> cadastrarPessoas(@RequestBody Pessoas pessoas){
		return ResponseEntity.status(HttpStatus.CREATED).body(pessoasRepository.save(pessoas));
	}
	
	/*editar um produto*/
	@PutMapping("/{id}")
	public ResponseEntity<Pessoas> atualizarPessoas(@PathVariable Long id, @RequestBody Pessoas pessoas){
		return pessoasRepository.findById(id)
		.map(gravado -> {
		gravado.setNome(pessoas.getNome());
		gravado.setCpf(pessoas.getCpf());
		gravado.setData(pessoas.getData());
		gravado.setEndereco(pessoas.getEndereco());
		gravado.setTelefone(pessoas.getTelefone());
		Pessoas atualizado = pessoasRepository.save(gravado);
		return ResponseEntity.ok().body(atualizado);
		})
		.orElse(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletarPessoas(@PathVariable Long id){
		return pessoasRepository.findById(id)
		.map(gravado -> { 
		pessoasRepository.deleteById(id);
		return ResponseEntity.noContent().<Void>build();
		})
		.orElse(ResponseEntity.notFound().build());
	}
}
