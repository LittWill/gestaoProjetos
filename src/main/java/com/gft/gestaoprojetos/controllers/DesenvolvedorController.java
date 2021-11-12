package com.gft.gestaoprojetos.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gft.gestaoprojetos.entities.Desenvolvedor;
import com.gft.gestaoprojetos.services.DesenvolvedorService;
import com.gft.gestaoprojetos.services.LinguagemService;

@Controller
@RequestMapping("desenvolvedor")
public class DesenvolvedorController {
	
	
	@Autowired
	private DesenvolvedorService desenvolvedorService;
	
	@Autowired
	private LinguagemService linguagemService;

	@RequestMapping(path = "editar")
	public ModelAndView editarDesenvolvedor(@RequestParam(required = false) Long id) {
		ModelAndView mv = new ModelAndView("desenvolvedor/form.html");

		Desenvolvedor desenvolvedor;

		//Caso a opção seja de cadastrar um desenvolvedor
		if (id == null) {
			desenvolvedor = new Desenvolvedor();
		} 
		//Caso a opção seja de alterar os dados de um desenvolvedor
		else {
			try {
				desenvolvedor = desenvolvedorService.obterDesenvolvedor(id);
			} catch (Exception e) {
				desenvolvedor = new Desenvolvedor();
				mv.addObject("mensagem", e.getMessage());
			}
		}
		
		mv.addObject("desenvolvedor", desenvolvedor);
		mv.addObject("linguagens", linguagemService.listarLinguagens());

		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "editar")
	public ModelAndView salvarDesenvolvedor(@Valid Desenvolvedor desenvolvedor, BindingResult bindingResult) {
		
		ModelAndView mv = new ModelAndView("desenvolvedor/form.html");
		
		//Verificar se o desenvolvedor recebido por parâmetro for um novo
		//retorna true e se não for, false
		boolean isNovoDesenvolvedor = (desenvolvedor.getId() == null);
		String mensagemConclusao = null;
		
		//Sem essa linha, quando se salva um novo desenvolvedor, os dados do antigo formulário
		//permanecem lá
		if (isNovoDesenvolvedor) {
			mv.addObject("desenvolvedor", new Desenvolvedor());
		}
		
		/*Se o Java detectar alguma incongruência definida na entidade,
		 * a mensagem retornada será de erro. 
		 * E o que estava escrito no formulário será devolvido
		 * para que o usuário continue a editá-lo.
		 */
		if (bindingResult.hasErrors()) {
			mensagemConclusao = "Erro ao salvar! Verifique os campos abaixo.";
			mv.addObject("desenvolvedor", desenvolvedor);	
		}
		else if (desenvolvedorService.siglaExistente(desenvolvedor)) {
			mensagemConclusao = "Esta sigla já foi utilizada!";
			mv.addObject("desenvolvedor", desenvolvedor);	
		}
		else if (desenvolvedorService.emailExistente(desenvolvedor)) {
			mensagemConclusao = "Este email já foi utilizado!";
			mv.addObject("desenvolvedor", desenvolvedor);	
		}
		else {
			desenvolvedorService.salvar(desenvolvedor);
			mensagemConclusao = "Desenvolvedor salvo com sucesso!";
		}
		

		mv.addObject("mensagem", mensagemConclusao);	
		/*
		 * Sem essa linha, as listagens ficariam em branco após salvar ou ocorrer um erro.
		 */
		mv.addObject("linguagens", linguagemService.listarLinguagens());
		
		return mv;
		
	}
	
	@RequestMapping(path = "delete")
	public ModelAndView excluirDesenvolvedor(@RequestParam Long id, RedirectAttributes redirectAttributes) {
		ModelAndView mv = new ModelAndView("redirect:/desenvolvedor");

		try {
			desenvolvedorService.excluir(id);
			redirectAttributes.addFlashAttribute("mensagem", "Desenvolvedor removido com sucesso!");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("mensagem", "Erro ao remover desenvolvedor!");
		}

		return mv;

	}
	
	@RequestMapping
	public ModelAndView listarDesenvolvedor(String nome, String siglaNome) {
		ModelAndView mv = new ModelAndView("desenvolvedor/listar.html");
		mv.addObject("nome", nome);
		mv.addObject("siglaNome", siglaNome);
		mv.addObject("desenvolvedores", desenvolvedorService.listarDesenvolvedores(nome, siglaNome));
		return mv;

	}

}
