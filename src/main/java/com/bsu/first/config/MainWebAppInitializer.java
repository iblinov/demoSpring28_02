package com.bsu.first.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.*;
import java.util.EnumSet;

public class MainWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
  @Override
  protected Class<?>[] getRootConfigClasses() {
    return null;
  }

  @Override
  protected Class<?>[] getServletConfigClasses() {
    return new Class[]{SpringConfig.class};
  }

  @Override
  protected String[] getServletMappings() {
    return new String[]{"/"};
  }

  @Override
  public void onStartup(ServletContext aServletContext) throws ServletException {
    super.onStartup(aServletContext);
    registerCharacterEncodingFilter(aServletContext);
    registerHiddenFieldFilter(aServletContext);
  }

  private void registerHiddenFieldFilter(ServletContext aContext) {
    aContext.addFilter("hiddenHttpMethodFilter",
            new HiddenHttpMethodFilter()).addMappingForUrlPatterns(null ,true, "/*");
  }

  private void registerCharacterEncodingFilter(ServletContext aContext) {
    EnumSet<DispatcherType> dispatcherTypes = EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD);

    CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
    characterEncodingFilter.setEncoding("UTF-8");
    characterEncodingFilter.setForceEncoding(true);

    FilterRegistration.Dynamic characterEncoding = aContext.addFilter("characterEncoding", characterEncodingFilter);
    characterEncoding.addMappingForUrlPatterns(dispatcherTypes, true, "/*");
  }
}
//
//        implements WebApplicationInitializer {
//  @Override
//  public void onStartup(final ServletContext sc) throws ServletException {
//
//    AnnotationConfigWebApplicationContext root =
//            new AnnotationConfigWebApplicationContext();
//
//    root.scan("com.bsu");
//    sc.addListener(new ContextLoaderListener(root));
//
//    ServletRegistration.Dynamic appServlet =
//            sc.addServlet("mvc", new DispatcherServlet(new GenericWebApplicationContext())); // TODO: 2/27/2023
//    appServlet.setLoadOnStartup(1);
//    appServlet.addMapping("/");
//  }
//}