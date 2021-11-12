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

import com.gft.gestaoprojetos.entities.Linguagem;
import com.gft.gestaoprojetos.services.LinguagemService;

@Controller
@RequestMapping("linguagem")
public class LinguagemController {

	@Autowired
	private LinguagemService linguagemService;

	@RequestMapping(path = "editar")
	public ModelAndView novaLinguagem(@RequestParam(required = false) Long id) {
		ModelAndView mv = new ModelAndView("linguagem/novo.html");

		Linguagem linguagem;

		if (id == null) {
			linguagem = new Linguagem();
		} else {
			try {
				linguagem = linguagemService.obterLinguagem(id);
			} catch (Exception e) {
				linguagem = new Linguagem();
				mv.addObject("mensagem", e.getMessage());
			}
		}
		mv.addObject("linguagens", linguagemService.listarLinguagens());
		mv.addObject("linguagem", linguagem);

		return mv;
	}

	@RequestMapping
	public ModelAndView listarLinguagens() {
		ModelAndView mv = new ModelAndView("linguagem/listar.html");
		mv.addObject("linguagens", linguagemService.listarLinguagens());
		return mv;
	}

	@RequestMapping(method = RequestMethod.POST, path = "editar")
	public ModelAndView salvarLinguagem(@Valid Linguagem linguagem, BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {
		ModelAndView mv = new ModelAndView("linguagem/novo.html");

		boolean isNovaLinguagem = (linguagem.getId() == null);
		String mensagemConclusao = null;

		if (isNovaLinguagem) {
			mv.addObject("linguagem", new Linguagem());
		}

		if (bindingResult.hasErrors()) {
			mensagemConclusao = "Erro ao salvar! Verifique o campo abaixo.";
			mv.addObject("linguagem", linguagem);
		} 
		else if (linguagemService.linguagemExistente(linguagem)) {
			mensagemConclusao = "Essa linguagem já existe!";
			mv.addObject("linguagem", linguagem);
		} 
		else {
			linguagemService.salvarLinguagem(linguagem);
			mensagemConclusao = "Linguagem salva com sucesso!";
		}

		mv.addObject("linguagens", linguagemService.listarLinguagens());
		mv.addObject("mensagem", mensagemConclusao);

		return mv;
	}

	@RequestMapping(path = "delete")
	public ModelAndView excluirLinguagem(@RequestParam Long id, RedirectAttributes redirectAttributes) {
		ModelAndView mv = new ModelAndView("redirect:/linguagem");

		try {
			linguagemService.excluirLinguagem(id);
			redirectAttributes.addFlashAttribute("mensagem", "Linguagem removida com sucesso!");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("mensagem", "Erro ao remover linguagem!");
		}

		return mv;

	}

	@RequestMapping(method = RequestMethod.GET, path = "altere")
	public ModelAndView editarLinguagem(@RequestParam Long id, RedirectAttributes redirectAttributes) {
		ModelAndView mv = new ModelAndView("linguagem/novo.html");
		try {
			Linguagem linguagem = linguagemService.obterLinguagem(id);
			mv.addObject("linguagem", linguagem);
			// mv.addObject("mensagem", "Linguagem alterada com sucesso!");
		} catch (Exception e) {
			// mv.addObject("mensagem", "Erro ao editar linguagem!");
		}

		return mv;
	}

}
