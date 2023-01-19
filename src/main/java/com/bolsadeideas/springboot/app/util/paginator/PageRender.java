package com.bolsadeideas.springboot.app.util.paginator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

public class PageRender<T> {

	private String url;
	private Page<T> page;

	private int totalPaginas;
	private int numElementosPorPagina;
	private int paginaActual;

	private List<PageItem> paginas;

	public PageRender(String url, Page<T> page) {
		this.url = url;
		this.page = page;
		this.paginas = new ArrayList<>();

		numElementosPorPagina = page.getSize();
		totalPaginas = page.getTotalPages();
		paginaActual = page.getNumber() + 1;

		Map<String, Integer> desdeHasta = this.getPaginacionDesdeHasta();
		int desde = desdeHasta.get("desde");
		int hasta = desdeHasta.get("hasta");

		for (int i = 0; i < hasta; i++)
			paginas.add(new PageItem(desde + i, paginaActual == desde + i));
		
	}

	public boolean isFirst() {
		return page.isFirst();
	}

	public boolean isLast() {
		return page.isLast();
	}

	public boolean hasNext() {
		return page.hasNext();
	}

	public boolean isHasPrevious() {
		return page.hasPrevious();
	}

	public String getUrl() {
		return url;
	}

	public Page<T> getPage() {
		return page;
	}

	public int getTotalPaginas() {
		return totalPaginas;
	}

	public int getNumElementosPorPagina() {
		return numElementosPorPagina;
	}

	public int getPaginaActual() {
		return paginaActual;
	}

	public List<PageItem> getPaginas() {
		return paginas;
	}

	private Map<String, Integer> getPaginacionDesdeHasta() {
		Map<String, Integer> desdeHasta = new HashMap<>();
		
		if (totalPaginas <= numElementosPorPagina) {
			desdeHasta.put("desde", 1);
			desdeHasta.put("hasta", totalPaginas);
		} else {
			desdeHasta = getPaginacionMuchasPaginas();
		}

		return desdeHasta;
	}

	private Map<String, Integer> getPaginacionMuchasPaginas() {
		Map<String, Integer> desdeHasta = new HashMap<>();

		if (paginaActual <= numElementosPorPagina / 2) {
			desdeHasta.put("desde", 1);
			desdeHasta.put("hasta", numElementosPorPagina);
		} 
		else if (paginaActual >= totalPaginas - numElementosPorPagina / 2) {
			desdeHasta.put("desde", totalPaginas - numElementosPorPagina + 1);
			desdeHasta.put("hasta", numElementosPorPagina);
		} 
		else {
			desdeHasta.put("desde", paginaActual - numElementosPorPagina / 2);
			desdeHasta.put("hasta", numElementosPorPagina);
		}

		return desdeHasta;
	}

}
