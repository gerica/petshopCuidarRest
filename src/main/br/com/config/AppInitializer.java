package br.com.config;

import javax.servlet.Filter;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import br.com.config.filter.AuthenticationTokenFilter;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		 return new Class[] { WebConfig.class };
//		return new Class<?>[] { RootConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		// return null;
		return new Class<?>[] { WebConfig.class };
	}

	@Override
	protected Filter[] getServletFilters() {
		return new Filter[] { new AuthenticationTokenFilter() };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

}